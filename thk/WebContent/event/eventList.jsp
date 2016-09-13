<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<style type="text/css">
#section {width: 1200px; margin: 0 auto;}
#section td {text-align: center;}
.button{
	display: inline-block;
	zoom: 1;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	text-align: center;
	line-height: 100 %;
	font-family: 'Jeju Gothic', sans-serif;
	text-shadow: 0 1px 1px rgba(0,0,0,.3);
	border-radius:8px;
	box-shadow:0 1px 2px #444;
}

.white{
    color: black;
    border: solid 1px #444;
    background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#fff));
}

.small{
	font-size:13px;
	padding: .2em 1em .275em

}

.under{
 text-decoration: none;
 color:black;
}
</style>

</head>

<body>
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	<div style="text-align: center; font-size: 30px; margin-top: 50px"><b>E V E N T</b></div>
						<div style="font-size: 15px ; text-align: center; ">전체 글 수 (${count})</div>
	
<div id="section">
	
	<c:if test="${sessionScope.memId == 'owner' }">
	
	<div><a href="/thk/eventWrite.cp" class="button white small under" 
		style="float: right; margin-top: 30px ">글쓰기</a></div>
		
	</c:if>
	
	<div style="padding-top:50px; margin-right: -40px">
	<table>
	<c:if test="${count == 0 }">
	<tr>
		<td height="40">
			현재 진행 중인 이벤트가 없습니다
		</td>
	</tr>

	</c:if>
	
	<c:if test="${count != 0 }">
	
    <tr> 
      <td style="height:40px; width: 50px ">번호</td> 
      <td style="height:40px; width: 700px " >제목</td> 
      <td style="height:40px; width: 150px ">작성자</td>
      <td style="height:40px; width: 70px " >조회수</td> 
      <td style="height:40px; width: 300px " >작성일</td> 
 	</tr>

	<c:forEach var="article" items="${articleList}"> 
	
	<tr>
   		<td style="height:30px; width: 50px ">
   			<c:out value="${number }"/>
   			<c:set var="number" value="${number -1 }"/>
   		</td>
   		<td style="height:30px; width: 700px ">
   		<a href="/thk/eventContent.cp?num=${article.num}&pageNum=${currentPage}" style="color: black; text-decoration: none;">
   		${article.subject}</a>
   		</td>
    	<td style="height:30px; width: 150px ">${article.writer}</td>
    	<td style="height:30px; width: 70px ">${article.readcount}</td>		
    	<td style="height:30px; width: 300px ">${article.reg}</td>
    </tr>
    
    
    </c:forEach>
	</c:if>
    </table>
	</div>
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
		<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
		<c:set var="startPage" value= "${result * 10 + 1 }" />
		<c:set var="pageBlock" value="${10}"/>
		<c:set var="endPage" value="${startPage + pageBlock -1}"/>
		
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		<div style="text-align: center; font-size: 18px; margin-top: 30px">	
		<c:if test="${startPage > 10 }">
	      <a href="eventList.cp?pageNum=${startPage - 10 } ">[이전]</a>
		</c:if>
			
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
		    <a href="eventList.cp?pageNum=${i}">[${i}]</a> 
		</c:forEach>

		<c:if test="${endPage < pageCount }">
			<a href="eventList.cp?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
		</div>
    </c:if>
    </div>
    
    <jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
 </body>
</html>

