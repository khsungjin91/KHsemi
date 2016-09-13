<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><title>:Thk.ment ${subject}:</title>
<style>

#section { width: 1200px; margin: 0 auto; padding-bottom:100px }
#section2 {width: 600px; margin: 0 auto; }
#subject { margin-top:30px; height: 30px; }
#select { height: 30px;  font-size: 18px}
#content {width: 600px; height:300px; border: 1px solid black; }
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

function redelete(){

if (confirm("�����Ͻðڽ��ϱ�??")) {
    self.location.href = "/thk/borderDeletePro.cp?num=${viewdto.num}";
	}else {
	self.location.href = "/thk/borderView.cp?num=${num}";
	}
}

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
  
  	
	<section id="section">
			<section id="section2">
				

<input type = "hidden" value="${Pnum}" name="Pnum">

<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">��������</div>

<div id="subject">
	
		
		<div style="float: left; margin-right:200px ">	�ۼ��� | | ${viewdto.writer}</div>			
		<div>	�ۼ��� | | ${viewdto.reg_date}</div>
		
	</div>
</div>

<div>���� | | ${viewdto.subject}</div>	
<div style="margin: 10px; font-size:18px; text-align: center; ">�۳���</div>		
<div id="content">${viewdto.content}</div>	



<div style="float: right;">
<c:if test="${sessionScope.memId == 'owner'}">
	<div id="gita" class="button white small">
		<a href ="/thk/borderFix.cp?num=${viewdto.num}&Pnum=${Pnum}" class="under">�ۼ���</a>
	</div>
	<div id="gita" style="float: right;">
		<input type="button" value="�ۻ���" onclick="return redelete();" class="button white small">
	</div>
</c:if>
	<div id="gita" class="button white small">
		<a href = "/thk/borderList.cp?num=${Pnum}" class="under">�۸��</a>
	</div>
</div>
		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>
    