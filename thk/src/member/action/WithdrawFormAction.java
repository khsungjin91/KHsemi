package member.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;

public class WithdrawFormAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try
		{
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");
			
			saw = "/member/withdrawForm.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}