<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.mnet Login:</title>
</head>
<body>

<c:if test="${check == 1}">

	<script> 
		window.location="/thk/FirstPage.cp";
	</script>


</c:if>

<c:if test="${check == 0 || check == -1}">

	<script> 
		alert("아이디 또는 비밀번호가 맞지 않습니다.");
		window.location="/thk/loginForm.cp";
	</script>

</c:if>




</body>
</html>