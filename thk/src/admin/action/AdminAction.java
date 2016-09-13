package admin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class AdminAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String saw="";
		
		try
		{
			saw = "/admin/admin.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
