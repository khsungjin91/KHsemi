<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto; padding-top:40px; margin-bottom: 30px }
#section td {height: 40px; font-size: 18px; padding: 10px}

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
	padding: .2em 1em .275em

}
</style>

</head>
<body>


<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>
	
	
	<section id="section">
	
		<div style="text-align: center; font-size: 25px; margin-bottom: 30px" ><b>상품등록</b></div>
		
		<center>
<form name="product" action="/thk/productAdminPro.cp" method="post" enctype="multipart/form-data">
   <table>
     <tr>
       <td>상품코드</td><td><input type="text"  name="p_code"></td>
     </tr>
     <tr>
       <td>상품명</td><td><input type="text"  name="p_name"></td>
     </tr>
     <tr>  
       <td>소재</td><td><input type="text"  name="p_material"></td>
     </tr>  
     <tr> 
       <td>썸네일</td><td><input type="file"  name="p_thumbnail"></td>
     </tr>
     <tr>
       <td>상세이미지</td><td><input type="file"  name="p_img"></td>
     </tr>  
     <tr>  
       <td>가격</td><td><input type="text"  name="p_price"></td>
     </tr>  
     <tr>  
       <td>할인가</td><td><input type="text"  name="p_price_dc"></td>
     </tr>  
     <tr>  
       <td>포인트</td><td><input type="text"  name="p_point"></td>
     </tr>
     <tr>  
       <td>카테고리</td><td>1차 : <input type="text"  name="p_category1" size="12">
       						2차 : <input type="text"  name="p_category2" size="12">
       						3차 : <input type="text"  name="p_category3" size="12"></td>
     </tr>
     <tr>
       <td>이미지1</td><td><input type="file"  name="img1"></td>
     </tr>
     <tr>
       <td>이미지2</td><td><input type="file"  name="img2"></td>
     </tr>
     <tr>
       <td>이미지3</td><td><input type="file"  name="img3"></td>
     </tr>
     <tr>
       <td>이미지4</td><td><input type="file"  name="img4"></td>
     </tr>
     <tr>
       <td>이미지5</td><td><input type="file"  name="img5"></td>
     </tr>
     
     <tr align="center">  
         <td colspan="2">
           <input type="submit" value="등록" class="button white small" >
           <input type="reset" value="취소" class="button white small" >
         </td>
       </tr>
   </table>
   
 </form> 
	</center>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>