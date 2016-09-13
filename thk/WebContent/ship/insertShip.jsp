<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>배송등록</title>
<style type="text/css">
#section {width: 1200px; margin: 0 auto}

</style>
</head>
<body>

<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>

<div id="section">

<div>배송등록</div>
<form action="/thk/insertShipPro.cp" method="post" >

<input type="hidden" name="mem_num" value="${memdto.mem_num}">
<input type="hidden" name="pay_code" value="${pay_code}">


<div> 주문번호 ${pay_code}</div>

<div> 송장번호 <input type="text" name="shipnum" size="15"></div>

<div>고객이름 ${memdto.get_name}</div>

<div>고객전화번호 ${memdto.get_phone}</div>

<div> 배송지  ${memdto.get_postnum} &nbsp; ${memdto.get_addrs1} &nbsp; ${memdto.get_addrs2} </div>

<div> 배송시 요구사항  ${memdto.get_memo}</div>

<div>
배송상태
<select name="shipcheck">
<option value="미입금">미입금</option>
<option value="입금확인">입금확인</option>
<option value="상품준비중">상품준비중</option>
<option value="입고지연">입고지연</option>
<option value="배송중">배송중</option>
<option value="배송완료">배송완료</option>
</select>
</div>

<input type="submit" value="등록">

</form>




</div>


<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>