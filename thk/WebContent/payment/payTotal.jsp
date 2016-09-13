<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>매출 통계</title>
<style type="text/css">
#section {width: 1200px; margin: 0 auto ;text-align: center;}
table {
	margin: 0 auto
}
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
<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>


<div id="section">
  <div align=center>결제 내역(등록 갯수:${count})</div>

<c:if test="${count == 0}">
  <div>결제 내역이 없습니다.</div>
</c:if>

<form action="/thk/paySearch.cp" method="post">
<div>
검색 :<input type="text" name="search">
	<select name="item">
		<option value="pay_code">결제번호</option>
		<option value="mem_num">회원번호</option>
		<option value="p_code">상품코드</option>
		<option value="p_size">사이즈</option>
		<option value="pay_amount">수량</option>
		<option value="pay_price">결제가격</option>
		<option value="p_point">상품포인트</option>
		<option value="pay_howpay">지불방법</option>
		<option value="reg_date">결제일</option>
	</select>
<input type= "submit" value="조회">
</div>
(결제일:yyyy-mm-dd)
</form>


<c:if test="${count > 0 }">
<table border=1 width=1000 cellpadding=0 cellspacing=0>
  <tr>
    <td align=center>결제번호</td>
    <td align=center>회원번호</td>
    <td align=center>상품코드</td>
    <td align=center>사이즈</td>
    <td align=center>수량</td>
    <td align=center>결제가격</td>
    <td align=center>상품포인트</td>
    <td align=center>지불방법</td>
    <td align=center>결제일</td>
  </tr>
  
 <c:forEach var="article" items="${articleList}" >
  <tr>
    <td align=center>${article.pay_code}</td>
    <td align=center>${article.mem_num}</td>
    <td align=center>${article.p_code }</td>
    <td align=center>${article.p_size }</td>
    <td align=center>${article.pay_amount}</td>
    <td align=center>
     <fmt:formatNumber value="${article.pay_price}" pattern="#,##0"/>원</td>
    <td align=center>${article.p_point}</td>
    <td align=center>
    
    <c:if test="${article.pay_howpay == 0100000000}">
		가상계좌
</c:if>

<c:if test="${article.pay_howpay == 1000000000}">
		신용카드
</c:if>	

    </td>
    <td align=center><fmt:formatDate value="${article.reg_date}" pattern="yyyy-MM-dd"/></td>
  </tr>


 </c:forEach>
</table>
</c:if>


<c:if test="${count > 0 }">
  <c:set var="pageCount" value="${count / pageSize+ (count % pageSize == 0 ? 0 :1)}"/>
  <c:set var="pageBlock" value="${10}"/>
  <fmt:parseNumber var="result" value="${currentPage / 10 }" integerOnly="true"/>
  <c:set var="startPage" value="${result * 10 +1 }"/>
  <c:set var="endPage" value="${startPage + pageBlock-1 }"/>
  <c:if test="${endPage > pageCount }">
  <c:set var="endPage" value="${pageCount}"/>
  </c:if>

  <c:if test="${startPage > 10 }">
  <a href="/thk/payTotal.cp?pageNum=${startPage - 10 }">[이전]</a>
  </c:if>

  <c:forEach var="i" begin="${startPage}" end="${endPage}">
    <a href="/thk/payTotal.cp?pageNum=${i}">[${i}]</a>
  </c:forEach>

  <c:if test="${endPage < pageCount }">
    <a href="/thk/payTotal.cp?pageNum=${startPage + 10}">[다음]</a>
  </c:if>
</c:if>
<br/>

<div align=right>
 	<a href="/thk/ypay.cp" name="ypay" class="button white small under">연 매출</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/mpay.cp" name="mpay" class="button white small under">월 매출</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/dpay.cp" name="dpay" class="button white small under">일 매출</a>
  <a href="/thk/mempay.cp" name="mempay" class="button white small under">개인 결제 총액</a>
</div>

</div>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>

