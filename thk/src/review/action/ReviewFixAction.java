package review.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.review.reviewDao;
import thk.review.reviewDto;

public class ReviewFixAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			HttpSession session = request.getSession();
			
			String id = (String) session.getAttribute("memId");
			
		
			
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			reviewDao fixdao = reviewDao.getInstance();
			reviewDto fixdto = fixdao.getFix(num);
			
			reviewDao product = reviewDao.getInstance();
			
			int mem_num = product.FindNum(id);
			List pcode = product.getPcode(mem_num);  // payment 에서 이용자가 구매한 상품코드 가져온다.
			
			
			request.setAttribute("num", num);
			request.setAttribute("refix", fixdto);
			request.setAttribute("pcode", pcode);
			
			
			saw = "/review/reviewFix.jsp";
		}catch(Exception e){
		
		}
		return saw;
	}
			

}
