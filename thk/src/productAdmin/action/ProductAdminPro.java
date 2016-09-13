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
import thk.product.ProductImgDao;
import thk.product.ProductImgDto;

public class ProductAdminPro implements SuperAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {

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
			    
			    
			    String p_code2 = multi.getParameter("p_code");
			      String sname3 =  multi.getFilesystemName("img1");
			      String oname3 =  multi.getOriginalFileName("img1");
			      String sname4 =  multi.getFilesystemName("img2");
			      String oname4 =  multi.getOriginalFileName("img2");
			      String sname5 =  multi.getFilesystemName("img3");
			      String oname5 =  multi.getOriginalFileName("img3");
			      String sname6 =  multi.getFilesystemName("img4");
			      String oname6 =  multi.getOriginalFileName("img4");
			      String sname7 =  multi.getFilesystemName("img5");
			      String oname7 =  multi.getOriginalFileName("img5");
			      String type2 = multi.getContentType("img1");
			    
			
			      ProductImgDto img = new ProductImgDto();
			      img.setP_code(multi.getParameter("p_code"));
			      img.setImg1(sname3);
			      img.setImg2(sname4);
			      img.setImg3(sname5);
			      img.setImg4(sname6);
			      img.setImg5(sname7);
			      System.out.println(p_code);
			      System.out.println(sname3);
			      System.out.println(sname4);
			      System.out.println(sname5);
			      System.out.println(sname6);
			      
			      ProductImgDao dao = ProductImgDao.getInstance();
				    
				  dao.insertImg(img);
			      
			    
			    saw= "/productAdmin/productAdminPro.jsp";
		}catch (Exception e){}
			return saw;
		}
		
	}