package review.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.review.reviewDao;

public class ReviewDeleteProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			
			int num = Integer.parseInt(request.getParameter("num"));
			String url = request.getParameter("url");
			String p_code = request.getParameter("p_code");
			
			reviewDao deldao = reviewDao.getInstance();
			
			int check = deldao.doDelete(num);
			
			
			request.setAttribute("p_code", p_code);
			request.setAttribute("check", check);
			request.setAttribute("url", url);
			
			
			saw = "/review/reviewDeletePro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}
	
	

}
