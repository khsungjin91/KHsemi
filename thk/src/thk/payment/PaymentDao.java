package thk.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import thk.event.eventDao;



public class PaymentDao {
	
private static PaymentDao instance = new PaymentDao();
	
	public static PaymentDao getInstance() {
		return instance;
	}
	private PaymentDao() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	public int BestCount(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from payment where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				x = rs.getInt(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	
	//결제내역
		public List TotalArticle(int start, int end) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select pay_code, mem_num, p_code, p_size, pay_amount, pay_price, p_point, pay_howpay, reg_date, r "
						+ "from (select pay_code, mem_num, p_code, p_size, pay_amount, pay_price, p_point, pay_howpay, reg_date, rownum r "
						+ "from payment order by reg_date desc) where r>=? and r<=?");
				        pstmt.setInt(1, start);
				        pstmt.setInt(2, end);
				        
				        rs= pstmt.executeQuery();
				        if (rs.next()) {
				        	articleList = new ArrayList(end);
				        	do{
				        	    PaymentDto article = new PaymentDto();
				        		article.setPay_code(rs.getLong("pay_code"));
				        		article.setMem_num(rs.getInt("mem_num"));
				        		article.setP_code(rs.getString("p_code"));
				        		article.setP_size(rs.getString("p_size"));
				        		article.setPay_amount(rs.getInt("pay_amount"));
				        		article.setPay_price(rs.getInt("pay_price"));
				        		article.setP_point(rs.getInt("p_point"));
				        		article.setPay_howpay(rs.getString("pay_howpay"));
				        		article.setReg_date(rs.getTimestamp("reg_date"));
				        		articleList.add(article);
				        	}while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return articleList;
		}
		
		
		public int ArticleCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from payment");
				 rs = pstmt.executeQuery();
				 if (rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
		}
		//일 매출
		public List Dpay(int start, int end) throws Exception {
			 Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 List articleList = null;
			 try{
					 conn = getConnection();
			         pstmt = conn.prepareStatement("select year_mt, pay_sum, r "
			        		+ "from (select year_mt, pay_sum, rownum r "
			         		+ "from (select year_mt, sum(sum_amount) as pay_sum "
			         		+ "from (select to_char(reg_date, 'yyyymmdd') as year_mt, pay_amount*pay_price as sum_amount "
			         		+ "from payment)  group by year_mt order by year_mt desc)) where r>=? and r<=?");
			         pstmt.setInt(1, start);
			         pstmt.setInt(2, end);
				  
				     rs = pstmt.executeQuery();
				     
				     if(rs.next()){
				    	 articleList = new ArrayList();
					 do{
						 PaymentDto article = new PaymentDto();
						 article.setYear_mt(rs.getString("year_mt"));
						 article.setPay_sum(rs.getString("pay_sum"));
						 articleList.add(article);
					 }while(rs.next());
				 }
			 }catch(Exception ex){
				ex.printStackTrace();
			 } finally{
				if(rs != null)try{rs.close();}catch(SQLException ex){}
				if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
				if(conn != null)try{conn.close();}catch(SQLException ex){}
			 }
			 return articleList;
		     }
		
		public int DpayCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from (select year_mt, sum(sum_amount) as pay_sum "
			         		+ "from (select to_char(reg_date, 'yyyymmdd') as year_mt, pay_amount*pay_price as sum_amount "
			         		+ "from payment) group by year_mt) ");
				 rs = pstmt.executeQuery();
				 if (rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
		}
		//월 매출
		public List Mpay(int start, int end) throws Exception {
			 Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 List articleList = null;
			 try{
					 conn = getConnection();
			         pstmt = conn.prepareStatement("select year_mt, pay_sum, r "
			         		+ "from (select year_mt, pay_sum, rownum r "
			         		+ "from (select year_mt, sum(sum_amount) as pay_sum "
			         		+ "from (select to_char(reg_date, 'yyyymm') as year_mt, pay_amount*pay_price as sum_amount "
			         		+ "from payment) group by year_mt order by year_mt desc)) where r>=? and r<=?");
			         pstmt.setInt(1, start);
			         pstmt.setInt(2, end);

			         rs = pstmt.executeQuery();
				     
				     if(rs.next()){
				    	 articleList = new ArrayList();
					 do{
						 PaymentDto article = new PaymentDto();
						 article.setYear_mt(rs.getString("year_mt"));
						 article.setPay_sum(rs.getString("pay_sum"));
						 articleList.add(article);
					 }while(rs.next());
				 }
			 }catch(Exception ex){
				ex.printStackTrace();
			 } finally{
				if(rs != null)try{rs.close();}catch(SQLException ex){}
				if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
				if(conn != null)try{conn.close();}catch(SQLException ex){}
			 }
			 return articleList;
		     }
		
		public int MpayCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from (select year_mt, sum(sum_amount) as pay_sum "
			         		+ "from (select to_char(reg_date, 'yyyymm') as year_mt, pay_amount*pay_price as sum_amount "
			         		+ "from payment) group by year_mt) ");
				 rs = pstmt.executeQuery();
				 if (rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
		}
		//연매출
		public List Ypay(int start, int end) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try { 
				conn = getConnection();
				pstmt = conn.prepareStatement("select year_mt, pay_sum, r "
						+ "from (select year_mt, pay_sum, rownum r "
						+ "from (select year_mt, sum(sum_amount) as pay_sum "
						+ "from (select to_char(reg_date, 'yyyy') as year_mt, pay_amount*pay_price as sum_amount "
						+ "from payment) group by year_mt order by year_mt desc)) where r>=? and r<=?");
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				rs = pstmt.executeQuery();
				if (rs.next()) { 
					articleList = new ArrayList();
			     do{
			    	 PaymentDto article = new PaymentDto();
			    	 article.setYear_mt(rs.getString("year_mt"));
			    	 article.setPay_sum(rs.getString("pay_sum"));
			    	 articleList.add(article);
			     }while(rs.next());
				}
			}catch (Exception ex){
				ex.printStackTrace();
			}finally{
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				}
			return articleList;
			}
		
