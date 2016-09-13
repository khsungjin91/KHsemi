<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Event</title>
<style type="text/css">
#section {width: 1200px; margin: 0 auto; margin-top: 50px;}
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

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
<div id="section">

<div style="margin-left:250px ">
	
<form enctype="multipart/form-data" action="/thk/eventWritePro.cp" method="post" >
	
	<p>제목 | | <input type="text" size="90" maxlength="50" name="subject"></p>
	
	<p>사진 선택<input type="file" name="img"></p>
	
	<p>내용</p>
	
	<p><textarea rows="30" cols="100" name="content"></textarea></p>
	
<div style="float: right; margin-right: 250px; padding: 30px">
	<input type="submit" value="작성" class="button white small">
	<input type="button" value="목록으로" onclick="window.location='/thk/eventList.cp'" class="button white small">
</div>	
</form>
</div>

</div>
 <jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>	
</body>
</html>