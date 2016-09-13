<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto;  }
table tr td {border: 1px solid gray}
table tr {height: 30px}
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
	
			<div style="text-align: center; padding: 30px; font-size: 20px"><b>��ǰ ���(��� ����:${count})</b></div> 
			
	<section id="section">
		
		
		 <form action="/thk/productListSearch.cp" method="post" align="right">
	   <div>
	  ��ǰ��ȸ :<input type="text" name="search">
		   <select name="item">
				<option value="p_code">��ǰ�ڵ�</option>
				<option value="p_name">��ǰ��</option>
				<option value="p_material">����</option>
				<option value="p_sale_date">�����</option>
		   </select>
	      <input type= "submit" value="��ȸ">
	   </div>
	   ( ��)�����:20160401)
	 </form>
		
			<a href="/thk/productAdmin.cp" class="button white small under" style="float: right; margin-bottom: 50px">��ǰ���</a>

<c:if test="${count == 0}">
  <div> ��ϵ� ��ǰ�� �����ϴ�.</div>
</c:if>

<c:if test="${count > 0 }">
<table width="1200">
  <tr bgcolor="black" style="color: white; height: 30px" >
    <td align=center>��ǰ�ڵ�</td>
    <td align=center>��ǰ��</td>
    <td align=center>����</td>
    <td align=center>�����</td>
    <td align=center>���̹���</td>
    <td align=center>����</td>
    <td align=center>���ΰ�</td>
    <td align=center>����Ʈ</td>
    <td align=center>ī�װ�</td>
    <td align=center>�����</td>
  </tr>
  
  <c:forEach var="article" items="${articleList}" >
  <c:set var="code" value="${article.p_code}"/>
  <tr>
    <td align=center>
      <a href="/thk/productCt.cp?p_code=${code}">
         ${article.p_code}</a></td>
    <td align=center>${article.p_name}</td>
    <td align=center>${article.p_material}</td>
    <td align=center><img src="/thk/save/${article.p_thumbnail}" width="100"></td>
    <td align=center><img src="/thk/save/${article.p_img}" width="100"></td>
    <td align=center>
     <fmt:formatNumber value="${article.p_price}" pattern="#,##0"/>��</td>
    <td align=center>
     <fmt:formatNumber value="${article.p_price_dc}" pattern="#,##0"/>��</td>
    <td align=center>${article.p_point} P</td>
    <td align=center> 1��: ${article.p_category1} 2��: ${article.p_category2} 3��: ${article.p_category3}</td>
    <td align=center>${article.p_sale_date}</td>
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

<div style="text-decoration: none; text-align: center; font-size: 15px">
  <c:if test="${startPage > 10 }">
  <a href="/thk/productAdminList.cp?pageNum=${startPage - 10 }">[����]</a>
  </c:if>

  <c:forEach var="i" begin="${startPage}" end="${endPage}">
    <a href="/thk/productAdminList.cp?pageNum=${i}">[${i}]</a>
  </c:forEach>

  <c:if test="${endPage< pageCount }">
    <a href="/thk/productAdminList.cp?pageNum=${startPage + 10}">[����]</a>
  </c:if>
</div>
</c:if>
	</section>
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>