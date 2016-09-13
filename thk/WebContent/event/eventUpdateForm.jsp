<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<style type="text/css">

#section {width: 1200px; margin: 0 auto; margin-top: 50px}
#section td {text-align: center; border: 1px solid gray;}

table tr td{height: 30px}

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
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>


<div id="section">

		<b>이벤트 수정</b>
	<br>
<form enctype="multipart/form-data" method="post" action="eventUpdatePro.cp?pageNum=${pageNum}" onsubmit="return writeSvae()">
	<input type="hidden" name="num" value="${num}">

	<table width="850">
		<tr>
			<td>작성자</td>
			<td>${article.writer}</td>
			<td>작성일</td>
			<td>${article.reg}</td>
		</tr>
			<tr>
			<td>사진</td>
			<td colspan="3"><input type="file" name="img">
			<input type="hidden" name="iimg" value="${article.img}"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3"><input type="text" size="90" maxlength="50" name="subject" value="${article.subject}"></td>
		</tr>
		<tr>
			<td colspan="4" style="border: 1px solid white"><textarea rows="30" cols="120" name="content">${article.content}</textarea></td>
		</tr>	
	</table>
		<div style="float: right; margin-right: 150px; padding:30px ">
		<input type="submit" value="수정" class ="button white small">
		<input type="reset" value="다시 작성" class ="button white small">
		<input type="button" value="돌아가기" onclick="window.location='/thk/eventContent.cp?num=${num}'" class ="button white small">	
		</div>
	</form>
	</div>
	
		
 <jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>	
	</body>
</html>

 