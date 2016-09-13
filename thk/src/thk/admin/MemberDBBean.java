package thk.admin;

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

public class MemberDBBean {
	private static MemberDBBean instance = new MemberDBBean();
	
	public static MemberDBBean getInstance(){
	
		return instance;
	}
	
	private MemberDBBean(){}
	
	
	public Connection getConnection()throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	
	public void insertMemrate(String rate, int mem_num, int allpay)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("update member set mem_rate=?,mem_sum=mem_sum+? where mem_num=?");
			pstmt.setString(1, rate);
			pstmt.setInt(2, allpay);
			pstmt.setInt(3, mem_num);
			
			pstmt.executeUpdate();
		
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}	
		}
	
	}
	
	
	
	
	//회원 관리 검색
		public List memSearch(String item, String search, int start, int end) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List memberlist = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_postnum,mem_addrs1,mem_addrs2,reg_date,r "
						+ "from (select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_postnum,mem_addrs1,mem_addrs2,reg_date,rownum r "
						+ "from member where "+item+" like '%"+search+"%' order by reg_date asc) where r>=? and r<=?");
				        pstmt.setInt(1, start);
				        pstmt.setInt(2, end);
				        
				        rs= pstmt.executeQuery();
				        if (rs.next()) {
				        	memberlist = new ArrayList(end);
				        	do{
				        	    MemberDataBean memberdata = new MemberDataBean();
				        	    memberdata.setMemNum(rs.getInt("mem_num"));
								memberdata.setMemId(rs.getString("mem_id"));
								memberdata.setMemName(rs.getString("mem_name"));
								memberdata.setMemPhone(rs.getString("mem_phone"));
								memberdata.setMemEmail(rs.getString("mem_email"));
								memberdata.setMemPostnum(rs.getString("mem_postnum"));
								memberdata.setMemAddrs1(rs.getString("mem_addrs1"));
								memberdata.setMemAddrs2(rs.getString("mem_addrs2"));
								memberdata.setReg_date(rs.getTimestamp("reg_date"));
				  
								memberlist.add(memberdata);
				        	}while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return memberlist;
		}
		
		public int msCount(String item, String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from member where "+item+" like '%"+search+"%'");
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
		//회원 관리 가입일 검색
		public List dateSearch(String item, String search) throws Exception { 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List memberlist = null;
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from member where to_char(reg_date,'RRRRMMDD') = ?");
				        pstmt.setString(1, search);
				        
				        rs= pstmt.executeQuery();
				        if (rs.next()) {
				        	memberlist = new ArrayList();
				        	do{
				        	    MemberDataBean memberdata = new MemberDataBean();
				        	    memberdata.setMemNum(rs.getInt("mem_num"));
								memberdata.setMemId(rs.getString("mem_id"));
								memberdata.setMemName(rs.getString("mem_name"));
								memberdata.setMemPhone(rs.getString("mem_phone"));
								memberdata.setMemEmail(rs.getString("mem_email"));
								memberdata.setMemPostnum(rs.getString("mem_postnum"));
								memberdata.setMemAddrs1(rs.getString("mem_addrs1"));
								memberdata.setMemAddrs2(rs.getString("mem_addrs2"));
								memberdata.setReg_date(rs.getTimestamp("reg_date"));
				  
								memberlist.add(memberdata);
				        	}while(rs.next());
				        }
			} catch(Exception ex) {
				ex.printStackTrace();
			}  finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return memberlist;
		}
		
		public int dateCount(String item, String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				 conn = getConnection();
				  pstmt = conn.prepareStatement("select count(*) from member where to_char(reg_date,'RRRRMMDD') = ?");
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
		
	
	
	public int getDelete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "";
		try{
			
			conn = getConnection();
			pstmt= conn.prepareStatement("select * from member where mem_id=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql = "delete from member where mem_id=?";
		    pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
				x=1;
				
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
	
	public int getCount()throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from member");
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
	
	
	
	public List getList(int Start, int End)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List memberlist = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_postnum,mem_addrs1,mem_addrs2,reg_date,r "
							+ "from (select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_postnum,mem_addrs1,mem_addrs2,reg_date,rownum r "
							+ "from (select mem_num,mem_id,mem_name,mem_phone,mem_email,mem_postnum,mem_addrs1,mem_addrs2,reg_date "
							+ "from member order by reg_date desc )order by reg_date desc) where r>=? and r<=?");
			
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				memberlist = new ArrayList(End);
				do{
					MemberDataBean memberdata = new MemberDataBean();
					
					memberdata.setMemNum(rs.getInt("mem_num"));
					memberdata.setMemId(rs.getString("mem_id"));
					memberdata.setMemName(rs.getString("mem_name"));
					memberdata.setMemPhone(rs.getString("mem_phone"));
					memberdata.setMemEmail(rs.getString("mem_email"));
					memberdata.setMemPostnum(rs.getString("mem_postnum"));
					memberdata.setMemAddrs1(rs.getString("mem_addrs1"));
					memberdata.setMemAddrs2(rs.getString("mem_addrs2"));
					memberdata.setReg_date(rs.getTimestamp("reg_date"));

					memberlist.add(memberdata);
					
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}
		return memberlist;
	}
	
}