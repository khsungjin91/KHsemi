package thk.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import thk.border.proBDto;
import thk.logon.LogonDataBean;

public class CartDBBean {


	private static CartDBBean instance = new CartDBBean();
		
	public static CartDBBean getInstance() {
		return instance;
	}
	private CartDBBean() {}
		
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}		

	
	
	public CartDataBean getSumprice(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDataBean sumprice = null;
		int sum_p = 0, sum_p_dc = 0, sum_p_p = 0;
		
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select sum(p_price) from cart where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sumprice = new CartDataBean();
				
				sum_p = rs.getInt(1);
				
				pstmt = conn.prepareStatement("select sum(p_price_dc) from cart where mem_num=?");
				pstmt.setInt(1, mem_num);
				rs = pstmt.executeQuery();
				if(rs.next()){
					
					sum_p_dc = rs.getInt(1);
					
					pstmt = conn.prepareStatement("select sum(p_point) from cart where mem_num=?");
					pstmt.setInt(1, mem_num);
					rs = pstmt.executeQuery();
					if(rs.next()){
						
						sum_p_p = rs.getInt(1);
						
						sumprice.setAll_p_sum(sum_p);
						sumprice.setAll_p_dc_sum(sum_p_dc);
						sumprice.setAll_p_p_sum(sum_p_p);
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
		}
		return sumprice;
		
	}
	
	
	
	
	public CartDataBean getPrice(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDataBean price = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_price,p_price_dc,p_point from product where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				price = new CartDataBean();
				
				price.setP_price(rs.getInt("p_price"));
				price.setP_price_dc(rs.getInt("p_price_dc"));
				price.setP_point(rs.getInt("p_point"));
				
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}return price;
		
	}
	
	
	public List getArticle(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList goodslist = null;
			
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from cart where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				goodslist = new ArrayList();
				
				do{
				CartDataBean goods = new CartDataBean();
				
				goods.setMem_num(rs.getInt("mem_num"));
				goods.setP_code(rs.getString("p_code"));
				goods.setP_name(rs.getString("p_name"));
				goods.setP_size(rs.getString("p_size"));
				goods.setP_img(rs.getString("p_img"));
				goods.setP_price(rs.getInt("p_price"));
				goods.setP_price_dc(rs.getInt("p_price_dc"));
				goods.setP_point(rs.getInt("p_point"));
				goods.setP_amount(rs.getInt("amount"));
				goods.setP_shipping(rs.getString("shipping"));
				
				goodslist.add(goods);
				
				}while(rs.next());
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
		}
		return goodslist;
	}
	
	
	
	public int getCount(int mem_num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from cart where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
		}
		return x;
	}
	
	
	public void inputCart(CartDataBean product) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="";
		try
		{
			
			conn = getConnection();
			pstmt= conn.prepareStatement("select * from cart where mem_num=? and p_code=? and p_size=?");
			pstmt.setInt(1, product.getMem_num());
			pstmt.setString(2, product.getP_code());
			pstmt.setString(3, product.getP_size());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pstmt = conn.prepareStatement("update cart set amount=amount+1 where mem_num=? and p_code=? and p_size=?");
				pstmt.setInt(1, product.getMem_num());
				pstmt.setString(2, product.getP_code());
				pstmt.setString(3, product.getP_size());
				pstmt.executeUpdate();
			}
			else
			{
				sql = "insert into cart values(?,?,?,?,?,?,?,?,?,?)";
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1, product.getMem_num());
				pstmt.setString(2, product.getP_code());
				pstmt.setString(3, product.getP_name());
				pstmt.setString(4, product.getP_size());
				pstmt.setString(5, product.getP_img());
				pstmt.setInt(6, product.getP_price_dc());
				pstmt.setInt(7, product.getP_point());
				pstmt.setInt(8, product.getP_amount());
				pstmt.setString(9, product.getP_shipping());
				pstmt.setInt(10, product.getP_price());

				pstmt.executeUpdate();
			}
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	public List getCartList(int mem_num, int Start, int End) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List cart_list = null;
		int sum = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_code,p_size,p_img,p_name,p_price_dc,p_point,amount,p_price, r "
							+"from (select p_code,p_size,p_img,p_name,p_price_dc,p_point,amount,p_price, rownum r "
							+ "from cart where mem_num=?) where r>=? and r<=?");
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			
			rs = pstmt.executeQuery();
			cart_list = new ArrayList();
			
			while(rs.next()){
			
				CartDataBean cart = new CartDataBean();
				cart.setP_code(rs.getString("p_code"));
				cart.setP_size(rs.getString("p_size"));
				cart.setP_img(rs.getString("p_img"));
				cart.setP_name(rs.getString("p_name"));
				cart.setP_price_dc(rs.getInt("p_price_dc"));
				cart.setP_amount(rs.getInt("amount"));
				cart.setP_price(rs.getInt("p_price"));
				cart.setP_point(rs.getInt("p_point"));
				cart_list.add(cart);
				
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				if(rs != null) try {rs.close();}catch(SQLException ex){}
				if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
				if(conn != null) try {conn.close();}catch(SQLException ex){}
			}
			
			return cart_list;
		
	}
	
	 	public void deleteCart(int num, String code, String size)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "";
		try{
			
			conn = getConnection();
			pstmt= conn.prepareStatement("select * from cart where mem_num=? and p_code=? and p_size=?");
			pstmt.setInt(1, num);
			pstmt.setString(2, code);
			pstmt.setString(3, size);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql = "delete from cart where mem_num=? and p_code=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, code);
				pstmt.executeUpdate();
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			
		}
	}
	
	 	
	 	
	
	public int modifyCart(int amount, int mem_num, String p_code,
					int p_price, int p_price_dc, int p_point, String p_size)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;

		try{
		
				conn = getConnection();
				pstmt = conn.prepareStatement("update cart set amount=?,p_price=?,p_price_dc=?,p_point=? "
												+ "where mem_num=? and p_code=? and p_size=?");
			
				pstmt.setInt(1, amount);
				pstmt.setInt(2, p_price);
				pstmt.setInt(3, p_price_dc);
				pstmt.setInt(4, p_point);
				pstmt.setInt(5, mem_num);
				pstmt.setString(6, p_code);
				pstmt.setString(7, p_size);

				pstmt.executeUpdate();
				
				check = 1;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}	
		}
		return check;
	
	}
	
	
}