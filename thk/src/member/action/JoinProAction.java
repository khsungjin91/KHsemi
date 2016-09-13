package member.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;

public class JoinProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			LogonDataBean data = new LogonDataBean();
			
			data.setMem_id(request.getParameter("mem_id"));
			data.setMem_pw(request.getParameter("mem_pw"));
			data.setMem_name(request.getParameter("mem_name"));
			data.setMem_phone(request.getParameter("mem_phone"));
			data.setMem_sms_yn(request.getParameter("mem_sms_yn"));
			data.setMem_email(request.getParameter("mem_email"));
			data.setMem_email_yn(request.getParameter("mem_email_yn"));
			data.setMem_postnum(request.getParameter("mem_postnum"));
			data.setMem_addrs1(request.getParameter("mem_addrs1"));
			data.setMem_addrs2(request.getParameter("mem_addrs2"));
			data.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			LogonDBBean db = LogonDBBean.getInstance();
			db.insertMember(data);
			
			//response.sendRedirect("loginForm.cp");
			
			saw = "/member/joinPro.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
