package review.action;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.review.reviewDao;


public class ReviewWriteAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			int num=0;
			
			int mem_num = Integer.parseInt(request.getParameter("mem_num"));
			
			if(request.getParameter("num") != null) {
			 num = Integer.parseInt(request.getParameter("num"));	
			}
			
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
			reviewDao product = reviewDao.getInstance();
			
			List pcode = product.getPcode(mem_num);  // payment 에서 이용자가 구매한 상품코드 가져온다.
			
			
			int pcodeCount = product.pcodeCount(mem_num);   // p_code 개수
			
		
			
			request.setAttribute("id", id);
			request.setAttribute("num", new Integer(num));
			request.setAttribute("mem_num", mem_num);
			request.setAttribute("pcode", pcode);
			
			saw = "/review/reviewWrite.jsp";
		}catch(Exception e){
			
		}
		return saw;
	}
	

}
