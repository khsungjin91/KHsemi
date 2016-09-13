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
#section td {height:40px; font-size: 18px; text-align: center}
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
<script type="text/javascript">

function prodelete(){

if (confirm("�����Ͻðڽ��ϱ�??")) {
    self.location.href = "/thk/productDelPro.cp?p_code=${article.p_code}";
	}else {
	self.location.href = "/thk/productCt.cp?p_code=${article.p_code}";
	}
}

</script>



	<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>
	
	<section id="section">
		
<form>
  <table  border=1  style="width:600px; margin: 0 auto"> 
   <tr>
     <td align="center" colspan="2">��ǰ</td>
   </tr>
   <tr>
     <td>��ǰ�ڵ�</td>
     <td>${article.p_code}</td>
   </tr>
   <tr>
     <td>��ǰ��</td>
     <td>${article.p_name}</td>
   </tr>
   <tr>
     <td>����</td>
     <td>${article.p_material}</td>
   </tr>         
   <tr>
     <td>�����</td>
     <td><img src="/thk/save/${article.p_thumbnail}" width="100"></td>
   </tr>
   <tr>
     <td>���̹���</td>
     <td><img src="/thk/save/${article.p_img}" width="100"></td>
   </tr>
   <tr>
     <td>����</td>
     <td>${article.p_price}</td>
   </tr>
   <tr>
     <td>���ΰ�</td>
     <td>${article.p_price_dc}</td>
   </tr>
   <tr>
     <td>����Ʈ</td>
     <td>${article.p_point}</td>
   </tr>
   <tr>  
     <td>ī�װ�</td>
     <td>1�� : ${article.p_category1}<br/>
         2�� : ${article.p_category2}<br/>
         3�� : ${article.p_category3}</td>
   </tr>
   <tr>  
     <td>�����</td>
     <td>${article.p_sale_date}</td>
   </tr>
    <tr align="center">  
         <td colspan="10">
           <input type="button" value="��ǰ ����" class="button white small"
           OnClick="document.location.href='/thk/productUd.cp?p_code=${article.p_code}'">
           <input type="button" value="��ǰ ����"  class="button white small"
           OnClick="return prodelete(); ">
           <input type="button" value="��ǰ ���"  class="button white small"
           OnClick="document.location.href='/thk/productAdminList.cp'">

         </td>
       </tr>
  </table>
 </form>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>