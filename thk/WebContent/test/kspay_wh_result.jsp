<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rcid		= request.getParameter("reWHCid"		);
	String rctype	= request.getParameter("reWHCtype"		);
	String rhash	= request.getParameter("reWHHash"			);

	String	authyn =  "";
	String	trno   =  "";
	String	trddt  =  "";
	String	trdtm  =  "";
	String	amt    =  "";
	String	authno =  "";
	String	msg1   =  "";
	String	msg2   =  "";
	String	ordno  =  "";
	String	isscd  =  "";
	String	aqucd  =  "";
	String	temp_v =  "";
	String	result =  "";
	String	halbu  =  "";
	String	cbtrno =  "";
	String	cbauthno =  "";

	String	resultcd =  "";

	//��ü���� �߰��Ͻ� ���ڰ��� �޴� �κ��Դϴ�
	String	a =  request.getParameter("a"); 
	String	b =  request.getParameter("b"); 
	String	c =  request.getParameter("c"); 
	String	d =  request.getParameter("d");

	test.action.KSPayWebHostBean ipg = new test.action.KSPayWebHostBean(rcid);
	if (ipg.kspay_send_msg("1"))		//KSNET ������� �� �Ʒ��� ��Ÿ���� ���� �׸��� �ʿ��� ��� Null ��� �ʿ��� �׸���� ������ �� �ֽ��ϴ�.
	{
		authyn	 = ipg.kspay_get_value("authyn");
		trno	 = ipg.kspay_get_value("trno"  );
		trddt	 = ipg.kspay_get_value("trddt" );
		trdtm	 = ipg.kspay_get_value("trdtm" );
		amt		 = ipg.kspay_get_value("amt"   );
		authno	 = ipg.kspay_get_value("authno");
		msg1	 = ipg.kspay_get_value("msg1"  );
		msg2	 = ipg.kspay_get_value("msg2"  );
		ordno	 = ipg.kspay_get_value("ordno" );
		isscd	 = ipg.kspay_get_value("isscd" );
		aqucd	 = ipg.kspay_get_value("aqucd" );
		temp_v	 = "";
		result	 = ipg.kspay_get_value("result");
		halbu	 = ipg.kspay_get_value("halbu");
		cbtrno	 = ipg.kspay_get_value("cbtrno");
		cbauthno	 = ipg.kspay_get_value("cbauthno");

		if (null != authyn && 1 == authyn.length())
		{
			if (authyn.equals("O"))
			{
				resultcd = "0000";
			}else
			{
				resultcd = authno.trim();
			}

			//ipg.kspay_send_msg("3");		// ����ó���� �Ϸ�Ǿ��� ��� ȣ���մϴ�.(�� ������ ������ �Ͻ������� kspay_send_msg("1")�� ȣ���Ͽ� �ŷ����� ��ȸ�� �����մϴ�.)
		}
	}
%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>*** KSNET WebHost ��� [JSP] ***</title>
</head>
<script>
// ���ݿ����� ��� ��ũ��Ʈ
function CashreceiptView(tr_no)
{
    receiptWin = "http://pgims.ksnet.co.kr/pg_infoc/src/bill/ps2.jsp?s_pg_deal_numb="+tr_no;
    window.open(receiptWin , "" , "scrollbars=no,width=434,height=580");
}
// �ſ�ī�� ������ ��� ��ũ��Ʈ
function receiptView(tr_no)
{
	receiptWin = "http://pgims.ksnet.co.kr/pg_infoc/src/bill/credit_view.jsp?tr_no="+tr_no;
    window.open(receiptWin , "" , "scrollbars=no,width=434,height=700");
}
</script>

<body>
<table width="560" border="0" cellspacing="0" cellpadding="0">
	<tr width="500">
		<td width="120">
			�������
		</td>
		<td width="380">
<%
						if (null == result || 4 != result.length())
						{
							out.println("(???)");
						}else
						{
							switch (result.charAt(0))
							{
								case '1' : out.println("�ſ�ī��"); break;
								case 'I' : out.println("�ſ�ī��"); break;
								case '2' : out.println("�ǽð�������ü"); break;
								case '6' : out.println("������¹߱�"); break; 
								case 'M' : out.println("�޴�������"); break; 
								case 'G' : out.println("��ǰ��"); break; 
								default  :  out.println("(????)"); break; 
							}
						}
%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2"></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			��������
		</td>
		<td width="380">
			<%=authyn%>(<%= ((authyn != null && authyn.equals("O")) ? "���μ���" : "���ΰ���") %>) <font color=red> :�������ΰ��� ���� �빮�� O,X�Դϴ�. </font>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�����ڵ�
		</td>
		<td width="380">
			<%=resultcd%>
		</td>
 	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�ֹ���ȣ
		</td>
		<td width="380">
			<%=ordno%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�ݾ�
		</td>
		<td width="380">
			<%=amt%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�ŷ���ȣ
		</td>
		<td width="380">
			<%=trno%> <font color=red>:KSNET���� �ο��� ������ȣ�Դϴ�. </font>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�ŷ�����
		</td>
		<td width="380">
			<%=trddt%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�ŷ��ð�
		</td>
		<td width="380">
			<%=trdtm%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
<% if (authyn != null && authyn.equals("O")) { %>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			ī��� ���ι�ȣ/���� �ڵ��ȣ
		</td>
		<td width="380">
			<%=authno%><font color=red>:ī��翡�� �ο��� ��ȣ�� �����Ѱ��� �ƴմϴ�. </font>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
<% } %>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�߱޻��ڵ�/������¹�ȣ/������ü��ȣ
		</td>
		<td width="380">
			<%=isscd%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			���Ի��ڵ�
		</td>
		<td width="380">
			<%=aqucd%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�޽���1
		</td>
		<td width="380">
			<%=msg1%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="120">
			�޽���2
		</td>
		<td width="380">
			<%=msg2%>
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
		  <% if (authyn != null && authyn.equals("O") && trno.charAt(0) == '1') { %> <!-- ��������� ��츸 ���������: �ſ�ī���� ��츸 ���� -->
	<tr bgcolor="#FFFFFF">
		<td width="400" colspan="2" align="center">
			<input type="button" value="���������" onClick="javascript:receiptView('<%=trno%>')">
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
      <% } %>
      <% if (authyn != null && authyn.equals("O") && trno.charAt(0) == '2') { %> <!-- ��������� ��츸 ���������: ������ü�� ��츸 ���� -->
	<tr bgcolor="#FFFFFF">
		<td width="400" colspan="2" align="center">
			<input type="button" value="���������" onClick="javascript:CashreceiptView('<%=cbtrno%>')">
		</td>
	</tr>
	<tr bgcolor="#E3E3E3">
		<td height="1" colspan="2">
		</td>
	</tr>
      <% } %>
</table>
</body>
</html>
