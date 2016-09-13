<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>���� ���</title>
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
  <div align=center>���� ����(��� ����:${count})</div>

<c:if test="${count == 0}">
  <div>���� ������ �����ϴ�.</div>
</c:if>

<form action="/thk/paySearch.cp" method="post">
<div>
�˻� :<input type="text" name="search">
	<select name="item">
		<option value="pay_code">������ȣ</option>
		<option value="mem_num">ȸ����ȣ</option>
		<option value="p_code">��ǰ�ڵ�</option>
		<option value="p_size">������</option>
		<option value="pay_amount">����</option>
		<option value="pay_price">��������</option>
		<option value="p_point">��ǰ����Ʈ</option>
		<option value="pay_howpay">���ҹ��</option>
		<option value="reg_date">������</option>
	</select>
<input type= "submit" value="��ȸ">
</div>
(������:yyyy-mm-dd)
</form>


<c:if test="${count > 0 }">
<table border=1 width=1000 cellpadding=0 cellspacing=0>
  <tr>
    <td align=center>������ȣ</td>
    <td align=center>ȸ����ȣ</td>
    <td align=center>��ǰ�ڵ�</td>
    <td align=center>������</td>
    <td align=center>����</td>
    <td align=center>��������</td>
    <td align=center>��ǰ����Ʈ</td>
    <td align=center>���ҹ��</td>
    <td align=center>������</td>
  </tr>
  
 <c:forEach var="article" items="${articleList}" >
  <tr>
    <td align=center>${article.pay_code}</td>
    <td align=center>${article.mem_num}</td>
    <td align=center>${article.p_code }</td>
    <td align=center>${article.p_size }</td>
    <td align=center>${article.pay_amount}</td>
    <td align=center>
     <fmt:formatNumber value="${article.pay_price}" pattern="#,##0"/>��</td>
    <td align=center>${article.p_point}</td>
    <td align=center>
    
    <c:if test="${article.pay_howpay == 0100000000}">
		�������
</c:if>

<c:if test="${article.pay_howpay == 1000000000}">
		�ſ�ī��
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
  <a href="/thk/payTotal.cp?pageNum=${startPage - 10 }">[����]</a>
  </c:if>

  <c:forEach var="i" begin="${startPage}" end="${endPage}">
    <a href="/thk/payTotal.cp?pageNum=${i}">[${i}]</a>
  </c:forEach>

  <c:if test="${endPage < pageCount }">
    <a href="/thk/payTotal.cp?pageNum=${startPage + 10}">[����]</a>
  </c:if>
</c:if>
<br/>

<div align=right>
 	<a href="/thk/ypay.cp" name="ypay" class="button white small under">�� ����</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/mpay.cp" name="mpay" class="button white small under">�� ����</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="/thk/dpay.cp" name="dpay" class="button white small under">�� ����</a>
  <a href="/thk/mempay.cp" name="mempay" class="button white small under">���� ���� �Ѿ�</a>
</div>

</div>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>

