package event.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.event.eventDao;
import thk.event.eventDto;
import thk.mvc.SuperAction;

import java.io.File;
import java.sql.Timestamp;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class EventUpdateFormAction implements SuperAction {

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {	
		String saw="";
	try{
	
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

			eventDao dbPro = eventDao.getInstance();
			eventDto article= dbPro.updateGetArticle(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		request.setAttribute("article", article);
			
	saw="/event/eventUpdateForm.jsp";
	}catch(Exception e){ e.printStackTrace();}
			
		
		
		return saw;
	}
}