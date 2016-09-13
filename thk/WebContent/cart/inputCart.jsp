<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.mnet Join:</title>
</head>
<body>

	<script> 
		
		if (confirm("장바구니에 담겼습니다. 장바구니로 이동하시겠습니까?")) {
		    self.location.href = "/thk/cartList.cp";
			}else {
			self.close();
			}
		
	</script>
</body>
</html>