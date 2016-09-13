<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>

<style>
#section { width: 1200px; margin: 80px auto;}
#loginarea {width:90%; height:300px; margin: 0 auto; border: 1px solid black; padding: 40px}

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
	<div style="margin-top: 40px; margin-bottom:40px; text-align: center; font-size: 30px ">�α���</div>
<div id="loginarea">
<c:choose>
	<c:when test="${memId ne null }">
  
  		
        		<p style="font-size: 20px;"> <c:out value="${memId}" />�� ȯ���մϴ�.</p>
        		
	      		
	      	<div style="text-align: center;">
	      		<div class="button white small">
	      		<a href="memberInfo.cp" class="under">My Page</a>
	      		</div>
	      		<div class="button white small">
	      		<a href="logout.cp" class="under">�α׾ƿ�</a>
				</div>	   
  			</div>	
	</c:when>
  
	<c:otherwise>
	
    	<c:if test="${requestScope.check eq 0}">
    
		<script type="text/javascipt">
			alert('��й�ȣ�� Ʋ�Ƚ��ϴ�.')
		</script>
   		</c:if>
   		
   		<c:if test="${requestScope.check eq -1}">
			<script type="text/javascipt">
				alert('���̵� �������� �ʽ��ϴ�.')
			</script>
   		</c:if>
    
  	
   <div id="textarea">	
		<form name="login" action="/thk/loginPro.cp" method="post" onSubmit="return checkIt()">
    	<table style="margin-top:60px; height:60%; width:35% ">
    		<tr>
    		<td colspan="2" style="height:15px; font-size:20px ">ȸ���α���</td>
    		</tr>
    		<tr>
    		<td style="height:8px; width:100px; font-size:20px">���̵�</td>
    		<td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_id" size="20" class="text"></td>
    		<td rowspan="2" style="height:16px; width:50px"><input type=submit value="�α���" class="button white middle"></td>
     		</tr>
     		<tr>
     		<td style="height:8px; width:100px; font-size:20px">�н�����</td>
     		<td style="height:8px; width:80px; font-size:15px"><input type="password" name="mem_pw" size="20" class="text"></td>
        	</tr>
        	<tr>
        	
        	<td colspan="3" align="center" style="height:20px; "><div class="button white small">
        		<a href="/thk/findme.cp" class="under">���̵� & �н����� ã��</a>
        	</div>
        	<div class="button white small">
        		<a href="/thk/joinForm.cp" class="under">ȸ������</a>
     		</div></td>
     		
     		</tr>
     	</table>
     	</form>
    </div>
		
		
	</c:otherwise>
</c:choose>
</div>
</section>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>