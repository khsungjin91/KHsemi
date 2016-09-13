package productAdmin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class ProductAdminAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw ="";
		try{
			saw = "/productAdmin/productAdmin.jsp";
		}catch(Exception e){}
		return saw;
	
	}
}
