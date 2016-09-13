<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<hr />
	
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
					2500원 <br />
					*가격 : ${code.p_price} 원	<br />	
					*할인가 : ${code.p_price_dc} 원<br />	
					*상품합계금액 : ${code.p_price_dc+2500} 원	<br />
					<c:set var="addShip" value="${code.p_price_dc+2500}"/>		
				</c:otherwise>
			</c:choose>	
			<hr />
			
			//////////////////////
			
						<c:set var="code" value="${code}"/>		
				상품명  ${code.p_name} <br />
				사이즈 ${param.size} <br />
				적립금 ${code.p_point} <br />
				판매가 ${code.p_price_dc} <br />
				
			<c:set var="cart" value="${cart}"/>
				수량
				<c:choose>
					<c:when test="${cart.amount != null}">
						${cart.amount}개	<br />
						 <input type="hidden" name="p_amount" value="${cart.p_amount}">
					</c:when>
					<c:otherwise>
						1개	<br />
						<input type="hidden" name="p_amount" value="1"/>
					</c:otherwise> 
				</c:choose>
				
				배송비 (일반택배 조건부무료) : 		
				<c:choose>
					<c:when test="${code.p_price>=50000}">
						무료배송<br />
						합계 : ${code.p_price_dc} 원	<hr />
						상품합계금액 : ${code.p_price_dc-param.usepoint} 원	<br />
						<c:set var="addShip" value="${code.p_price_dc+0}"/>
					</c:when>
					<c:otherwise>
						2500원 <br />
						합계 : ${code.p_price_dc+2500} 원 <hr />
						상품합계금액 : ${code.p_price_dc-param.usepoint} 원	<br />		
						<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
					</c:otherwise>
				</c:choose>	
				<hr /><hr />
</body>
</html>