package review.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import thk.mvc.SuperAction;
import thk.product.ProductDBBean;
import thk.review.reviewDao;
import thk.review.reviewDto;

public class ReviewFixPro implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String saw = "";
		try{
			try {
				request.setCharacterEncoding("euc-kr");
			} catch (UnsupportedEncodingException e1) {
			
				e1.printStackTrace();
			}
			
			 String path = request.getRealPath("save"); //업로드할 경로
			    int maxSize = 1024*1024*5; //5M
			    String enc = "euc-kr"; //인코딩
			    DefaultFileRenamePolicy de = new DefaultFileRenamePolicy();
			    MultipartRequest multi = new MultipartRequest(request,path,maxSize,enc,de);
			 
			    String sname = multi.getFilesystemName("save");
			    String oname = multi.getOriginalFileName("save");
			    String type = multi.getContentType("save");
			    
			    
			    String p_code = multi.getParameter("p_code");
			    
			    System.out.println(p_code);
			    
			    ProductDBBean name = ProductDBBean.getInstance(); 
			    
			    String p_name = name.getName(p_code); 
			

			reviewDto refixdto = new reviewDto();
			
			refixdto.setNum(Integer.parseInt(multi.getParameter("num")));
			refixdto.setP_code(multi.getParameter("p_code"));
			refixdto.setP_name(p_name);
			refixdto.setImg(sname);
			refixdto.setSubject(multi.getParameter("subject"));
			refixdto.setContent(multi.getParameter("content"));
			refixdto.setStar(multi.getParameter("star"));
			refixdto.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			
			
			reviewDao redao = reviewDao.getInstance();
			int check = redao.Fixinsert(refixdto);
			
			int num = Integer.parseInt(multi.getParameter("num"));
		
			System.out.println(num);
			System.out.println(check);
			
			request.setAttribute("num", num);
			request.setAttribute("check", check);
			
		
			
			saw = "/review/reviewFixPro.jsp";
			
		}catch(Exception e){
			
		}
		return saw;
		
	}

}
