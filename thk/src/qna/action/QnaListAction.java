package qna.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;

public class QnaListAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			

			String Pnum = request.getParameter("Pnum");
			
			
			
			if(Pnum == null){
				Pnum = "1";
			}
			
			
			
			int Psize = 10;
			
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)* Psize +1;
			int End = thisPage * Psize;
			int Count = 0;
			int number = 0;
			List list = null;
			
			
			
			qnaDao countdao = qnaDao.getInstance();
			Count = countdao.getCount();
		
		
			
			if(Count >0){
				list = countdao.getQnaList(Start, End);
			}
			
			
			
			number = Count-( thisPage -1 )* Psize;
			
			
			request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("Start", new Integer(Start));
			request.setAttribute("End",new Integer(End));
			request.setAttribute("thisPage", new Integer(thisPage));
			request.setAttribute("Count", new Integer(Count));
			
			request.setAttribute("qnalist", list);
			
			
			saw = "/qna/qnaList.jsp";
			
			
			
		}catch(Exception e){
			
		}
			return saw;
		}
		
	}
