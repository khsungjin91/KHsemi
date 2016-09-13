<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<title>주문하기</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<body onload="init()">

		<script type="text/javascript">	
		
		function usechange(el){
		
			var struse =$(el).val();
	
				if(struse == null || struse.length == 0){
					$(el).parent().next().text(0);
					return;
				}else if(isNaN(struse)){
					return;
				}
				
				var su = parseInt(struse);
				var price = parseInt($(el).parent().prev().text());
				var mitotal = price-su;		
				
				$(el).parent().next().text(mitotal);
	
				setTotalPrice();
		}
		</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<div id="section">

	<h3>주문상세내역</h3><hr />
	
	<form action="/thk/payForm.cp" method="post" name="userinput" onSubmit="return checkIt();">	
	
<c:set var="code" value="${code}"/>
	
		

<c:choose>
	<c:when test="${url != null}">
		<b>상품정보</b> <br />
		<input type="hidden" name="url" value="${url}">
		*상품명 : ${code.p_name} <br />
		*선택사이즈 : ${param.p_size} <br />
		*적립금 : ${code.p_point} 원 <br />
		*판매가 : ${code.p_price} 원 <br />
		*수량 :	${p_amount} 개 <br/>
		
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
				<input type="hidden" value="${sum_p}" name="sum_p" >
				<input type="hidden" value="${sum_p_dc}" name="sum_p_dc" >
				<input type="hidden" value="${sum_p_p}" name="sum_p_p" >
		
		<c:forEach var="cart" items="${cart}">
				
				<b>상품정보</b> <br />
				*상품명 : ${cart.p_name} <br />
				*선택사이즈 : ${cart.p_size} <br />
				*적립금 : ${cart.p_point} 원 <br />
				*판매가 : ${cart.p_price} 원 <br />
				*수량  :	${cart.p_amount} 개<br />
				*가격  :	${cart.p_price}	원<br />
				*할인가 : ${cart.p_price_dc} 원<br/>
				<hr />
		</c:forEach>
			
			<c:choose>
			
				
			<c:when test="${sum_p >=50000}">
					무료배송<br />
					*가격 : ${sum_p} 원	<br />	
					*할인가 : ${sum_p_dc} 원<br />	
					*상품합계금액 : ${sum_p_dc} 원	<br />
					<c:set var="addShip" value="${sum_p_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*배송비 : 2500원 <br />
					*가격 : ${sum_p} 원	<br />	
					*할인가 : ${sum_p_dc} 원<br />	
					*상품합계금액 : ${sum_p_dc+2500} 원	<br />
					<c:set var="addShip" value="${sum_p_dc+2500}"/>	
					
					*받으실적립금 : ${sum_p_p} 원
					
			</c:otherwise>
		</c:choose>	
			
		
		
	</c:otherwise>	
