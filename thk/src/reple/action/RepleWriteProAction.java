package reple.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.reple.RepleDao;
import thk.reple.RepleDto;

public class RepleWriteProAction implements SuperAction{
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String saw = "";
	try{
		
		int num = Integer.parseInt(request.getParameter("qna_num"));
		
		String content = request.getParameter("content");
				
				
		
		RepleDao insert = RepleDao.getInstance();
		
		RepleDto inreple = new RepleDto();
		
		inreple.setWriter(request.getParameter("writer"));
		inreple.setContent(content.replace("\r\n","<br>"));
		inreple.setReg_date(new Timestamp(System.currentTimeMillis()));
		inreple.setQna_num(num);
		
		insert.insertReple(inreple);
		
		request.setAttribute("num", num);
		
		saw = "/reple/repleWritePro.jsp";
		
	}catch(Exception e){
		
	}
	return saw;
	
		
		
	}

}
