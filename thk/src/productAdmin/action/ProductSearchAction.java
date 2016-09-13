package productAdmin.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.ProductDao;
import thk.mvc.SuperAction;

public class ProductSearchAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String saw = "";
		try{
	    
		String item = (String)request.getParameter("item");
		String search = (String)request.getParameter("search");	
		String pageNum = request.getParameter("pageNum");
		
		
		System.out.println(pageNum);
		
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
		
		if(item.equals("p_sale_date")){
			count = dbPro.dateCount(item,search);
			articleList = dbPro.dateSearch(item, search);
		}else{
			count = dbPro.proCount(item,search);
			articleList = dbPro.proSearch(item,search,startRow,endRow);
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
		request.setAttribute("item", item);
		request.setAttribute("search", search);
		
		saw= "/productAdmin/productListSearch.jsp";
		
		} catch(Exception e){
		}
		return saw;
		}

}
