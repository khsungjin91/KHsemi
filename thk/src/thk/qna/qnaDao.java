package thk.qna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;


public class qnaDao {


	private static qnaDao instance = new qnaDao();
	
	public static qnaDao getInstance(){
		return instance;
	}
	
	private qnaDao(){}
	
	public Connection getConnection()throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	/*
	public void insertReple(qnaDto dto0224)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = dto0224.getNum();
		int ref = dto0224.getRef();
		int re_step = dto0224.getRe_step();
		int re_level = dto0224.getRe_level();
		int number = 0;
		String sql = "";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(num) from ");
			rs = pstmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1)+1;
			else
				number = 1;
			
			if (num != 0 ){
				sql = "update psboard set re_step=re_step+1 where ref= ? and re_step> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step); 
				pstmt.executeUpdate();
				re_step = re_step+1;
				re_level = re_level+1;
			}else{
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			sql = "insert into psboard(num,writer,subject,email,pw,reg_date, ";
			sql += "ref,re_step,re_level,content,ip) values(psboard_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto0224.getWriter());
		pstmt.setString(2, dto0224.getSubject());
		pstmt.setString(3, dto0224.getEmail());
		pstmt.setString(4, dto0224.getPw());
		pstmt.setTimestamp(5, dto0224.getReg_date());
		pstmt.setInt(6, ref);
		pstmt.setInt(7, re_step);
		pstmt.setInt(8, re_level);
		pstmt.setString(9, dto0224.getContent());
		
		pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}	
		}		
	}
	*/
	
	
	
