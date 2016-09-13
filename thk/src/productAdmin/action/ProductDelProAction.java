package productAdmin.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.ProductDao;
import thk.mvc.SuperAction;

public class ProductDelProAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		String saw = "";
		try{
			
			String p_code = request.getParameter("p_code");
			String pageNum = request.getParameter("pageNum");
			
			ProductDao deldao = ProductDao.getInstance();
			int check = deldao.deleteArticle(p_code);
			
			System.out.println(check);
			request.setAttribute("p_code", p_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", new Integer(check));
			
			
			
		saw = "/productAdmin/productDelPro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}
	

}
