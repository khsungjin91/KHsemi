package pay.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class Kspay_wh_rcvAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try{ request.setCharacterEncoding("euc-kr"); }
		catch(UnsupportedEncodingException ex){ ex.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			saw = "/pay/kspay_wh_rcv.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
