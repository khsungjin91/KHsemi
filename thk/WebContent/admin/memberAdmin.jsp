<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#section { width: 1300px; margin: 0 auto;  }
#section2 {width: 1200px; margin: 0 auto }
#section2 td {height: 40px}
</style>

</head>
<body>
<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>
	
	<section id="section">
		<div id="section2">
		
		 <form action="/thk/memberSearch.cp" method="post" align="right">
   <div>
  ȸ����ȸ :<input type="text" name="search">
	   <select name="item">
			<option value="mem_num">ȸ����ȣ</option>
			<option value="mem_id">���̵�</option>
			<option value="mem_name">ȸ���̸�</option>
			<option value="mem_phone">��ȭ��ȣ</option>
			<option value="mem_email">�̸���</option>
			<option value="reg_date">������</option>
	   </select>
      <input type= "submit" value="��ȸ">
  </div>
( ��)������:20160401)
</form>

    <div style="text-align: center; font-size: 20px; margin-bottom: 30px"><b>��üȸ���� ${Count} ��</b></div>
    
    <c:if test="${Count == 0}">
    ��ϵ� ȸ���� �����ϴ�.
    </c:if>
    <c:if test="${Count > 0}">   
   <table width="1200" border="1">
     <tr>
     	<td>��ȣ</td>
     	<td>ȸ����ȣ</td>
     	<td>���̵�</td>
     	<td>ȸ���̸�</td>
     	<td>��ȭ��ȣ</td>
     	<td>�̸���</td>
     	<td>�����ȣ</td>
     	<td>�ּ�</td>
     	<td>���ּ�</td>    
     	<td>������</td>
     </tr>
    <c:forEach var="li" items="${list}">
     <tr>
    	<td><c:out value="${number}"/>
      <c:set var="number" value="${number-1}"/></td>
     	<td>${li.memNum}</td>
     	<td>${li.memId}</td>
     	<td>${li.memName}</td>
     	<td>${li.memPhone}</td>
     	<td>${li.memEmail}</td>
     	<td>${li.memPostnum}</td>
     	<td>${li.memAddrs1}</td>
     	<td>${li.memAddrs2}</td>    
     	<td>${li.reg_date} </td>
     </tr>
    </c:forEach>
    </table>
    
    </c:if>
    
    
    
    
    <c:if test="${Count > 0}">
    	<c:set var="pageCount" value="${Count /Psize + (Count % Psize == 0?0:1)}"/>
   		<c:set var="pageBlock" value="${10}"/>
   		<fmt:parseNumber var="result" value="${thisPage/10}" integerOnly="true"/>
   		<c:set var = "startPage" value = "${result * 10 +1 }"/>
   		<c:set var = "endPage" value="${startPage + pageBlock -1 }"/>
   		
   		
    <div style="text-align: center; margin-top: 30px; font-size: 20px">
    <c:if test="${endPage > pageCount}">
    	<c:set  var= "endPage" value = "${pageCount}"/>
	</c:if>
	
	<c:if test="${startPage >10}">
	<a href = "/thk/memberAdmin.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/memberAdmin.cp?Pnum=${i}">[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/memberAdmin.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
    
    </div>
    </c:if>
    
			</div>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>

