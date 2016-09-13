<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>

#section { width: 1200px; margin: 0 auto; border: 1px solid black; }

</style>

<script>
    function openCartDelete(userinput) {
    	
        // url�� ����� �Է� id�� �����մϴ�.
        url = "cartDelete.cp?pcode=" + userinput.p_code.value + "&psize=" + userinput.p_size.value;
        
        location.href=url;
        }
</script>

</head>
<body onload="init();">

<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>	
	
	<section id="section">
		
		<c:if test="${Count == 0 }">
		<div>��ٱ��Ͽ� ��� ��ǰ�� �����ϴ�.</div>
		</c:if>
		
		<c:if test="${Count != 0 }">
		
			<c:forEach var="cart_list" items="${cart_list}">
			
					<c:set var="code" value="${cart_list.p_code}"/>
					<form method="post" action="cartModify.cp" name="userinput">
						<a href="/thk/product_1.cp?p_code=${code}">
						
						<img src="/thk/save/${cart_list.p_img}" height="90"></a>&nbsp;&nbsp;&nbsp;&nbsp;
									${cart_list.p_name}  						&nbsp;&nbsp;&nbsp;&nbsp;
						����:&nbsp;${cart_list.p_price}							&nbsp;&nbsp;
						���ΰ���:&nbsp;${cart_list.p_price_dc}					&nbsp;&nbsp;
						��������Ʈ:&nbsp;${cart_list.p_point}					&nbsp;&nbsp;
						������:&nbsp;${cart_list.p_size}						&nbsp;&nbsp;&nbsp;&nbsp;
						
						
						<input type="text" name="amount" size="3" value="${cart_list.p_amount}" />
						<input type="hidden" name="p_code" value="${code}" />
						<input type="hidden" name="p_size" value="${cart_list.p_size}">
						
						<input type="submit" value="����"> &nbsp;
						
						<input type="button" name="delete" value="����"  onClick="openCartDelete(this.form)">
								
					</form>
				
 									
			</c:forEach>
			
			
			<form method="post" action="/thk/orderForm.cp">
				<input type="hidden" name="sum_p" value="${sumdate.all_p_sum}" />
				<input type="hidden" name="sum_p_dc" value="${sumdate.all_p_dc_sum}" />
				<input type="hidden" name="sum_p_p" value="${sumdate.all_p_p_sum}" />
				<input type="hidden" name="mem_num" value="${mem_num}">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="cart" value="cartlist">
				
				<input type="submit" value="�����ϱ�">	
			</form>
			
			</c:if>
			
	</section>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>
	
	
