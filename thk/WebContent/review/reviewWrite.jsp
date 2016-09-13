<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>


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
		alert("��ǰ�ڵ带 �Է��� �ּ���");
		return false;
	}
	if(!userinput.subject.value){
		alert("������ �Է��� �ּ���");
		return false;
	}
	if(!userinput.content.value){
		alert("������ �Է��� �ּ���");
		return false;
	}
}
</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	
	<section id="section">
			<section id="section2">
		
	
			<h1>�����ۼ�</h1>


<form action="/thk/reviewWritePro.cp" method="post" name="userinput" onSubmit="return checkIt();"
		enctype = "multipart/form-data">

<input type="hidden" value="${mem_num}" name="mem_num">
<input type="hidden" value="${num}" name="num">
<input type="hidden" value="${id}" name="mem_id">
<input type="hidden" value="review" name="uri">
��ǰ�̸�

<select name="p_code">

<c:forEach var="codelist" items="${pcode}">

<option value="${codelist.p_code}">${codelist.p_name}</option>

</c:forEach>
</select>



����<input type="text" name="subject" size="50"><br/>

���� <input type="file" name="save"><br/>

<div style="margin: 10px; font-size:18px; text-align: center; ">���䳻��</div>

<div><textarea rows="20" cols="80" name="content"></textarea></div>

<div style="margin-top:20px ">
����   <c:forEach var="i" begin="1" step="1" end="5">
     <input type="radio" name="star" value="${i}" title="${i}��"/>${i}��
     </c:forEach>
</div>
<div style="margin-top:20px; float: right;">
	<input type="submit" value="�ۼ�" class="button white small" >
	<input type="reset" value="�ٽ��ۼ�" class="button white small">
	<input type="button" value="�������" onclick="javascript:window.location='/thk/reviewList.cp'" class="button white small">
</div>
</form>
		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>