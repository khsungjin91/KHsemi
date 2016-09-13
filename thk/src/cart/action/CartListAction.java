package cart.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.review.reviewDao;
import thk.cart.CartDBBean;
import thk.cart.CartDataBean;

public class CartListAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try { request.setCharacterEncoding("euc-kr"); }
		catch(UnsupportedEncodingException ex) { ex.printStackTrace(); }
		
		String saw="";
		
		try
		{
			CartDBBean cart_db = CartDBBean.getInstance();
			LogonDBBean log_db = LogonDBBean.getInstance();
			
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");
			int mem_num = log_db.getMemberNum(mem_id);
			
			String Pnum = request.getParameter("Pnum");
			
			if(Pnum==null)
			{
				Pnum="1";
			}
			int Psize = 10;
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)*Psize + 1;
			int End = thisPage*Psize;
			int Count = 0;
			int number = 0;
			
			List cart_list = null;
			Count = cart_db.getCount(mem_num);
			
			if(Count > 0)
				cart_list = cart_db.getCartList(mem_num, Start, End);

			number = Count-(thisPage - 1)* Psize;
	
		CartDataBean sumdate = cart_db.getSumprice(mem_num);
		
		System.out.println(sumdate.getAll_p_dc_sum());
		System.out.println(sumdate.getAll_p_sum());
		System.out.println(sumdate.getAll_p_p_sum());
			
			request.setAttribute("thisPage", new Integer(thisPage));
	        request.setAttribute("Start", new Integer(Start));
	        request.setAttribute("End", new Integer(End));
	        request.setAttribute("Count", new Integer(Count));
	        request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			
			
			request.setAttribute("cart_list", cart_list);
			request.setAttribute("mem_num", new Integer(mem_num));
			request.setAttribute("id", mem_id);
			request.setAttribute("sumdate", sumdate);
			
			
			
			saw = "/cart/cartList.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
