<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result == 1}">
	<script type="text/javascript">
		alert("������ �Ϸ�Ǿ����ϴ�.")
		window.location="/thk/qnaMyList.cp";
	</script>
</c:if>


<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("�ý��� ���� �߻�");
		history.go(-1);
	</script>
</c:if>



<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("�ý��� ���� �߻�");
		history.go(-1);
	</script>
</c:if>

</body>
</html>