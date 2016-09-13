package product.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.cart.CartDBBean;
import thk.cart.CartDataBean;
import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;
import thk.product.ProductDBBean;
import thk.product.ProductDataBean;

public class OrderFormAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	String saw= "";
	
	try{
		int sum_p = 0;
		int sum_p_dc = 0;
		int sum_p_p = 0;
		
		ProductDBBean article = ProductDBBean.getInstance();
		CartDBBean goods = CartDBBean.getInstance();

		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		String p_code = request.getParameter("p_code");
		String size = request.getParameter("p_size");
		String mem_id = request.getParameter("id");
		String url = request.getParameter("url");
		String cartlist = request.getParameter("cart");
		int p_amount = 1; 
		
		
		if(cartlist != null){
		sum_p = Integer.parseInt(request.getParameter("sum_p"));
		sum_p_dc = Integer.parseInt(request.getParameter("sum_p_dc")); 
	    sum_p_p =  Integer.parseInt(request.getParameter("sum_p_p"));
		}else{
			
		}
		
		ProductDataBean code = article.getArticle(p_code);
		
		LogonDataBean num = article.getMember(mem_id);
		
		List cart = goods.getArticle(mem_num);
	
		
		request.setAttribute("code", code);
		request.setAttribute("cart", cart);
		request.setAttribute("num", num);
		request.setAttribute("size", size);
		request.setAttribute("mem_num", mem_num);
		request.setAttribute("url", url);  // 결제경로
		request.setAttribute("p_amount", p_amount);
		request.setAttribute("sum_p", new Integer(sum_p));
		request.setAttribute("sum_p_dc", new Integer(sum_p_dc));
		request.setAttribute("sum_p_p",  new Integer(sum_p_p));
		
		
		saw = "/product/orderForm.jsp";
	}catch(Exception e){
		
	}
	return saw;
	
	}
}
