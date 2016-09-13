package event.action;

import java.util.Collections;
import java.net.StandardSocketOptions;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Synthesizer;

import thk.event.eventDao;
import thk.event.eventDto;
import thk.mvc.SuperAction;
import java.text.SimpleDateFormat;
import java.io.File;
import java.sql.Timestamp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventListAction implements SuperAction{

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String view = "";
		int pageSize = 10;
		
	try{
			
		String pageNum = request.getParameter("pageNum"); 
		if (pageNum == null){ pageNum = "1"; } 
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1)*pageSize+1; 
		
		int endRow = currentPage * pageSize; 
		int count = 0; 
		int number = 0; 
		
		List articleList = null;
		
		eventDao evnDao = eventDao.getInstance();
		count = evnDao.getArticleCount();
		
		if(count > 0){ 
			articleList = evnDao.getArticles(startRow, endRow);
		}else{
			articleList= Collections.EMPTY_LIST;
		}
		number= count-(currentPage-1)*pageSize;
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow",new Integer(endRow));
		request.setAttribute("currentPage", new Integer(currentPage));

		
		request.setAttribute("articleList", articleList);
		
		view="/event/eventList.jsp";
		
		
	}catch(Exception e){
			
		}
		return view;
	}
}