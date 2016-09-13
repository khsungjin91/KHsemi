package review.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.review.reviewDao;

public class ReviewListAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			
			HttpSession session = request.getSession();
			
			String id = (String) session.getAttribute("memId");
			
			String Pnum = request.getParameter("Pnum");
			
			
			
			if(Pnum == null){
				Pnum = "1";
			}
			
			int Psize = 12;
			
			
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)* Psize +1;
			int End = thisPage * Psize;
			int Count = 0;
			int number = 0;
			List relist = null;
			
			
			
			reviewDao redao = reviewDao.getInstance();
			Count = redao.reCount();
			
			
			int mem_num = redao.FindNum(id);
			
			
			int possreview = redao.pcodeCount(mem_num);
			
			
		
			if(Count >0){
				relist = redao.getReviewList(Start, End);
			}
		
			
			number = Count-( thisPage -1 )* Psize;
			
			
			
			request.setAttribute("id", id);
			request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("Start", new Integer(Start));
			request.setAttribute("End",new Integer(End));
			request.setAttribute("thisPage", new Integer(thisPage));
			request.setAttribute("Count", new Integer(Count));
			
			request.setAttribute("relist", relist);
			request.setAttribute("mem_num", new Integer(mem_num));
			request.setAttribute("possreview", possreview);
			
			
			saw ="/review/reviewList.jsp";
			
		}catch(Exception e){
			
		}
			return saw;
		}

	}
