package product.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

public class PayFormAction implements SuperAction {
	
		public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
			try{
				request.setCharacterEncoding("euc-kr");
			}catch(UnsupportedEncodingException ex){
				ex.printStackTrace();}
			String saw="";
			try{
				ProductDBBean paydao = ProductDBBean.getInstance();
				CartDBBean goods = CartDBBean.getInstance();
				LogonDBBean log_db = LogonDBBean.getInstance();
				HttpSession session = request.getSession();
				
				String mem_id = (String)session.getAttribute("memId");
				int mem_num = log_db.getMemberNum(mem_id);
				String p_code = request.getParameter("p_code");
				String size = request.getParameter("p_size");

						
				ProductDataBean code = paydao.getArticle(p_code);
				LogonDataBean num = paydao.getMember(mem_id);
				List cart = goods.getArticle(mem_num);
							
				
				request.setAttribute("code", code);
				request.setAttribute("cart", cart);
				request.setAttribute("num", num);
				request.setAttribute("size", size);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
				Date dTime = new Date();
				TimeZone tz = TimeZone.getTimeZone("Asia/Seoul"); 
				String curTime = sdf.format(dTime);
				
				String pay_code = curTime+mem_num;
				System.out.println(pay_code);
				request.setAttribute("pay_code", pay_code);
				
				saw = "/product/payForm.jsp";
				
			}catch(Exception e){
					e.printStackTrace();
			}
			return saw;
		}
}
