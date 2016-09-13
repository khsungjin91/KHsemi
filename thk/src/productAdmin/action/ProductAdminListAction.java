package productAdmin.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.ProductDao;
import thk.mvc.SuperAction;

public class ProductAdminListAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		String saw = "";
		try{
	
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) { 
			pageNum = "1";
			
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		
		ProductDao dbPro = ProductDao.getInstance();
		count = dbPro.getArticleCount();
		
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow);
		} 

		number = count -(currentPage - 1 ) * pageSize; 
		
		
	
		
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("articleList", articleList);
		
		request.setAttribute("pageNum", pageNum);

		
		saw= "/productAdmin/productAdminList.jsp";
		
		} catch(Exception e){
		}
		return saw;
		}

}
