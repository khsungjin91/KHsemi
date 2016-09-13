<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>

<style>
#section { width: 1200px; margin: 80px auto;}
#loginarea {width:90%; height:300px; margin: 0 auto; border: 1px solid black; padding: 40px}
#article { width: 964px; margin: 20px auto; border: 1px solid black; }
#product_List {width: 300px; height: 250px; margin: 7px 7px; border: 1px solid black; float: left; }
#product { width: 900px; margin: 20px auto; border: 1px solid black; }

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

.middle{
	font-size:15px;
	padding: 1.5em 1em 1.5em;
}

.small{
	font-size:13px;
	padding: .2em 1em .275em

}

.under{
 text-decoration: none;
 color:black;
 float: right;
}

.text{
height:25px;
font-size:15px
}

p { margin-top: 50px; margin-bottom:30px; text-align: center; }
</style>
</head>

<body>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
  
<section id="section">
<div id="loginarea">
    
<div id="textarea">	
  <form name="idCheck" method="post" >
  <c:if test="${mem_id != null }">
 
      <p style="font-size: 20px;">  ' ${mem_name} ' 회원님의 비밀번호는 ' ${mem_pw} ' 입니다! </p>

    <div style="text-align: center;">
            <div class="button white small">
        		<a href="/thk/loginForm.cp" class="under">로그인하기</a>
  			</div>
  	</div>
  	
  </c:if>  
 <c:if test="${mem_id == null }">
  <script>
         alert("이름 또는 메일이 맞지 않습니다.");
         history.go(-1);
     </script>
     </c:if>
  
   </form>
   </div>

</div>
</section>
      
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>

