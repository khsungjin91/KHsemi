<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">

#section { width: 1200px; margin: 0 auto;  padding-top: 30px; margin-bottom: 30px}
#section td {text-align: center; height: 30px}

.button{
	display: inline-block;
	zoom: 1;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	text-align: center;
	line-height: 100 %;
	font-family: 'Jeju Gothic', sans-serif;
	text-shadow: 0 1px 1px rgba(0,0,0,.3);
	border-radius:8px;
	box-shadow:0 1px 2px #444;
}
.white{
    color: black;
    border: solid 1px #444;
    background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#fff));
}

.small{
	font-size:13px;
	padding: .5em 2em .275em

}
.under{
 text-decoration: none;
 color:black;
}
</style>
</head>
<body>


<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>	

<div id="section">

	<div style="text-align: center; font-size: 20px;"><b>배 송 조 회</b></div>

<table border="1">
<tr>
<td>주문코드</td>
<td>주문자이름</td>
<td>주문자번호</td>
<td>배송지</td>
<td>배송메모</td>
<td>배송상태</td>
<td>송장번호</td>
</tr>

<c:if test="${perCount == 0}">
<tr>
<td colspan="7">주문하신 상품이 없습니다.</td>
</tr>
</c:if>


<c:if test="${perCount != 0 }">

<c:forEach var="pership" items="${pership}">

<tr>
<td height="40">${pership.pay_code}</td>
<td height="40">${pership.get_name}</td>
<td height="40">${pership.get_phone}</td>
<td height="40">
${pership.get_postnum}<br/>
${pership.get_addrs1}<br/>
${pership.get_addrs2}
</td>
<td height="40">${pership.get_memo}</td>
<td height="40">
<c:if test = "${pership.shipcheck == '배송완료' }">
${pership.shipcheck}
<a href="/thk/reviewList.cp" class="button white small under">리뷰작성하기</a> <br/>
</c:if>

<c:if test = "${pership.shipcheck != '배송완료'}">
${pership.shipcheck}
</c:if>
</td>
<td height="40">${pership.shipnum}</td>
</tr>

</c:forEach>

</c:if>
</table>

</div>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>


</body>
</html>