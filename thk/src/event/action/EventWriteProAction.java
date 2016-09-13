package event.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thk.mvc.SuperAction;
import thk.event.eventDao;
import thk.event.eventDto;

import java.io.File;
import java.sql.Timestamp;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EventWriteProAction implements SuperAction{
	
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	
		String view = "";
	try{
		try{
		request.setCharacterEncoding("euc-kr");
		}catch(UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String path = request.getRealPath("save");
		int maxSize= 1024*1024*5; // 파일 최대 용량. 5메가
		String enc = "euc-kr"; // 파일 이름 인코딩처리
		
		DefaultFileRenamePolicy de= new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, enc, de);
		
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		String upname=multi.getFilesystemName("img");
		String onname=multi.getOriginalFileName("img");
		String imgtype=multi.getContentType("img");
		
		String [] t = imgtype.split("/");  
			if(!t[0].equals("image")){
			File imgFile =multi.getFile("img");
			imgFile.delete();
			}
			
			eventDto article = new eventDto();
			article.setSubject(multi.getParameter("subject"));
			article.setContent(multi.getParameter("content"));
			article.setImg(upname);
			article.setReg(new Timestamp(System.currentTimeMillis()) );
			
			eventDao edao = eventDao.getInstance();
			edao.insertEvent(article);
			
		//	response.sendRedirect("eventList.jsp");
		
		
			view="/event/eventWritePro.jsp";

	}catch(Exception e){
	}
		return view;
	}
}
