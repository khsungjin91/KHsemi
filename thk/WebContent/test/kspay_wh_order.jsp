<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>*** KSNET WebHost Sample [JSP] ***</title>
</head>
<script>

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
		document.KSPayWeb.action = "kspay_wh_result.cp";
		document.KSPayWeb.submit();
	}
	// eparamSet() - �Լ����� : ����Ϸ��� (kspay_wh_rcv.jsp�κ���)������� �޾� ������ ���������(kspay_wh_result.jsp)�� ���۵� form�� �����մϴ�.
	function eparamSet(rcid, rctype, rhash){
		document.KSPayWeb.reWHCid.value 	= rcid;
		document.KSPayWeb.reWHCtype.value   = rctype  ;
		document.KSPayWeb.reWHHash.value 	= rhash  ;
	}

</script>
<body>
<!-----------------------------------------<Part 1. KSPayWeb Form: ����������ּ� ���� > ---------------------------------------->
<!--���� �Ϸ��� ������� �޾�ó���� ����������� �ּ�-->
<form name=KSPayWeb action = "kspay_wh_result.cp" method=post>
<table width="560" border="0" cellspacing="0" cellpadding="0">
	<tr width="500" align="center">
	</tr>
    <tr width="500" align="center">
		<td align="center">
		</td>
	</tr>
    <tr width="500" align="center">
    	<td width="110">
    	 	��ǰ��
    	</td>
		<td width="390">
			<!-- ${p_name} ��ǰ �̸� ���--> ��ǰ�̸�
		</td>
	</tr>
	<tr width="500" align="center">
		<td width="110">
			�ݾ�
		</td> 
			<!--�ݾ��� ,���� �Է� -->
		<td width="500">
			<!-- ${p_price} -->10000
		</td>
	</tr>
	<tr width="500" align="center">
		<td width="110">
			�ֹ��ڸ�
		</td> 
		<td width="390">
			<!-- ${mem_name} -->�ֹ��� �̸�
		</td>
	</tr>
	<tr bgcolor="#E3E3E3" width="500" align="center">
		<td height="1" colspan="2"></td>
	</tr>
	<tr width="500" align="center">
		<td width="110">
			���ڿ���</td> 
			<!--KSPAY���� ���������� ���Ϸ� �����ݴϴ�.(�ſ�ī��ŷ����� �ش�)-->
		<td width="290">
			<input type="text" name="sndEmail" value="kspay@carrot.co.kr" size="30"><!-- value �� ${mem_email} ���� -->
		</td>
	</tr>
	<tr bgcolor="#E3E3E3" width="500" align="center">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr width="500" align="center">
		<td width="110">
			�̵���ȭ
		</td> 
		<!--��ȭ��ȣ value ���� ���ڸ� �ְ� ���ֽñ� �ٶ��ϴ�. : '-' �� ���� �ȵ˴ϴ�.-->
		<td width="290">
			<input type="text" name="sndMobile" value="01112341234" size="12" maxlength="12"> <!-- value �� ${mem_phone} ����-->
		</td>
	</tr>
	<tr bgcolor="#E3E3E3" width="500" align="center">
		<td height="1" colspan="2"></td>
	</tr>
    <tr width="500" align="center">
		<td height="40" align="center">
			<input type="button" value="�� ��" onClick="javascript:_pay(document.KSPayWeb);">
		</td>
    </tr>
</table>
	<input type=hidden  name=sndServicePeriod  value="YYYY��MM��DD��~YYYY��MM��DD��"> <!-- ���� ��ۻ�ǰ�̾ƴ� ��������ǰ�� �����Ⱓǥ�� -->
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
<!--�� �Ķ���͵��� �����Ȱ�� ������(kspay_result.jsp)�� ���۵˴ϴ�.-->
	<input type="hidden" name="sndPayMethod" value="1000000000"> <!-- �������� -->
	<input type="hidden" name="sndStoreid" value="2999199999"> <!-- �׽�Ʈ ���̵� -->
	<input type="hidden" name="sndOrdernumber" value="carrot_1234"> <!-- �ֹ���ȣ -->
	<input type="hidden" name="sndGoodname" value="���10kg"> <!-- ��ǰ�̸� -->
	<input type="hidden" name="sndAmount" value="1004"> <!-- ��ǰ���� -->
	<input type="hidden" name="sndOrdername" value="���䳢"> <!-- �ֹ��� �̸� -->
<!--------------------------------------------------------------------------------------------------------------------------->
</form>
</body>
</html>