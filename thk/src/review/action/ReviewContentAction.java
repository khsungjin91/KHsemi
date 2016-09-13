package review.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.review.reviewDao;
import thk.review.reviewDto;

public class ReviewContentAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
			int num =Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			
			
			reviewDao redao = reviewDao.getInstance();
			reviewDto re = redao.getReview(num);
			
			
			request.setAttribute("re", re);		
			request.setAttribute("num", num);
			request.setAttribute("id", id);
			
			saw = "/review/reviewContent.jsp";		
		}catch(Exception e){
			
		}
		return saw;
		
		}
	}
