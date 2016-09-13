<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 30px}
table {
	margin: 0 auto;
	border: 1px solid gray;
}

#section table tr {border: 1px solid gray;}
#section tr td{ border: 1px solid gray; text-align: center; height: 28px;}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>	

<div id="section">

	<div style="text-align: center; font-size: 20px;"><b>�� �� �� ȸ</b></div>
	
		<div> ${id}����  �ֹ� �����Դϴ�.</div>

<c:if test="${perCount == 0}">
�ֹ��Ͻ� ��ǰ�� �����ϴ�.
</c:if>


<c:if test="${perCount != 0 }">
<table>

<tr>
<td>�ֹ��ڵ�</td>
<td>��ǰ�ڵ�</td>
<td>��ǰ�̸�</td>
<td>��ǰ������</td>
<td>��ǰ����</td>
<td>��ǰ����</td>
<td>��������Ʈ</td>
<td>�������</td>
<td>�ֹ�����</td>
</tr>


<c:forEach var="persner" items="${persner}">

<tr>
<td>${persner.pay_code}</td>
<td>${persner.p_code}</td>
<td>${persner.p_name}</td>
<td>${persner.p_size}mm</td>
<td>${persner.pay_amount}��</td>
<td>
<fmt:formatNumber value="${persner.pay_price}" pattern="#,##0"/>��</td>
<td>${persner.p_point} point</td>
<td>
<c:if test="${persner.pay_howpay == 0100000000}">
		�������
</c:if>

<c:if test="${persner.pay_howpay == 1000000000}">
		�ſ�ī��
</c:if>	
</td>
<td>${persner.reg_date}</td>
</tr>

</c:forEach>

</table>
</c:if>

</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>