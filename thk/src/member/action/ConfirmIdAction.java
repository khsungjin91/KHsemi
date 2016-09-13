package member.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;

public class ConfirmIdAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			String mem_id = request.getParameter("mem_id");
			LogonDBBean db = LogonDBBean.getInstance();
			int check = db.confirmId(mem_id);
			
			request.setAttribute("memId", mem_id);
			request.setAttribute("check", check);
			
			saw = "/member/confirmId.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
