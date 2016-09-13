package cart.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.cart.CartDBBean;
import thk.cart.CartDataBean;

public class CartModifyAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try { request.setCharacterEncoding("euc-kr"); }
		catch(UnsupportedEncodingException ex) { ex.printStackTrace(); }
		
		String saw = "";
		
		try
		{
			LogonDBBean log_db = LogonDBBean.getInstance();
			CartDBBean cart_db = CartDBBean.getInstance();
			
			HttpSession session = request.getSession();
			
			String mem_id = (String)session.getAttribute("memId");
			int mem_num = log_db.getMemberNum(mem_id);
			int amount =  Integer.parseInt(request.getParameter("amount"));
			String code = request.getParameter("p_code");
			String p_size = request.getParameter("p_size");
			
			CartDataBean price = cart_db.getPrice(code);
			
			int p_price = price.getP_price() * amount;
			int p_price_dc = price.getP_price_dc() * amount;
			int p_point = price.getP_point() * amount;
			
			
			int check = cart_db.modifyCart(amount, mem_num, code, p_price, p_price_dc, p_point, p_size);
			
			saw = "/cart/cartModify.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
