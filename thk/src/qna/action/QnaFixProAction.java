package qna.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;
import thk.qna.qnaDto;

public class QnaFixProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			try {
				request.setCharacterEncoding("euc-kr");
			} catch (UnsupportedEncodingException e1) {
			
				e1.printStackTrace();
			}
			
		int num = Integer.parseInt(request.getParameter("num"));

			qnaDao fixdao = qnaDao.getInstance();
			qnaDto fixdto = new qnaDto();
			
			
			fixdto.setNum(Integer.parseInt(request.getParameter("num")));
			fixdto.setSubject(request.getParameter("subject"));
			fixdto.setContent(request.getParameter("content"));
			fixdto.setEmail(request.getParameter("email"));
			fixdto.setPhone(request.getParameter("phone"));
			
			
			int result = fixdao.updateFix(fixdto);
			
			
			request.setAttribute("result", result);
			
			
			saw="/qna/qnaFixPro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}
	
}
