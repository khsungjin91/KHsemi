package review.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class ImageViewAction implements SuperAction{
	

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	
		String saw = "";
	try{
		
		String img = request.getParameter("img");
		

		
		request.setAttribute("img", img);
		
		saw="/review/imgView.jsp";
		
	}catch(Exception e){
		
	}
	return saw;

	}
}
