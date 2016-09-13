<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<title>�ֹ��ϱ�</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<body onload="init()">

		<script type="text/javascript">	
		
		function usechange(el){
		
			var struse =$(el).val();
	
				if(struse == null || struse.length == 0){
					$(el).parent().next().text(0);
					return;
				}else if(isNaN(struse)){
					return;
				}
				
				var su = parseInt(struse);
				var price = parseInt($(el).parent().prev().text());
				var mitotal = price-su;		
				
				$(el).parent().next().text(mitotal);
	
				setTotalPrice();
		}
		</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<div id="section">

	<h3>�ֹ��󼼳���</h3><hr />
	
	<form action="/thk/payForm.cp" method="post" name="userinput" onSubmit="return checkIt();">	
	
<c:set var="code" value="${code}"/>
	
		

<c:choose>
	<c:when test="${url != null}">
		<b>��ǰ����</b> <br />
		<input type="hidden" name="url" value="${url}">
		*��ǰ�� : ${code.p_name} <br />
		*���û����� : ${param.p_size} <br />
		*������ : ${code.p_point} �� <br />
		*�ǸŰ� : ${code.p_price} �� <br />
		*���� :	${p_amount} �� <br/>
		
		*��ۺ� (�Ϲ��ù� ���Ǻι���) : 		
		<c:choose>
			<c:when test="${code.p_price>=50000}">
					������<br />
					*���� : ${code.p_price} ��	<br />	
					*���ΰ� : ${code.p_price_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${code.p_price_dc} ��	<br />
					<c:set var="addShip" value="${code.p_price_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*��ۺ� : 2500�� <br />
					*���� : ${code.p_price} ��	<br />	
					*���ΰ� : ${code.p_price_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${code.p_price_dc+2500} ��	<br />
					<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
					
					*������������ : ${code.p_point} ��
					
			</c:otherwise>
		</c:choose>	

	</c:when>
		
		
	<c:otherwise>
				<input type="hidden" value="${sum_p}" name="sum_p" >
				<input type="hidden" value="${sum_p_dc}" name="sum_p_dc" >
				<input type="hidden" value="${sum_p_p}" name="sum_p_p" >
		
		<c:forEach var="cart" items="${cart}">
				
				<b>��ǰ����</b> <br />
				*��ǰ�� : ${cart.p_name} <br />
				*���û����� : ${cart.p_size} <br />
				*������ : ${cart.p_point} �� <br />
				*�ǸŰ� : ${cart.p_price} �� <br />
				*����  :	${cart.p_amount} ��<br />
				*����  :	${cart.p_price}	��<br />
				*���ΰ� : ${cart.p_price_dc} ��<br/>
				<hr />
		</c:forEach>
			
			<c:choose>
			
				
			<c:when test="${sum_p >=50000}">
					������<br />
					*���� : ${sum_p} ��	<br />	
					*���ΰ� : ${sum_p_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${sum_p_dc} ��	<br />
					<c:set var="addShip" value="${sum_p_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*��ۺ� : 2500�� <br />
					*���� : ${sum_p} ��	<br />	
					*���ΰ� : ${sum_p_dc} ��<br />	
					*��ǰ�հ�ݾ� : ${sum_p_dc+2500} ��	<br />
					<c:set var="addShip" value="${sum_p_dc+2500}"/>	
					
					*������������ : ${sum_p_p} ��
					
			</c:otherwise>
		</c:choose>	
			
		
		
	</c:otherwise>	
</c:choose>	
	
				<hr />
	

	<c:set var="num" value="${num}"/>

		<input type="hidden" name="mem_id" value="${num.mem_id}">
		<input type="hidden" name="mem_name" value="${num.mem_name}">
		<input type="hidden" name="mem_postnum" value="${num.mem_postnum}">
		<input type="hidden" name="mem_addrs1" value="${num.mem_addrs1}">
		<input type="hidden" name="mem_addrs2" value="${num.mem_addrs2}">
		<input type="hidden" name="mem_phone" value="${num.mem_phone}">
		<input type="hidden" name="mem_num" value="${mem_num}">
		<input type="hidden" name="mem_point" value="${num.mem_point}">
				
		
	<h3>�ֹ����ۼ�</h3><hr />
		<div>
		<b>�ֹ�������</b><br />
			*�ֹ��Ͻô� �� ${num.mem_name}<br />
			*�ּ� ${num.mem_addrs1} ${num.mem_addrs2}<br />
			*����ȣ${num.mem_phone}<br />
			*�̸���${num.mem_email}<br />
			</div><hr />
			
			

		<input type="hidden" name="p_code" value="${code.p_code}" />
		<input type="hidden" name="p_name" value="${code.p_name}" />
		<input type="hidden" name="p_thumbnail" value="${code.p_thumbnail}" />
		<input type="hidden" name="p_price" value="${code.p_price}" />
		<input type="hidden" name="p_price_dc" value="${code.p_price_dc}" />
		<input type="hidden" name="p_point" value="${code.p_point}" />
		<input type="hidden" name="p_size" value="${param.p_size}"/>
		<input type="hidden" name="p_amount" value=""${param.p_amount}>	
		
		<div>
		<b>�������</b><br />
			*������ �� <input type="text" name="shipName" value="${num.mem_name}"> <br />
			*������ �� <input type="text" name="shipPostnum" value="${num.mem_postnum}" id="sample6_postcode">
					<input type="button" onclick="sample6_execDaumPostcode()"value="�����ȣ�˻�"> <br />
					<input type="text" name="shipAddrs1" value="${num.mem_addrs1}" id="sample6_address"> <br />
					<input type="text" name="shipAddrs2" value="${num.mem_addrs2}" id="sample6_address2"> <br />
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
			function sample6_execDaumPostcode() {
				new daum.Postcode({
            		oncomplete: function(data) {
                	// �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

                		// �� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
                		// �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
                		var fullAddr = ''; // ���� �ּ� ����
                		var extraAddr = ''; // ������ �ּ� ����

                		// ����ڰ� ������ �ּ� Ÿ�Կ� ���� �ش� �ּ� ���� �����´�.
                		if (data.userSelectedType === 'R') { // ����ڰ� ���θ� �ּҸ� �������� ���
                    		fullAddr = data.roadAddress;

                		} else { // ����ڰ� ���� �ּҸ� �������� ���(J)
                    		fullAddr = data.jibunAddress;
                		}

                		// ����ڰ� ������ �ּҰ� ���θ� Ÿ���϶� �����Ѵ�.
                		if(data.userSelectedType === 'R'){
                    		//���������� ���� ��� �߰��Ѵ�.
                    		if(data.bname !== ''){
                        		extraAddr += data.bname;
                    		}
                    		// �ǹ����� ���� ��� �߰��Ѵ�.
                    		if(data.buildingName !== ''){
                        		extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    		}
                    		// �������ּ��� ������ ���� ���ʿ� ��ȣ�� �߰��Ͽ� ���� �ּҸ� �����.
                    		fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                		}

               			// �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
                		document.getElementById('sample6_postcode').value = data.zonecode; //5�ڸ� �������ȣ ���
                		document.getElementById('sample6_address').value = fullAddr;

                		// Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
                		document.getElementById('sample6_address2').focus();
            		}
				}).open();
			}
			
		</script>		
					
					
					
			*�ڵ�����ȣ <input type="text" name="shipPhone" value="${num.mem_phone}"> <br />
			*����� ���� <input type="text" name="shipMemo" maxlength="100"> <br />			
			</div><hr />
	
	
		<div>
		<b>�����ݾ�</b></div>
			�հ�ݾ�<div id= "price">${addShip}</div>
			<div>������ ��� : <input type="text" name="usepoint" value="0" onkeyup="usechange(event.target);">��
				 &nbsp;(���� ������ ${num.mem_point}��)</div>
			�����ݾ�<div id="total-price">${addShip}</div><hr />
			
		<div>
		== ������ �����ŷ��� ���Ͽ� ���� ������ ������ ���� ���θ����� ������ �̴Ͻý��� ���ž��� (����ũ��) ���񽺸� �̿��Ͻ� �� �ֽ��ϴ�. ==
		</div><hr />
		
		
		<div>
		<b>��������</b> <br />
			*�Ϲݰ��� ����ũ�ΰ���
			<input type="radio" name=sndPaymethod value="1000000000">�ſ�ī��
				<input type="radio" name=sndPaymethod value="0100000000">�������
				<input type="radio" name=sndPaymethod value="0010000000">������ü<br />
			(�������Ա��� ��� �Ա�Ȯ�� �ĺ��� ��۴ܰ谡 ����˴ϴ�.)<br />
		</div>

		<div>
		<input type="submit" name="submit" value="�����ϱ�"> 
		<input type="button" value="����ϱ�" onClick="javascript:window.location='main.cp'">
		</div>
	</form>

</div>

<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>

	<script type="text/javascript">
		function checkIt(){
	
			if(!document.userinput.getName.value){
				document.userinput.getName.focus();
				alert("������ ���� ������ �Է����ּ���");
				return false;
			}
			if(!document.userinput.getPostnum.value){
				alert("�����ȣ�� �Է����ּ���");
				return false;
			}
			if(!document.userinput.getAddrs1.value){
				document.userinput.getAddrs1.focus();
				alert("�ּҸ� �Է����ּ���");
				return false;
			}
			if(!document.userinput.getAddrs2.value){
				document.userinput.getAddrs2.focus();
				alert("���ּҸ� �Է����ּ���");
				return false;
			}
			if(!document.userinput.getPhone.value){
				document.userinput.getPhone.focus();
				alert("����ó�� �Է����ּ���");
				return false;
			}
		}
	</script>

</html>


