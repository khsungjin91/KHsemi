package product.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thk.admin.MemberDBBean;
import thk.logon.LogonDBBean;
import thk.logon.LogonDataBean;
import thk.mvc.SuperAction;
import thk.payment.PaymentDao;
import thk.payment.PaymentDto;
import thk.product.ProductDBBean;
import thk.ship.ShipDao;
import thk.ship.ShipDto;

public class OrderEndAction implements SuperAction{


	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try{request.setCharacterEncoding("euc-kr");			
		}catch(UnsupportedEncodingException ex){	ex.printStackTrace();	}

		String saw = "";
		
		try
		{
			String rcid		= request.getParameter("reWHCid"		);
			String rctype	= request.getParameter("reWHCtype"		);
			String rhash	= request.getParameter("reWHHash"			);

			String	authyn =  "";
			String	trno   =  "";
			String	trddt  =  "";
			String	trdtm  =  "";
			String	amt    =  "";
			String	authno =  "";
			String	msg1   =  "";
			String	msg2   =  "";
			String	ordno  =  "";
			String	isscd  =  "";
			String	aqucd  =  "";
			String	temp_v =  "";
			String	result =  "";
			String	halbu  =  "";
			String	cbtrno =  "";
			String	cbauthno =  "";

			String	resultcd =  "";

			thk.pay.KSPayWebHostBean ipg = new thk.pay.KSPayWebHostBean(rcid);
			if (ipg.kspay_send_msg("1"))		//KSNET 결제결과 중 아래에 나타나지 않은 항목이 필요한 경우 Null 대신 필요한 항목명을 설정할 수 있습니다.
			{
				authyn	 = ipg.kspay_get_value("authyn");
				trno	 = ipg.kspay_get_value("trno"  );
				trddt	 = ipg.kspay_get_value("trddt" );
				trdtm	 = ipg.kspay_get_value("trdtm" );
				amt		 = ipg.kspay_get_value("amt"   );
				authno	 = ipg.kspay_get_value("authno");
				msg1	 = ipg.kspay_get_value("msg1"  );
				msg2	 = ipg.kspay_get_value("msg2"  );
				ordno	 = ipg.kspay_get_value("ordno" );
				isscd	 = ipg.kspay_get_value("isscd" );
				aqucd	 = ipg.kspay_get_value("aqucd" );
				temp_v	 = "";
				result	 = ipg.kspay_get_value("result");
				halbu	 = ipg.kspay_get_value("halbu");
				cbtrno	 = ipg.kspay_get_value("cbtrno");
				cbauthno	 = ipg.kspay_get_value("cbauthno");

				request.setAttribute("trddt", trddt);
				request.setAttribute("amt", amt);
				request.setAttribute("authno", authno);
				request.setAttribute("isscd", isscd);
				
				if (null != authyn && 1 == authyn.length())
				{
					if (authyn.equals("O"))
					{
						resultcd = "0000";
					}else
					{
						resultcd = authno.trim();
					}

					//ipg.kspay_send_msg("3");		// 정상처리가 완료되었을 경우 호출합니다.(이 과정이 없으면 일시적으로 kspay_send_msg("1")을 호출하여 거래내역 조회가 가능합니다.)
				}
			}

			//////////////////////////////////////////////////////////////
			//payment DB에 insert하기	
			LogonDBBean log_db = LogonDBBean.getInstance();
			String pay_code = request.getParameter("sndOrdernumber");	
			long paycode = Long.parseLong(pay_code);
			HttpSession session = request.getSession();
			String mem_id = (String)session.getAttribute("memId");	
			int mem_num = log_db.getMemberNum(mem_id);
			String p_code = request.getParameter("p_code");
			String p_size = request.getParameter("p_size");
			int pay_amount = Integer.parseInt(request.getParameter("pay_amount")); //null
			int pay_price = Integer.parseInt(request.getParameter("p_price_dc")); //null
			int p_point = Integer.parseInt(request.getParameter("p_point")); //null
			String sndPaymethod = request.getParameter("sndPaymethod");
			Timestamp time = new Timestamp(System.currentTimeMillis());	
			String p_name= request.getParameter("p_name");
			
	
				PaymentDto pay = new PaymentDto();
				pay.setPay_code(paycode);
				pay.setMem_num(mem_num);
				pay.setP_code(p_code);
				pay.setP_size(p_size);
				pay.setPay_amount(pay_amount);
				pay.setPay_price(pay_price);
				pay.setP_point(p_point);
				pay.setPay_howpay(sndPaymethod);
				pay.setReg_date(time);
				pay.setP_name(p_name);
	
				PaymentDao payDao = PaymentDao.getInstance();
				payDao.insertPayment(pay);				
	
			//ship DB에 인서트하기	
			long shipPay_code = Long.parseLong(request.getParameter("sndOrdernumber"));
			int shipMem_num = log_db.getMemberNum(mem_id);
			String get_name = request.getParameter("shipName");
			String get_phone = request.getParameter("shipPhone");
			String get_postnum = request.getParameter("shipPostnum");
			String get_addrs1 = request.getParameter("shipAddrs1");
			String get_addrs2 = request.getParameter("shipAddrs2");
			String get_memo = request.getParameter("shipMemo");
			String use_point = request.getParameter("usepoint");
			int usepoint = Integer.parseInt(use_point);
		
				ShipDto ship = new ShipDto();
				ship.setPay_code(shipPay_code);
				ship.setMem_num(shipMem_num);
				ship.setGet_name(get_name);
				ship.setGet_phone(get_phone);
				ship.setGet_postnum(get_postnum);
				ship.setGet_addrs1(get_addrs1);
				ship.setGet_addrs2(get_addrs2);
				ship.setGet_memo(get_memo);
			
				ShipDao sDao = ShipDao.getInstance();			
				sDao.insertShip(ship);
				
				
				
				
			//주문날짜 출력하기
			Date date = new Date();
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
			String strdate = simpleDate.format(date);
			
			request.setAttribute("strdate", strdate);
			
			log_db.usePoint(mem_num,usepoint);
			log_db.inputPoint(mem_num, p_point);
			
			int mypoint = log_db.getPoint(mem_num);
			System.out.println(mypoint);
			
			
			LogonDBBean persner = LogonDBBean.getInstance();
			
			int allpay = persner.PayAllPrice(mem_num);
			
			MemberDBBean rate = MemberDBBean.getInstance();
			
			String one = "짚신";
			String two = "고무신";
			String three = "운동화";
			String four = "꽃신";
			
			
			
			if(allpay >= 100000 && allpay < 300000 ){
			
				rate.insertMemrate(one,shipMem_num,allpay);
			}else if(allpay >= 300000 && allpay < 500000){
		
				rate.insertMemrate(two,shipMem_num,allpay);
			}else if(allpay >= 500000 && allpay < 1000000){
			
				rate.insertMemrate(three,shipMem_num,allpay);
			}else if(allpay >= 1000000){
			
				rate.insertMemrate(four,shipMem_num,allpay);
			}
	
			
			
			saw = "/product/orderEnd.jsp";
		}
		catch(Exception e) {}
		return saw;
	}

}
