package member.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;
import thk.qna.qnaDao;

public class ModifyFormAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw ="";
		
		try
		{
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");
			String pw =request.getParameter("pw");
			
			LogonDBBean db = LogonDBBean.getInstance();
			LogonDataBean data = db.getMember(mem_id);
			
			qnaDao pwdao  = qnaDao.getInstance();
			
			int check = pwdao.pwCheck(mem_id, pw);
			
			
			request.setAttribute("check", check);
			request.setAttribute("data", data);
			
			saw = "/member/modifyForm.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
