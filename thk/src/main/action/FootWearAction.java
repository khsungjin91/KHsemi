package main.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.product.ProductDBBean;

public class FootWearAction implements SuperAction{
	
public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw="";
		
		try
		{
			String Pnum = request.getParameter("Pnum");
			String p_category1 = request.getParameter("p_category1");
			String p_category2 = request.getParameter("p_category2");
			
	
			
			if(Pnum==null)
			{
				Pnum="1";
			}
			int Psize = 24;
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)*Psize + 1;
			int End = thisPage*Psize;
			//int CtgCount = 0;
			//int Cate2Count = 0;
			
			int Count = 0;
			int number = 0;
			
			ProductDBBean pdao = ProductDBBean.getInstance();
			
			
			List footlist = null;
			
			Count = pdao.getCount(p_category1);
			
			if(Count > 0){
				footlist = pdao.getFootList(p_category1,Start,End);
			}

				
			Count = pdao.Ca2Count(p_category2);
			
			if(Count > 0){
				footlist = pdao.getCategory2(p_category2, Start, End);			
			}
			

			
			
			number = Count-(thisPage - 1)* Psize;
			
			
		
			request.setAttribute("thisPage", new Integer(thisPage));
	        request.setAttribute("Start", new Integer(Start));
	        request.setAttribute("End", new Integer(End));
	        request.setAttribute("Count", new Integer(Count));
	        request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("footlist", footlist);
			
			saw = "/main/footwear.jsp";
		}
		catch(Exception e){}
		return saw;
	}

}

