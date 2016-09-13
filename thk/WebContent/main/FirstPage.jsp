<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Welcome Three Shop</title>
<style type="text/css">

#section { width:1200px; margin: 0 auto 30px; margin-top:30px; margin-bottom:30px;  }
#section a {text-decoration: none; color: black}
tr {float:left; width:280px; text-align:center; margin-bottom:30px; height: 350px }
#catrgory {text-align: center; font-size: 20px; font-weight:bold; margin-top:30px  }
#productinfo ul li { display:block; margin-left:-30px; }
#center {margin: 0 auto; text-align: center;} 
#center a {text-decoration: none; font-size: 20px}
#type {
	background:url(""); 
	background-repeat: no-repeat; 
	margin: 0 auto; 
	margin-top: 50px;
	margin-bottom: 50px;
	width:60%; 
	height:60px; 
	border:1px solid black;
} 

</style>
</head>
<body>


<jsp:include page="/WEB-INF/CssForm/FirstPagetop.jsp"></jsp:include>

<hr color="black" width="90%">

<div id="section">

	<div style="text-align: center; padding-top: 50px; font-size: 30px; font-weight: bold;"> NEW ARRIVAL </div>

		<c:if test="${Count == 0}">
		등록된 상품이 없습니다.
		</c:if>
		
		<c:if test="${Count != 0}">
		<table style="margin-left: 20px">
			<c:forEach var="foot_list" items="${foot_list}">
			<tr>
			<td>
			<c:set var="code" value="${foot_list.p_code}"/>
			<a href="/thk/product_1.cp?p_code=${code}"><img src="/thk/save/${foot_list.p_thumbnail}" width="" height="200"></a>
			<div id="productinfo">
				<ul>
					<li style="font-size:15px">${foot_list.p_name}</li>
					<li style="font-size:13px; text-decoration: line-through;">
					<fmt:formatNumber value="${foot_list.p_price}" pattern="#,##0"/>원</li>
					<li style="font-size:20px">
					<fmt:formatNumber value="${foot_list.p_price_dc}" pattern="#,##0"/>원</li>
						<li>
					  <c:if test="${foot_list.result == 1}">
					    <img src="/thk/save/icon/new.png" width="50">
					    </c:if>
					  <c:if test="${foot_list.readcount >=10 }">
					     <img src="/thk/save/icon/HOT.png" width="50">
					  </c:if>
					</li>	
				</ul>
			</div>
			</td>		
			</tr>
			</c:forEach>
		</table>	
		</c:if>

</div>



<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>