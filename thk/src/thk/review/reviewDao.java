package thk.review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;


public class reviewDao {

	private static reviewDao instance = new reviewDao();
	
	public static reviewDao getInstance(){
		return instance;
	}
	
	private reviewDao(){}
	
	
	public Connection getConnection()throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	 
	public int CompareCode(int mem_num , String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int compare = -1;
		String comcode = "";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select p_code from payment where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				comcode = rs.getString(1);
					 
					if(p_code.equals(comcode)){
						compare = 1;
						break;
					}else{
						compare = 0;
					}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return compare;
		
		
	}
	
	
	
	
	
	public int repleCount(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_review where p_code=?");
			pstmt.setString(1, p_code);
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

	
	
	
	public List ProductReple(String p_code,int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List plist = null;
		reviewDto pdto = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,mem_id,subject,content,img,star,reg_date,readcount,r "
					+ "from (select num,mem_id,subject,content,img,star,reg_date,readcount,rownum r "
					+ "from (select num,mem_id,subject,content,img,star,reg_date,readcount "
					+ "from bo_review where p_code=? order by reg_date desc ) order by reg_date desc) where r>=? and r<=?");
			pstmt.setString(1, p_code);
			pstmt.setInt(2, Start);
			pstmt.setInt(3, End);
			rs = pstmt.executeQuery();
			if(rs.next()){
				plist = new ArrayList(End);
				do{
				
					pdto = new reviewDto();
				
				pdto.setNum(rs.getInt("num"));
				pdto.setMem_id(rs.getString("mem_id"));
				pdto.setSubject(rs.getString("subject"));
				pdto.setContent(rs.getString("content"));
				pdto.setImg(rs.getString("img"));
				pdto.setStar(rs.getString("star"));
				pdto.setReg_date(rs.getTimestamp("reg_date"));
				pdto.setReadcount(rs.getInt("readcount"));
				
				plist.add(pdto);
			
			}while(rs.next());
		}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
			
		}
		return plist;
	
	}

	
	
	public int pcodeCount(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pcodeCount = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from payment where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				pcodeCount = rs.getInt(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return pcodeCount;
	}
	
	
	
	
	public List getPcode(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List codelist = null;

		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from payment where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				codelist = new ArrayList();
				do{
				
					
					reviewDto codedto = new reviewDto();
					
					codedto.setP_name(rs.getString("p_name"));
					codedto.setP_code(rs.getString("p_code"));
					
					codelist.add(codedto);
					
				}while(rs.next());
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
		}
		return codelist;
		
	}
	
	
	
	
	
	
	
	public int FindNum(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mem_num = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select mem_num from member where mem_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				mem_num = rs.getInt(1);
	
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
			
		}
		return mem_num;
		
	}
	
	
	
	
	
	
	public List doSearch(String item ,String search, int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List serlist = null;
		reviewDto serdto = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount,r "
					+ "from (select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount,rownum r "
					+ "from (select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount "
					+ "from bo_review where "+item+" like '%"+search+"%' ) order by reg_date desc) where r>=? and r<=?");
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				serlist = new ArrayList(End);
				do{
				
				serdto = new reviewDto();
				
				serdto.setNum(rs.getInt("num"));
				serdto.setMem_num(rs.getInt("mem_num"));
				serdto.setMem_id(rs.getString("mem_id"));
				serdto.setP_code(rs.getString("p_code"));
				serdto.setP_name(rs.getString("p_name"));
				serdto.setSubject(rs.getString("subject"));
				serdto.setContent(rs.getString("content"));
				serdto.setImg(rs.getString("img"));
				serdto.setStar(rs.getString("star"));
				serdto.setReg_date(rs.getTimestamp("reg_date"));
				serdto.setReadcount(rs.getInt("readcount"));
				
				serlist.add(serdto);
				
				
				
			}while(rs.next());
		}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}		
			
		}
		return serlist;
		
		
	}
	
	
	
	
	public int searchCount(String item, String search)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_review where "+item+" like '%"+search+"%'");
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
	
	
	
	
	
	public int doDelete(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{		
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_review where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				pstmt = conn.prepareStatement("delete from bo_review where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				x = 1;
			}else{
				x = 0;
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
	
	
	
	
	
	public int Fixinsert(reviewDto fixdto)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "";
		try{		
	
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_review where num=?");
			pstmt.setInt(1, fixdto.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql="update bo_review set p_code=?,p_name=?,img=?,subject=?,content=?,star=?,reg_date=? where num=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, fixdto.getP_code());
				pstmt.setString(2, fixdto.getP_name());
				pstmt.setString(3, fixdto.getImg());
				pstmt.setString(4, fixdto.getSubject());
				pstmt.setString(5, fixdto.getContent());
				pstmt.setString(6, fixdto.getStar());
				pstmt.setTimestamp(7, fixdto.getReg_date());
				pstmt.setInt(8, fixdto.getNum());
				
				pstmt.executeUpdate();
				
				x = 1;
				
			}else{
				
				x = 0;
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
	
	
	
	public reviewDto getFix(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		reviewDto fixdto = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bo_review where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
			
				fixdto = new reviewDto();
				
				fixdto.setNum(rs.getInt("num"));
				fixdto.setP_code(rs.getString("p_code"));
				fixdto.setMem_id(rs.getString("mem_id"));
				fixdto.setSubject(rs.getString("subject"));
				fixdto.setContent(rs.getString("content"));
				fixdto.setImg(rs.getString("img"));
				fixdto.setStar(rs.getString("star"));
				
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
	
	
	
	
	public reviewDto getReview(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		reviewDto redto = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update bo_review set readcount=readcount+1 where num=?"); 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from bo_review where num=?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				redto = new reviewDto();
				
				redto.setMem_num(rs.getInt("mem_num"));
				redto.setP_name(rs.getString("p_name"));
				redto.setMem_id(rs.getString("mem_id"));
				redto.setSubject(rs.getString("subject"));
				redto.setContent(rs.getString("content"));
				redto.setImg(rs.getString("img"));
				redto.setStar(rs.getString("star"));
				redto.setReadcount(rs.getInt("readcount"));
				redto.setReg_date(rs.getTimestamp("reg_date"));
		
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
		}
		return redto;
	}
	
	
	
	
	
	
	
	public int reCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from bo_review");
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
	
	
	
	
	
	
	
	
	public List getReviewList(int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List relist = null;
		
		try{
			conn = getConnection();    
			pstmt = conn.prepareStatement("select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount,r "
					+ "from (select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount,rownum r "
					+ "from (select num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date,readcount "
					+ "from bo_review order by reg_date desc) order by reg_date desc) where r>=? and r<=?");
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				relist = new ArrayList(End);
				do{
				
				reviewDto redto = new reviewDto();
				
				redto.setNum(rs.getInt("num"));
				redto.setMem_num(rs.getInt("mem_num"));
				redto.setMem_id(rs.getString("mem_id"));
				redto.setP_code(rs.getString("p_code"));
				redto.setP_name(rs.getString("p_name"));
				redto.setSubject(rs.getString("subject"));
				redto.setContent(rs.getString("content"));
				redto.setImg(rs.getString("img"));
				redto.setStar(rs.getString("star"));
				redto.setReg_date(rs.getTimestamp("reg_date"));
				redto.setReadcount(rs.getInt("readcount"));
			
				relist.add(redto);
				
			}while(rs.next());

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){try{rs.close();}catch(SQLException s){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			if(conn != null){try{conn.close();}catch(SQLException s){}}
			
		}	
		return relist;
	}

	
	
	
	
	public void ReWrite(reviewDto redto)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int number = 0;
		try{
			
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(num) from bo_review");
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1)+1;
			}else{
				number = 1;  // 최초 새글
			}
		
			sql = "insert into bo_review(num,mem_num,mem_id,p_code,p_name,subject,content,img,star,reg_date) "
					+ "values (review_seq.NEXTVAL,?,?,?,?,?,?,?,?,?) ";
			
		
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, redto.getMem_num());
			pstmt.setString(2, redto.getMem_id());
			pstmt.setString(3, redto.getP_code());
			pstmt.setString(4, redto.getP_name());
			pstmt.setString(5, redto.getSubject());
			pstmt.setString(6, redto.getContent());
			pstmt.setString(7, redto.getImg());
			pstmt.setString(8, redto.getStar());
			pstmt.setTimestamp(9, redto.getReg_date());
			
			
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
