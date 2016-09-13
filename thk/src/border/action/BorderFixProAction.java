package border.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.border.proBDto;
import thk.mvc.SuperAction;

public class BorderFixProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
	
			e1.printStackTrace();
		}
		try{
			proBDto fixdto = new proBDto();
	
			String Pnum =request.getParameter("Pnum");
	
			fixdto.setNum(Integer.parseInt(request.getParameter("num")));
			fixdto.setSubject(request.getParameter("subject"));
			fixdto.setContent(request.getParameter("content"));

	
			proBDao fixdao = proBDao.getInstance();
			int also = fixdao.insertFix(fixdto);
	
			request.setAttribute("Pnum", Pnum);
			request.setAttribute("also", also);
	
	
			saw = "/border/borderFixPro.jsp";
			}catch(Exception e){

			}
		return saw;
	}
}