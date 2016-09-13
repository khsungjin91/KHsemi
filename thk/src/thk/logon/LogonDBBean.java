package thk.logon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

public class LogonDBBean {
	private static LogonDBBean instance = new LogonDBBean();
	   
	public static LogonDBBean getInstance() { return instance; }
   
	private LogonDBBean() { }
   
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	
	
	public int PaiedAllProduct(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int allproduct = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from payment where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				allproduct = rs.getInt(1);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return allproduct;
	}
	
	
	
	
	
	public int PersnerCartCount(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt =conn.prepareStatement("select count(*) from cart where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count  = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return count;
	}
	
	
	
	
	public int PayAllPrice(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int allpay = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select sum(pay_price) from payment where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				allpay = rs.getInt(1);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return allpay;
	}

	
	
	
	 public LogonDataBean idSearch(String mem_name, String mem_email) throws Exception
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			LogonDataBean member=null;
			
	       try
	       {
	    	   conn = getConnection();
	    	   pstmt = conn.prepareStatement(
	    			   "select * from member where mem_name=? and mem_email=?");
	    	   pstmt.setString(1, mem_name);
	    	   pstmt.setString(2, mem_email);
	    	   rs = pstmt.executeQuery();

	    	   if (rs.next()) {
	    		   member = new LogonDataBean();
	    		   member.setMem_id(rs.getString("mem_id"));
	        	   member.setMem_name(rs.getString("mem_name"));
	    	   }
	       }
	       catch(Exception ex) { ex.printStackTrace(); }
	       finally
	       {
	    	   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	       }
	       return member;
		}
	 
	 
	 
	 public LogonDataBean pwSearch(String mem_name, String mem_email, String mem_id) throws Exception
		{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			LogonDataBean member=null;
			
	       try
	       {
	    	   conn = getConnection();
	    	   pstmt = conn.prepareStatement(
	    			   "select * from member where mem_name=? and mem_email=? and mem_id=?");
	    	   pstmt.setString(1, mem_name);
	    	   pstmt.setString(2, mem_email);
	    	   pstmt.setString(3, mem_id);
	    	   rs = pstmt.executeQuery();

	    	   if (rs.next()) {
	    		   member = new LogonDataBean();
	    		   member.setMem_id(rs.getString("mem_id"));
	        	   member.setMem_name(rs.getString("mem_name"));
	        	   member.setMem_pw(rs.getString("mem_pw"));
	    	   }
	       }
	       catch(Exception ex) { ex.printStackTrace(); }
	       finally
	       {
	    	   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	       }
	       return member;
		}

	
	
	
	
	public int getMemberNum(String mem_id) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int num = -1;
       
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select mem_num from member where mem_id = ?");
			
			pstmt.setString(1, mem_id);
			
			rs= pstmt.executeQuery();

