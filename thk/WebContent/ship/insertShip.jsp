<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��۵��</title>
<style type="text/css">
#section {width: 1200px; margin: 0 auto}

</style>
</head>
<body>

<jsp:include page="/WEB-INF/CssForm/admintop.jsp"></jsp:include>

<div id="section">

<div>��۵��</div>
<form action="/thk/insertShipPro.cp" method="post" >

<input type="hidden" name="mem_num" value="${memdto.mem_num}">
<input type="hidden" name="pay_code" value="${pay_code}">


<div> �ֹ���ȣ ${pay_code}</div>

<div> �����ȣ <input type="text" name="shipnum" size="15"></div>

<div>���̸� ${memdto.get_name}</div>

<div>����ȭ��ȣ ${memdto.get_phone}</div>

<div> �����  ${memdto.get_postnum} &nbsp; ${memdto.get_addrs1} &nbsp; ${memdto.get_addrs2} </div>

<div> ��۽� �䱸����  ${memdto.get_memo}</div>

<div>
��ۻ���
<select name="shipcheck">
<option value="���Ա�">���Ա�</option>
<option value="�Ա�Ȯ��">�Ա�Ȯ��</option>
<option value="��ǰ�غ���">��ǰ�غ���</option>
<option value="�԰�����">�԰�����</option>
<option value="�����">�����</option>
<option value="��ۿϷ�">��ۿϷ�</option>
</select>
</div>

<input type="submit" value="���">

</form>




</div>


<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>

</body>
</html>