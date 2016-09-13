<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>
#section { width: 1300px; margin: 0 auto;  }
#section tr {height: 28px; border: 1px solid gray;}
#section tr td{ border: 1px solid gray; text-align: center}

#font { color: white;}

table {
	margin: 0 auto;
	border: 1px solid gray;
}

</style>
</head>

<body>

<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>	

<div style="text-align: center; font-size: 20px; font-weight: bold; padding: 20px">��۰���</div>

<div id="section">

<table width="1200">
<tr bgcolor="black" id="font">
<td>�ֹ���ȣ</td>
<td>���̸�</td>
<td>��ȭ��ȣ</td>
<td>�����</td>
<td>�䱸����</td>
<td>��ۻ���</td>
<td>�����׸� (��ۻ���, �����ȣ)</td>
</tr>

<c:if test="${shipCount == 0}">
<tr>
<td>��ϵ� ��������� �����ϴ�.</td>
</tr>
</c:if>


<c:if test="${shipCount != 0 }">

<c:forEach var="shiplist" items="${shiplist}">
<tr>
<td>${shiplist.pay_code}</td>
<td>${shiplist.get_name}</td>
<td>${shiplist.get_phone}</td>
<td>${shiplist.get_postnum} <br/> ${shiplist.get_addrs1} <br/> ${shiplist.get_addrs2}  </td>
<td>${shiplist.get_memo}</td>
<td>${shiplist.shipcheck}</td>

<td>
<form action="/thk/insertShipPro.cp" method="post">

<input type="hidden" name="mem_num" value="${mem_num}">
<input type="hidden" name="pay_code" value="${shiplist.pay_code}">

<select name="shipcheck">
<option value="���Ա�">���Ա�</option>
<option value="�Ա�Ȯ��">�Ա�Ȯ��</option>
<option value="��ǰ�غ���">��ǰ�غ���</option>
<option value="�԰�����">�԰�����</option>
<option value="�����">�����</option>
<option value="��ۿϷ�">��ۿϷ�</option>
</select>

<input type="text" name="shipnum" value="${shiplist.shipnum}" size="15">

<input type="submit" value="����" >

</form>
</td>
</tr>
</c:forEach>



</c:if>
</table>

  <c:if test="${shipCount > 0}">
    	<c:set var="pageCount" value="${shipCount /Psize + (shipCount % Psize == 0?0:1)}"/>
   		<c:set var="pageBlock" value="${10}"/>
   		<fmt:parseNumber var="result" value="${thisPage/10}" integerOnly="true"/>
   		<c:set var = "startPage" value = "${result * 10 +1 }"/>
   		<c:set var = "endPage" value="${startPage + pageBlock -1 }"/>
   		
   		
    
    <c:if test="${endPage > pageCount}">
    	<c:set  var= "endPage" value = "${pageCount}"/>
	</c:if>
<div id="center"> 	
	<c:if test="${startPage >10}">
	<a href = "/thk/ownerShipList.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/ownerShipList.cp?Pnum=${i}" >[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/ownerShipList.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
</div>   
    </c:if>
</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>