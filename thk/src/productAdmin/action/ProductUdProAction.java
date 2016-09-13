package productAdmin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.ProductDao;
import thk.admin.ProductDto;
import thk.mvc.SuperAction;

public class ProductUdProAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try {
			request.setCharacterEncoding("euc-kr"); 

		    
			ProductDto article= new ProductDto();
			article.setP_code(request.getParameter("p_code"));
			article.setP_name(request.getParameter("p_name"));	
			article.setP_material(request.getParameter("p_material"));	
			article.setP_price(Integer.parseInt(request.getParameter("p_price")));	
			article.setP_price_dc(Integer.parseInt(request.getParameter("p_price_dc")));			
			article.setP_point(Integer.parseInt(request.getParameter("p_point")));	
			article.setP_category1(request.getParameter("p_category1"));		
			article.setP_category2(request.getParameter("p_category2"));		
			article.setP_category3(request.getParameter("p_category3"));		
		  
		    
		    ProductDao dbPro = ProductDao.getInstance();
		    int check = dbPro.updateArticle(article);
		    
		    
		    
		   
		    request.setAttribute("check", new Integer(check));
		    
		 
		    saw = "/productAdmin/productUdPro.jsp";
		}catch(Exception e){
			
		}
		return saw;
	}
}