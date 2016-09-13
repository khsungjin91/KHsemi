package thk.ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ShipDao {

	private static ShipDao instance = new ShipDao();
	public static ShipDao getInstance() {
		return instance;
	}
	private ShipDao() {	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}

	
	public List getShipList(int Start, int End)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList shiplist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select pay_code,get_name,get_phone,get_postnum,get_addrs1,get_addrs2,get_memo,shipnum,shipcheck,r "
					+ "from (select pay_code,get_name,get_phone,get_postnum,get_addrs1,get_addrs2,get_memo,shipnum,shipcheck,rownum r "
					+ "from (select pay_code,get_name,get_phone,get_postnum,get_addrs1,get_addrs2,get_memo,shipnum,shipcheck "
					+ "from ship order by pay_code desc) order by pay_code desc ) where r>=? and  r<=? ");
			pstmt.setInt(1, Start);
			pstmt.setInt(2, End);
			rs = pstmt.executeQuery();
			if(rs.next()){
				shiplist = new ArrayList();
				do{
					
					ShipDto shipdto = new ShipDto();
					
					shipdto.setPay_code(rs.getLong("pay_code"));
					shipdto.setGet_name(rs.getString("get_name"));
					shipdto.setGet_phone(rs.getString("get_phone"));
					shipdto.setGet_postnum(rs.getString("get_postnum"));
					shipdto.setGet_addrs1(rs.getString("get_addrs1"));
					shipdto.setGet_addrs2(rs.getString("get_addrs2"));
					shipdto.setGet_memo(rs.getString("get_memo"));
					shipdto.setShipnum(rs.getString("shipnum"));
					shipdto.setShipcheck(rs.getString("shipcheck"));
					
					shiplist.add(shipdto);
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}
		}
		return shiplist;
	}
	
	
	
	
	
	public int shipCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from ship");
			rs = pstmt.executeQuery();
			
		if(rs.next()){
			
			count = rs.getInt(1);
			
			
		}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}
		}
		return count;
	}
	
	
	
	//List로 바꾸기
	public List PersnerShipinfo(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList infolist = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from ship where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				infolist = new ArrayList();
				
				do{
					
					ShipDto infodto = new ShipDto();
					
					infodto.setPay_code(rs.getLong("pay_code"));
					infodto.setGet_name(rs.getString("get_name"));
					infodto.setGet_phone(rs.getString("get_phone"));
					infodto.setGet_addrs1(rs.getString("get_addrs1"));
					infodto.setGet_addrs2(rs.getString("get_addrs2"));
					infodto.setGet_postnum(rs.getString("get_postnum"));
					infodto.setGet_memo(rs.getString("get_memo"));
					infodto.setShipcheck(rs.getString("shipcheck"));
					infodto.setShipnum(rs.getString("shipnum"));
					
					infolist.add(infodto);
				
				}while(rs.next());
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}	
		}
		return infolist;
	}
	
	
	
	
	
	public int persnerShipCount(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from ship where mem_num=?");
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			
		if(rs.next()){
			
			count = rs.getInt(1);
			
			
		}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}
		}
		return count;
	}
	
	
	
	
	public int UpdateShip(ShipDto upship)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from ship where pay_code=?");
			pstmt.setLong(1, upship.getPay_code());
			rs = pstmt.executeQuery();
			if(rs.next()){
			
			pstmt = conn.prepareStatement("update ship set shipnum=?, shipcheck=? where pay_code=?");
			pstmt.setString(1, upship.getShipnum());
			pstmt.setString(2, upship.getShipcheck());
			pstmt.setLong(3, upship.getPay_code());
			
			pstmt.executeUpdate();
			
			x = 1;
			}else{x = 0;}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}
		}
		return x;
	}
	
	
	
	
	public ShipDto getShipinfo(long pay_code)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShipDto info = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from ship where pay_code=?");
			pstmt.setLong(1, pay_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				info = new ShipDto();
				
				info.setMem_num(rs.getInt("mem_num"));
				info.setGet_name(rs.getString("get_name"));
				info.setGet_phone(rs.getString("get_phone"));
				info.setGet_addrs1(rs.getString("get_addrs1"));
				info.setGet_addrs2(rs.getString("get_addrs2"));
				info.setGet_postnum(rs.getString("get_postnum"));
				info.setGet_memo(rs.getString("get_memo"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}	
		}
		return info;
	}
	
	
	
	
	
	
	public void insertShip(ShipDto ship) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql="";
		
		try{
			sql = "insert into ship(pay_code, mem_num, get_name, get_phone, get_postnum, get_addrs1, get_addrs2, get_memo)";
			sql += "values(?,?,?,?,?,?,?,?)";
			
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setLong(1, ship.getPay_code());
			pstmt.setInt(2, ship.getMem_num());
			pstmt.setString(3, ship.getGet_name());
			pstmt.setString(4, ship.getGet_phone());
			pstmt.setString(5, ship.getGet_postnum());
			pstmt.setString(6, ship.getGet_addrs1());
			pstmt.setString(7, ship.getGet_addrs2());
			pstmt.setString(8, ship.getGet_memo());
			
			pstmt.executeUpdate();
			
		}
		catch(Exception e){e.printStackTrace();
		}
		finally{
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
			if(conn != null)try{conn.close();}catch(SQLException e){}

		}
	}
}
