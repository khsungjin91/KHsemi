package cart.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.cart.CartDBBean;
import thk.cart.CartDataBean;

public class InputCartAction implements SuperAction {

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try { request.setCharacterEncoding("euc-kr"); }
		catch(UnsupportedEncodingException ex) { ex.printStackTrace(); }
				
		String saw = "";
				
		try
		{
			CartDataBean product = new CartDataBean();
			CartDBBean db = CartDBBean.getInstance();
			int amount = 1;
			String pshipping = "0";
			
			int p_price =  Integer.parseInt(request.getParameter("p_price"));
			int p_price_dc = Integer.parseInt(request.getParameter("p_price_dc"));
			int p_point = Integer.parseInt(request.getParameter("p_point"));
			
			
			product.setMem_num(Integer.parseInt(request.getParameter("mem_num")));
			product.setP_code(request.getParameter("p_code"));
			product.setP_name(request.getParameter("p_name"));
			product.setP_size(request.getParameter("p_size"));
			product.setP_img(request.getParameter("p_img"));
			product.setP_price(p_price * amount);
			product.setP_price_dc(p_price_dc * amount);
			product.setP_point(p_point * amount);
			product.setP_amount(amount);
			product.setP_shipping(pshipping);

			db.inputCart(product);
	
				
			saw="/cart/inputCart.jsp";
		}
		catch(Exception e) {}
		return saw;
	}
}
