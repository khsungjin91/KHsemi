package border.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.border.proBDto;
import thk.mvc.SuperAction;

public class BorderFixAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try{
			
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			proBDao fixdao = proBDao.getInstance();
			proBDto fixdto = fixdao.getFix(num);
			
			
			request.setAttribute("Pnum", new Integer(Pnum));
			request.setAttribute("fixdto", fixdto);
			
			
			
			saw = "/border/borderFix.jsp";
			
		}catch(Exception e){
			
		}
		
		return saw;
		
		}

		
	}