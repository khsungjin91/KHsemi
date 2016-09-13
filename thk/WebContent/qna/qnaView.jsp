<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>


<style>
#section { width: 1200px; margin: 0 auto; padding-bottom:100px  }
#section2 {width: 600px; margin: 0 auto; }
#subject { margin-top:30px; height: 30px; }
#select { height: 30px;  font-size: 18px}
#content {width: 600px; height:300px; border: 1px solid black; overflow: hidden; }
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
	
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
	<section id="section">
		<section id="section2">
			
	<input type = "hidden" value="${Pnum}" name="Pnum">

<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">Q&A ����</div>

<div id="subject">
<div style="float: left; margin-right:200px ">�ۼ��� | |
${qnadto.writer}</div>
<div>�ۼ��� | |
${qnadto.reg_date}</div>
</div>

<div style="margin-bottom: 10px">
${qnadto.category1}[��б�] ${qnadto.subject} 
</div>

<div>������ǰ | |
${qnadto.category2} &gt;&gt; ${qnadto.category3}
</div> 

<div style="margin: 10px; font-size:18px; text-align: center; ">���ǳ���</div>
<div id="content">
${qnadto.content}
</div>
<div>�̸��� ${qnadto.email}</div>
<div>��ȭ��ȣ${qnadto.phone}</div>

<div style="float: right;">



<c:if test = "${id == 'owner' || id == qnadto.writer}">

	<div id="gita" class="button white small">
		<a href ="/thk/qnaFix.cp?num=${qnadto.num}&Pnum=${Pnum}" class="under">�ۼ���</a>
	</div>
	<div id="gita" style="float: right;">
		<input type = "button" value ="�ۻ���" name = "delete" 
	onclick = "javascript:window.location='/thk/qnaDelete.cp?num=${qnadto.num}'" class="button white small" >
	</div>
</c:if>

<c:choose>
	<c:when test="${id == 'owner'}">
	<div id="gita" class="button white small">
		<a href = "/thk/qnaList.cp?num=${Pnum}" class="under">�۸��</a>
	</div>
	</c:when>

	<c:otherwise>
	<div id="gita" class="button white small">
		<a href = "/thk/qnaMyList.cp?num=${Pnum}" class="under">�۸��</a>
	</div>
	</c:otherwise>
</c:choose>
</div>

<c:if test="${replecount > 0}">
<div style="margin-top:50px; ">
���Ǵ��

<div>�ۼ��� | | ${repledto.writer}</div>

<div>${repledto.content}</div>

<div>�ۼ����� | | ${retime}</div>

</div>
</c:if>

<c:if test = "${id == 'owner'}">
<div style="margin-top:50px; text-align: center">	
<b>���Ǵ亯</b>
</div>
<form action="/thk/repleWritePro.cp" method="post">
<input type="hidden" name="writer" value="${id}">
<input type="hidden" name="qna_num" value="${num}">

<div style="margin-top:20px"><textarea cols="83" rows="10" name="content"></textarea> </div>
<input type="submit" value="�亯">
</form>
</c:if>


		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>