package pay.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.mvc.SuperAction;
import thk.payment.PaymentDao;

public class OwnerPayListAction implements SuperAction {

		
		public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
			String saw = "";
			
			try{
				
				String Pnum = request.getParameter("Pnum");
				
				if(Pnum == null){
					Pnum = "1";
				}
				int Psize = 15;
				int thisPage = Integer.parseInt(Pnum);
				int Start = (thisPage - 1) * Psize + 1;
				int End = thisPage * Psize;
				int payCount = 0;
				int number = 0;
				
				List paylist = null;
				
				PaymentDao paydao = PaymentDao.getInstance();
				
				payCount = paydao.payCount();
				
				
				if(payCount >0)
					paylist= paydao.getPay(Start, End);
				
				number = payCount-( thisPage -1 )* Psize;

				 	request.setAttribute("thisPage", new Integer(thisPage));
			        request.setAttribute("Start", new Integer(Start));
			        request.setAttribute("End", new Integer(End));
			        request.setAttribute("payCount", new Integer(payCount));
			        request.setAttribute("Psize", new Integer(Psize));
					request.setAttribute("number", new Integer(number));
					
					request.setAttribute("paylist", paylist);
				
				saw = "/pay/ownerPayList.jsp";
				
			}catch(Exception e){
				
			}
			return saw;
			
		
		

		}

	}