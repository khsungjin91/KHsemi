<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>


<style>
#section { width: 1200px; margin: 0 auto; padding-bottom: 80px}
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
	if(!userinput.subject.value){
		alert("제목을 입력해 주세요");
		return false;
	}

	if(!userinput.content.value){
		alert("내용을 입력해 주세요");
		return false;
	}
}
	
	
function doChange(srcE, targetId){
	   var val = srcE.value;
	   var targetE = document.getElementById(targetId);
	   targetE.length = 0;
	   
	     switch (val) {
	       case "nike" :
	         addOption(['점핑슈즈'],targetE);
	         addOption(['에어'],targetE);
	         addOption(['조던'],targetE);
	       break;

	       case "adidas" :
	         addOption(['슈퍼스타'],targetE);
	         addOption(['단화'],targetE);
	         addOption(['클래식'],targetE);
	       break;
	   
	       case "newbal" :
		     addOption(['조깅'],targetE);
		     addOption(['런닝'],targetE);
		     addOption(['하이킹'],targetE);
		   break;
	     }
}
	     
function addOption(text, obj) {
	    obj.options[obj.length] = new Option(text[0]);
}

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
	<section id="section">		

		
<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">Q&A 질문하기</div>

<form action = "/thk/qnaWritePro.cp" method="post" name="userinput" onSubmit="return checkIt();">
<input type = "hidden" name="num" value="${num}"/>
<input type = "hidden" name="writer" value="${id}">
<section id="section2">
<div id="subject">
제목
<select name="category1">
<option value="">문의항목</option>
<option value = "배송문의">배송문의</option>
<option value = "사이즈문의">사이즈문의</option>
<option value = "불만사항">불만사항</option>
<option value = "교환질문">교환질문</option>
<option value = "기타">기타</option>
</select>
<input type="text" name="subject"  size="60" height="25">
</div>

<div id="select">
	상세품목
	<select name="category2" id="sel1" onchange="doChange(this, 'sel2')">
		<option value="default">---select---</option>
		<option value="nike">나이키</option>
		<option value="adidas">아이다스</option>
		<option value="newbal">뉴발란스</option>
	</select>
	<select name="category3" id="sel2">
		<option value="default">---select---</option>
	</select>
</div>

<div style="margin: 10px; font-size:18px; text-align: center; ">문의내용</div>

<div id="content">
	<textarea name="content" cols="85" rows="20"></textarea>
</div>


<div id="gita">
이메일<input type = "text" name="email" value="${info.mem_email }">
</div>
<div id="gita">
전화번호<input type = "text" name="phone"  value="${info.mem_phone }">
</div>

<div style="float: right;">
	
		<input type="submit" value="문의하기" name="submit" class="button white small" id="gita">

		<input type="reset" value="다시쓰기" name="reset" id="gita" class="button white small">
		
<c:choose>
	<c:when test="${id == 'owner'}">
	<div id="gita" class="button white small">
		<a href = "/thk/qnaList.cp?num=${Pnum}" class="under">글목록</a>
	</div>
	</c:when>

	<c:otherwise>
	<div id="gita" class="button white small">
		<a href = "/thk/qnaMyList.cp?num=${Pnum}" class="under">글목록</a>
	</div>
	</c:otherwise>
</c:choose>
</div>
</section>
	</form>


	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>