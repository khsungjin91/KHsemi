<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style type="text/css">

#section {width: 1200px; margin: 0 auto; margin-top: 50px}
#section td {text-align: center; border: 1px solid gray;}
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
<script type="text/javascript">
function redelete(){

if (confirm("삭제하시겠습니까??")) {
    self.location.href = "/thk/eventDeletePro.cp?num=${num}";
	}else {
	self.location.href = "/thk/eventContent.cp?num=${num}";
	}
}

</script>
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>


	<div id="section">
	
		<div style="margin-bottom: 50px; font-size: 25px; text-align: center"><b>이벤트내용</b></div>
<table>
		<tr>
			<td style="height:30px; width: 50px ">번호</td>
    		<td style="height:30px; width: 700px ">글제목</td>
			<td style="height:30px; width: 150px ">작성자</td>
			<td style="height:30px; width: 70px ">조회수</td>
    		<td style="height:30px; width: 300px ">작성일</td>
		</tr>
		<tr>
			<td style="height:30px; width: 50px">${article.num}</td>
    		<td style="height:30px; width: 700px">${article.subject}</td>
    		<td style="height:30px; width: 150px">${article.writer}</td>
			<td style="height:30px; width: 70px">${article.readcount}</td>
    		<td style="height:30px; width: 300px">${article.reg}</td>
		</tr>
  		<tr>
    		<td colspan="5" style="border: 1px solid white">
    		<img src="/thk/save/${article.img}"><br/>
    		<pre>${article.content}</pre></td>
  		</tr>
</table>
   
   			 
	<div style="float: right; padding: 30px"> 
	<c:if test="${sessionScope.memId == 'owner'}"> 
	  		<input type="button" value="글수정"  class="button white small"
	  			onclick="window.location='/thk/eventUpdateForm.cp?num=${article.num}'">
	  		
	  		<input type="button" value="글삭제"  class="button white small"
	  			onclick="return redelete();">
	   	
	</c:if>  
	
       		<input type="button" value="글목록" class="button white small"
       			onclick="document.location.href='/thk/eventList.cp?pageNum=${pageNum}'">
	</div>
	
	</div>
	
 <jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>	
	</body>
</html>