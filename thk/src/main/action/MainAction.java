package main.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.payment.PaymentDao;
import thk.product.ProductDBBean;
import thk.review.reviewDao;

public class MainAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw="";
		
		try
		{
			String Pnum = request.getParameter("Pnum");
			
			
			if(Pnum==null)
			{
				Pnum="1";
			}
			int Psize = 12;
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)*Psize + 1;
			int End = thisPage*Psize;
			int Count = 0;
			int accCount = 0;
			int apealCount=0;
			int number = 0;
			int Best = 0;
			
			ProductDBBean pdao = ProductDBBean.getInstance();
			PaymentDao best = PaymentDao.getInstance();
			
			
			
			List foot_list = null;
			List acc_list = null;
			List apeal_list = null;
			
			
			Count = pdao.getCount();
			
			if(Count > 0){
				foot_list = pdao.getMain1(Start, End);
			}
			
			
			accCount = pdao.getaccCount();
			
			if(accCount > 0){
				acc_list = pdao.getMain2(Start, End);
			}
			
			
			apealCount = pdao.getapealCount();
			
			if(apealCount > 0){
				apeal_list = pdao.getMain3(Start, End);
			}
			
			
			
			
			number = Count-(thisPage - 1)* Psize;
			
		
			request.setAttribute("thisPage", new Integer(thisPage));
	        request.setAttribute("Start", new Integer(Start));
	        request.setAttribute("End", new Integer(End));
	        request.setAttribute("Count", new Integer(Count));
	        request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			
			request.setAttribute("foot_list", foot_list);
			request.setAttribute("acc_list", acc_list);
			request.setAttribute("apeal_list", apeal_list);
			
			
			saw = "/main/main.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