			if(rs.next())
				num = rs.getInt("mem_num"); 
			else
				num = -1;//해당 아이디 없음		
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return num;
	}
	
	
	
	
	public void insertMember(LogonDataBean member) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
       
		String sql="";
		try
		{
			sql = "insert into member(mem_num,mem_id,mem_pw,mem_name,mem_phone,"
					+ "mem_sms_yn,mem_email,mem_email_yn,mem_postnum,mem_addrs1,mem_addrs2,reg_date)";
			sql+=" values(mem_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pw());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_phone());
			pstmt.setString(5, member.getMem_sms_yn());
			pstmt.setString(6, member.getMem_email());
			pstmt.setString(7, member.getMem_email_yn());
			pstmt.setString(8, member.getMem_postnum());
			pstmt.setString(9, member.getMem_addrs1());
			pstmt.setString(10, member.getMem_addrs2());
			pstmt.setTimestamp(11, member.getReg_date());

			pstmt.executeUpdate();
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

	public int userCheck(String mem_id, String mem_pw) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		int x=-1;
       
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select mem_pw from member where mem_id = ?");
			
			pstmt.setString(1, mem_id);
			
			rs= pstmt.executeQuery();

			if(rs.next())
			{
				dbpasswd= rs.getString("mem_pw"); 
				
				if(dbpasswd.equals(mem_pw))
					x= 1; //인증 성공
				else
					x= 0; //비밀번호 틀림
			}
			else
				x= -1;//해당 아이디 없음
			
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	

	public int confirmId(String mem_id) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		int x=-1;
       
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select mem_id from member where mem_id = ?");
			
			pstmt.setString(1, mem_id);
			
			rs= pstmt.executeQuery();

			if(rs.next())
				x= 1; //해당 아이디 있음
			else
				x= -1;//해당 아이디 없음		
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}


	public LogonDataBean getMember(String mem_id) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member=null;
		
       try
       {
    	   conn = getConnection();
    	   pstmt = conn.prepareStatement("select * from member where mem_id=?");
    	   pstmt.setString(1, mem_id);
    	   rs = pstmt.executeQuery();

    	   if (rs.next()) {
    		   member = new LogonDataBean();
    		   
    		   member.setMem_id(rs.getString("mem_id"));
    		   member.setMem_pw(rs.getString("mem_pw"));
        	   member.setMem_name(rs.getString("mem_name"));
        	   member.setMem_phone(rs.getString("mem_phone"));
        	   member.setMem_sms_yn(rs.getString("mem_sms_yn"));
        	   member.setMem_email(rs.getString("mem_email"));
        	   member.setMem_email_yn(rs.getString("mem_email_yn"));
        	   member.setMem_postnum(rs.getString("mem_postnum"));
        	   member.setMem_addrs1(rs.getString("mem_addrs1"));
        	   member.setMem_addrs2(rs.getString("mem_addrs2"));
        	   member.setMem_rate(rs.getString("mem_rate"));
        	   member.setMem_sum(rs.getInt("mem_sum"));
        	   member.setMem_point(rs.getInt("mem_point"));
        	   member.setReg_date(rs.getTimestamp("reg_date"));     
    	   }
       }
       catch(Exception ex) { ex.printStackTrace(); }
       finally
       {
    	   if (rs != null) try { rs.close(); } catch(SQLException ex) {}
           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
       }
       return member;
	}
   
	
	
	
	public void updateMember(LogonDataBean member) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
       
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"update member set mem_pw=?, mem_phone=?, mem_sms_yn=?, mem_email=?, mem_email_yn=?,"
					+ "mem_postnum=?, mem_addrs1=?, mem_addrs2=? where mem_id=?");
			
			pstmt.setString(1, member.getMem_pw());
			pstmt.setString(2, member.getMem_phone());
			pstmt.setString(3, member.getMem_sms_yn());
			pstmt.setString(4, member.getMem_email());
			pstmt.setString(5, member.getMem_email_yn());
			pstmt.setString(6, member.getMem_postnum());
			pstmt.setString(7, member.getMem_addrs1());
			pstmt.setString(8, member.getMem_addrs2());
			pstmt.setString(9, member.getMem_id());
           
			pstmt.executeUpdate();
		}
		catch(Exception ex) { ex.printStackTrace(); }
		finally
		{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
   
	 public int deleteMember(String mem_id, String mem_pw)
			    throws Exception {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs= null;
		 String dbpasswd="";
		 int x=-1;
		 try {
			 conn = getConnection();

			 pstmt = conn.prepareStatement(
					 "select mem_pw from member where mem_id = ?");
			 pstmt.setString(1, mem_id);
			 rs = pstmt.executeQuery();
			            
			 if(rs.next()){
				 dbpasswd= rs.getString("mem_pw"); 
				 if(dbpasswd.equals(mem_pw)){
					 pstmt = conn.prepareStatement(
							 "delete from member where mem_id=?");
					 pstmt.setString(1, mem_id);
					 pstmt.executeUpdate();
					 x= 1; //회원탈퇴 성공
				 }else
					 x= 0; //비밀번호 틀림
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
	 
	 public List getMemberList(int startRow, int endRow) throws Exception
	 {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 List memberList=null;
		 
		 try
		 {
			 conn = getConnection();
			 pstmt = conn.prepareStatement(
					 "select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_rate,mem_sum,mem_point,reg_date,r" +
						"from (select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_rate,mem_sum,mem_point,reg_date,rownum r"+
							 "from (select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_rate,mem_sum,mem_point,reg_date" + 
					 			"from member order by mem_num desc) order by mem_num desc) where r >= ? and r <= ?");
			 pstmt.setInt(1, startRow); 
			 pstmt.setInt(2, endRow); 

			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 memberList = new ArrayList(endRow); 
				 
				 do
				 { 
					 LogonDataBean member= new LogonDataBean();
					 
					 member.setMem_num(rs.getInt("mem_num"));
					 member.setMem_id(rs.getString("mem_id"));
					 member.setMem_name(rs.getString("mem_name"));
					 member.setMem_phone(rs.getString("mem_phone"));
					 member.setMem_email(rs.getString("mem_email"));
					 member.setMem_rate(rs.getString("mem_rate"));
					 member.setMem_sum(rs.getInt("mem_sum"));
					 member.setMem_point(rs.getInt("mem_point"));
					 member.setReg_date(rs.getTimestamp("reg_date"));
					 
					 
					 memberList.add(member); 
				 }
				 
				 while(rs.next());
			 }
		 }
		 
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 
		 finally
		 {
			 if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		 }
		 
		 return memberList;
	}
	 
	 public int getMemberCount()
	 {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 int x=0;

		 try
		 {
			 conn = getConnection();
			 pstmt = conn.prepareStatement("select count(*) from member");
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 x= rs.getInt(1); 
			 }
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		 }
		 return x;
	 }
	 
	 
	//포인트 꺼내오기
	 public int getPoint(int mem_num) throws Exception {
	 	Connection conn = null;
	 	PreparedStatement pstmt = null;
	 	ResultSet rs = null;
	 	int point = 0;
	 	
	 	try{
	 		conn = getConnection();
	 		pstmt = conn.prepareStatement("select mem_point from member where mem_num=?");
	 		pstmt.setInt(1, mem_num);
	 		pstmt.executeQuery();
	 		rs = pstmt.executeQuery();
	 		if(rs.next()){

	 			point = rs.getInt("mem_point");
	 		}			
	 				
	 	}catch(Exception ex){
	 		
	 		ex.printStackTrace();
	 		
	 	}finally{
	 		if(conn != null)try{conn.close();}catch(SQLException ex){}
	 		if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
	 		if (rs != null)try{rs.close();}catch(SQLException ex){}	
	 	}
	 	return point;

	 }



	 //포인트 사용 후 회원DB에 포인트 업데이트하기
	 public void usePoint(int mem_num, int usepoint)throws Exception{
	 	Connection conn = null;
	 	PreparedStatement pstmt = null;
	 	ResultSet rs = null;
	 	LogonDataBean article = null;
	 	int x = 0;
	 	
	 	try{
	 		conn = getConnection();
	 		pstmt = conn.prepareStatement("update member set mem_point=mem_point-? where mem_num=?");
	 		pstmt.setInt(1, usepoint);
	 		pstmt.setInt(2, mem_num);
	 		rs = pstmt.executeQuery();
	 		
	 	}catch(Exception e){
	 		
	 		e.printStackTrace();
	 		
	 	}finally{
	 		
	 		if(conn != null)try{conn.close();}catch(SQLException ex){}
	 		if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
	 		if (rs != null)try{rs.close();}catch(SQLException ex){}	
	 		
	 	}
	 }

	//포인트 사용 후 회원DB에 포인트 업데이트하기
		 public void inputPoint(int mem_num, int p_point)throws Exception{
		 	Connection conn = null;
		 	PreparedStatement pstmt = null;
		 	ResultSet rs = null;
		 	LogonDataBean article = null;
		 	int x = 0;
		 	
		 	try{
		 		conn = getConnection();
		 		pstmt = conn.prepareStatement("update member set mem_point=mem_point+? where mem_num=?");
		 		pstmt.setInt(1, p_point);
		 		pstmt.setInt(2, mem_num);
		 		rs = pstmt.executeQuery();				
		 		
		 	}catch(Exception e){
		 		
		 		e.printStackTrace();
		 		
		 	}finally{
		 		
		 		if(conn != null)try{conn.close();}catch(SQLException ex){}
		 		if (pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		 		if (rs != null)try{rs.close();}catch(SQLException ex){}	
		 		
		 	}
		 }
}