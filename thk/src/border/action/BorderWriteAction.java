package border.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;

public class BorderWriteAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		
		try{
			
			int num=0,ref=1,re_step=0,re_level=0;  
	 
	          if(request.getParameter("num")!=null){
		       num=Integer.parseInt(request.getParameter("num"));
		       ref=Integer.parseInt(request.getParameter("ref"));
		       re_step=Integer.parseInt(request.getParameter("re_step"));
		       re_level=Integer.parseInt(request.getParameter("re_level"));
	
	          }
	          
	          request.setAttribute("num", new Integer(num));
		      request.setAttribute("ref", new Integer(ref));
		      request.setAttribute("re_step", new Integer(re_step));
		      request.setAttribute("re_level", new Integer(re_level));  
	          
	          
			saw = "/border/borderWrite.jsp";
			
		}catch(Exception e){
			
		}
		
		return saw;
	}
	

}