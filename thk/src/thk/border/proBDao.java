package thk.border;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class proBDao {


	private static proBDao instance = new proBDao();
		
	public static proBDao getInstance(){
	
		return instance;
	}
	
	private proBDao(){}
	
	
	public Connection getConnection()throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
public int insertFix(proBDto fixdto)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int x = -1;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_notice where num=?");
			pstmt.setInt(1, fixdto.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				
			sql = "update bo_notice set subject=?,content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, fixdto.getSubject());
			pstmt.setString(2, fixdto.getContent());
			pstmt.setInt(3, fixdto.getNum());
			pstmt.executeUpdate();
			
			x=1;
			}else{
			x=0;
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
	
	
	
	public proBDto getFix(int num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		proBDto fixdto = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				fixdto = new proBDto();
				
				fixdto.setNum(rs.getInt("num"));
				fixdto.setWriter(rs.getString("writer"));
				fixdto.setSubject(rs.getString("subject"));
				fixdto.setContent(rs.getString("content"));
				fixdto.setReg_date(rs.getTimestamp("reg_date"));
				
		
				
			}
			
		}catch(Exception e){
			
		}finally{
			
		}
		return fixdto;
		
		
		
		
	}
	
	public int getDelete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "";
		try{
			
			conn = getConnection();
			pstmt= conn.prepareStatement("select * from bo_notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql = "delete from bo_notice where num=?";
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
	
	
	
	
	public proBDto getView(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		proBDto viewdto = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				viewdto =new proBDto();
				
				viewdto.setNum(rs.getInt("num"));
				viewdto.setWriter(rs.getString("writer"));
				viewdto.setSubject(rs.getString("subject"));
				viewdto.setContent(rs.getString("content"));
				viewdto.setReg_date(rs.getTimestamp("reg_date"));

			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
		}
		return viewdto;
		
	}
	
	
	
	
	public int getCount()throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_notice");
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
		List listdto = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,writer,subject,content,reg_date,r from (select num,writer,subject,content,reg_date,rownum r "
					+ "from bo_notice order by reg_date desc) where r>=? and r<=?");
			
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				listdto = new ArrayList(End);
				do{
					proBDto prodto = new proBDto();
					
					prodto.setNum(rs.getInt("num"));
					prodto.setWriter(rs.getString("writer"));
					prodto.setSubject(rs.getString("subject"));
					prodto.setContent(rs.getString("content"));
					prodto.setReg_date(rs.getTimestamp("reg_date"));

					listdto.add(prodto);
					
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}
		return listdto;
	}
	
	
	
	
	
	public void insertWrite(proBDto writedto)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = writedto.getNum();
		int number= 0 ;

		String sql = "";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(num) from bo_notice");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1)+1;
			}else{
				number = 1;
			}
			
				
			sql = "insert into bo_notice(num,writer,subject,content,reg_date) values (bo_seq.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, writedto.getWriter());
			pstmt.setString(2, writedto.getSubject());
			pstmt.setString(3, writedto.getContent());
			pstmt.setTimestamp(4, writedto.getReg_date());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}	
		}
	
	
	}
}