package review.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.review.reviewDao;

public class ReviewSearchAction implements SuperAction {

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		String saw = "";
		try{
			
			String item = (String)request.getParameter("item");
			String search = (String)request.getParameter("search");
			String Pnum = request.getParameter("Pnum");
			
			System.out.println(item);
			System.out.println(search);
			
			
			if(Pnum == null){
				Pnum = "1";
			}
			
			int Psize = 10;
			
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)* Psize +1;
			int End = thisPage * Psize;
			int Count = 0;
			int number = 0;
			List searchlist = null;
			
			reviewDao serdao = reviewDao.getInstance();
			Count = serdao.searchCount(item,search);
		
			
			if(Count >0){
			searchlist = serdao.doSearch(item,search,Start,End);
			}
		
			
			number = Count-( thisPage -1 )* Psize;
			
		
			request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("Start", new Integer(Start));
			request.setAttribute("End",new Integer(End));
			request.setAttribute("thisPage", new Integer(thisPage));
			request.setAttribute("Count", new Integer(Count));
			
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("item", item);
			request.setAttribute("search", search);
			
			
	
			
			
			
			saw = "/review/reviewSearch.jsp";
		}catch(Exception e){
			
		}
		return saw;
	}

}
