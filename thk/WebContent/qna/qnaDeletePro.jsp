<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:if test="${check == 1}">

<script type="text/javascript">

alert("���������� �����Ǿ����ϴ�.");
window.location="/thk/qnaMyList.cp";

</script>

</c:if>

<c:if test="${check == 0 }">

<script type="text/javascript">

alert("��й�ȣ�� Ȯ�����ּ���.");
history.go(-1);

</script>

</c:if>

<c:if test="${check == -1}">

<script type="text/javascript">

alert("���̵� & ��й�ȣ�� Ȯ�����ּ���.");
history.go(-1);

</script>

</c:if>

</body>
</html>