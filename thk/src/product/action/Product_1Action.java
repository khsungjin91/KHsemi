package product.action;

import java.io.UnsupportedEncodingException;
import java.net.StandardSocketOptions;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Synthesizer;

import thk.mvc.SuperAction;
import thk.payment.PaymentDao;
import thk.product.ProductDBBean;
import thk.product.ProductDataBean;
import thk.product.ProductImgDao;
import thk.product.ProductImgDto;
import thk.review.reviewDao;

public class Product_1Action implements SuperAction {
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try{
			request.setCharacterEncoding("euc-kr");
		}catch(UnsupportedEncodingException ex){
			ex.printStackTrace();
		}
		
		String saw = "";
		try{			
			
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("memId");
			
			
			ProductDBBean article = ProductDBBean.getInstance();
			
			String p_code = request.getParameter("p_code");
			
			String Pnum = request.getParameter("Pnum");
			
			if(Pnum == null){
				Pnum = "1";
			}
			
			int Psize = 10;
			
			int thisPage = Integer.parseInt(Pnum);
			int Start = (thisPage - 1)* Psize +1;
			int End = thisPage * Psize;
			int Count = 0;
			int Best = 0;
			
			PaymentDao best = PaymentDao.getInstance();
			reviewDao p_dao = reviewDao.getInstance();
			
			Best = best.BestCount(p_code);
			ProductDataBean code = article.getArticle(p_code);
			
			List p_reple = p_dao.ProductReple(p_code, Start, End);
			int replecount = p_dao.repleCount(p_code);
		
			
			int mem_num = p_dao.FindNum(id);
			
			int compare = p_dao.CompareCode(mem_num, p_code);

			ProductImgDao img = ProductImgDao.getInstance();
			ProductImgDto imgdto = img.getImage(p_code);
			
			
			request.setAttribute("Psize", new Integer(Psize));
			request.setAttribute("Start", new Integer(Start));
			request.setAttribute("End",new Integer(End));
			request.setAttribute("thisPage", new Integer(thisPage));
			request.setAttribute("Count", new Integer(Count));
			
			request.setAttribute("p_code", p_code);
			request.setAttribute("replecount", new Integer(replecount));
			request.setAttribute("p_reple", p_reple);
			request.setAttribute("code", code);
			request.setAttribute("compare", compare);
			request.setAttribute("id", id);
			request.setAttribute("mem_num", mem_num);
			request.setAttribute("imgdto", imgdto);
			request.setAttribute("Best", Best);
			
			
			saw = "/product/product_1.jsp";
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
			return saw;
		}
		
	}
