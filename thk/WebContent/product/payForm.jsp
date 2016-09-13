<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>결제하기</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<script language="javascript">

	function _pay(_frm) 
	{
 		_frm.sndReply.value           = getLocalUrl("kspay_wh_rcv.cp") ;

		var agent = navigator.userAgent;
		var midx		= agent.indexOf("MSIE");
		var out_size	= (midx != -1 && agent.charAt(midx+5) < '7');
    	
		var width_	= 500;
		var height_	= out_size ? 568 : 518;
		var left_	= screen.width;
		var top_	= screen.height;
    	
		left_ = left_/2 - (width_/2);
		top_ = top_/2 - (height_/2);
		
		op = window.open('about:blank','AuthFrmUp',
		        'height='+height_+',width='+width_+',status=yes,scrollbars=no,resizable=no,left='+left_+',top='+top_+'');

		if (op == null)
		{
			alert("팝업이 차단되어 결제를 진행할 수 없습니다.");
			return false;
		}
		
		_frm.target = 'AuthFrmUp';
		_frm.action ='https://kspay.ksnet.to/store/KSPayFlashV1.3/KSPayPWeb.jsp';
		//_frm.action ='http://210.181.28.116/store/KSPayFlashV1.3/KSPayPWeb.jsp';
		
		_frm.submit();
    }

	function getLocalUrl(mypage) 
	{ 
		var myloc = location.href; 
		return myloc.substring(0, myloc.lastIndexOf('/')) + '/' + mypage;
	} 
	
	// goResult() - 함수설명 : 결재완료후 결과값을 지정된 결과페이지(kspay_wh_result.jsp)로 전송합니다.
	function goResult(){
		document.KSPayWeb.target = "";
		document.KSPayWeb.action = "orderEnd.cp";
		document.KSPayWeb.submit();
	}
	// eparamSet() - 함수설명 : 결재완료후 (kspay_wh_rcv.jsp로부터)결과값을 받아 지정된 결과페이지(kspay_wh_result.jsp)로 전송될 form에 세팅합니다.
	function eparamSet(rcid, rctype, rhash){
		document.KSPayWeb.reWHCid.value 	= rcid;
		document.KSPayWeb.reWHCtype.value   = rctype  ;
		document.KSPayWeb.reWHHash.value 	= rhash  ;
	}
	
	
	 function check(id) {
	     var agree = document.getElementById(id);
	     if(agree.checked) { //동의여부 체크
	    	javascript:_pay(document.KSPayWeb)
	     }else {
	       alert("동의하셔야 결제가 진행됩니다");
 	       return false;
	     }
	   }


</script>
<body>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<div id="section">
<h2>결제하기</h2> <hr />
	<form action="/thk/orderEnd.cp" name="KSPayWeb" method="post">
	<c:set var="code" value="${code}"/>
		
		
<c:choose>
	<c:when test="${param.url != null}">
		<b>상품정보</b> <br />
		*상품명 : ${code.p_name} <br />
		*선택사이즈 : ${size}  <br />
		*적립금 : ${code.p_point} 원 <br />
		*판매가 : ${code.p_price} 원 <br />
		*수량 :	1 개 <br/>
		<input type="hidden" name="pay_amount" value="1"/>
		*배송비 (일반택배 조건부무료) : 		
		<c:choose>
			<c:when test="${code.p_price>=50000}">
					무료배송<br />
					*가격 : ${code.p_price} 원	<br />	
					*할인가 : ${code.p_price_dc} 원<br />	
					*상품합계금액 : ${code.p_price_dc} 원	<br />
					<c:set var="addShip" value="${code.p_price_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*배송비 : 2500원 <br />
					*가격 : ${code.p_price} 원	<br />	
					*할인가 : ${code.p_price_dc} 원<br />	
					*상품합계금액 : ${code.p_price_dc+2500} 원	<br />
					<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
					
					*받으실적립금 : ${code.p_point} 원
					
			</c:otherwise>
		</c:choose>	

	</c:when>
		
		
	<c:otherwise>
		<c:forEach var="cart" items="${cart}">
				
				<b>상품정보</b> <br />
				*상품명 : ${cart.p_name} <br />
				*선택사이즈 : ${cart.p_size} <br />
				*적립금 : ${cart.p_point} 원 <br />
				*판매가 : ${cart.p_price} 원 <br />
				*수량  :	${cart.p_amount} 개<br />
				 <input type="hidden" name="pay_amount" value="${cart.p_amount}">
				*가격  :	${cart.p_price}	원<br />
				*할인가 : ${cart.p_price_dc} 원<br/>
				<hr />
		</c:forEach>
			
			<c:choose>
			<c:when test="${param.sum_p >=50000}">
					무료배송<br />
					*가격 : ${param.sum_p} 원	<br />	
					*할인가 : ${param.sum_p_dc} 원<br />	
					*배송비포함합계금액 : ${param.sum_p_dc} 원	<br />
					<c:set var="addShip" value="${param.sum_p_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*배송비 : 2500원 <br />
					*가격 : ${param.sum_p} 원	<br />	
					*할인가 : ${param.sum_p_dc} 원<br />	
					*배송비포함합계금액 : ${param.sum_p_dc+2500} 원	<br />
					<c:set var="addShip" value="${param.sum_p_dc+2500}"/>	
					
					*받으실적립금 : ${param.sum_p_p} 원
					
			</c:otherwise>
		</c:choose>	
			
		
		
	</c:otherwise>	
