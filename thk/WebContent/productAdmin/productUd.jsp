<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto;  }
#section td {height:40px; font-size: 18px;}
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

</style>

</head>
<body>
<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>

	
	<section id="section">
	
<form name="productUd" action="/thk/productUdPro.cp" method="post" >
			<div style="text-align: center; font-size: 25px;  margin-top: 30px"><b>��ǰ����</b></div>

  <table style="width:700px; margin-left: 300px" > 

   <tr>
     <td>��ǰ�ڵ�</td>
     <td align="left"><input type="hidden" name="p_code" value="${article.p_code}">${article.p_code}</td>
   </tr>
   <tr>
     <td>��ǰ��</td>
     <td align="left"><input type="text" name="p_name" value="${article.p_name}"></td>
   </tr>
   <tr>
     <td>����</td>
     <td align="left"><input type="text" name="p_material" value="${article.p_material}"></td>
   </tr>         
   <tr>
     <td>�����</td>
     <td align="left"><img src="/thk/save/${article.p_thumbnail}" width="100"></td>
   </tr>
   <tr>
     <td>���̹���</td>
     <td align="left"><img src="/thk/save/${article.p_img}" width="100"></td>
   </tr>
   <tr>
     <td>����</td>
     <td align="left"><input type="text" name="p_price" value="${article.p_price}"></td>
   </tr>
   <tr>
     <td>���ΰ�</td>
     <td align="left"><input type="text" name="p_price_dc" value="${article.p_price_dc}"></td>
   </tr>
   <tr>
     <td>����Ʈ</td>
     <td align="left"><input type="text" name="p_point" value="${article.p_point}"></td>
   </tr>
   <tr>  
     <td>ī�װ�</td>
     <td align="left">1�� <input type="text" name="p_category1" value="${article.p_category1}" size="12"> >
         2�� <input type="text" name="p_category2" value="${article.p_category2}" size="12"> >
         3�� <input type="text" name="p_category3" value="${article.p_category3}" size="12"></td>
   </tr>
   <tr>  
     <td colspan="2"  align="left">
       <input type="submit" value="����" class="button white small">
       <input type="button" value="����Ϸ�����" class="button white small"
       	OnClick="document.location.href='/thk/productAdmin.cp'">
     </td>
   </tr>
 </table>
</form>
	
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>