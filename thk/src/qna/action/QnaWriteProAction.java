package qna.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;
import thk.qna.qnaDto;

public class QnaWriteProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
		
			e1.printStackTrace();
		}
		
		String saw="";
		
		try{
			qnaDto insertdto = new qnaDto(); 
		
			String content = request.getParameter("content");
			
	
			
			insertdto.setNum(Integer.parseInt(request.getParameter("num")));
			insertdto.setWriter(request.getParameter("writer"));
			insertdto.setCategory1(request.getParameter("category1"));
			insertdto.setCategory2(request.getParameter("category2"));
			insertdto.setCategory3(request.getParameter("category3"));
			insertdto.setSubject(request.getParameter("subject"));
			insertdto.setSecret(request.getParameter("secret"));
			insertdto.setPw(request.getParameter("pw"));
			insertdto.setContent(content.replace("\r\n","<br>"));
			insertdto.setReg_date(new Timestamp(System.currentTimeMillis()));
			insertdto.setEmail(request.getParameter("email"));
			insertdto.setPhone(request.getParameter("phone"));
			
			
			

			qnaDao insertdao = qnaDao.getInstance();
			insertdao.insertWrite(insertdto);
			
			saw = "/qna/qnaWritePro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
		
	}

}
