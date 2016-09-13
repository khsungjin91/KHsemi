<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>
#section { width: 1300px; margin: 0 auto;  }
#center {margin: 0 auto; text-align: center;} 
#center a {text-decoration: none; font-size: 20px; color:black}
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
	border-radius:6px;
	box-shadow:0 1px 2px #444;
}
.white{
    color: black;
    border: solid 1px #444;
    background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#fff));
}

.small{
	font-size:20px;
	padding:2em 2em 2em

}

</style>
</head>
<body>

<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>

<div id="section">

<div> 주문관리 </div>

<c:if test="${payCount == 0  }">
주문 받은 상품이 없습니다.
</c:if>


<c:if test="${payCount != 0  }">

<table>
<tr>
<td>주문번호</td>
<td>상품코드</td>
<td>상품이름</td>
<td>상품사이즈</td>
<td>주문수량</td>
<td>결제가격</td>
<td>결제방법</td>
<td>결제일자</td>
<td></td>
</tr>

<c:forEach var="paylist" items="${paylist}">
<tr>
<td>${paylist.pay_code}</td>
<td>${paylist.p_code}</td>
<td>${paylist.p_name}</td>
<td>${paylist.p_size}</td>
<td>${paylist.pay_amount}</td>
<td>${paylist.pay_price}</td>
<td>${paylist.pay_howpay}</td>
<td>${paylist.reg_date}</td>
<td>
<a href="/thk/onship.cp?pay_code=${paylist.pay_code}" class="button white small">배송등록</a>
</td>
</tr>
</c:forEach>
</table>

</c:if>

  <c:if test="${Count > 0}">
    	<c:set var="pageCount" value="${Count /Psize + (Count % Psize == 0?0:1)}"/>
   		<c:set var="pageBlock" value="${10}"/>
   		<fmt:parseNumber var="result" value="${thisPage/10}" integerOnly="true"/>
   		<c:set var = "startPage" value = "${result * 10 +1 }"/>
   		<c:set var = "endPage" value="${startPage + pageBlock -1 }"/>
   		
   		
    
    <c:if test="${endPage > pageCount}">
    	<c:set  var= "endPage" value = "${pageCount}"/>
	</c:if>
<div id="center"> 	
	<c:if test="${startPage >10}">
	<a href = "/thk/borderList.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/borderList.cp?Pnum=${i}" >[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/borderList.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
</div>   
    </c:if>



</div>



<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>



</body>
</html>