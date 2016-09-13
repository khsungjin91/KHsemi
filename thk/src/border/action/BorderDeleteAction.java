package border.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class BorderDeleteAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try{
			
			int num =Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
		
	
			request.setAttribute("num", new Integer(num));
			request.setAttribute("Pnum", new Integer(Pnum));
		
			
			
			saw = "/border/borderDelete.jsp";	
			
		}catch(Exception e){
			
		}
		return saw;	
	}
		
}