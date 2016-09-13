package payment.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.payment.PaymentDao;
import thk.payment.PaymentDto;
import thk.mvc.SuperAction;

public class MemSearchAction implements SuperAction {
	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try { 
			
			String item = (String)request.getParameter("item");
			String search = (String)request.getParameter("search");
			String pageNum = request.getParameter("pageNum");
		
			if (pageNum == null) {
				pageNum = "1";
				
			}
			int pageSize= 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage -1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			
			List articleList = null;
			
			PaymentDao dbPro = PaymentDao.getInstance();
			count = dbPro.searchCount(item,search);
			
			if (count > 0 ) { 
				articleList = dbPro.memSearch(item,search);
			}

			number = count - (currentPage - 1 ) * pageSize;
			
		
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("starRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("pageSize", new Integer(pageSize));
			
			
			request.setAttribute("articleList", articleList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("item", item);
			request.setAttribute("search", search);
			
			
			
			saw = "/payment/memSearch.jsp";
			
		}catch(Exception e) {}
		return saw;
		}

	
}