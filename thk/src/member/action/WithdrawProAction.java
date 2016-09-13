package member.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;

public class WithdrawProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try
		{
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");
			String mem_pw = request.getParameter("mem_pw");
			
			LogonDBBean db = LogonDBBean.getInstance();
			
			int check = db.deleteMember(mem_id, mem_pw);
			
			if(check==1)
			{
				session.invalidate();
			}
			
			saw = "/member/withdrawPro.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
