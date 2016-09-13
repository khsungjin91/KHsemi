package qna.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;
import thk.qna.qnaDto;

public class QnaFixAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw ="";
		
		
		try{
			
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			qnaDao fixdao = qnaDao.getInstance();
			qnaDto fixdto = fixdao.getFix(num);
			
			System.out.println(num);
			request.setAttribute("fixdto", fixdto);
			request.setAttribute("Pnum", Pnum);
			
			saw = "/qna/qnaFix.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}


}
