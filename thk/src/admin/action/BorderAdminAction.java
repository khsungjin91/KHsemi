package admin.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.border.proBDao;
import thk.mvc.SuperAction;

public class BorderAdminAction implements SuperAction{
	
		public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			String saw = "";
			try{
				
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
				
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
				
				List list = null;
				
				proBDao listdao = proBDao.getInstance();
				Count = listdao.getCount();
				if(Count >0)
					list= listdao.getList(Start, End);
				
				number = Count-( thisPage -1 )* Psize;
				
			
			    request.setAttribute("thisPage", new Integer(thisPage));
		        request.setAttribute("Start", new Integer(Start));
		        request.setAttribute("End", new Integer(End));
		        request.setAttribute("Count", new Integer(Count));
		        request.setAttribute("Psize", new Integer(Psize));
				request.setAttribute("number", new Integer(number));
				request.setAttribute("list", list);
				request.setAttribute("id", id);
				
				
				saw = "/admin/borderAdmin.jsp";
				
				
			}catch(Exception e){
				
			}
			return saw;
			
		}

	}

