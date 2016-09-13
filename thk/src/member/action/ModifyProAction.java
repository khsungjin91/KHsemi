package member.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;

public class ModifyProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");
			
			LogonDBBean db = LogonDBBean.getInstance();
			LogonDataBean data = db.getMember(mem_id);
			
			request.setAttribute("data", data);
			
			data.setMem_pw(request.getParameter("mem_pw"));
			data.setMem_phone(request.getParameter("mem_phone"));
			data.setMem_sms_yn(request.getParameter("mem_sms_yn"));
			data.setMem_email(request.getParameter("mem_email"));
			data.setMem_email_yn(request.getParameter("mem_email_yn"));
			data.setMem_postnum(request.getParameter("mem_postnum"));
			data.setMem_addrs1(request.getParameter("mem_addrs1"));
			data.setMem_addrs2(request.getParameter("mem_addrs2"));
			
			db.updateMember(data);
			
			saw = "/member/modifyPro.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
