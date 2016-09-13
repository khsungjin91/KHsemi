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

alert("성공적으로 삭제되었습니다.");
window.location="/thk/qnaMyList.cp";

</script>

</c:if>

<c:if test="${check == 0 }">

<script type="text/javascript">

alert("비밀번호를 확인해주세요.");
history.go(-1);

</script>

</c:if>

<c:if test="${check == -1}">

<script type="text/javascript">

alert("아이디 & 비밀번호를 확인해주세요.");
history.go(-1);

</script>

</c:if>

</body>
</html>