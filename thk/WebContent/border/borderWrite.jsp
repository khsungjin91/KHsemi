<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="/stylefile/style.css" rel="stylesheet" type="text/css">
<style>
#section { width: 1200px; margin: 0 auto; padding-bottom: 100px}
#section2 {width: 650px; margin: 0 auto; }
#subject { margin-top:30px; height: 30px; font-size: 18px}
#select { height: 30px;  font-size: 18px}
#content {}
.gita {margin-top: 20px}

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

	<section id="section">
		<section id="section2">
	
  
<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">
공지글쓰기 
</div>

<form action="/thk/borderWritePro.cp" method="post">
<input type = "hidden" name="num" value="${num}"/>
<input type = "hidden" name = "writer" value = "${memId}"/>

<div id="subject">
제목 | | 
<input type="text" name="subject"  size="75" height="25" value = "[공지]"> 
</div>

<div style="margin: 10px; font-size:18px; text-align: center; ">공지내용</div>

<div id="content">
	<textarea name="content" cols="85" rows="20"></textarea>
</div>

<div style="float: right; margin-top:20px ">

<input type="submit" value="공지" class="button white small gita">
<input type="reset" name= "reset" value="다시쓰기" class="button white small gita">
<div class="button white small" class="gita">
	<a href ="/thk/borderList.cp" class="under">목록</a>
</div>
</div>

</form>
		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>