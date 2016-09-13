package border.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.mvc.SuperAction;

public class BorderDeleteProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try{
			
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			proBDao deletedao = proBDao.getInstance();
			int check = deletedao.getDelete(num);
			
			request.setAttribute("check", new Integer(check));
			
			System.out.println(check);

			saw = "/border/borderDeletePro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
		}
	}