package member.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;
import thk.review.reviewDao;

public class MemberInfoAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw="";
		
		try
		{
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
			LogonDBBean persner = LogonDBBean.getInstance();
			reviewDao mnum =  reviewDao.getInstance();
			
			int mem_num = mnum.FindNum(id);
			
			int pay = persner.PayAllPrice(mem_num);
			int ccount =  persner.PersnerCartCount(mem_num);
			int product = persner.PaiedAllProduct(mem_num);
			LogonDataBean info = persner.getMember(id);
			
		
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			
			String indate = date.format(info.getReg_date());
		
			
			request.setAttribute("allpay", pay);
			request.setAttribute("cartcount", ccount);
			request.setAttribute("allproduct", product);
			request.setAttribute("info", info);	
			request.setAttribute("indate", indate);
			request.setAttribute("id", id);
			
			
			saw = "/member/memberInfo.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}
