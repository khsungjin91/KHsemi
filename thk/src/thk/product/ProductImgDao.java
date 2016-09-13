package thk.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class ProductImgDao {

	private static ProductImgDao instance = new ProductImgDao();
	
	public static ProductImgDao getInstance() {
		return instance;
	}
	
	private ProductImgDao() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	
	public ProductImgDto getImage(String p_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductImgDto imgdto = null;
		try{
			conn = getConnection();
			pstmt =conn.prepareStatement("select * from image where p_code=?");
			pstmt.setString(1, p_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				imgdto = new ProductImgDto();
				
				imgdto.setImg1(rs.getString("img1"));
				imgdto.setImg2(rs.getString("img2"));
				imgdto.setImg3(rs.getString("img3"));
				imgdto.setImg4(rs.getString("img4"));
				imgdto.setImg5(rs.getString("img5"));
					
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return imgdto;
	}
	
	
	
	
	
	
	public void insertImg(ProductImgDto img)
	throws Exception { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"insert into image values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, img.getP_code());
			pstmt.setString(2, img.getImg1());
			pstmt.setString(3, img.getImg2());
			pstmt.setString(4, img.getImg3());
			pstmt.setString(5, img.getImg4());
			pstmt.setString(6, img.getImg5());
			pstmt.setString(7, img.getImg6());
			pstmt.setString(8, img.getImg6());
			pstmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}