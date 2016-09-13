package thk.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
