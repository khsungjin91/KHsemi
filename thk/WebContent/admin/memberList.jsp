<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

    <html>
    <head>
    <title>:Thk.ment:</title>
    <style>
p{
 border:solid 1px blue; 
}
div{
 margin:20px; border:solid 1px green;
}
</style>
    </head>
<nav id="logon">
<c:choose>
  <c:when test="${memId eq null }">
		<input type="button" value="login" onclick="javascript:window.location='loginForm.cp'">
		<input type="button" value="join" onclick="javascript:window.location='joinForm.cp'">
	</c:when>
	<c:otherwise>
		<c:if test="${memId eq owner}">
			<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
			<input type="button" value="owner" onclick="javascript:window.location='owner.cp'">
		</c:if>
		<c:if test="${memId ne owner}">
			<c:out value="${memId}" />�� ȯ���մϴ�!
			<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
			<input type="button" value="My Page" onclick="javascript:window.location='memberInfo.cp'">
		</c:if>
	</c:otherwise>
</c:choose>
</nav>
	
	
	<header id="header">
		<a href="/thk/main.cp">Th.K Ment
	</header>
	
	
	<nav id="menu">
		<ul>
			<li><a href="/thk/about.cp">ABOUT</a></li>
			<li><a href="/thk/guide.cp">GUIDE</a></li>
			<li><a href="/thk/agreement.cp">AGREEMENT</a></li>
			<li><a href="/thk/private.cp">PRIVATE</a></li>
			<li><a href="/thk/borderList.cp">BORDER</a></li>
		</ul>
	</nav>

    <div>�۸�� (��ü�� : ${Count}��)</div>
    
    <div><a href="/thk/borderWrite.cp">�۾���</a></div>
    <c:out value="${Count}"/>
    <c:if test="${Count == 0}">
    ��ϵ� �Խù��� �����ϴ�.
    </c:if>
    <c:if test="${Count > 0}">
    
    <div>��ȣ  ���� �ۼ��� ��ȸ�� �ۼ�����</div>
    
    
   
    <c:forEach var="li" items="${memlist}">
    <div>  ��ȣ <c:out value="${number}"/>
      <c:set var="number" value="${number-1}"/>
   ��ȣ ${li.mem_num}
   �Ƶ� ${li.mem_id}
   �̸� ${li.mem_name}
   ���� ${li.mem_phone}
   ��� ${li.mem_rate}
    �հ� ${li.mem_sum}
    ����Ʈ ${li.mem_point}
    ������ ${li.reg_date} </div>

    </c:forEach>
    </c:if>
    
    
    
    
    <c:if test="${Count > 0}">
    	<c:set var="pageCount" value="${Count /Psize + (Count % Psize == 0?0:1)}"/>
   		<c:set var="pageBlock" value="${10}"/>
   		<fmt:parseNumber var="result" value="${thisPage/10}" integerOnly="true"/>
   		<c:set var = "startPage" value = "${result * 10 +1 }"/>
   		<c:set var = "endPage" value="${startPage + pageBlock -1 }"/>
   		
   		
    
    <c:if test="${endPage > pageCount}">
    	<c:set  var= "endPage" value = "${pageCount}"/>
	</c:if>
	
	<c:if test="${startPage >10}">
	<a href = "/thk/borderList.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/borderList.cp?Pnum=${i}">[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/borderList.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
    
    </c:if>
    
    
    
    
    </body>
    
    </html>
