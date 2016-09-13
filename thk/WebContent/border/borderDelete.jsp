<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<style>
#logon{ width: 1024px; height: 30px; margin: 0 auto; border: 1px solid black; text-align:right}
#logonAd{ width: 1024px; height: 30px; margin: 0 auto; border: 1px solid black; text-align:right; background: #002187; color: white;}
#header{ width: 1024px; height: 110px; margin: 0 auto; border: 1px solid black; }
#logo{ width: 350px; height: 100px; margin: 5px 5px; border: 1px solid black; float: left; }
#menu1{width: 650px; height: 100px; margin: 5px 5px; border: 1px solid black; float: right;}
#menu1 > ul { overflow: hidden; }
#menu1 > ul > li { float: left; position:relative;}
#menu1 > ul > li > a { display: black; padding: 10px 10px; color: black; text-transform:uppercase;}
#menu1 > ul > li > a:hover { text-decoration:uderline; }
#section { width: 1024px; margin: 0 auto; border: 1px solid black; }
#border { width: 964px; height: 800px; margin: 20px auto; border: 1px solid black; }
#article { width: 964px; margin: 20px auto; border: 1px solid black; }
#product_List {width: 300px; height: 250px; margin: 7px 7px; border: 1px solid black; float: left; }
#product { width: 900px; margin: 20px auto; border: 1px solid black; }
#footer { width: 1024px; height: 210px; margin: 0 auto; border: 1px solid black; }
#guide { width: 964px; height: 40px; margin: 5px 30px; border: 1px solid black; }
#guide > ul { overflow: hidden; }
#guide > ul > li { float: left; }
#guide > ul > li > a { display: black; padding: 10px 50px; color: black; font-size:9px; }
#company { width: 964px; height: 150px; margin: 5px 30px; border: 1px solid black;}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${memId == null }">
			<nav id="logon">
				<input type="button" value="login" onclick="javascript:window.location='loginForm.cp'">
				<input type="button" value="join" onclick="javascript:window.location='joinForm.cp'">
			</nav>
		</c:when>
		<c:otherwise>
			<c:if test="${memId != null && memId != 'owner'}">
				<nav id="logon">
					<c:out value="${memId}" />님 환영합니다! &nbsp;
					<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
					<input type="button" value="My Page" onclick="javascript:window.location='memberInfo.cp'">
				</nav>
			</c:if>
			<c:if test="${memId == 'owner'}">
				<nav id="logonAd">
					<input type="button" value="admin" onclick="javascript:window.location='admin.cp'">
					<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
				</nav>
			</c:if>
		</c:otherwise>
	</c:choose>

  <header id="header">
		<div id=logo>
			<a href="/thk/main.cp">Th.K Ment</a>
		</div>
		<nav id="menu1">
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
  </header>
  
  	
	<section id="section">
		

		<article id="article">
  
<p> 삭제 하시겠습니까?? </p>



<form action="/thk/borderDeletePro.cp?Pnum=${Pnum}" method="post">
<input type="hidden" name="num" value="${num}">
<input type="submit"  value = "Yes" >
</form>
<input type="button"  value = "No" onclick="javascript:window.location='/thk/borderList.cp?Pnum=${Pnum}'">

		</article>
	</section>
	
	
	<footer id="footer">
		<nav id="guide">
		<ul>
				<li><a href="/thk/main.cp">HOME</a></li>
				<li><a href="/thk/about.cp">COMPANY</a></li>
				<li><a href="/thk/guide.cp">GUIDE</a></li>
				<li><a href="/thk/agreement.cp">AGREEMENT</a></li>
				<li><a href="/thk/private.cp">PRIVATE</a></li>
			</ul>
		</nav>
		<div id="company">
			상호 : Th.K Ment | 주소 : 대한민국<br />
			사업자등록번호 : 777-77-77777 | 통신판매업신고 : 2016-대한민국-7777<br />
			교환 및 반품 문의 온라인 스토어 고객센터 : 7777-7777<br />
			메일: thkment7@thk.com<br />
			Copyright 2016 th.k 뜩 all right reserved<br />
		</div>
	</footer>
</body>
</html>