		public int YpayCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from (select year_mt, sum(sum_amount) as pay_sum "
						+ "from (select to_char(reg_date, 'yyyy') as year_mt, pay_amount*pay_price as sum_amount "
						+ "from payment) group by year_mt) ");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				} catch(Exception ex) {
				ex.printStackTrace();
				} finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {} 
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					
				}
				return x;
			}
					
	    //결제내역 검색
		public List Search(String item, String search, int start, int end) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select pay_code, mem_num, p_code, p_size, pay_amount, pay_price, p_point, pay_howpay, reg_date, r "
						+ "from (select pay_code, mem_num, p_code, p_size, pay_amount, pay_price, p_point, pay_howpay, reg_date, rownum r "
						+ "from payment where "+item+" like '%"+search+"%' order by reg_date desc) where r>=? and r<=?");
				        pstmt.setInt(1, start);
				        pstmt.setInt(2, end);
				        
				        rs= pstmt.executeQuery();
				        if (rs.next()) {
				        	articleList = new ArrayList(end);
				        	do{
				        	    PaymentDto article = new PaymentDto();
				        		article.setPay_code(rs.getLong("pay_code"));
				        		article.setMem_num(rs.getInt("mem_num"));
				        		article.setP_code(rs.getString("p_code"));
				        		article.setP_size(rs.getString("p_size"));
				        		article.setPay_amount(rs.getInt("pay_amount"));
				        		article.setPay_price(rs.getInt("pay_price"));
				        		article.setP_point(rs.getInt("p_point"));
				        		article.setPay_howpay(rs.getString("pay_howpay"));
				        		article.setReg_date(rs.getTimestamp("reg_date"));
				  
				        		articleList.add(article);
				        	}while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return articleList;
		}
		
		public int searchCount(String item, String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from payment where "+item+" like '%"+search+"%'");
				 rs = pstmt.executeQuery();
				 if (rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
		}
	    //결제내역 결제일 검색
		public List dateSearch(String item, String search) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from payment where to_char(reg_date,'RRRR-MM-DD') = ?");
					pstmt.setString(1, search);
				        rs= pstmt.executeQuery();
				        if (rs.next()) {
				        	articleList = new ArrayList();
				        	do{
				        	    PaymentDto article = new PaymentDto();
				        		article.setPay_code(rs.getLong("pay_code"));
				        		article.setMem_num(rs.getInt("mem_num"));
				        		article.setP_code(rs.getString("p_code"));
				        		article.setP_size(rs.getString("p_size"));
				        		article.setPay_amount(rs.getInt("pay_amount"));
				        		article.setPay_price(rs.getInt("pay_price"));
				        		article.setP_point(rs.getInt("p_point"));
				        		article.setPay_howpay(rs.getString("pay_howpay"));
				        		article.setReg_date(rs.getTimestamp("reg_date"));
				        		articleList.add(article);
				        	}while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return articleList;
		}
		public int dateCount(String item, String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from payment where to_char(reg_date,'RRRR-MM-DD') = ?");
				  pstmt.setString(1, search);
				 rs = pstmt.executeQuery();
				 if (rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x;
		}
		//회원 결제 총액
		public List TotalPay() throws Exception {
			 Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 List articleList = null;
			 int x = 0;
			 try{
					 conn = getConnection();
			         pstmt = conn.prepareStatement("select mem_num, sum(sum_amount) as pay_sum "
			         		+ "from (select mem_num, pay_amount*pay_price as sum_amount "
			         		+ "from payment ) group by mem_num ");
			      
				     rs = pstmt.executeQuery();
				     
				     if(rs.next()){
				    	 articleList = new ArrayList();
					 do{
						 PaymentDto article = new PaymentDto();
						 article.setMem_num(rs.getInt("mem_num"));
						 article.setPay_sum(rs.getString("pay_sum"));
						 articleList.add(article);
					 }while(rs.next());
				 }
			 }catch(Exception ex){
				ex.printStackTrace();
			 } finally{
				if(rs != null)try{rs.close();}catch(SQLException ex){}
				if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
				if(conn != null)try{conn.close();}catch(SQLException ex){}
			 }
			 return articleList;
		}
		
		public int TotalCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from (select mem_num, sum(sum_amount) as pay_sum "
		         		+ "from (select mem_num, pay_amount*pay_price as sum_amount "
		         		+ "from payment ) group by mem_num) ");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				} catch(Exception ex) {
				ex.printStackTrace();
				} finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {} 
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					
				}
				return x;
			}
		
		//회원 결제 총액 검색
		public List memSearch(String item, String search) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select mem_num, sum(sum_amount) as pay_sum "
		         		+ "from (select mem_num, pay_amount*pay_price as sum_amount "
		         		+ "from payment where "+item+" like '%"+search+"%') group by mem_num ");
		      
			     rs = pstmt.executeQuery();
			     
			     if(rs.next()){
			    	 articleList = new ArrayList();
				 do{
					 PaymentDto article = new PaymentDto();
					 article.setMem_num(rs.getInt("mem_num"));
					 article.setPay_sum(rs.getString("pay_sum"));
					 articleList.add(article);
				 }while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return articleList;
		}
	
	
	public void insertPayment(PaymentDto pay) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql="";
		
		try{
			sql = "insert into payment(pay_code, mem_num, p_code, p_size," 
				+ "pay_amount, pay_price, p_point, pay_howpay, reg_date, p_name)";
			sql+= "values(?,?,?,?,?,?,?,?,?,?)";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, pay.getPay_code());
			pstmt.setInt(2, pay.getMem_num());
			pstmt.setString(3, pay.getP_code());
			pstmt.setString(4, pay.getP_size());
			pstmt.setInt(5, pay.getPay_amount());
			pstmt.setInt(6, pay.getPay_price());
			pstmt.setInt(7, pay.getP_point());
			pstmt.setString(8, pay.getPay_howpay());
			pstmt.setTimestamp(9, pay.getReg_date());
			pstmt.setString(10, pay.getP_name());
			
			pstmt.executeUpdate();
			
		}
		catch(Exception ex) {	ex.printStackTrace();
		}
		finally{
			if (pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if (conn != null) try{conn.close();} catch(SQLException ex){}
		}
	}

	
	
	
	public int payCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from payment");
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				count = rs.getInt(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try{rs.close();} catch(SQLException ex){}
			if (pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if (conn != null) try{conn.close();} catch(SQLException ex){}
			
		}
		return count;
	}
	
	
	
	
