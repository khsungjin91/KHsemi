package productAdmin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class ProductDelAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			
			String p_code = request.getParameter("p_code");
			String pageNum = request.getParameter("pageNum");
			
			request.setAttribute("p_code", p_code);
			request.setAttribute("pageNum", pageNum);
			
		saw = "/productAdmin/productDel.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}
	

}
