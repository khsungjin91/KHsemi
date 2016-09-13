<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>

<style>
#section { width: 1200px; margin: 0 auto; }
#loginarea {width:1000px;  margin: 0 auto; padding: 60px}

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

p {font-size: 20px; font-weight: bold; margin-left:150px;} 
table {margin-bottom:50px}
</style>
</head>

<body>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<c:if test="${memId != null }">

<script>

alert("�α��λ����Դϴ�.");
history.go(-1);

</script>

</c:if>

<c:if test="${memId == null }">
  
<section id="section">
	<div style="margin-top: 40px; margin-bottom:40px; text-align: center; font-size: 30px ">���̵� & ��й�ȣ ã��</div>
<div id="loginarea">
   
  <p>���̵� ã��</p>
 <form name="findId" action="findIdPro.cp" method="post" >
    <table width="500" >
      <tr>
        <td style="height:8px; width:100px; font-size:15px;"> �̸� </td>
        <td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_name" size="20" class="text"></td>
        <td rowspan="2" style="height:16px; width:50px"><input type="submit" value="���̵� ã��" class="button white middle"></td>
      </tr>
      <tr>
        <td style="height:8px; width:100px; font-size:15px;">�̸���</td>
        <td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_email" size="20" class="text"></td>
      </tr>
 
    </table>
</form>
   
   <p>��й�ȣ ã��</p>
   <form name="findPw" action="findPwPro.cp" method="post" >
    <table width="500" >
      <tr>
        <td style="height:8px; width:100px; font-size:15px;"> ���̵� </td>
        <td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_id" size="20" class="text"></td>
        <td rowspan="3" style="height:16px; width:50px"><input type="submit" value="��й�ȣ ã��" class="button white middle"></td>
      </tr>
      <tr>
        <td style="height:8px; width:100px; font-size:15px;"> �̸� </td>
        <td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_name" size="20" class="text"></td>
        
      </tr>
      <tr>
        <td style="height:8px; width:100px; font-size:15px;">�̸���</td>
        <td style="height:8px; width:80px; font-size:15px;"><input type="text" name="mem_email" size="20" class="text"></td>
      </tr>
      </table>
     </form>
      
      
     
        	<div class="button white small" style="float: right; margin-right: 300px; margin-top: 30px">
        		<a href="/thk/loginForm.cp" class="under">�α���</a>
        	</div>
      
   </div>

</section>
</c:if>
      
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>