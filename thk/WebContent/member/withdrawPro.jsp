<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
</head>
<body onload="javascript:window_onload()">
<c:choose>
	<c:when test="${memId eq null }">
		<script> 
			alert("���������� Ż�� �Ǿ����ϴ�.");
		
			setTimeout('go_url()',300);
		
			function go_url()
			{
				location.href="main.cp";
			}
		</script>
	</c:when>
	<c:otherwise>
		<c:if test="${memId ne owner}">
			<script>
				alert("��й�ȣ�� ���� �ʽ��ϴ�!");
				history.go(-1);
			</script>
		</c:if>
	</c:otherwise>
</c:choose>
</body>
</html>