public List persnerPay(int mem_num,int Start, int End)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList paylist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date, r "
					+"from (select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date,rownum r "
					+"from (select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date from payment  "
					+"where mem_num=? order by reg_date desc) order by reg_date desc ) where r>=? and r<=?");
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				paylist = new ArrayList();
				
				do{
					PaymentDto paydto = new PaymentDto();
					
					paydto.setPay_code(rs.getLong("pay_code"));
					paydto.setMem_num(rs.getInt("mem_num"));
					paydto.setP_code(rs.getString("p_code"));
					paydto.setP_name(rs.getString("p_name"));
					paydto.setP_size(rs.getString("p_size"));
					paydto.setPay_amount(rs.getInt("pay_amount"));
					paydto.setPay_price(rs.getInt("pay_price"));
					paydto.setP_point(rs.getInt("p_point"));
					paydto.setPay_howpay(rs.getString("pay_howpay"));
					paydto.setReg_date(rs.getTimestamp("reg_date"));
					
					paylist.add(paydto);
						
				}while(rs.next());
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try{rs.close();} catch(SQLException ex){}
			if (pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if (conn != null) try{conn.close();} catch(SQLException ex){}
			
		}
		return paylist;
		
	}
	
public int persnerCount(int mem_num)throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int count = 0;
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement("select count(*) from payment where mem_num=?");
		pstmt.setInt(1, mem_num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			
			count = rs.getInt(1);
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (rs != null) try{rs.close();} catch(SQLException ex){}
		if (pstmt != null) try{pstmt.close();} catch(SQLException ex){}
		if (conn != null) try{conn.close();} catch(SQLException ex){}
		
	}
	return count;
}

	
	
	
	
	public List getPay(int Start, int End)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList paylist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date, r "
					+"from (select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date,rownum r "
					+"from (select pay_code,mem_num,p_code,p_name,p_size,pay_amount,pay_price,p_point,pay_howpay,reg_date from payment  "
					+" order by reg_date desc) order by reg_date desc ) where r>=? and r<=?");
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				paylist = new ArrayList();
				
				do{
					PaymentDto paydto = new PaymentDto();
					
					paydto.setPay_code(rs.getLong("pay_code"));
					paydto.setMem_num(rs.getInt("mem_num"));
					paydto.setP_code(rs.getString("p_code"));
					paydto.setP_name(rs.getString("p_name"));
					paydto.setP_size(rs.getString("p_size"));
					paydto.setPay_amount(rs.getInt("pay_amount"));
					paydto.setPay_price(rs.getInt("pay_price"));
					paydto.setP_point(rs.getInt("p_point"));
					paydto.setPay_howpay(rs.getString("pay_howpay"));
					paydto.setReg_date(rs.getTimestamp("reg_date"));
					
					paylist.add(paydto);
						
				}while(rs.next());
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try{rs.close();} catch(SQLException ex){}
			if (pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if (conn != null) try{conn.close();} catch(SQLException ex){}
			
		}
		return paylist;
		
	}
	

}
