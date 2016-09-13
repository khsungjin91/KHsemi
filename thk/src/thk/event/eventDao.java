package thk.event;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;


public class eventDao {
	
	private static eventDao instance = new eventDao();

	public static eventDao getInstance(){
		return instance;
	}
	private eventDao(){}//생성자
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public void insertEvent(eventDto edto)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into Bo_event values(event_seq.nextval, 0,'owner',?,?,?,?)");
			//쿼리문 작성. 글번호 시퀀스, 조회수, 작성자, 제목, 내용, 파일, 날짜 시스템

		

			pstmt.setString(1, edto.getSubject());
			pstmt.setString(2, edto.getContent());
			pstmt.setString(3, edto.getImg());
			pstmt.setTimestamp(4, edto.getReg());
			pstmt.executeUpdate(); //쿼리 실행
		}catch(Exception ex){
			ex.printStackTrace();
		} finally { // finally는 마지막에 실행된다. 연결을 끊는 부분. sql 사용이 끝난 뒤 연결을 끊지 않으면 계속 동작되고 있기에 메모리 누수 현상이 일어난다.
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}//SQLException은 sql문의 예외를 처리하는 것.
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	}
	
	public int getArticleCount() throws Exception{ //게시판에 있는 게시물 갯수를 알아내는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_event");
			//지정 테이블의 레코드 갯수를 표시하는 쿼리문.
			rs = pstmt.executeQuery(); //select기 때문에 Query();
			if(rs.next()){ //카운트된 숫자가 첫 번째 커서에 나올 것.
				x = rs.getInt(1); //첫 번째 값을 뽑아내 x에 대입. 리턴으로 쓴다.
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //x에 레코드 숫자를 담아 int값으로 리턴한다.
	}
	
	
	public List getArticles(int start, int end)throws Exception{
		//중요하고 어려운 메소드. 게시판에 게시물들을 정렬하여 뿌려주기 위해 사용되는 메ㅐ소드
		//쿼리문에 가상의 컬럼 rownum을 이용해 게시물들을 정렬시키고 앤드로와 스탓로를 기준으로 게시물들을 뽑아내 화면에 표시한다.
		Connection conn= null; //Connection의 클래스 conn객체 생성
		PreparedStatement pstmt = null; //쿼리문 쓰기 위한 PreparedStatement객체 pstmt생성
		ResultSet rs = null; //쿼리 결과값을 담아 사용하기 위한 클래스 ResultSet 객체 rs 생성
		List articleList=null; // 게시판에 반본적으로 게시물을 뿌리기 위해 List클래스의 articleList 객체 생성
		String sql=""; //이건 그냥 sql문을 담아 쓰기 위해 만든 변수.
		try{
			conn= getConnection(); //디비 연결.
			sql="select * from (select  num, readcount, writer, subject, content, img, reg ,";
			sql+="rownum r from(select num, readcount, writer, subject, content, img, reg from bo_event order by reg desc) )where r >= ? and r <= ?";
			//가장 안쪽의 괄호로 날짜로 정렬 후 다시 감사 Rownum으로 정렬 후 앤드로와 스탓로로 기준을 잡아 페이지 안의 게시물을 가져옴
			pstmt =conn.prepareStatement(sql);
				
					/*select * from    
					(select  num, readcount, writer, subject, content, img, reg , rownum r from
					(select num, readcount, writer, subject, content, img, reg from bo_event order by reg desc) )
					where r >= 1 and r <= 10;
				//	수정한 쿼리 두 번 감싸야 한다. reg로 정렬한 후 rownum 값으로 정렬한다
					
				"select num, readcount, writer, subject, content, img, reg, r " 
				+ "from (select num, readcount, writer, subject, content, img, reg, rownum r " 
				+ "from bo_event order by reg desc) where r >= ? and r<= ? ");	
				//틀렸던 부분. rownum으로 정렬한 후 다시 reg 기준으로 정렬한다. 
				*/
				pstmt.setInt(1,  start);
				pstmt.setInt(2,  end);  
			//pstmt =conn.prepareStatement("select * from bo_event order by reg desc where r >= ? and r <= ?");
					
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					eventDto article= new eventDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setImg(rs.getString("img"));
					article.setReg(rs.getTimestamp("reg"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return articleList;
		
	}
	public eventDto getArticle(int num)throws Exception{
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		eventDto article=null;
		try{
			conn= getConnection();
			pstmt = conn.prepareStatement("update bo_event set readcount=readcount+1 where num=?");
				//조회수 올리는 쿼리
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt= conn.prepareStatement("select * from bo_event where num = ?"); 
					//글번호검색
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new eventDto();
				article.setNum(rs.getInt("num"));
				article.setReadcount(rs.getInt("readcount"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setImg(rs.getString("img"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				
			}
	}catch(Exception ex){
		ex.printStackTrace();
	}finally {
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	} 
		return article;
	}
	
	public void updateArticle(eventDto article)throws Exception{
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=getConnection();
			pstmt = conn.prepareStatement("update Bo_event set subject=?, content=?, img=? where num=?");
		//	sql="update bo_event set subject=?,content=?, img=? where num= ?";
		//	pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getImg());
			pstmt.setInt(4, article.getNum());
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		} finally { // finally는 마지막에 실행된다. 연결을 끊는 부분. sql 사용이 끝난 뒤 연결을 끊지 않으면 계속 동작되고 있기에 메모리 누수 현상이 일어난다.
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}//SQLException은 sql문의 예외를 처리하는 것.
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	}

 /*
	public void deleteArticle(int num) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt= null;

	
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("delete from bo_event where num= ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		} finally { 
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	} */
	
	public eventDto deleteArticle(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		eventDto article=null;
		
			try{
				conn=getConnection();
				pstmt= conn.prepareStatement("select * from bo_event where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
					article = new eventDto();
					article.setImg(rs.getString("img"));
			
			
					pstmt=conn.prepareStatement("delete from bo_event where num= ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			} finally { 
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return article;
			
	}


			
	public eventDto updateGetArticle(int num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		eventDto article =null;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("select * from bo_event where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article=new eventDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setImg(rs.getString("img"));
				article.setContent(rs.getString("content"));
				article.setReg(rs.getTimestamp("reg"));
				article.setReadcount(rs.getInt("readcount"));
				
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	}
	}
	

