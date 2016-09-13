package member.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;

public class FindPwProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw="";
		
		try
		{
			String mem_id = request.getParameter("mem_id");
			String mem_name = request.getParameter("mem_name");
			String mem_email = request.getParameter("mem_email");

			LogonDBBean manager = LogonDBBean.getInstance();
			LogonDataBean mem= manager.pwSearch(mem_name,mem_email,mem_id);
			
			if(mem != null){
				request.setAttribute("mem_id", mem.getMem_id());
				request.setAttribute("mem_name", mem.getMem_name());
				request.setAttribute("mem_pw", mem.getMem_pw());
			}
			saw = "/member/findPwPro.jsp";
				
		}
		catch(Exception e){}
		return saw;
	}
}