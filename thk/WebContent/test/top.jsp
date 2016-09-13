<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
#frame { width:1550px; height:600px; background:url("/thk/save/topfix2.jpg"); margin:0 auto; border: 1px solid black;}
#frame2 {height:100px; border: 1px solid black; margin-top:80px}
#login { margin-right:100px; height:10px; text-align:right;}
#logo { margin-left:100px; margin-top:80px; width:330px; height:100px; background:url("/thk/save/mainlogofix.png"); border: 1px solid black;}
.menu1 { width:700px; height:100px; border: 1px solid black; float:right; margin-right:150px;}

</style>
</head>
<body>


<c:choose>
		<c:when test="${memId == null }">
			<div id="login">
				<input type="button" value="login" onclick="javascript:window.location='/thk/loginForm.cp'">
				<input type="button" value="join" onclick="javascript:window.location='/thk/joinForm.cp'">
			</div>
		</c:when>
		<c:otherwise>
			<c:if test="${memId != null && memId != 'owner'}">
				<div id="login">
					<c:out value="${memId}" />님 환영합니다! &nbsp;
					<input type="button" value="logout" onclick="javascript:window.location='/thk/logout.cp'">
					<input type="button" value="My Page" onclick="javascript:window.location='/thk/memberInfo.cp'">
				</div>
			</c:if>
			<c:if test="${memId == 'owner'}">
				<div id="login">
					<input type="button" value="admin" onclick="javascript:window.location='admin.cp'">
					<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
	
<header id="frame">

	<div id="frame2">
		<div id="logo">
			<a href="/thk/main.cp"></a>
		</div>
		<nav class="menu1">
			<ul>
				<li><a href="/thk/about.cp">ABOUT</a></li>
				<li><a href="/thk/lookbook.cp">LOOKBOOK</a></li>
				
				<li><a href="/thk/newsList.cp">COMMUNITY</a>
				  <div id="menu2">
				    <ul>
				      <li><a href="/thk/newsList.cp">NEWS&EVENT</a></li>
				      <li><a href="/thk/reviewList.cp">REVIEW</a></li>
				    </ul>
				  </div>
				</li>
				<li><a href="/thk/borderList.cp">SUPPORT</a>
				  <div id="menu3">
				    <ul>
				      <li><a href="/thk/borderList.cp">NOTICE</a></li>
				      <li><a href="/thk/qnaList.cp">Q&A</a></li>
				      <li><a href="/thk/faq.cp">FAQ</a></li>
				    </ul>
				  </div>
				</li>
				<li><a href="/thk/allProductList.cp">E-SHOP</a>
				  <div id="menu4">
				    <ul>
				      <li><a href="/thk/allProductList.cp">ALL PRODUCT</a></li>
				      <li><a href="/thk/footWearList.cp">FOOTWEAR</a></li>
				      <li><a href="/thk/accessoriesList.cp">ACCESSORIES</a></li>
				      <li><a href="/thk/apparelList.cp">APPAREL</a></li>
				    </ul>
				  </div>
				  </li>
			</ul>
			</nav>
			</div>
</header>


</body>
</html>