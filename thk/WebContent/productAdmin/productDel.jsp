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
            alert('접근권한이 없습니다.');
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
				<li><a href="/thk/memberAdmin.cp">회원관리</a></li>
				<li><a href="/thk/productAdminList.cp">상품관리</a></li>
				<li><a href="/thk/.cp">주문관리</a></li>
				<li><a href="/thk/.cp">배송관리</a></li>
				<li><a href="/thk/borderAdmin.cp">공지관리</a></li>
				<li><a href="/thk/qnaAdmin.cp">Q&A 관리</a></li>
				<li><a href="/thk/reviewAdmin.cp">리뷰관리</a></li>
				<li><a href="/thk/.cp">매출관리</a></li>
			</ul>
	</nav>
	</header>
	
	<section id="section">
		<article id="article">
			<b>상품 삭제</b>
  <form action="/thk/productAdmin/productDelPro.cp?p_code=${p_code}&pageNum=${pageNum}" method="post">
   <table border="1"cellspacing="0" cellpadding="0" >
   <tr>
     <td><input type="submit" value="삭제" >
     <input type="button" value="취소" 
     onClick="document.location.href='/thk/productAdmin/productCt.cp?p_code=${p_code}&pageNum=${pageNum}'">
     </td>
   </tr>
 </table>
</form>
		</article>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>