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
	
		*��ۺ� (�Ϲ��ù� ���Ǻι���) : 		
			<c:choose>
				<c:when test="${code.p_price>=50000}">
					������<br />
					*���� : ${code.p_price} ��	<br />	
					*���ΰ� : ${code.p_price_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${code.p_price_dc} ��	<br />
					<c:set var="addShip" value="${code.p_price_dc+0}"/>
				</c:when>
					
				<c:otherwise>
					2500�� <br />
					*���� : ${code.p_price} ��	<br />	
					*���ΰ� : ${code.p_price_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${code.p_price_dc+2500} ��	<br />
					<c:set var="addShip" value="${code.p_price_dc+2500}"/>		
				</c:otherwise>
			</c:choose>	
			<hr />
			
			//////////////////////
			
						<c:set var="code" value="${code}"/>		
				��ǰ��  ${code.p_name} <br />
				������ ${param.size} <br />
				������ ${code.p_point} <br />
				�ǸŰ� ${code.p_price_dc} <br />
				
			<c:set var="cart" value="${cart}"/>
				����
				<c:choose>
					<c:when test="${cart.amount != null}">
						${cart.amount}��	<br />
						 <input type="hidden" name="p_amount" value="${cart.p_amount}">
					</c:when>
					<c:otherwise>
						1��	<br />
						<input type="hidden" name="p_amount" value="1"/>
					</c:otherwise> 
				</c:choose>
				
				��ۺ� (�Ϲ��ù� ���Ǻι���) : 		
				<c:choose>
					<c:when test="${code.p_price>=50000}">
						������<br />
						�հ� : ${code.p_price_dc} ��	<hr />
						��ǰ�հ�ݾ� : ${code.p_price_dc-param.usepoint} ��	<br />
						<c:set var="addShip" value="${code.p_price_dc+0}"/>
					</c:when>
					<c:otherwise>
						2500�� <br />
						�հ� : ${code.p_price_dc+2500} �� <hr />
						��ǰ�հ�ݾ� : ${code.p_price_dc-param.usepoint} ��	<br />		
						<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
					</c:otherwise>
				</c:choose>	
				<hr /><hr />
</body>
</html>