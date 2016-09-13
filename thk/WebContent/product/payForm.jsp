<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�����ϱ�</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<script language="javascript">

	function _pay(_frm) 
	{
 		_frm.sndReply.value           = getLocalUrl("kspay_wh_rcv.cp") ;

		var agent = navigator.userAgent;
		var midx		= agent.indexOf("MSIE");
		var out_size	= (midx != -1 && agent.charAt(midx+5) < '7');
    	
		var width_	= 500;
		var height_	= out_size ? 568 : 518;
		var left_	= screen.width;
		var top_	= screen.height;
    	
		left_ = left_/2 - (width_/2);
		top_ = top_/2 - (height_/2);
		
		op = window.open('about:blank','AuthFrmUp',
		        'height='+height_+',width='+width_+',status=yes,scrollbars=no,resizable=no,left='+left_+',top='+top_+'');

		if (op == null)
		{
			alert("�˾��� ���ܵǾ� ������ ������ �� �����ϴ�.");
			return false;
		}
		
		_frm.target = 'AuthFrmUp';
		_frm.action ='https://kspay.ksnet.to/store/KSPayFlashV1.3/KSPayPWeb.jsp';
		//_frm.action ='http://210.181.28.116/store/KSPayFlashV1.3/KSPayPWeb.jsp';
		
		_frm.submit();
    }

	function getLocalUrl(mypage) 
	{ 
		var myloc = location.href; 
		return myloc.substring(0, myloc.lastIndexOf('/')) + '/' + mypage;
	} 
	
	// goResult() - �Լ����� : ����Ϸ��� ������� ������ ���������(kspay_wh_result.jsp)�� �����մϴ�.
	function goResult(){
		document.KSPayWeb.target = "";
		document.KSPayWeb.action = "orderEnd.cp";
		document.KSPayWeb.submit();
	}
	// eparamSet() - �Լ����� : ����Ϸ��� (kspay_wh_rcv.jsp�κ���)������� �޾� ������ ���������(kspay_wh_result.jsp)�� ���۵� form�� �����մϴ�.
	function eparamSet(rcid, rctype, rhash){
		document.KSPayWeb.reWHCid.value 	= rcid;
		document.KSPayWeb.reWHCtype.value   = rctype  ;
		document.KSPayWeb.reWHHash.value 	= rhash  ;
	}
	
	
	 function check(id) {
	     var agree = document.getElementById(id);
	     if(agree.checked) { //���ǿ��� üũ
	    	javascript:_pay(document.KSPayWeb)
	     }else {
	       alert("�����ϼž� ������ ����˴ϴ�");
 	       return false;
	     }
	   }


</script>
<body>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

<div id="section">
<h2>�����ϱ�</h2> <hr />
	<form action="/thk/orderEnd.cp" name="KSPayWeb" method="post">
	<c:set var="code" value="${code}"/>
		
		
<c:choose>
	<c:when test="${param.url != null}">
		<b>��ǰ����</b> <br />
		*��ǰ�� : ${code.p_name} <br />
		*���û����� : ${size}  <br />
		*������ : ${code.p_point} �� <br />
		*�ǸŰ� : ${code.p_price} �� <br />
		*���� :	1 �� <br/>
		<input type="hidden" name="pay_amount" value="1"/>
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
		<c:forEach var="cart" items="${cart}">
				
				<b>��ǰ����</b> <br />
				*��ǰ�� : ${cart.p_name} <br />
				*���û����� : ${cart.p_size} <br />
				*������ : ${cart.p_point} �� <br />
				*�ǸŰ� : ${cart.p_price} �� <br />
				*����  :	${cart.p_amount} ��<br />
				 <input type="hidden" name="pay_amount" value="${cart.p_amount}">
				*����  :	${cart.p_price}	��<br />
				*���ΰ� : ${cart.p_price_dc} ��<br/>
				<hr />
		</c:forEach>
			
			<c:choose>
			<c:when test="${param.sum_p >=50000}">
					������<br />
					*���� : ${param.sum_p} ��	<br />	
					*���ΰ� : ${param.sum_p_dc} ��<br />	
					*��ۺ������հ�ݾ� : ${param.sum_p_dc} ��	<br />
					<c:set var="addShip" value="${param.sum_p_dc+0}"/>
			</c:when>
					
			<c:otherwise>
					*��ۺ� : 2500�� <br />
					*���� : ${param.sum_p} ��	<br />	
					*���ΰ� : ${param.sum_p_dc} ��<br />	
					*��ۺ������հ�ݾ� : ${param.sum_p_dc+2500} ��	<br />
					<c:set var="addShip" value="${param.sum_p_dc+2500}"/>	
					
					*������������ : ${param.sum_p_p} ��
					
			</c:otherwise>
		</c:choose>	
			
		
		
	</c:otherwise>	
