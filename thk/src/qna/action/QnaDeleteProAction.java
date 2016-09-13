package qna.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.mvc.SuperAction;
import thk.qna.qnaDao;

public class QnaDeleteProAction implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String saw = "";
		try{
			HttpSession session = request.getSession();
			
			
			int num = Integer.parseInt(request.getParameter("num"));

			//�н����� üũ�� ���� id�ҷ� �´�
			String mem_id = (String)session.getAttribute("memId");
			String pw = request.getParameter("pw");
			qnaDao qnadao = qnaDao.getInstance();
			
			int check = qnadao.pwCheck(mem_id,pw);
			int result = qnadao.qnaDelete(num);
			
			request.setAttribute("result", result);
			request.setAttribute("check", check);
			
			
			
			saw = "/qna/qnaDeletePro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
	}

}