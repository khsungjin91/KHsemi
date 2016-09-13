package ship.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.ship.ShipDao;
import thk.ship.ShipDto;

public class InsertShipAction implements SuperAction{
	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String saw = "";
		
		try{
			
			Long pay_code = Long.parseLong(request.getParameter("pay_code"));
			
			
			ShipDao meminfo = ShipDao.getInstance();
			
			ShipDto memdto = meminfo.getShipinfo(pay_code);
			
			
			
			
			request.setAttribute("memdto", memdto);
			request.setAttribute("pay_code", pay_code);
			
			saw = "/ship/insertShip.jsp";
		}catch(Exception e){
			
		}
		return saw;
	}


}