</c:choose>	

	<c:set var="num" value="${num}"/>
		<h2>주문정보확인</h2><hr />
			<div>
			<h4>주문자정보</h4>
			</div>
				<div>
				주문자 성함 ${num.mem_name} <br />
				주문자 연락처 ${num.mem_phone} <br />
				이메일 ${num.mem_email} <br />
				</div>
			
			<div>
			<h4>배송정보</h4>
			</div>
				<div>
				받는분 성함 ${param.shipName} <br />
				받는분 연락처 ${param.shipPhone} <br />
				우편번호 ${param.shipPostnum} <br />
				주소 ${param.shipAddrs1}&nbsp;${param.shipAddrs2} <br />
				</div>
			
			<div>
			<h4>결제금액</h4>
			</div>
				<div>
				총 주문금액 ${code.p_price_dc} 원<br />
				배송비 : 	
				
				<c:choose>
					<c:when test="${param.url != null}">
					
						<c:choose>
							<c:when test="${code.p_price>=50000}">
							무료배송<br />
							합계 : ${code.p_price_dc} 원	  <br />
					
								<c:set var="addShip" value="${code.p_price_dc+0}"/>
							</c:when>
							<c:otherwise>
							배송비 : 2500원 <br />
							합계 : ${code.p_price_dc+2500} 원   <br />
					
							<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
							</c:otherwise>
						</c:choose>	
							적립금 사용 -${param.usepoint} 원<br />
							결제금액 ${addShip-param.usepoint} 원<br />
						
					</c:when>
						
				<c:otherwise>
				
							<c:choose>
								<c:when test="${param.sum_p >=50000}">
							무료배송<br />
							합계 : ${param.sum_p} 원	  <br />
							
								<c:set var="addShip" value="${param.sum_p_dc+0}"/>
								</c:when>
								<c:otherwise>
							배송비 : 2500원 <br />
							합계 : ${param.sum_p+2500} 원   <br />
				
								<c:set var="addShip" value="${param.sum_p_dc+2500}"/>	
								</c:otherwise>
							</c:choose>	
							적립금 사용 -${param.usepoint} 원<br />
							결제금액 ${addShip-param.usepoint} 원<br />
				
				</c:otherwise>
				
				</c:choose>
				</div>
		
			<div>
			<h4>결제수단</h4>
			</div>
					<c:if test="${param.sndPaymethod==1000000000}">
						<div>
							결제방법 &nbsp;&nbsp; 신용카드<br />
							결제금액 &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} 원
						</div>			
					</c:if>
					

					<c:if test="${param.sndPaymethod==0010000000}">
						<div>
							결제방법 &nbsp;&nbsp; 계좌이체<br />
							결제금액 &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} 원
						</div>			
					</c:if>


					<c:if test="${param.sndPaymethod==0100000000}">
						<div>
							결제방법 &nbsp;&nbsp; 가상계좌<br />
							결제금액 &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} 원
						</div>			
					</c:if>
						<div><h4>구매내용확인</h4>	</div>
	<div>
	<input type="checkbox" id="agree"/>&nbsp;
	구매하실 상품의 상품정보 및 가격을 확인하였으며, 이에 동의합니다. (전자상거래법 제 8조 2항)
		</div>
	<div>
		<input type="button" value="결 제" onClick="check('agree')">	
		<input type="button" value="취소하기" onClick="history.go(-1)">	
	</div>

