<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.mnet Logout:</title>
</head>
<body onload="javascript:window_onload()">
	<script> 
		alert("로그아웃 되었습니다.");
		
		setTimeout('go_url()',300);
		
		function go_url()
		{
			location.href="loginForm.cp"
		}
	</script>
</body>
</html>