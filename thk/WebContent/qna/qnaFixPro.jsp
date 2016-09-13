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
		alert("수정이 완료되었습니다.")
		window.location="/thk/qnaMyList.cp";
	</script>
</c:if>


<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("시스템 문제 발생");
		history.go(-1);
	</script>
</c:if>



<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("시스템 문제 발생");
		history.go(-1);
	</script>
</c:if>

</body>
</html>