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
		${memId}�̹� ������� ���̵��Դϴ�.

		<form name="checkForm" method="post" action="confirmId.cp">
			�ٸ� ���̵� �����ϼ���.<p>
			<input type="text" size="10" maxlength="12" name="mem_id"> 
			<input type="submit" value="ID�ߺ�Ȯ��">
		</form>
	</c:when>
	<c:otherwise>
		<c:if test="${requestScope.check eq -1}">
      		<p>�Է��Ͻ� ${memId} �� ����Ͻ� �� �ִ� ID�Դϴ�. </p>
      		<input type="button" value="�ݱ�" onclick="setid()">
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
