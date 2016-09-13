package event.action;

import java.util.List;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import thk.mvc.SuperAction;


public class EventDeleteFormAction implements SuperAction{

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum= request.getParameter("pageNum");
		
		
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
	   

		
		String view="/event/eventDeleteForm.jsp";
		
		
		return view;
	}
}