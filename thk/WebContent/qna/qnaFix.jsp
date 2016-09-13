<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>

<style>
#section { width: 1200px; margin: 0 auto; padding-bottom: 100px}
#section2 {width: 650px; margin: 0 auto; }
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
		alert("������ �Է��� �ּ���");
		return false;
	}
	if(!userinput.pw.value){
		alert("��й�ȣ�� �Է����ּ���");
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
<form action="/thk/qnaFixPro.cp?num=${fixdto.num}" method="post" name="userinput" onSubmit="return checkIt();">

<input type = "hidden" value="${Pnum}" name="Pnum">

<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">Q&A ����</div>

	<div style="margin-top: 30px">
	<b>�ۼ���</b> | | ${fixdto.writer}
	</div>

	<div>
	[${fixdto.category1}] | | ���� <input type="text" value= "${fixdto.subject}" name="subject" size="60" height="25" >
	</div>
	
	<div>
	<b>������ǰ</b> | | ${fixdto.category2} &gt;&gt; ${fixdto.category3} 
	</div>
	
	<div style="margin: 10px; font-size:18px; text-align: center; ">����</div>	
	
	<div id="content">
	<textarea name="content" cols="85" rows="20">${fixdto.content}</textarea>
	</div>
	
	<div id="gita">
	�̸��� <input type="text" value="${fixdto.email}" name="email">
	</div>
	<div id="gita">
	��ȭ��ȣ <input type="text" value="${fixdto.phone}" name="phone">
	</div>
	
<div style="float: right;">
	
	
	<input type="submit" value="����" class="button white small" id="gita" >
	
		
	<div id="gita" class="button white small">
	<a href="/thk/qnaView.cp?num=${fixdto.num}" class="under">�ۺ���</a>
	</div>

	<div id="gita" class="button white small">
	<a href = "/thk/qnaList.cp?Pnum=${Pnum}" class="under">�۸��</a>
	</div>

</div>


</form>
		</section>
</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>