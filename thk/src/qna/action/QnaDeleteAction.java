package qna.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;

public class QnaDeleteAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw="";
		try{
			
			int num = Integer.parseInt(request.getParameter("num"));
			
			
			request.setAttribute("num", num);
			
			
			saw="/qna/qnaDelete.jsp";
			
		}catch(Exception e){
					
		}
		return saw;
	}
	

}