</c:choose>	
	
				<hr />
	

	<c:set var="num" value="${num}"/>

		<input type="hidden" name="mem_id" value="${num.mem_id}">
		<input type="hidden" name="mem_name" value="${num.mem_name}">
		<input type="hidden" name="mem_postnum" value="${num.mem_postnum}">
		<input type="hidden" name="mem_addrs1" value="${num.mem_addrs1}">
		<input type="hidden" name="mem_addrs2" value="${num.mem_addrs2}">
		<input type="hidden" name="mem_phone" value="${num.mem_phone}">
		<input type="hidden" name="mem_num" value="${mem_num}">
		<input type="hidden" name="mem_point" value="${num.mem_point}">
				
		
	<h3>주문서작성</h3><hr />
		<div>
		<b>주문자정보</b><br />
			*주문하시는 분 ${num.mem_name}<br />
			*주소 ${num.mem_addrs1} ${num.mem_addrs2}<br />
			*폰번호${num.mem_phone}<br />
			*이메일${num.mem_email}<br />
			</div><hr />
			
			

		<input type="hidden" name="p_code" value="${code.p_code}" />
		<input type="hidden" name="p_name" value="${code.p_name}" />
		<input type="hidden" name="p_thumbnail" value="${code.p_thumbnail}" />
		<input type="hidden" name="p_price" value="${code.p_price}" />
		<input type="hidden" name="p_price_dc" value="${code.p_price_dc}" />
		<input type="hidden" name="p_point" value="${code.p_point}" />
		<input type="hidden" name="p_size" value="${param.p_size}"/>
		<input type="hidden" name="p_amount" value=""${param.p_amount}>	
		
		<div>
		<b>배송정보</b><br />
			*받으실 분 <input type="text" name="shipName" value="${num.mem_name}"> <br />
			*받으실 곳 <input type="text" name="shipPostnum" value="${num.mem_postnum}" id="sample6_postcode">
					<input type="button" onclick="sample6_execDaumPostcode()"value="우편번호검색"> <br />
					<input type="text" name="shipAddrs1" value="${num.mem_addrs1}" id="sample6_address"> <br />
					<input type="text" name="shipAddrs2" value="${num.mem_addrs2}" id="sample6_address2"> <br />
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
			function sample6_execDaumPostcode() {
				new daum.Postcode({
            		oncomplete: function(data) {
                	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                		// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                		// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                		var fullAddr = ''; // 최종 주소 변수
                		var extraAddr = ''; // 조합형 주소 변수

                		// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                		if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    		fullAddr = data.roadAddress;

                		} else { // 사용자가 지번 주소를 선택했을 경우(J)
                    		fullAddr = data.jibunAddress;
                		}

                		// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                		if(data.userSelectedType === 'R'){
                    		//법정동명이 있을 경우 추가한다.
                    		if(data.bname !== ''){
                        		extraAddr += data.bname;
                    		}
                    		// 건물명이 있을 경우 추가한다.
                    		if(data.buildingName !== ''){
                        		extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    		}
                    		// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    		fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                		}

               			// 우편번호와 주소 정보를 해당 필드에 넣는다.
                		document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                		document.getElementById('sample6_address').value = fullAddr;

                		// 커서를 상세주소 필드로 이동한다.
                		document.getElementById('sample6_address2').focus();
            		}
				}).open();
			}
			
		</script>		
					
					
					
			*핸드폰번호 <input type="text" name="shipPhone" value="${num.mem_phone}"> <br />
			*남기실 말씀 <input type="text" name="shipMemo" maxlength="100"> <br />			
			</div><hr />
	
	
		<div>
		<b>결제금액</b></div>
			합계금액<div id= "price">${addShip}</div>
			<div>적립금 사용 : <input type="text" name="usepoint" value="0" onkeyup="usechange(event.target);">원
				 &nbsp;(보유 적립금 ${num.mem_point}원)</div>
			결제금액<div id="total-price">${addShip}</div><hr />
			
		<div>
		== 고객님은 안전거래를 위하여 현금 등으로 결제시 저희 쇼핑몰에서 가입한 이니시스의 구매안전 (에스크로) 서비스를 이용하실 수 있습니다. ==
		</div><hr />
		
		
		<div>
		<b>결제수단</b> <br />
			*일반결제 에스크로결제
			<input type="radio" name=sndPaymethod value="1000000000">신용카드
				<input type="radio" name=sndPaymethod value="0100000000">가상계좌
				<input type="radio" name=sndPaymethod value="0010000000">계좌이체<br />
			(무통장입금의 경우 입금확인 후부터 배송단계가 진행됩니다.)<br />
		</div>

		<div>
		<input type="submit" name="submit" value="결제하기"> 
		<input type="button" value="취소하기" onClick="javascript:window.location='main.cp'">
		</div>
	</form>

</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>

	<script type="text/javascript">
		function checkIt(){
	
			if(!document.userinput.getName.value){
				document.userinput.getName.focus();
				alert("받으실 분의 성함을 입력해주세요");
				return false;
			}
			if(!document.userinput.getPostnum.value){
				alert("우편번호를 입력해주세요");
				return false;
			}
			if(!document.userinput.getAddrs1.value){
				document.userinput.getAddrs1.focus();
				alert("주소를 입력해주세요");
				return false;
			}
			if(!document.userinput.getAddrs2.value){
				document.userinput.getAddrs2.focus();
				alert("상세주소를 입력해주세요");
				return false;
			}
			if(!document.userinput.getPhone.value){
				document.userinput.getPhone.focus();
				alert("연락처를 입력해주세요");
				return false;
			}
		}
	</script>

</html>


