<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>


<style>
#section { width: 1400px; margin: 0 auto; }
h1 {margin: 0 auto; text-align: center;}
tr {float:left; width:320px; height:350px; margin: 5px; border: 3px solid gray; }
#reviewinfo ul li { display:block; margin-left:-30px; }
#center {margin: 0 auto; text-align: center;} 
#center a {text-decoration: none; font-size: 20px}
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


</style>

</head>

<body>
<script type="text/javascript">

function checkbuy(){
	
	alert("상품구매 후 리뷰작성이 가능합니다.");
	
	
}

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<section id="section">

		
			
<h1 style="margin-top:30px">리뷰게시판</h1>



<c:if test="${id == null}">
<a href="/thk/loginForm.cp" class="button white small under" style="float: right; margin-right: 50px">로그인</a>
</c:if>

<c:if test="${id != null}">

<c:if test="${possreview == 0 }">
<input type="button" value ="글쓰기" onclick="return checkbuy();" class="button white small" style="float: right; margin-right: 50px ">
</c:if>


<c:if test="${possreview != 0 }">
<a href="/thk/reviewWrite.cp?mem_num=${mem_num}"  class="button white small under" style="float: right; margin-right: 50px ">글쓰기</a>
</c:if>

</c:if>



<br/>


<c:if test="${Count == 0 }">

<p style="text-align: center; padding:5px ">작성된 상품리뷰가 없습니다.</p>
	
</c:if>



<c:if test="${Count != 0 }">
<table style="padding-left: 30px">
<c:forEach var="relist" items="${relist}" >
	<tr>
		<td>
		<c:if test="${relist.img != null}">
			<a href="/thk/reviewContent.cp?num=${relist.num}"><img src="/thk/save/${relist.img}" width="290" height="200" style="margin-left:15px " ></a>
		</c:if>
		<c:if test="${relist.img == null}">
 				<img src="/thk/save/noimg.gif" width="290" height="200" style="margin-left:15px ">
		</c:if>
		<div id="reviewinfo" >
			<ul>
				<li><a href="/thk/reviewContent.cp?num=${relist.num}" style="text-decoration: none; color: black;">
				${relist.subject} </a>
					<c:if test="${relist.readcount >= 20}">
					<img src="/thk/save/icon/best.png" width="40">
					</c:if>
				</li>
				<li>상품이름 &nbsp; ${relist.p_name}</li>
				<li>작성자 &nbsp; ${relist.mem_id}</li>
				<li>조회수 &nbsp; ${relist.readcount}</li>
				<li>상품평점 &nbsp; ${relist.star}</li>
			</ul>
		</div>
		</td>
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
	<a href = "/thk/reviewList.cp?Pnum=${startPage-10}"> &lt; </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<a href = "/thk/reviewList.cp?Pnum=${i}">[${i}]</a> 
	</c:forEach>
	
    <c:if test="${endPage < pageCount}">
    <a href = "/thk/reviewList.cp?Pnum=${startPage+10}"> &gt; </a>
    </c:if>
</div>   
    </c:if>



<form action="/thk/search.cp" method="get">

<div id="center" style="margin: 5px 10px">
	<input type="hidden" value="${mem_num}" name="mem_num">
	검색 : <input type="text" name="search" size="30" style="height:30px ; font-size: 15px">
	<select name="item">
	<option value="">조건검색</option>
	<option value="p_name">상품이름</option>
	<option value="mem_id">작성자</option>
	<option value="subject">제목</option>
	</select>
<input type= "submit" value="검색">
</div>

</form>

	</section>
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>