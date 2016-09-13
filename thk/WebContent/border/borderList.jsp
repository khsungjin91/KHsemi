<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#section { width: 1200px; margin: 0 auto;  padding-bottom:50px  }
tr { text-align: center;}
#center {margin: 0 auto; text-align: center;} 
#center a {text-decoration: none; font-size: 20px; color:black}
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
	padding: .5em 2em .275em

}
.under{
 text-decoration: none;
 color:black;
}
.top{
float: left;
}

</style>

</head>
<body>


<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	<section id="section">
	
    <div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px">공지사항 (전체글 : ${Count})</div>
    
    <c:if test="${id == 'owner'}"> 
    <a href="/thk/borderWrite.cp" class="button white small under" style="float: right; margin-right: 50px; margin-bottom: 30px ">글쓰기</a>
   	</c:if>
    <c:if test="${id != null && id != 'owner'}">
    <div style="float: right; margin-right: 50px; margin-bottom: 30px; ">
    <span style=" font-size:20px; font-weight:bold;" >${id}</span>님 환영합니다.</div>
    </c:if>
    <c:if test="${id == null}">
  	<div style="float: right; margin-right: 50px; margin-bottom: 30px ">환영합니다.</div>
    </c:if>
    
    
    <c:if test="${Count == 0}">
    
      <table width="1100">
    	<tr>
    		<td style="height:40px; width:50px">번호</td>
    		<td style="height:40px; width:700px">제목</td>
    		<td style="height:40px; width:150px">작성자</td>
    		<td style="height:40px; width:60px">조회수</td>
    		<td style="height:40px; width:300px">작성일자</td>
    	</tr>
		<tr>
  			<td colspan="5" style="height:40px;"> 등록된 게시물이 없습니다.</td> 
    	</tr>
    </table>
    
    </c:if>
    
    <c:if test="${Count > 0}">
    <table width="1100" >
    
    <tr>
   		<td style="height:40px; width:50px">번호</td>
    	<td style="height:40px; width:700px">제목</td>
    	<td style="height:40px; width:150px">작성자</td>
    	<td style="height:40px; width:60px">조회수</td>
    	<td style="height:40px; width:300px">작성일자</td>
    </tr>
    <c:forEach var="li" items="${list}">
  	<tr>
		<td style="height:30px; width:50px "><c:out value="${number}"/><c:set var="number" value="${number-1}"/></td>
		<td style="height:30px; width:700px ">
		<a href ="/thk/borderView.cp?num=${li.num}&Pnum=${thisPage}" style="text-decoration: none; color:black">${li.subject}</a></td>
		<td style="height:30px; width:150px ">${li.writer}</td>
		<td style="height:30px; width:60px "></td>
		<td style="height:30px; width:300px ">${li.reg_date}</td>
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
   		
   		
    
    <c:if test="${endPage > pageCount}">
    	<c:set  var= "endPage" value = "${pageCount}"/>
	</c:if>
<div id="center"> 	
	<c:if test="${startPage >10}">
	<a href = "/thk/borderList.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/borderList.cp?Pnum=${i}" >[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/borderList.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
</div>   
    </c:if>
    
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>
