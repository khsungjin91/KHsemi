package member.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class JoinFormAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			saw = "/member/joinForm.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
