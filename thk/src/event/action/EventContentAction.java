package event.action;

import java.net.StandardSocketOptions;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Synthesizer;

import thk.event.eventDao;
import thk.event.eventDto;
import thk.mvc.SuperAction;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventContentAction implements SuperAction{

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String view = "";
		
	try{
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		eventDao dbPro = eventDao.getInstance();
		eventDto article = dbPro.getArticle(num);
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		
	    request.setAttribute("article", article);
	    
		
		view="/event/eventContent.jsp";

	}catch(Exception e){
	}
	return view;
}
}