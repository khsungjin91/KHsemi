package ship.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.ship.ShipDao;

public class OwnerShipListAction implements SuperAction {

	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String saw = "";
		
		try{
			
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");  
			
			String Pnum = request.getParameter("Pnum");
			
			LogonDBBean mem = LogonDBBean.getInstance();
			
			int mem_num = mem.getMemberNum(id);

			
			
			if(Pnum == null){
				Pnum = "1";
			}
		
			int Psize = 10;
			
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)* Psize +1;
			int End = thisPage * Psize;
			int shipCount = 0;
			int number = 0;
			List shiplist = null;
			
			
			
			ShipDao shipdao = ShipDao.getInstance();
			
			shipCount = shipdao.shipCount();
		
		
			
			if(shipCount >0){
				shiplist = shipdao.getShipList(Start, End);
			}
			
			
			
			number = shipCount-( thisPage -1 )* Psize;
			
			request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("Start", new Integer(Start));
			request.setAttribute("End",new Integer(End));
			request.setAttribute("thisPage", new Integer(thisPage));
			request.setAttribute("shipCount", new Integer(shipCount));
			
			request.setAttribute("shiplist", shiplist);
			request.setAttribute("mem_num", mem_num);
			
			
			
			saw = "/product/ownerShipList.jsp";
		
		}catch(Exception e){
			
		}
		return saw;
		
	
	

	}

}