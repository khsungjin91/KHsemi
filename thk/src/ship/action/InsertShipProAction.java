package ship.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.ship.ShipDao;
import thk.ship.ShipDto;

public class InsertShipProAction implements SuperAction{
	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
	
		
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String saw = "";
		
		try{
			Long pay_code = Long.parseLong(request.getParameter("pay_code"));
			
			int mem_num = Integer.parseInt(request.getParameter("mem_num"));
			
			
			
			String shipnum = request.getParameter("shipnum");
		
			String shipcheck = request.getParameter("shipcheck");
		
			
			ShipDao update = ShipDao.getInstance();
			
			
			ShipDto updto = new ShipDto();
			
			updto.setPay_code(pay_code);
			updto.setShipnum(shipnum);
			updto.setShipcheck(shipcheck);
			
			int check = update.UpdateShip(updto);

			
			saw = "/ship/insertShipPro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;

	}
}
