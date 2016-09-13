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

public class ReviewWriteProAction implements SuperAction {
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
			
			    String uri = multi.getParameter("uri");
			    
			    ProductDBBean name = ProductDBBean.getInstance();
			   
			    String p_code = multi.getParameter("p_code");
			    
			    String p_name = name.getName(p_code); 
			    
			    
		
			reviewDto redto = new reviewDto();
			
			
			redto.setMem_num(Integer.parseInt(multi.getParameter("mem_num")));
			redto.setMem_id(multi.getParameter("mem_id"));
			redto.setP_code(multi.getParameter("p_code"));
			redto.setImg(sname);
			redto.setP_name(p_name);
			redto.setSubject(multi.getParameter("subject"));
			redto.setContent(multi.getParameter("content"));
			redto.setStar(multi.getParameter("star"));
			redto.setReg_date(new Timestamp(System.currentTimeMillis()));
			
			reviewDao redao = reviewDao.getInstance();
			redao.ReWrite(redto);
					
			request.setAttribute("p_code", p_code);
	
		
			if(uri != null){
			saw = "/review/reviewWritePro.jsp";
			
			}else{
			
			request.setAttribute("p_code", p_code);
			
			saw = "/review/productReview.jsp";
			}
		}catch(Exception e){
			
		}
		return saw;
	}


}