</c:choose>	

	<c:set var="num" value="${num}"/>
		<h2>�ֹ�����Ȯ��</h2><hr />
			<div>
			<h4>�ֹ�������</h4>
			</div>
				<div>
				�ֹ��� ���� ${num.mem_name} <br />
				�ֹ��� ����ó ${num.mem_phone} <br />
				�̸��� ${num.mem_email} <br />
				</div>
			
			<div>
			<h4>�������</h4>
			</div>
				<div>
				�޴º� ���� ${param.shipName} <br />
				�޴º� ����ó ${param.shipPhone} <br />
				�����ȣ ${param.shipPostnum} <br />
				�ּ� ${param.shipAddrs1}&nbsp;${param.shipAddrs2} <br />
				</div>
			
			<div>
			<h4>�����ݾ�</h4>
			</div>
				<div>
				�� �ֹ��ݾ� ${code.p_price_dc} ��<br />
				��ۺ� : 	
				
				<c:choose>
					<c:when test="${param.url != null}">
					
						<c:choose>
							<c:when test="${code.p_price>=50000}">
							������<br />
							�հ� : ${code.p_price_dc} ��	  <br />
					
								<c:set var="addShip" value="${code.p_price_dc+0}"/>
							</c:when>
							<c:otherwise>
							��ۺ� : 2500�� <br />
							�հ� : ${code.p_price_dc+2500} ��   <br />
					
							<c:set var="addShip" value="${code.p_price_dc+2500}"/>	
							</c:otherwise>
						</c:choose>	
							������ ��� -${param.usepoint} ��<br />
							�����ݾ� ${addShip-param.usepoint} ��<br />
						
					</c:when>
						
				<c:otherwise>
				
							<c:choose>
								<c:when test="${param.sum_p >=50000}">
							������<br />
							�հ� : ${param.sum_p} ��	  <br />
							
								<c:set var="addShip" value="${param.sum_p_dc+0}"/>
								</c:when>
								<c:otherwise>
							��ۺ� : 2500�� <br />
							�հ� : ${param.sum_p+2500} ��   <br />
				
								<c:set var="addShip" value="${param.sum_p_dc+2500}"/>	
								</c:otherwise>
							</c:choose>	
							������ ��� -${param.usepoint} ��<br />
							�����ݾ� ${addShip-param.usepoint} ��<br />
				
				</c:otherwise>
				
				</c:choose>
				</div>
		
			<div>
			<h4>��������</h4>
			</div>
					<c:if test="${param.sndPaymethod==1000000000}">
						<div>
							������� &nbsp;&nbsp; �ſ�ī��<br />
							�����ݾ� &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} ��
						</div>			
					</c:if>
					

					<c:if test="${param.sndPaymethod==0010000000}">
						<div>
							������� &nbsp;&nbsp; ������ü<br />
							�����ݾ� &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} ��
						</div>			
					</c:if>


					<c:if test="${param.sndPaymethod==0100000000}">
						<div>
							������� &nbsp;&nbsp; �������<br />
							�����ݾ� &nbsp;&nbsp; ${code.p_price_dc-param.usepoint} ��
						</div>			
					</c:if>
						<div><h4>���ų���Ȯ��</h4>	</div>
	<div>
	<input type="checkbox" id="agree"/>&nbsp;
	�����Ͻ� ��ǰ�� ��ǰ���� �� ������ Ȯ���Ͽ�����, �̿� �����մϴ�. (���ڻ�ŷ��� �� 8�� 2��)
		</div>
	<div>
		<input type="button" value="�� ��" onClick="check('agree')">	
		<input type="button" value="����ϱ�" onClick="history.go(-1)">	
	</div>

