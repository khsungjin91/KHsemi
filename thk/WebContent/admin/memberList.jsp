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
			<c:out value="${memId}" />님 환영합니다!
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

    <div>글목록 (전체글 : ${Count}개)</div>
    
    <div><a href="/thk/borderWrite.cp">글쓰기</a></div>
    <c:out value="${Count}"/>
    <c:if test="${Count == 0}">
    등록된 게시물이 없습니다.
    </c:if>
    <c:if test="${Count > 0}">
    
    <div>번호  제목 작성자 조회수 작성일자</div>
    
    
   
    <c:forEach var="li" items="${memlist}">
    <div>  번호 <c:out value="${number}"/>
      <c:set var="number" value="${number-1}"/>
   번호 ${li.mem_num}
   아뒤 ${li.mem_id}
   이름 ${li.mem_name}
   폰번 ${li.mem_phone}
   등급 ${li.mem_rate}
    합계 ${li.mem_sum}
    포인트 ${li.mem_point}
    가입일 ${li.reg_date} </div>

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
