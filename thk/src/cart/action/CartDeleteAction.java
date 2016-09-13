package cart.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.cart.CartDBBean;

public class CartDeleteAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try { request.setCharacterEncoding("euc-kr"); }
		catch(UnsupportedEncodingException ex) { ex.printStackTrace(); }
		
		String saw = "";
		try{
			
			CartDBBean cart_db = CartDBBean.getInstance();
			LogonDBBean log_db = LogonDBBean.getInstance();
			HttpSession session = request.getSession();
			
			String mem_id = (String)session.getAttribute("memId");
			String code = request.getParameter("pcode");
			String size = request.getParameter("psize");
			int num = log_db.getMemberNum(mem_id);
			
			cart_db.deleteCart(num,code,size);
			
			

			saw = "/cart/cartDelete.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
		}
	}