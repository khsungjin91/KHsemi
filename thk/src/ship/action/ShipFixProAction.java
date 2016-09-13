package ship.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;
import thk.ship.ShipDao;

public class ShipFixProAction implements SuperAction{
	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String saw = "";
		try{
			
		
			
			
			
			saw = "/product/ownerShipList.jsp";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return saw;
				
	}
}