	public int myCount(String writer)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn =getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_qna where writer=?");
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				count = rs.getInt(1);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
		}
		return count;
		
	}
	
	
	public List getMyqna(String writer, int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList mylist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,writer,secret,subject,content,reg_date,ref,re_step,re_level,r "
					+ "from (select num,writer,secret,subject,content,reg_date,ref,re_step,re_level,rownum r "
					+ "from (select num,writer,secret,subject,content,reg_date,ref,re_step,re_level from bo_qna "
					+ "where writer=? order by reg_date desc ) order by reg_date desc ) where r>=? and r<=?");
			pstmt.setString(1, writer);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mylist = new ArrayList();
				do{
					qnaDto mydto = new qnaDto();
					
					mydto.setNum(rs.getInt("num"));
					mydto.setWriter(rs.getString("writer"));
					mydto.setSecret(rs.getString("secret"));
					mydto.setSubject(rs.getString("subject"));
					mydto.setContent(rs.getString("content"));
					mydto.setReg_date(rs.getTimestamp("reg_date"));
					mydto.setRef(rs.getInt("ref"));
					mydto.setRe_step(rs.getInt("re_step"));
					mydto.setRe_level(rs.getInt("re_level"));
					
					mylist.add(mydto);
					
				}while(rs.next());
			}
			
		}catch(Exception e){
				e.printStackTrace();
		}finally{
		if(rs != null){try{rs.close();}catch(SQLException s){}}
		if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
		if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return mylist;
	}


	
	
	public int updateFix(qnaDto fixdto)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_qna where num=?");
			pstmt.setInt(1, fixdto.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
			
			pstmt = conn.prepareStatement("update bo_qna set subject=?, content=?, email=?, phone=? where num=? ");
				
			pstmt.setString(1, fixdto.getSubject());
			pstmt.setString(2, fixdto.getContent());
			pstmt.setString(3, fixdto.getEmail());
			pstmt.setString(4, fixdto.getPhone());
			pstmt.setInt(5, fixdto.getNum());
			
			pstmt.executeUpdate();
			
			x = 1; 
			}else{x = 0;}
			
		}catch(Exception e){
				e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return x;
		
		
	}
	
	
	
	
	
	
	public qnaDto getFix(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		qnaDto fixdto = null;
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				fixdto = new qnaDto();
				
				fixdto.setNum(rs.getInt("num"));
				fixdto.setWriter(rs.getString("writer"));
				fixdto.setSubject(rs.getString("subject"));
				fixdto.setContent(rs.getString("content"));
				fixdto.setCategory1(rs.getString("category1"));
				fixdto.setCategory2(rs.getString("category2"));
				fixdto.setCategory3(rs.getString("category3"));
				fixdto.setEmail(rs.getString("email"));
				fixdto.setPhone(rs.getString("phone"));
				
			}
			
			
		}catch(Exception e){
			 e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return fixdto;
		
		
	}
	
	
	
	public int pwCheck(String mem_id, String pw)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		String pwch = "";
	
		try{
			conn = getConnection();
			//member DB 에서 id를 찾아 해당id의 pw를 꺼내준다.
			pstmt = conn.prepareStatement("select mem_pw from member where mem_id=?");
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				pwch = rs.getString("mem_pw");
				if(pwch.equals(pw)){
					
					check = 1;
				
				}else{
					
					check = 0;
				}
				
			}else{ check = -1;}
		
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return check;
	}
	

	
	
	
	public int qnaDelete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				pstmt = conn.prepareStatement("delete from bo_qna where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				result = 1;		
			}else{
				result = 0;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}	
		}
		return result;
		
		
	}
	
	
	
	
	
	
	public qnaDto getWrite(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		qnaDto viewdto = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				viewdto = new qnaDto();
				
				viewdto.setNum(rs.getInt("num"));
				viewdto.setWriter(rs.getString("writer"));
				viewdto.setReg_date(rs.getTimestamp("reg_date"));
				viewdto.setCategory1(rs.getString("category1"));
				viewdto.setCategory2(rs.getString("category2"));
				viewdto.setCategory3(rs.getString("category3"));
				viewdto.setSubject(rs.getString("subject"));
				viewdto.setContent(rs.getString("content"));			
				viewdto.setEmail(rs.getString("email"));
				viewdto.setPhone(rs.getString("phone"));
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
	
	
	
	
	
	
	
	public void insertWrite(qnaDto insertdto)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;
		String sql = "";
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(num) from bo_qna");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1)+1;
			}else{
				number = 1;
			}
		
			sql = "insert into bo_qna(num,writer,category1,subject,secret,category2,category3,content,reg_date,email,phone) "
					+ "values (bo_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, insertdto.getWriter());
			pstmt.setString(2, insertdto.getCategory1());
			pstmt.setString(3, insertdto.getSubject());
			pstmt.setString(4, insertdto.getSecret());
			pstmt.setString(5, insertdto.getCategory2());
			pstmt.setString(6, insertdto.getCategory3());
			pstmt.setString(7, insertdto.getContent());
			pstmt.setTimestamp(8, insertdto.getReg_date());
			pstmt.setString(9, insertdto.getEmail());
			pstmt.setString(10, insertdto.getPhone());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}	
		}
		
	}
	
	
	
	
	
	
	public int getCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_qna");
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
		}
		return count;
	}
	
	
	
	
	
	
	
	
	public List getQnaList(int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List qnalist = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,writer,secret,subject,content,reg_date,ref,re_step,re_level,r "
					+ "from (select num,writer,secret,subject,content,reg_date,ref,re_step,re_level,rownum r "
					+ "from (select num,writer,secret,subject,content,reg_date,ref,re_step,re_level from bo_qna order by reg_date desc ) "
					+ "order by reg_date desc ) where r>=? and r<=?");
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				qnalist = new ArrayList(End);
				do{
				
				qnaDto qnadto = new qnaDto();
				
				qnadto.setNum(rs.getInt("num"));
				qnadto.setWriter(rs.getString("writer"));
				qnadto.setSecret(rs.getString("secret"));
				qnadto.setSubject(rs.getString("subject"));
				qnadto.setContent(rs.getString("content"));
				qnadto.setReg_date(rs.getTimestamp("reg_date"));
				qnadto.setRef(rs.getInt("ref"));
				qnadto.setRe_step(rs.getInt("re_step"));
				qnadto.setRe_level(rs.getInt("re_level"));
				
				qnalist.add(qnadto);
				
			}while(rs.next());

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}	
		return qnalist;
	}


}
