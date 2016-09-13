<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>


<c:if test="${check == 1}">

<script type="text/javascript">

alert("수정되었습니다.");
window.location="/thk/reviewContent.cp?num=${num}";

</script>
</c:if>

</body>
</html>