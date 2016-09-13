package border.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.border.proBDao;
import thk.border.proBDto;
import thk.mvc.SuperAction;

public class BorderWriteProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		String saw = "";
		try{
			proBDto writedto = new proBDto();
			
			writedto.setNum(Integer.parseInt(request.getParameter("num")));
			writedto.setWriter(request.getParameter("writer"));
			writedto.setSubject(request.getParameter("subject"));
			writedto.setContent(request.getParameter("content"));
			writedto.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			
			
			proBDao writedao = proBDao.getInstance();
			
			writedao.insertWrite(writedto);
			

			saw = "/border/borderWritePro.jsp";
		}catch(Exception e){
			
		}
		return saw;
		
	}
	

}