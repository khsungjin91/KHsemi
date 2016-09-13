package pay.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.admin.MemberDBBean;
import thk.logon.LogonDBBean;
import thk.mvc.SuperAction;
import thk.payment.PaymentDao;
import thk.payment.PaymentDto;

public class PersnerPayAction implements SuperAction {

	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String saw = "";
		
		try{
			
			String Pnum = request.getParameter("Pnum");
			
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
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
			
			LogonDBBean fnum = LogonDBBean.getInstance();
			PaymentDao paydao = PaymentDao.getInstance();
			
			
			int mem_num = fnum.getMemberNum(id);
			
			payCount = paydao.persnerCount(mem_num);
			
			List persner = paydao.persnerPay(mem_num, Start, End);
			
			if(payCount >0)
				paylist= paydao.getPay(Start, End);
			
			number = payCount-( thisPage -1 )* Psize;

			 	request.setAttribute("thisPage", new Integer(thisPage));
		        request.setAttribute("Start", new Integer(Start));
		        request.setAttribute("End", new Integer(End));
		        request.setAttribute("payCount", new Integer(payCount));
		        request.setAttribute("Psize", new Integer(Psize));
				request.setAttribute("number", new Integer(number));
				
				request.setAttribute("persner", persner);
				request.setAttribute("id", id);
				
			
			saw = "/pay/persnerPay.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}

}
