package main.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;

public class LoginProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw="";
		
		try
		{
			String mem_id = request.getParameter("mem_id");
			String mem_pw = request.getParameter("mem_pw");

			LogonDBBean manager = LogonDBBean.getInstance();
			int check= manager.userCheck(mem_id,mem_pw);
			
			
			if(check==1){
				HttpSession session = request.getSession();
				session.setAttribute("memId",mem_id);
				request.setAttribute("check", check);
				
				saw = "/main/loginPro.jsp";
				
			}else{
				request.setAttribute("check", check);
				saw = "/main/loginPro.jsp";
			}
		}
		catch(Exception e){}
		return saw;
	}
}