package productAdmin.action;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import thk.admin.ProductDao;
import thk.admin.ProductDto;
import thk.mvc.SuperAction;

public class ProductAdminProAction implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		String saw="";
		try{
			      request.setCharacterEncoding("euc-kr"); 

		
			      String path= request.getRealPath("save");  //업로드 폴더 "e://save//"; 
			      int maxSize= 1024*1024*5;   // 5M
			      String enc ="euc-kr";    // 인코딩
			      DefaultFileRenamePolicy de = new DefaultFileRenamePolicy();  // 새이름
			      
			      MultipartRequest multi = new MultipartRequest(request,path,maxSize,enc,de); //파일 중복 방지 덮어씌우기 방지.
			      
			      String p_code = multi.getParameter("p_code");
			      String sname1 =  multi.getFilesystemName("p_thumbnail");
			      String oname1 =  multi.getOriginalFileName("p_thumbnail");
			      String sname2 =  multi.getFilesystemName("p_img");
			      String oname2 =  multi.getOriginalFileName("p_img");
			      String type = multi.getContentType("p_thumbnail");
			      String [] t = type.split("/");
			      if(!t[0].equals("image")){
			    	 File saveFile = multi.getFile("save");
			    	 saveFile.delete();
			 }
			          
				ProductDto member = new ProductDto();
				member.setP_code(multi.getParameter("p_code"));
				member.setP_name(multi.getParameter("p_name"));
				member.setP_material(multi.getParameter("p_material"));
				member.setP_thumbnail(sname1);
				member.setP_img(sname2);
				member.setP_price(Integer.parseInt(multi.getParameter("p_price")));
				member.setP_price_dc(Integer.parseInt(multi.getParameter("p_price_dc")));
				member.setP_point(Integer.parseInt(multi.getParameter("p_point")));
				member.setP_category1(multi.getParameter("p_category1"));
				member.setP_category2(multi.getParameter("p_category2"));
				member.setP_category3(multi.getParameter("p_category3"));
			  
			    member.setP_sale_date(new Timestamp(System.currentTimeMillis()));
			    ProductDao manager = ProductDao.getInstance();
			    manager.insertMember(member);
				 
			    response.sendRedirect("productPro.jsp");
			    
			    saw= "/project/productPro.jsp";
		}catch (Exception e){}
			return saw;
		}
		
	}
