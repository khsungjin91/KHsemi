package productAdmin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.ProductDao;
import thk.admin.ProductDto;
import thk.mvc.SuperAction;

public class ProductCtAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String saw = "";
		
		try{
			
	
			String p_code = (String)request.getParameter("p_code");
			String pageNum = request.getParameter("pageNum");
			
			
		    ProductDao dbPro = ProductDao.getInstance();
		    ProductDto article = dbPro.updateGetArticle(p_code);
		    
		    System.out.println(p_code);
		    
		    request.setAttribute("p_code", p_code);
		    request.setAttribute("pageNum", pageNum);
		    request.setAttribute("article", article);
		
		    saw = "/productAdmin/productCt.jsp";
		    	
		}catch(Exception e){
			
				e.printStackTrace();
		}return saw;
		
		}

}
