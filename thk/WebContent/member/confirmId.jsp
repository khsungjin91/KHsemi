<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.mnet Confirm ID:</title>
</head>
<body>
<c:choose>
	<c:when test="${requestScope.check eq 1}">
		${memId}이미 사용중인 아이디입니다.

		<form name="checkForm" method="post" action="confirmId.cp">
			다른 아이디를 선택하세요.<p>
			<input type="text" size="10" maxlength="12" name="mem_id"> 
			<input type="submit" value="ID중복확인">
		</form>
	</c:when>
	<c:otherwise>
		<c:if test="${requestScope.check eq -1}">
      		<p>입력하신 ${memId} 는 사용하실 수 있는 ID입니다. </p>
      		<input type="button" value="닫기" onclick="setid()">
		</c:if>
	</c:otherwise>
</c:choose>
</body>
</html>
<script>

  function setid()
    {		
    	opener.document.userinput.mem_id.value="${memId}";
		self.close();
    }
</script>
