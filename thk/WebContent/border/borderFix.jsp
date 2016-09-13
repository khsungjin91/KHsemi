<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<style>
#section { width: 1200px; margin: 0 auto;  padding-bottom: 100px}
#section2 {width: 600px; margin: 0 auto; }
#subject { margin-top:30px; height: 30px; font-size: 18px}
#gita {margin-top: 20px}
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

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
  	
	<section id="section">
		
		<section id="section2">
			

<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">공지수정</div>

<c:set var="fixdto" value="${fixdto}"/>

<form action="/thk/borderFixPro.cp?Pnum=${Pnum}" method="post">

<input type="hidden" name = "num" value="${fixdto.num}"/>

<div id="subject">
제목 | | &nbsp; <input type ="text" value="${fixdto.subject}" name="subject" size="67" height="25"> <br/>
</div>

<div style="margin: 10px; font-size:18px; text-align: center; ">수정내용</div>

<textarea name="content" cols="80" rows="20">${fixdto.content}</textarea>

<div style="float: right;">
<input type="submit" value="수정" name="fix" class="button white small" id="gita">
<input type="button" value="목록" name="list"
		onclick = "javascript:window.location='/thk/borderList.cp'" 
		style="margin-right: 30px"  class="button white small" id="gita">
</div>
</form>

		</section>
	</section>
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>	

</body>
</html>