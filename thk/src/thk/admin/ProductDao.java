package thk.admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class ProductDao {

	private static ProductDao instance = new ProductDao();
	
	public static ProductDao getInstance() {
		return instance;
	}
	
	private ProductDao() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	public List proSearch(String item, String search, int start, int end) throws Exception { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select p_code, p_name, p_material, p_thumbnail, p_img, p_price, p_price_dc, p_point, p_category1, p_category2, p_category3, p_sale_date, r "
				    + "from (select p_code, p_name, p_material ,p_thumbnail, p_img, p_price, p_price_dc, p_point, p_category1, p_category2, p_category3, p_sale_date, rownum r "
				    + "from product where "+item+" like '%"+search+"%' order by p_sale_date desc) where r>=? and r<=?");
			        pstmt.setInt(1, start);
			        pstmt.setInt(2, end);
			        
			        rs= pstmt.executeQuery();
			        if (rs.next()) {
			        	articleList = new ArrayList(end);
			        	do{
			        		ProductDto article = new ProductDto();
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
	
	
	
	
	
	public int proCount(String item, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			 conn = getConnection();
			  pstmt = conn.prepareStatement("select count(*) from product where "+item+" like '%"+search+"%'");
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
	
	
	
	//상품 등록일 검색
	public List dateSearch(String item, String search) throws Exception { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from product where to_char(p_sale_date,'RRRRMMDD') = ?");
			        pstmt.setString(1, search);
			        
			        rs= pstmt.executeQuery();
			        if (rs.next()) {
			        	articleList = new ArrayList();
			        	do{
			        		ProductDto article = new ProductDto();
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
			  pstmt = conn.prepareStatement("select count(*) from product where to_char(p_sale_date,'RRRRMMDD') = ?");
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

	
	
	
	public void insertMember(ProductDto member)
	throws Exception { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"insert into product values(?,?,?,?,?,?,?,?,?,?,?,sysdate,?)");
			pstmt.setString(1, member.getP_code());
			pstmt.setString(2, member.getP_name());
			pstmt.setString(3, member.getP_material());
			pstmt.setString(4, member.getP_thumbnail());
			pstmt.setString(5, member.getP_img());
			pstmt.setInt(6, member.getP_price());
			pstmt.setInt(7, member.getP_price_dc());
			pstmt.setInt(8, member.getP_point());
			pstmt.setString(9, member.getP_category1());
			pstmt.setString(10, member.getP_category2());
			pstmt.setString(11, member.getP_category3());
			pstmt.setInt(12, member.getReadcount());
			pstmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	public ProductDto getMember(String p_code)throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDto member=null;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"select * from product where p_code = ?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				member = new ProductDto();
				member.setP_code(rs.getString("p_code"));
				member.setP_name(rs.getString("p_name"));
				member.setP_material(rs.getString("p_material"));
				member.setP_thumbnail(rs.getString("p_thumbnail"));
				member.setP_img(rs.getString("p_img"));
	            member.setP_price(rs.getInt("p_price"));
	            member.setP_price_dc(rs.getInt("p_price_dc"));
	            member.setP_point(rs.getInt("p_point"));
	            member.setP_category1(rs.getString("p_category1"));
	            member.setP_category2(rs.getString("p_category2"));
	            member.setP_category3(rs.getString("p_category3"));
	            member.setP_sale_date(rs.getTimestamp("p_sale_date"));   
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return member;
	}
	
	
	public List getArticles(int start, int end) throws Exception { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select p_code, p_name, p_material, p_thumbnail, p_img, p_price, p_price_dc, p_point, p_category1, p_category2, p_category3, p_sale_date, r "
					+ "from (select p_code, p_name, p_material ,p_thumbnail, p_img, p_price, p_price_dc, p_point, p_category1, p_category2, p_category3, p_sale_date, rownum r "
					+ "from (select p_code, p_name, p_material ,p_thumbnail, p_img, p_price, p_price_dc, p_point, p_category1, p_category2, p_category3, p_sale_date "
					+ "from product order by p_sale_date desc)order by p_sale_date desc) where r>=? and r<=?");
			        pstmt.setInt(1, start);
			        pstmt.setInt(2, end);
			        
			        rs= pstmt.executeQuery();
			        if (rs.next()) {
			        	articleList = new ArrayList(end);
			        	do{
			        		ProductDto article = new ProductDto();
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

	public int getArticleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from product");
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
	
	public ProductDto updateGetArticle(String p_code)throws Exception {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDto article = null;
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from product where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new ProductDto();
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
			}
		} catch(Exception ex){
		    ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return article;
	}
	
	public int updateArticle(ProductDto article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int x = -1;
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from product where p_code=?");
			pstmt.setString(1, article.getP_code());
			rs = pstmt.executeQuery();
			if(rs.next()){
				
		    sql = "update product set p_name=?, p_material=?,p_price=?,p_price_dc=?, "
		    	+ "p_point=?,p_category1=?,p_category2=?,p_category3=? where p_code=?";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getP_name());
			pstmt.setString(2, article.getP_material());
			pstmt.setInt(3, article.getP_price());
			pstmt.setInt(4, article.getP_price_dc());
			pstmt.setInt(5, article.getP_point());
			pstmt.setString(6, article.getP_category1());
			pstmt.setString(7, article.getP_category2());
			pstmt.setString(8, article.getP_category3());
			pstmt.setString(9, article.getP_code());
			pstmt.executeUpdate();
			
				x=1;
			
			}else{
			
				x=0;
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
	
	
	
	
	
	public int deleteArticle(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        int x = -1;
        		
        try{
        	
        	conn = getConnection();
        	pstmt = conn.prepareStatement("select * from product where p_code=?");
        	pstmt.setString(1, p_code);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
            pstmt = conn.prepareStatement("delete from product where p_code=?");
            pstmt.setString(1, p_code);
            pstmt.executeUpdate();
            
              x = 1;
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
	
	//메인 리스트
		public List getProduct() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List p_list = null;
					
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product");
				rs = pstmt.executeQuery();
				p_list = new ArrayList();
				
				while(rs.next()){
				
					ProductDto pdto = new ProductDto();
					pdto.setP_code(rs.getString("p_code"));
					pdto.setP_thumbnail(rs.getString("p_thumbnail"));
					pdto.setP_name(rs.getString("p_name"));
					pdto.setP_price(rs.getInt("p_price"));
					pdto.setP_price_dc(rs.getInt("p_price_dc"));
					
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
		
		
}

	
			
	            

	

    
