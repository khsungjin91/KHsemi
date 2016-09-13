<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rcid		= request.getParameter("reCommConId"		);
	String rctype	= request.getParameter("reCommType"		);
	String rhash	= request.getParameter("reHash"			);
	
	String p_protocol = request.getScheme().equalsIgnoreCase("https") ? "https" : "http";
%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>KSPay(<%=rcid%>)</title>
<script>
    function init()
	{
 		if (typeof(top.opener) == "undefined" || typeof(top.opener.eparamSet) == "undefined" || typeof(top.opener.goResult) == "undefined")
 		{
 			alert("ERROR: �ֹ��������� Ȯ���� �� ���� ������ �ߴ��մϴ�!!");
 			self.close();
 			return;
 		}
<% if (null != rcid && 10 > rcid.length()){ %>
		alert("ERROR: ������û����(<%=rcid%>)�� Ȯ���� �� ���� ������ �ߴ��մϴ�!!");
		self.close();
		return;
<% }else{ %>
        top.opener.eparamSet("<%=rcid%>","<%=rctype%>","<%=rhash%>");
        top.opener.goResult();
<% } %>
		setTimeout( 'self.close()', '3000' );
    }

    init();
</script>
</head>
<body>
 	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
        <td valign="middle" align="center"><table width="450" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="<%=p_protocol%>://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/processing.gif" width="450" height="141"></td>
          </tr>
          <tr>
            <td><img src="<%=p_protocol%>://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/process_step.gif" width="456" height="20"></td>
          </tr>
        </table>		
		</td>
      </tr>
    </table>
</body>
</html>