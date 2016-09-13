package thk.reple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RepleDao {
	
	
	private static RepleDao instance = new RepleDao();
	
	
	public static RepleDao getInstance(){
		return instance;
	}
	
	private RepleDao (){}

	
	public Connection getConnection()throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	public int repleCount(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from reple where qna_num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			 if(rs.next()){
				 count = rs.getInt(1);
			 }
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}return count;
	}
	
	
	
	
	
	public RepleDto getReple(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RepleDto reple = null; 
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from reple where qna_num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				reple = new RepleDto();
				
				reple.setWriter(rs.getString("writer"));
				reple.setContent(rs.getString("content"));
				reple.setReg_date(rs.getTimestamp("reg_date"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}
		return reple;
	}
	
	
	
	
	
	
	public void insertReple(RepleDto inreple){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into reple(num, writer, content, reg_date, qna_num ) "
					+ "values(reple_seq.NEXTVAL,?,?,?,?)");
			
			pstmt.setString(1, inreple.getWriter());
			pstmt.setString(2, inreple.getContent());
			pstmt.setTimestamp(3, inreple.getReg_date());
			pstmt.setInt(4, inreple.getQna_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
				
		}
		
	}
	
}
