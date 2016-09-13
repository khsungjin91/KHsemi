package thk.admin;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.mvc.SuperAction;

public class memberListAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String saw = "";
		try{
			
		
			
			String Pnum = request.getParameter("Pnum");
			
			if(Pnum == null){
				Pnum = "1";
			}
			int Psize = 10;
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1) * Psize + 1;
			int End = thisPage * Psize;
			int Count = 0;
			int number = 0;
			
			List memlist = null;
			
			MemberDBBean db = MemberDBBean.getInstance();
			Count = db.getCount();
			System.out.println(Count);
			
			if(Count >0)
				memlist= db.getList(Start, End);
			
			number = Count-( thisPage -1 )* Psize;
			System.out.println(number);
			
		
		    request.setAttribute("thisPage", new Integer(thisPage));
	        request.setAttribute("Start", new Integer(Start));
	        request.setAttribute("End", new Integer(End));
	        request.setAttribute("Count", new Integer(Count));
	        request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("memlist", memlist);
			
			
			
			saw = "/admin/memberList.jsp";
			
			
		}catch(Exception e){
			
		}
		return saw;
		
	}

}