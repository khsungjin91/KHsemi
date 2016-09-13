<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#logonAd{ width: 1024px; height: 30px; margin: 0 auto; border: 1px solid black; text-align:right; background: #002187; color: white;}
#header2{ width: 1024px; height: 110px; margin: 0 auto; border: 1px solid black; background: #002187; color: white; }
#logo{ width: 350px; height: 100px; margin: 5px 5px; border: 1px solid black; float: left; }
#menu5{width: 650px; height: 100px; margin: 5px 5px; border: 1px solid black; float: right;}
#menu5 > ul { overflow: hidden; }
#menu5 > ul > li { float: left; position:relative;}
#menu5 > ul > li > a { display: black; padding: 10px 10px; color: black; text-transform:uppercase;}
#menu5 > ul > li > a:hover { text-decoration:uderline; }
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
	  <c:when test="${memId == 'owner'}">
		  <nav id="logonAd">
			  <input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
  			<input type="button" value="main" onclick="javascript:window.location='main.cp'">
  		</nav>
  	</c:when>
  	<c:otherwise>
  			<nav id="logon">
          <script>
            alert('���ٱ����� �����ϴ�.');
            location.href="main.jsp"
          </script>
  			</nav>

  	</c:otherwise>
  </c:choose>
	
	
	<header id="header2">
		<div id=logo>
			<a href="/thk/main.cp">Th.K Ment</a>
		</div>
		<nav id="menu5">
			<ul>
				<li><a href="/thk/memberAdmin.cp">ȸ������</a></li>
				<li><a href="/thk/productAdmin.cp">��ǰ����</a></li>
				<li><a href="/thk/.cp">�ֹ�����</a></li>
				<li><a href="/thk/.cp">��۰���</a></li>
				<li><a href="/thk/borderAdmin.cp">��������</a></li>
				<li><a href="/thk/qnaAdmin.cp">Q&A ����</a></li>
				<li><a href="/thk/reviewAdmin.cp">�������</a></li>
				<li><a href="/thk/.cp">�������</a></li>
			</ul>
	</nav>
	</header>
	
	<section id="section">
		<div id="border">
			����
		</div>
		<article id="article">

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
			��ȣ : Th.K Ment | �ּ� : ���ѹα�<br />
			����ڵ�Ϲ�ȣ : 777-77-77777 | ����Ǹž��Ű� : 2016-���ѹα�-7777<br />
			��ȯ �� ��ǰ ���� �¶��� ����� ������ : 7777-7777<br />
			����: thkment7@thk.com<br />
			Copyright 2016 th.k �� all right reserved<br />
		</div>
	</footer>
</body>
</html>

