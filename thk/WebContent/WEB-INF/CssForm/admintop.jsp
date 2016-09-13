<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#logo {  width:330px; height:100px; background:url("/thk/save/mainlogofix.png"); margin: 0 auto;}
#menu { margin-left: 200px; margin-top: 30px}
#menu ul li	{float: left; padding:30px}
#menu ul li a {text-decoration: none; font-size: 18px; color:black}

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
	padding: .2em 1em .275em

}

</style>

</head>
<body>
  <c:choose>
	  <c:when test="${memId == 'owner'}">
		
		
		<div style="float: right;">
		관리자 페이지
			 <input type="button" value="logout" onclick="javascript:window.location='logout.cp'"
			 	class="button white small">
  			<input type="button" value="main" onclick="javascript:window.location='main.cp'"
  				class="button white small">
  		</div>
  	</c:when>
  	<c:otherwise>
  			
          <script>
            alert('접근권한이 없습니다.');
            location.href="main.jsp"
          </script>
  		

  	</c:otherwise>
  </c:choose>
	
	
	
	
		<div id="logo" style="margin-top: 50px">
			<a href="/thk/payTotal.cp"><img src="/thk/save/mainlogofix.png"></a>
		</div>
		
		
		<div id="menu">
				<ul>
					<li><a href="/thk/memberAdmin.cp">회원관리</a></li>
					<li><a href="/thk/productAdminList.cp">상품관리</a></li>
					<li><a href="/thk/ownerPayList.cp">주문관리</a></li>
					<li><a href="/thk/ownerShipList.cp">배송관리</a></li>
					<li><a href="/thk/borderAdmin.cp">공지관리</a></li>
					<li><a href="/thk/qnaList.cp">Q&A 관리</a></li>
					<li><a href="/thk/payTotal.cp">매출관리</a></li>
				</ul>
		</div>
	
	<hr width="90%" color="gray">

</body>
</html>