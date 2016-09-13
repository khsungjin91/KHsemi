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
	private eventDao(){}//������
	
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
			//������ �ۼ�. �۹�ȣ ������, ��ȸ��, �ۼ���, ����, ����, ����, ��¥ �ý���

		

			pstmt.setString(1, edto.getSubject());
			pstmt.setString(2, edto.getContent());
			pstmt.setString(3, edto.getImg());
			pstmt.setTimestamp(4, edto.getReg());
			pstmt.executeUpdate(); //���� ����
		}catch(Exception ex){
			ex.printStackTrace();
		} finally { // finally�� �������� ����ȴ�. ������ ���� �κ�. sql ����� ���� �� ������ ���� ������ ��� ���۵ǰ� �ֱ⿡ �޸� ���� ������ �Ͼ��.
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}//SQLException�� sql���� ���ܸ� ó���ϴ� ��.
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	}
	
	public int getArticleCount() throws Exception{ //�Խ��ǿ� �ִ� �Խù� ������ �˾Ƴ��� �޼ҵ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_event");
			//���� ���̺��� ���ڵ� ������ ǥ���ϴ� ������.
			rs = pstmt.executeQuery(); //select�� ������ Query();
			if(rs.next()){ //ī��Ʈ�� ���ڰ� ù ��° Ŀ���� ���� ��.
				x = rs.getInt(1); //ù ��° ���� �̾Ƴ� x�� ����. �������� ����.
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; //x�� ���ڵ� ���ڸ� ��� int������ �����Ѵ�.
	}
	
	
	public List getArticles(int start, int end)throws Exception{
		//�߿��ϰ� ����� �޼ҵ�. �Խ��ǿ� �Խù����� �����Ͽ� �ѷ��ֱ� ���� ���Ǵ� �ޤ��ҵ�
		//�������� ������ �÷� rownum�� �̿��� �Խù����� ���Ľ�Ű�� �ص�ο� ��ſ�θ� �������� �Խù����� �̾Ƴ� ȭ�鿡 ǥ���Ѵ�.
		Connection conn= null; //Connection�� Ŭ���� conn��ü ����
		PreparedStatement pstmt = null; //������ ���� ���� PreparedStatement��ü pstmt����
		ResultSet rs = null; //���� ������� ��� ����ϱ� ���� Ŭ���� ResultSet ��ü rs ����
		List articleList=null; // �Խ��ǿ� �ݺ������� �Խù��� �Ѹ��� ���� ListŬ������ articleList ��ü ����
		String sql=""; //�̰� �׳� sql���� ��� ���� ���� ���� ����.
		try{
			conn= getConnection(); //��� ����.
			sql="select * from (select  num, readcount, writer, subject, content, img, reg ,";
			sql+="rownum r from(select num, readcount, writer, subject, content, img, reg from bo_event order by reg desc) )where r >= ? and r <= ?";
			//���� ������ ��ȣ�� ��¥�� ���� �� �ٽ� ���� Rownum���� ���� �� �ص�ο� ��ſ�η� ������ ��� ������ ���� �Խù��� ������
			pstmt =conn.prepareStatement(sql);
				
					/*select * from    
					(select  num, readcount, writer, subject, content, img, reg , rownum r from
					(select num, readcount, writer, subject, content, img, reg from bo_event order by reg desc) )
					where r >= 1 and r <= 10;
				//	������ ���� �� �� ���ξ� �Ѵ�. reg�� ������ �� rownum ������ �����Ѵ�
					
				"select num, readcount, writer, subject, content, img, reg, r " 
				+ "from (select num, readcount, writer, subject, content, img, reg, rownum r " 
				+ "from bo_event order by reg desc) where r >= ? and r<= ? ");	
				//Ʋ�ȴ� �κ�. rownum���� ������ �� �ٽ� reg �������� �����Ѵ�. 
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
				//��ȸ�� �ø��� ����
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt= conn.prepareStatement("select * from bo_event where num = ?"); 
					//�۹�ȣ�˻�
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
		} finally { // finally�� �������� ����ȴ�. ������ ���� �κ�. sql ����� ���� �� ������ ���� ������ ��� ���۵ǰ� �ֱ⿡ �޸� ���� ������ �Ͼ��.
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}//SQLException�� sql���� ���ܸ� ó���ϴ� ��.
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
	

