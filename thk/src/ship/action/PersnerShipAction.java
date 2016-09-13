package ship.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.ship.ShipDao;
import thk.ship.ShipDto;

public class PersnerShipAction implements SuperAction{

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String saw = "";
		try{
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
			LogonDBBean ship = LogonDBBean.getInstance();
			
			int mem_num = ship.getMemberNum(id);
			
			ShipDao getship = ShipDao.getInstance();
			
			List pership = getship.PersnerShipinfo(mem_num);
				
			int perCount = getship.persnerShipCount(mem_num);
			
		
			
			request.setAttribute("pership", pership);
			request.setAttribute("perCount", perCount);
			
			
			saw = "/ship/persnerShip.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}
	
	

}
