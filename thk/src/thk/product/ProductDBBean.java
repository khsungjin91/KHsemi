package thk.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import thk.logon.LogonDataBean;
import thk.payment.PaymentDto;


public class ProductDBBean {

	private static ProductDBBean instance = new ProductDBBean();
	
	public static ProductDBBean getInstance() {
		return instance;
	}
	private ProductDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	
	public int Ca2Count(String p_category2)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where p_category2=?");
			pstmt.setString(1, p_category2);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				count = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		return count;
	}

	
	
	
	public List getCategory2(String p_category2, int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList category2 = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, r "
					+ "from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result,rownum r "
					+ "from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,case when p_sale_date+7 > sysdate then 1 else 0 end result "
					+ "from product where p_category2=? order by p_sale_date desc) order by p_sale_date desc ) where r>=? and r<=?");
			pstmt.setString(1, p_category2);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				category2 = new ArrayList();
				do{
					ProductDataBean ca2dto = new ProductDataBean();
					
					ca2dto.setP_code(rs.getString("p_code"));
					ca2dto.setP_thumbnail(rs.getString("p_thumbnail"));
					ca2dto.setP_name(rs.getString("p_name"));
					ca2dto.setP_price(rs.getInt("p_price"));
					ca2dto.setP_price_dc(rs.getInt("p_price_dc"));
					ca2dto.setReadcount(rs.getInt("readcount"));
					ca2dto.setResult(rs.getInt("result"));
					
					category2.add(ca2dto);
					
				}while(rs.next());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		return category2;
	}
	
	
	
	
	
	
	public List getFootList(String p_category1, int Start, int End)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList footlist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, r "
					+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, rownum r "
					+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,case when p_sale_date+7 > sysdate then 1 else 0 end result from product where "
					+"p_category1=? order by p_sale_date desc) order by p_sale_date desc ) where r>=? and r<=?");
			pstmt.setString(1, p_category1);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				footlist = new ArrayList();
				
				do{
					ProductDataBean footdto = new ProductDataBean();
					
					footdto.setP_code(rs.getString("p_code"));
					footdto.setP_thumbnail(rs.getString("p_thumbnail"));
					footdto.setP_name(rs.getString("p_name"));
					footdto.setP_price(rs.getInt("p_price"));
					footdto.setP_price_dc(rs.getInt("p_price_dc"));
					footdto.setReadcount(rs.getInt("readcount"));
					footdto.setResult(rs.getInt("result"));
					
					
					footlist.add(footdto);
					
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		return footlist;
	}
	
	
	
	public List getMain2(int Start, int End) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List p_list = null;
		String cap = "ACCESSORIES";
				
		try{
			conn = getConnection();
	
			pstmt = conn.prepareStatement("select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result,rownum r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,case when p_sale_date+7 > sysdate then 1 else 0 end result from product "
							+"where p_category1=? order by p_sale_date desc) order by p_sale_date desc) where r>=? and r<=?");
			pstmt.setString(1, cap);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			
			rs = pstmt.executeQuery();
			p_list = new ArrayList();
			
			while(rs.next()){
			
				ProductDataBean pdto = new ProductDataBean();
				pdto.setP_code(rs.getString("p_code"));
				pdto.setP_thumbnail(rs.getString("p_thumbnail"));
				pdto.setP_name(rs.getString("p_name"));
				pdto.setP_price(rs.getInt("p_price"));
				pdto.setP_price_dc(rs.getInt("p_price_dc"));
				pdto.setReadcount(rs.getInt("readcount"));
				pdto.setResult(rs.getInt("result"));
				
				
				p_list.add(pdto);
				
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				if(rs != null) try {rs.close();}catch(SQLException ex){}
				if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
				if(conn != null) try {conn.close();}catch(SQLException ex){}
			}
			
			return p_list;
		
	}
	
	
	
	public List getMain3(int Start, int End) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List p_list = null;
		String cap = "APPAREL";
				
		try{
			conn = getConnection();
	
			pstmt = conn.prepareStatement("select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result,rownum r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,case when p_sale_date+7 > sysdate then 1 else 0 end result from product "
							+"where p_category1=? order by p_sale_date desc) order by p_sale_date desc) where r>=? and r<=?");
			pstmt.setString(1, cap);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			
			rs = pstmt.executeQuery();
			p_list = new ArrayList();
			
			while(rs.next()){
			
				ProductDataBean pdto = new ProductDataBean();
				pdto.setP_code(rs.getString("p_code"));
				pdto.setP_thumbnail(rs.getString("p_thumbnail"));
				pdto.setP_name(rs.getString("p_name"));
				pdto.setP_price(rs.getInt("p_price"));
				pdto.setP_price_dc(rs.getInt("p_price_dc"));
				pdto.setReadcount(rs.getInt("readcount"));
				pdto.setResult(rs.getInt("result"));
				
				
				p_list.add(pdto);
				
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				if(rs != null) try {rs.close();}catch(SQLException ex){}
				if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
				if(conn != null) try {conn.close();}catch(SQLException ex){}
			}
			
			return p_list;
		
	}
	
	
	
	public int getCount(String p_category1)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int FootCount = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where p_category1=?");
			pstmt.setString(1, p_category1);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				FootCount = rs.getInt(1);
				
			}
		}catch(Exception e){
				e.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		return FootCount;
		
		
	}
	
	
	
	
	public String getName(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String p_name = "";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_name from product where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				p_name = rs.getString(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		return p_name;
	}


	
	//상품정보 꺼내오기
	public ProductDataBean getArticle(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDataBean article = null;
		
		try{
			conn = getConnection();
			
			pstmt = conn.prepareStatement("update product set readcount=readcount+1 where p_code=?");
			pstmt.setString(1, p_code);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from product where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				article = new ProductDataBean();
				article.setP_code(rs.getString("p_code"));
				article.setP_name(rs.getString("p_name"));
				article.setP_material(rs.getString("p_material"));
				article.setP_thumbnail(rs.getString("p_thumbnail"));
				article.setP_img(rs.getString("p_img"));
				article.setP_price(rs.getInt("p_price"));
				article.setP_price_dc(rs.getInt("p_price_dc"));
				article.setP_point(rs.getInt("p_point"));
				article.setP_category1(rs.getString("p_category1"));
				article.setP_category2(rs.getString("p_category2"));
				article.setP_category3(rs.getString("p_category3"));
				article.setP_sale_date(rs.getTimestamp("p_sale_date"));
				article.setReadcount(rs.getInt("readcount"));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			if(rs != null) try {rs.close();}catch(SQLException ex){}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex){}
		}
		
		return article;
	}
	
	
