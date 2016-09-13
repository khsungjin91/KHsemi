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

public class EventDeleteProAction implements SuperAction {

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
	try{
		request.setCharacterEncoding("euc-kr");
		String path = request.getRealPath("save");
		
		int num = Integer.parseInt(request.getParameter("num")); 
		String pageNum = request.getParameter("pageNum"); 
		System.out.println(num);
		eventDao dbPro = eventDao.getInstance();
		eventDto article = dbPro.deleteArticle(num);
		
		File file = new File(path+"//"+article.getImg());
		file.delete();
		
		
		
		
	}catch(Exception e){ e.printStackTrace();}
			
		String view="/event/eventDeletePro.jsp";
		
		
		return view;
	}
	}