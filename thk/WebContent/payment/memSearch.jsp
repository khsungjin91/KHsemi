<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>ȸ�� ���� �Ѿ�</title>

<style type="text/css">
#section {width: 1200px; margin: 0 auto; text-align: center;}
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

  <div align=center>ȸ�� ���� �Ѿ�(��� ����:${count})</div>

<c:if test="${count == 0}">
  <div>ȸ�� ���� �Ѿ��� �����ϴ�.</div>
</c:if>

<form action="/thk/memSearch.cp" method="post">
<div>
	<select name="item">
	  <option value="mem_num">ȸ����ȸ</option>
	</select>
   <input type="text" name="search">
   <input type= "submit" value="�˻�"></div>
</form>

<c:if test="${count > 0 }">
<table border=1 width=700 cellpadding=0 cellspacing=0>
  <tr>
    <td align=center>ȸ��</td>
    <td align=center>���� �Ѿ�</td>
  </tr>
  
 <c:forEach var="article" items="${articleList}" >
  <tr>
    <td align=center>${article.mem_num}</td>
    <td align=center>${article.pay_sum}</td>
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
  <a href="/thk/mempay.cp?pageNum=${startPage - 10 }">[����]</a>
  </c:if>

  <c:forEach var="i" begin="${startPage}" end="${endPage}">
    <a href="/thk/mempay.cp?pageNum=${i}">[${i}]</a>
  </c:forEach>

  <c:if test="${endPage< pageCount }">
    <a href="/thk/mempay.cp?pageNum=${startPage + 10}">[����]</a>
  </c:if>
</c:if>
<br/>

<div align=right>
  <a href="/thk/payTotal.cp" name="payTotal" class="button white small under">���� ����</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/ypay.cp" name="ypay" class="button white small under">�� ����</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/mpay.cp" name="mpay" class="button white small under">�� ����</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/dpay.cp" name="dpay" class="button white small under">�� ����</a>
</div>
</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>