<!----------------------------------------------- <Part 2. 추가설정항목(메뉴얼참조)>  ----------------------------------------------->

	<!-- 0. 공통 환경설정 -->
	<input type=hidden	name=sndReply value="">
	<input type=hidden  name=sndGoodType value="1"> 	<!-- 상품유형: 실물(1),디지털(2) -->
	
	<!-- 1. 신용카드 관련설정 -->
	
	<!-- 신용카드 결제방법  -->
	<!-- 일반적인 업체의 경우 ISP,안심결제만 사용하면 되며 다른 결제방법 추가시에는 사전에 협의이후 적용바랍니다 -->
	<input type=hidden  name=sndShowcard value="I,M"> <!-- I(ISP), M(안심결제), N(일반승인:구인증방식), A(해외카드), W(해외안심)-->
	
	<!-- 신용카드(해외카드) 통화코드: 해외카드결제시 달러결제를 사용할경우 변경 -->
	<input type=hidden	name=sndCurrencytype value="WON"> <!-- 원화(WON), 달러(USD) -->
	
	<!-- 할부개월수 선택범위 -->
	<!--상점에서 적용할 할부개월수를 세팅합니다. 여기서 세팅하신 값은 결제창에서 고객이 스크롤하여 선택하게 됩니다 -->
	<!--아래의 예의경우 고객은 0~12개월의 할부거래를 선택할수있게 됩니다. -->
	<input type=hidden	name=sndInstallmenttype value="ALL(0:2:3:4:5:6:7:8:9:10:11:12)">
	
	<!-- 가맹점부담 무이자할부설정 -->
	<!-- 카드사 무이자행사만 이용하실경우  또는 무이자 할부를 적용하지 않는 업체는  "NONE"로 세팅  -->
	<!-- 예 : 전체카드사 및 전체 할부에대해서 무이자 적용할 때는 value="ALL" / 무이자 미적용할 때는 value="NONE" -->
	<!-- 예 : 전체카드사 3,4,5,6개월 무이자 적용할 때는 value="ALL(3:4:5:6)" -->
	<!-- 예 : 삼성카드(카드사코드:04) 2,3개월 무이자 적용할 때는 value="04(3:4:5:6)"-->
	<!-- <input type=hidden	name=sndInteresttype value="10(02:03),05(06)"> -->
	<input type=hidden	name=sndInteresttype value="NONE">

	<!-- 2. 온라인입금(가상계좌) 관련설정 -->
	<input type=hidden	name=sndEscrow value="1"> 			<!-- 에스크로사용여부 (0:사용안함, 1:사용) -->
	
	<!-- 3. 월드패스카드 관련설정 -->
	<input type=hidden	name=sndWptype value="1">  			<!--선/후불카드구분 (1:선불카드, 2:후불카드, 3:모든카드) -->
	<input type=hidden	name=sndAdulttype value="1">  		<!--성인확인여부 (0:성인확인불필요, 1:성인확인필요) -->
	
	<!-- 4. 계좌이체 현금영수증발급여부 설정 -->
    <input type=hidden  name=sndCashReceipt value="0">          <!--계좌이체시 현금영수증 발급여부 (0: 발급안함, 1:발급) -->

	<!-- 5. 상품권, 게임문화상품권 관련 설정 -->
	<input type=hidden  name=sndMembId value="userid"> <!-- 가맹점사용자ID (문화,게임문화 상품권결제시 필수) -->
	
<!----------------------------------------------- <Part 3. 승인응답 결과데이터>  ----------------------------------------------->
<!-- 결과데이타: 승인이후 자동으로 채워집니다. (*변수명을 변경하지 마세요) -->

	<input type=hidden name=reWHCid 	value="">
	<input type=hidden name=reWHCtype 	value="">
	<input type=hidden name=reWHHash 	value="">
<!--------------------------------------------------------------------------------------------------------------------------->

<!--업체에서 추가하고자하는 임의의 파라미터를 입력하면 됩니다.-->
<!--이 파라메터들은 지정된결과 페이지(kspay_result.cp)로 전송됩니다.-->
	<input type="hidden" name="sndStoreid" value="2999199999">
	<input type="hidden" name="sndOrdernumber" value="${pay_code}">
	<input type="hidden" name="sndGoodname" value="${code.p_name}"> 
	<input type="hidden" name="sndAmount" value="${code.p_price_dc-param.usepoint}">
	<input type="hidden" name="sndOrdername" value="${num.mem_name}">
	<input type="hidden" name="sndEmail" value="${num.mem_email}">
	<input type="hidden" name="sndMobile" value="${num.mem_phone}">
	<input type="hidden" name="sndServicePeriod"  value="YYYY년MM월DD일~YYYY년MM월DD일"> <!-- 실제 배송상품이아닌 컨텐츠상품시 제공기간표시 -->
	<input type="hidden" name="sndPaymethod" value="${param.sndPaymethod}">
<!--------------------------------------------------------------------------------------------------------------------------->
	<input type="hidden" name="mem_id" value="${memId}" />
	<input type="hidden" name="p_price_dc" value="${code.p_price_dc}"/>
	<input type="hidden" name="p_name" value="${code.p_name}">
	<input type="hidden" name="p_point" value="${code.p_point}" />
	<input type="hidden" name="p_code" value="${code.p_code}">
	<input type="hidden" name="p_size" value="${param.p_size}"/>
	<input type="hidden" name="shipName" value="${param.shipName}"/>
	<input type="hidden" name="shipPhone" value="${param.shipPhone}"/>
	<input type="hidden" name="shipPostnum" value="${param.shipPostnum}"/>
	<input type="hidden" name="shipAddrs1" value="${param.shipAddrs1}"/>
	<input type="hidden" name="shipAddrs2" value="${param.shipAddrs2}"/>
	<input type="hidden" name="shipMemo" value="${param.shipMemo}"/>
	<input type="hidden" name="usepoint" value="${param.usepoint}" />	
	<input type="hidden" name="p_code" value="${code.p_code}"/>					
	<input type="hidden" name="mem_num" value="${num.mem_num}" />
<!--------------------------------------------------------------------------------------------------------------------------->

</form>

</body>
</html>

