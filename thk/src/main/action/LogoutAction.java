package main.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;

public class LogoutAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try{
		 HttpSession session = request.getSession();
		 session.invalidate();
		 saw = "/main/logout.jsp";
		}catch(Exception e){}
		return saw;

  }
}