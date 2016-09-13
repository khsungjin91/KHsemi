<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>


<style type="text/css">

#section { width: 1200px; margin: 0 auto; padding-bottom:100px  }
#section2 { width: 600px; margin: 0 auto;  }

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

</style>

</head>

<body>

<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>

<section id="section">

	<section id="section2">
			
			<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 60px;">회원정보수정</div>
			
			
		<div style="text-align: center; margin-top: 50px; font-size: 18px">
			<form method="post" action="/thk/modifyForm.cp" name="userinput" onSubmit="return checkIt()">
			
				<div style="margin-bottom: 20px">비밀번호 재확인</div>
				
				<div style="margin-bottom: 20px"><input type="password" name="pw" size="20" maxlength="12" height="25"></div>
				
				<input type="submit" name="confirm" value="확 인" class="button white small" >
				<input type="button" name="cancell" value="취 소" onclick="javascript:window.location='memberInfo.cp'" class="button white small">
				
			</form>
		</div>
			
		</section>

</section>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
	
</body>
</html>