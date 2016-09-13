<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#section { width: 1200px; margin: 0 auto;  padding: 100px }
#section2 {width: 400px; margin: 0 auto;  }
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

function redelete(){

if (confirm("삭제하시겠습니까??")) {
    self.location.href = "/thk/reviewDeletePro.cp?num=${num}";
	}else {
	self.location.href = "/thk/reviewContent.cp?num=${num}";
	}
}

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
	
	<section id="section">
		<section id="section2" style="text-align: center; font-size: 18px">

		
<input type="hidden" name="num" value="${num}">
	

		<div style="margin-bottom: 30px;">제목 | | ${re.subject}  ${re.mem_num}</div>

		<div style="margin-bottom: 30px;">작성일자 | | ${re.reg_date}</div>
	
	
<c:if test="${re.img == null}">
<div><img src="/thk/save/noimg.gif" width="400" height="300"></div> 
</c:if>

<c:if test="${re.img != null}">
<div><img src="/thk/save/${re.img}" width="400" height="300"></div> 
</c:if>


<div style="margin-bottom: 20px">
<div style="float: left;">상품이름 | | ${re.p_name} </div>
<div>작성자 | | ${re.mem_id}</div>
</div>

<div style="margin-bottom: 30px">
<div style="float: left;">조회수 | |  ${re.readcount}</div>
<div style="margin-right: -35px">평가 | | ${re.star} 점</div> 
</div>

</section>

<div style="margin: 10px; font-size:18px; text-align: center; ">리뷰내용</div>
<div style="margin:0 auto;  width: 600px;">${re.content}</div>

		

<div style="margin-top:50px; margin-right:300px ">

	<div class="button white small" style="float: right;" >
		<a href="/thk/reviewList.cp" class="under" >글목록</a>
	</div>
	<c:if test="${re.mem_id == id || sessionScope.memId == 'owner'}">
		<input type="button" value="글삭제" onclick="return redelete();"  class="button white small" id="under" style="float: right;" >
	<div class="button white small" style="float: right;" >
		<a href="/thk/reviewFix.cp?num=${num}" class="under">글수정</a>
	</div>
	</c:if> 

</div>

		
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>