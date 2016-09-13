<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="/test/style.css" rel="stylesheet" type="text/css">
<style type="text/css">

#frame2 {height:100px;  margin-top:50px; }
#login {  text-align:right; background: black; color: white;}
#owner {background:blue; border:1px solid blue; text-align: right;}
#logo {margin-left:100px;  width:330px; height:100px;  float:left;}
#menu { width:900px; height:100px; margin-right:100px;  float:right; font-size:15px; letter-spacing:3px; padding-top:30px; }
#menu > ul > li { float: left;  position:relative; display:block; }
#menu ul li:hover ul { display: block;}
#menu a {text-decoration:none; color:black;}
.padding {padding-left:30px; padding-right:30px; }


#menu ul ul {
	display:none;  
	background-color:white;
 -moz-border-radius-bottom-left: 5px;
  -khtml-border-bottom-left-radius: 5px;
  -webkit-border-bottom-left-radius: 5px;
  border-bottom-left-radius: 5px;
 -moz-border-radius-bottom-right: 5px;
  -khtml-border-bottom-right-radius: 5px;
  -webkit-border-bottom-right-radius: 5px;
  border-bottom-right-radius: 5px;
	border: 1px solid black;
}


#menu ul ul li { display: block; margin-left:-35px; padding:10px  }
  
table { margin:0 auto; margin-top:30px}
td { margin:10px; width:320px; height:193px; }

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

.under{
 text-decoration: none;
 color:black;
}

</style>
</head>
<body>

<c:choose>
		<c:when test="${memId == null }">
		<div id="login">
			<div  class="button white small">
				<a href="/thk/loginForm.cp" class="under">로그인</a>
			</div>
			<div  class="button white small">
				<a href="/thk/joinForm.cp" class="under">회원가입</a>
				
			</div>
		</div>
		</c:when>
		<c:otherwise>
			<c:if test="${memId != null && memId != 'owner'}">
			
		<div id="login">
		<c:out value="${memId}" />님 환영합니다! &nbsp;	
			<div  class="button white small">
				<a href="/thk/logout.cp" class="under">로그아웃</a>
			</div>
			<div  class="button white small">
				<a href="/thk/memberInfo.cp" class="under">마이페이지</a>
			</div>		
		</div>
			</c:if>
			<c:if test="${memId == 'owner'}">
		<div id="owner">
			<div  class="button white small">
				<a href="/thk/payTotal.cp" class="under">관리자페이지</a>
			</div>
			<div  class="button white small">
				<a href="/thk/logout.cp" class="under">로그아웃</a>
				
			</div>
		</div>
		
			</c:if>
		</c:otherwise>
	</c:choose>
	
	
<header id="frame2">

		<div id="logo">
		
			<a href="/thk/FirstPage.cp"><img src="/thk/save/mainlogofix.png"></a>
			
		</div>
		
	<nav id="menu">
		<ul>
			<li><a href="/thk/about.cp" class="padding">ABOUT </a></li>
			<li><a href="/thk/lookbook.cp" class="padding">LOOKBOOK </a></li>
				
			<li><a href="/thk/eventList.cp" class="padding">COMMUNITY </a>
				<div id="menu2">
				    <ul>
				      <li><a href="/thk/eventList.cp">NEWS&EVENT </a></li>
				      <li><a href="/thk/reviewList.cp">REVIEW </a></li>
				    </ul>
				</div>
			</li>
				
			<li><a href="/thk/borderList.cp" class="padding">SUPPORT </a>
				<div id="menu3">
				   <ul>
				     <li><a href="/thk/borderList.cp">NOTICE</a></li>
				     <li><a href="/thk/qnaMyList.cp">Q&A</a></li>
				     <li><a href="/thk/faq.cp">FAQ</a></li>
				   </ul>
				 </div>
			</li>
			
			<li><a href="/thk/main.cp" class="padding">E-SHOP </a>
				 <div id="menu4">
				   <ul>
				      <li><a href="/thk/main.cp">ALL PRODUCT</a></li>
				      <li><a href="/thk/footwear.cp?p_category1=FOOTWEAR">FOOTWEAR</a></li>
				      <li><a href="/thk/accessory.cp?p_category1=ACCESSORIES">ACCESSORIES</a></li>
				      <li><a href="/thk/apparel.cp?p_category1=APPAREL">APPAREL</a></li>
				   </ul>
				 </div>
			 </li>
			 
			</ul>
		</nav>
</header>
<hr width="90%" color="gray">


</body>
</html>