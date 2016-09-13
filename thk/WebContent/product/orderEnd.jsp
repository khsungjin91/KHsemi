<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>주문완료</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<body>
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
<h1>주문완료</h1><br />
	<div>
	<h3>주문요약정보</h3>
	</div>
			<c:if test="${param.sndPaymethod==1000000000}">
					<div>
					==신용카드결제==<br/>
					주문번호 ${param.sndOrdernumber} <br/>
					주문자명 ${param.sndOrdername} <br/>
					주문일자 ${strdate} 			<br/>
					주문금액 ${param.sndAmount} <br/>
					적립 포인트 ${param.p_point} <br />
					</div>
				</c:if>
				
				
				<c:if test="${param.sndPaymethod==0010000000}">
					<div>
					==계좌이체결제==<br/>
					주문번호 ${param.sndOrdernumber} <br/>
					주문자명 ${param.sndOrdername} <br/>
					주문일자 ${trddt} <br/>
					주문금액 ${amt} <br/>
					입금은행 ${authno}<br/>
					계좌번호 ${isscd} <br/>
					적립 포인트 ${param.p_point} <br />
					</div>
				</c:if>
				
				
				<c:if test="${param.sndPaymethod==0100000000}">
					<div>
					==가상계좌결제==<br/>
					주문번호 ${param.sndOrdernumber} <br/>
					주문자명 ${param.sndOrdername} <br/>
					주문일자 ${trddt} <br/>
					주문금액 ${amt} <br/>
					입금은행 ${authno}<br/>
					계좌번호 ${isscd} <br/>
					적립 포인트 ${param.p_point}
					</div>
				</c:if>
	
</body>

</html>
		
	<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>

</html>