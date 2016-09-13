package event.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;


public class EventWriteAction implements SuperAction{
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	
		String saw="/event/eventWrite.jsp";

		return saw;
	}
}
