package test.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class KSPay_wh_resultAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		
		try
		{
			saw = "/test/kspay_wh_result.jsp";
		}
		catch(Exception e) {}
		
		return saw;
	}

}