package admin.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.admin.MemberDBBean;
import thk.mvc.SuperAction;

public class MemberSearchAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String saw = "";
		try{
			
		
			String item = (String)request.getParameter("item");
			String search = (String)request.getParameter("search");
			String Pnum = request.getParameter("Pnum");
			
			if(Pnum == null){
				Pnum = "1";
			}
			int Psize = 20;
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1) * Psize + 1;
			int End = thisPage * Psize;
			int Count = 0;
			int number = 0;
			
			List list = null;
			
			MemberDBBean listdao = MemberDBBean.getInstance();
			
			if(item.equals("reg_date")){
				Count = listdao.dateCount(item,search);
				list = listdao.dateSearch(item,search);
			}else { 
				Count = listdao.msCount(item,search);
				list = listdao.memSearch(item,search,Start,End);
			}
			number = Count-( thisPage -1 )* Psize;
			
		
		    request.setAttribute("thisPage", new Integer(thisPage));
	        request.setAttribute("Start", new Integer(Start));
	        request.setAttribute("End", new Integer(End));
	        request.setAttribute("Count", new Integer(Count));
	        request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			
			request.setAttribute("list", list);
			request.setAttribute("Pnum", Pnum);
			request.setAttribute("item", item);
			request.setAttribute("search", search);
			
			saw = "/admin/memberSearch.jsp";
			
			
		}catch(Exception e){
			
		}
		return saw;
		
	}

}