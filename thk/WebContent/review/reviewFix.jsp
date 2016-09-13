<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto;  padding: 80px }
#section2 {width: 600px; margin: 0 auto; }
#subject { margin-top:30px; height: 30px; font-size: 18px}
#select { height: 30px;  font-size: 18px}
#content {}
#gita {margin-top: 20px}

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
function checkIt(){
	
	var userinput = eval(document.userinput);
	
	if(!userinput.p_code.value){
		alert("상품코드를 입력해 주세요");
		return false;
	}
	if(!userinput.subject.value){
		alert("제목을 입력해 주세요");
		return false;
	
	}
	if(!userinput.content.value){
		alert("내용을 입력해 주세요");
		return false;
	}
}
</script>

	
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
	<section id="section">

		<section id="section2">
		
		
			<h1>리뷰수정</h1>

<form action="/thk/reviewFixPro.cp?num=${refix.num}" method="post" name="userinput" onSubmit="return checkIt();"
		enctype = "multipart/form-data">


<input type="hidden" value="${refix.num}" name="num">
<input type="hidden" value="${refix.mem_id}" name="writer">



상품이름

<select name="p_code">

<c:forEach var="codelist" items="${pcode}">

<option value="${codelist.p_code}">${codelist.p_name}</option>

</c:forEach>
</select>


제목<input type="text" name="subject" size="50" value="${refix.subject}"><br/>

사진 	<input type="file" name="save"><br/>

<div style="margin: 10px; font-size:18px; text-align: center; ">수정내용</div>

	<textarea rows="20" cols="80" name="content">${refix.content}</textarea><br/>

<div style="margin-top:20px ">
평점   <c:forEach var="i" begin="1" step="1" end="5">
     <input type="radio" name="star" value="${i}" title="${i}점"/>${i}점
     </c:forEach>
</div>

<div style="margin-top:20px; float: right;">
	<input type="submit" value="수정하기" class="button white small">
	
	<input type="button" value="목록으로" class="button white small"
			onclick="javascript:window.location='/thk/reviewList.cp'">
</div>

</form>


		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>