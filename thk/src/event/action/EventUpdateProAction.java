package event.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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



public class EventUpdateProAction implements SuperAction {

	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		
	try{
		try{
		request.setCharacterEncoding("euc-kr");
		}catch(UnsupportedEncodingException e1){ e1.printStackTrace();}
		
		String pageNum = request.getParameter("pageNum");
		
		String path = request.getRealPath("save");
	 	int maxSize = 1024*1024*5;
	 	String enc = "euc-kr";
	 	DefaultFileRenamePolicy de = new DefaultFileRenamePolicy();
	 	
	 	MultipartRequest multi = new MultipartRequest(request, path, maxSize, enc, de);
	 	
	 	String subject = multi.getParameter("subject");
	 	String content = multi.getParameter("content");
	 	
	 	String upname=multi.getFilesystemName("img");
	 	String onnaum=multi.getOriginalFileName("img");
	 	String imgtype=multi.getContentType("img");
	 	
	 	String [] t = imgtype.split("/");
	 		if(!t[0].equals("image")){
	 			File imgFile = multi.getFile("img");
	 			imgFile.delete();
	 		}
	 		eventDto edto = new eventDto();
	 		edto.setSubject(multi.getParameter("subject"));
	 		edto.setContent(multi.getParameter("content"));
	 		edto.setImg(multi.getFilesystemName("img"));
	 		
	 		edto.setNum(Integer.parseInt(multi.getParameter("num")));
	 		
	 		eventDao edao=eventDao.getInstance();
	 		edao.updateArticle(edto);
		
	 		request.setAttribute("pageNum", new Integer(pageNum));
	 		
	
		}catch(Exception e){
		}
		String view="/event/eventUpdatePro.jsp";
		
		return view;
	}
}