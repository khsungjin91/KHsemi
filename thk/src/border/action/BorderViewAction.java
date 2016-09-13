package border.action;

import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.border.proBDto;
import thk.mvc.SuperAction;

public class BorderViewAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try{
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			proBDao viewdao = proBDao.getInstance();
			proBDto viewdto = viewdao.getView(num);
			
			request.setAttribute("num", new Integer(num));
			request.setAttribute("Pnum", Pnum);
			request.setAttribute("viewdto", viewdto);
			
			
			saw = "/border/borderView.jsp";
			
		}catch(Exception e){		
		
		}
		return saw;
		}
	}
