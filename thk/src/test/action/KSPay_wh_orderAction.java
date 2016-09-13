package test.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class KSPay_wh_orderAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		
		try
		{
			String p_code = request.getParameter("p_code");
			String p_name = request.getParameter("p_name");
			String p_price = request.getParameter("payPrice");
			String mem_name = request.getParameter("mem_name");
			String mem_email= request.getParameter("mem_email");
			String mem_phone = request.getParameter("sndMobile");
			String p_point = request.getParameter("p_point");
			String size = request.getParameter("size");

			saw = "/test/kspay_wh_order.jsp";
		}
		catch(Exception e) {}
		
		return saw;
	}

}