<!----------------------------------------------- <Part 2. �߰������׸�(�޴�������)>  ----------------------------------------------->

	<!-- 0. ���� ȯ�漳�� -->
	<input type=hidden	name=sndReply value="">
	<input type=hidden  name=sndGoodType value="1"> 	<!-- ��ǰ����: �ǹ�(1),������(2) -->
	
	<!-- 1. �ſ�ī�� ���ü��� -->
	
	<!-- �ſ�ī�� �������  -->
	<!-- �Ϲ����� ��ü�� ��� ISP,�Ƚɰ����� ����ϸ� �Ǹ� �ٸ� ������� �߰��ÿ��� ������ �������� ����ٶ��ϴ� -->
	<input type=hidden  name=sndShowcard value="I,M"> <!-- I(ISP), M(�Ƚɰ���), N(�Ϲݽ���:���������), A(�ؿ�ī��), W(�ؿܾȽ�)-->
	
	<!-- �ſ�ī��(�ؿ�ī��) ��ȭ�ڵ�: �ؿ�ī������� �޷������� ����Ұ�� ���� -->
	<input type=hidden	name=sndCurrencytype value="WON"> <!-- ��ȭ(WON), �޷�(USD) -->
	
	<!-- �Һΰ����� ���ù��� -->
	<!--�������� ������ �Һΰ������� �����մϴ�. ���⼭ �����Ͻ� ���� ����â���� ���� ��ũ���Ͽ� �����ϰ� �˴ϴ� -->
	<!--�Ʒ��� ���ǰ�� ���� 0~12������ �Һΰŷ��� �����Ҽ��ְ� �˴ϴ�. -->
	<input type=hidden	name=sndInstallmenttype value="ALL(0:2:3:4:5:6:7:8:9:10:11:12)">
	
	<!-- �������δ� �������Һμ��� -->
	<!-- ī��� ��������縸 �̿��Ͻǰ��  �Ǵ� ������ �Һθ� �������� �ʴ� ��ü��  "NONE"�� ����  -->
	<!-- �� : ��üī��� �� ��ü �Һο����ؼ� ������ ������ ���� value="ALL" / ������ �������� ���� value="NONE" -->
	<!-- �� : ��üī��� 3,4,5,6���� ������ ������ ���� value="ALL(3:4:5:6)" -->
	<!-- �� : �Ｚī��(ī����ڵ�:04) 2,3���� ������ ������ ���� value="04(3:4:5:6)"-->
	<!-- <input type=hidden	name=sndInteresttype value="10(02:03),05(06)"> -->
	<input type=hidden	name=sndInteresttype value="NONE">

	<!-- 2. �¶����Ա�(�������) ���ü��� -->
	<input type=hidden	name=sndEscrow value="1"> 			<!-- ����ũ�λ�뿩�� (0:������, 1:���) -->
	
	<!-- 3. �����н�ī�� ���ü��� -->
	<input type=hidden	name=sndWptype value="1">  			<!--��/�ĺ�ī�屸�� (1:����ī��, 2:�ĺ�ī��, 3:���ī��) -->
	<input type=hidden	name=sndAdulttype value="1">  		<!--����Ȯ�ο��� (0:����Ȯ�κ��ʿ�, 1:����Ȯ���ʿ�) -->
	
	<!-- 4. ������ü ���ݿ������߱޿��� ���� -->
    <input type=hidden  name=sndCashReceipt value="0">          <!--������ü�� ���ݿ����� �߱޿��� (0: �߱޾���, 1:�߱�) -->

	<!-- 5. ��ǰ��, ���ӹ�ȭ��ǰ�� ���� ���� -->
	<input type=hidden  name=sndMembId value="userid"> <!-- �����������ID (��ȭ,���ӹ�ȭ ��ǰ�ǰ����� �ʼ�) -->
	
<!----------------------------------------------- <Part 3. �������� ���������>  ----------------------------------------------->
<!-- �������Ÿ: �������� �ڵ����� ä�����ϴ�. (*�������� �������� ������) -->

	<input type=hidden name=reWHCid 	value="">
	<input type=hidden name=reWHCtype 	value="">
	<input type=hidden name=reWHHash 	value="">
<!--------------------------------------------------------------------------------------------------------------------------->

<!--��ü���� �߰��ϰ����ϴ� ������ �Ķ���͸� �Է��ϸ� �˴ϴ�.-->
<!--�� �Ķ���͵��� �����Ȱ�� ������(kspay_result.cp)�� ���۵˴ϴ�.-->
	<input type="hidden" name="sndStoreid" value="2999199999">
	<input type="hidden" name="sndOrdernumber" value="${pay_code}">
	<input type="hidden" name="sndGoodname" value="${code.p_name}"> 
	<input type="hidden" name="sndAmount" value="${code.p_price_dc-param.usepoint}">
	<input type="hidden" name="sndOrdername" value="${num.mem_name}">
	<input type="hidden" name="sndEmail" value="${num.mem_email}">
	<input type="hidden" name="sndMobile" value="${num.mem_phone}">
	<input type="hidden" name="sndServicePeriod"  value="YYYY��MM��DD��~YYYY��MM��DD��"> <!-- ���� ��ۻ�ǰ�̾ƴ� ��������ǰ�� �����Ⱓǥ�� -->
	<input type="hidden" name="sndPaymethod" value="${param.sndPaymethod}">
<!--------------------------------------------------------------------------------------------------------------------------->
	<input type="hidden" name="mem_id" value="${memId}" />
	<input type="hidden" name="p_price_dc" value="${code.p_price_dc}"/>
	<input type="hidden" name="p_name" value="${code.p_name}">
	<input type="hidden" name="p_point" value="${code.p_point}" />
	<input type="hidden" name="p_code" value="${code.p_code}">
	<input type="hidden" name="p_size" value="${param.p_size}"/>
	<input type="hidden" name="shipName" value="${param.shipName}"/>
	<input type="hidden" name="shipPhone" value="${param.shipPhone}"/>
	<input type="hidden" name="shipPostnum" value="${param.shipPostnum}"/>
	<input type="hidden" name="shipAddrs1" value="${param.shipAddrs1}"/>
	<input type="hidden" name="shipAddrs2" value="${param.shipAddrs2}"/>
	<input type="hidden" name="shipMemo" value="${param.shipMemo}"/>
	<input type="hidden" name="usepoint" value="${param.usepoint}" />	
	<input type="hidden" name="p_code" value="${code.p_code}"/>					
	<input type="hidden" name="mem_num" value="${num.mem_num}" />
<!--------------------------------------------------------------------------------------------------------------------------->

</form>

</body>
</html>

