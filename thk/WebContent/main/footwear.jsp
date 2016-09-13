<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>FOOTWEAR</title>

<style>
#section2 {width: 1000px; margin: 0 auto; height: 150px; margin-bottom:30px; }
#section { width:1200px; margin: 0 auto; margin-top:30px; margin-bottom:50px; padding-left:40px}
tr {float:left; width:290px;  text-align:center; margin-bottom:30px; height: 350px }
#catrgory {text-align: center; font-size: 20px; font-weight:bold; margin-top:30px  }
#productinfo ul li { display:block; margin-left:-30px; }


#section2 ul li{ display: block; float: left; padding: 55px; margin-right: 30px }
#section2 a {text-decoration: none; color: black}
#section a {text-decoration: none; color: black}

</style>
</head>

<body>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>


<div id="section">

	
		
	<div id="catrgory"> FOOTWEAR </div>
	
	<div id="section2">	
	<ul>
		<li><a href="/thk/footwear.cp?p_category2=NIKE"><img src="/thk/save/category/nike" width="90"></a></li>
		
		<li><a href="/thk/footwear.cp?p_category2=ADIDAS"><img src="/thk/save/category/adidas.jpg" width="90"></a></li>
		
		<li><a href="/thk/footwear.cp?p_category2=NEW BALANCE"><img src="/thk/save/category/newbal" width="90"></a></li>
		
		<li><a href="/thk/footwear.cp?p_category2=CONVERSE"><img src="/thk/save/category/convers" width="90"></a></li>
	</ul>
	</div>

<table bor>
		<c:forEach var="footlist" items="${footlist}">
		<tr>
			<td>
			<c:set var="code" value="${footlist.p_code}"/>
			<a href="/thk/product_1.cp?p_code=${code}"><img src="/thk/save/${footlist.p_thumbnail}" height="230" ></a>
			<div id="productinfo">
				<ul>
					<li style="font-size:15px">${footlist.p_name}</li>
					<li style="font-size:13px; text-decoration: line-through;">
					<fmt:formatNumber value="${footlist.p_price}" pattern="#,##0"/>원</li>
					
					<li style="font-size:20px">
					<fmt:formatNumber value="${footlist.p_price_dc}" pattern="#,##0"/>원</li>
					<li>
					 <c:if test="${footlist.result == 1}">
					    <img src="/thk/save/icon/new.png" width="50">
					    </c:if>
					  <c:if test="${footlist.readcount >=10 }">
					     <img src="/thk/save/icon/HOT.png" width="50">
					  </c:if>
					</li>
				</ul>
			</div>
			</td>		
			</tr>
		</c:forEach>
</table>
</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>