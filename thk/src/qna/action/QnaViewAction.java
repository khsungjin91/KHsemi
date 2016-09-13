package qna.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;
import thk.qna.qnaDto;
import thk.reple.RepleDao;
import thk.reple.RepleDto;

public class QnaViewAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try{
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			String retime = "";
			RepleDto repledto = null;
			
			
			int num = Integer.parseInt(request.getParameter("num"));
			String Pnum = request.getParameter("Pnum");
			
			
			qnaDao qnaview = qnaDao.getInstance();
			RepleDao reple = RepleDao.getInstance();
			
			qnaDto qnadto = qnaview.getWrite(num);
			int replecount = reple.repleCount(num);
			
			if(replecount > 0 ){
					repledto = reple.getReple(num);
					
					SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd");
					retime = time.format(repledto.getReg_date());
			}
			
			request.setAttribute("id", id);
			request.setAttribute("num", num);
			request.setAttribute("Pnum", Pnum);
			request.setAttribute("qnadto", qnadto);
			request.setAttribute("retime", retime);
			request.setAttribute("repledto", repledto);
			request.setAttribute("replecount", replecount);
			
			
			
			
		saw = "/qna/qnaView.jsp";	
		}catch(Exception e){
			
		}
		return saw;
	}

}