public int getaccCount()throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String cap = "ACCESSORIES";
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where p_category1=?");
			pstmt.setString(1, cap);
			
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


public int getapealCount()throws Exception{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int x = 0;
	String cap = "APPAREL";
	try{
		
		conn = getConnection();
		pstmt = conn.prepareStatement("select count(*) from product where p_category1=?");
		pstmt.setString(1, cap);
		
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
	

	public int getCount()throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String cap = "FOOTWEAR";
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where p_category1=?");
			pstmt.setString(1, cap);
			
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
	
	//메인 리스트
	public List getMain1(int Start, int End) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List p_list = null;
		String cap = "FOOTWEAR";
				
		try{
			conn = getConnection();
	
			pstmt = conn.prepareStatement("select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result, r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,result,rownum r "
							+"from (select p_code,p_thumbnail,p_name,p_price,p_price_dc,p_sale_date,readcount,case when p_sale_date+7 > sysdate then 1 else 0 end result from product "
							+"where p_category1=? order by p_sale_date desc) order by p_sale_date desc) where r>=? and r<=?");
			pstmt.setString(1, cap);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			
			rs = pstmt.executeQuery();
			p_list = new ArrayList();
			
			while(rs.next()){
			
				ProductDataBean pdto = new ProductDataBean();
				pdto.setP_code(rs.getString("p_code"));
				pdto.setP_thumbnail(rs.getString("p_thumbnail"));
				pdto.setP_name(rs.getString("p_name"));
				pdto.setP_price(rs.getInt("p_price"));
				pdto.setP_price_dc(rs.getInt("p_price_dc"));
				pdto.setReadcount(rs.getInt("readcount"));
				pdto.setResult(rs.getInt("result"));
				
				
				p_list.add(pdto);
				
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				if(rs != null) try {rs.close();}catch(SQLException ex){}
				if(pstmt != null) try {pstmt.close();}catch(SQLException ex){}
				if(conn != null) try {conn.close();}catch(SQLException ex){}
			}
			
			return p_list;
		
	}
	
	

	
	//주문하기 페이지에 회원정보 받기
	public LogonDataBean getMember(String mem_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean mdto = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from member where mem_id=?");
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
				if(rs.next()){
				mdto = new LogonDataBean();
				
				mdto.setMem_id(rs.getString("mem_id"));
				mdto.setMem_num(rs.getInt("mem_num"));
				mdto.setMem_name(rs.getString("mem_name"));
				mdto.setMem_postnum(rs.getString("mem_postnum"));
				mdto.setMem_addrs1(rs.getString("mem_addrs1"));
				mdto.setMem_addrs2(rs.getString("mem_addrs2"));
				mdto.setMem_phone(rs.getString("mem_phone"));
				mdto.setMem_email(rs.getString("mem_email"));
				mdto.setMem_point(rs.getInt("mem_point"));		
			}
			
				
		}catch(Exception ex){
			ex.printStackTrace();
			
			
		}finally{
			if (conn != null)try{conn.close();}catch(SQLException ex){}
			if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
			if (rs != null)try{rs.close();}catch(SQLException ex){}
		}
		
		return mdto;
	}
	
	


//포인트 꺼내오기
public LogonDataBean getPoint(int mem_num) throws Exception {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LogonDataBean article = null;
	
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement("select * from member where mem_num=?");
		pstmt.setInt(1, mem_num);
		pstmt.executeQuery();
		rs = pstmt.executeQuery();
		while(rs.next()){
			article = new LogonDataBean();
			article.setMem_num(rs.getInt("mem_num"));
			article.setMem_point(rs.getInt("mem_point"));
		}			
				
	}catch(Exception ex){
		
		ex.printStackTrace();
		
	}finally{
		if(conn != null)try{conn.close();}catch(SQLException ex){}
		if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		if (rs != null)try{rs.close();}catch(SQLException ex){}	
	}
	return article;

}



//포인트 사용 후 회원DB에 포인트 업데이트하기
public LogonDataBean updatePoint(int mem_num, int mem_point)throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LogonDataBean article = null;
	
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement("update member set mem_point=? where mem_num=? ");
		pstmt.setInt(1, mem_point);
		pstmt.setInt(2, mem_num);
		rs = pstmt.executeQuery();
		
		if (rs.next()){
			article = new LogonDataBean();
			article.setMem_point(rs.getInt("mem_point"));
		}				
		
	}catch(Exception e){
		
		e.printStackTrace();
		
	}finally{
		
		if(conn != null)try{conn.close();}catch(SQLException ex){}
		if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		if (rs != null)try{rs.close();}catch(SQLException ex){}	
		
	}return article;
}





	
}
