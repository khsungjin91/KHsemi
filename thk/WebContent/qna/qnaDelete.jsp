<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto;  padding-top: 100px; padding-bottom:100px}
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

<script type="text/javascript">

function pwCheck(){
	
	if(!document.userinput.pw.value){
		
		alert("비밀번호를 입력해주세요");
		return false;
		
	}
	
}

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
	<section id="section">

			<div style="width: 600px; margin: 0 auto; text-align: center;">
				<form action="/thk/qnaDeletePro.cp" method="post" onSubmit="return pwCheck();" name="userinput">

				<input type="hidden" value="${num}" name="num">
				<div style="text-align: center; font-size: 18px; margin-bottom: 10px">비밀번호</div>
				<div style="text-align: center; margin-bottom: 10px"><input type="password" name="pw"></div>				
			
			
				
				<input type="submit" value="삭제" class="button white small" >
				<input type="button" value="뒤로가기" class="button white small" 
				onclick="javascript:window.location='/thk/qnaView.cp?num=${num}'">
			
				
				</form>
		</div>
		
	</section>
	

	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>