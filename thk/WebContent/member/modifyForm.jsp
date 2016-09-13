<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>:Thk.ment:</title>
<style>
#section { width: 1200px; margin: 0 auto; padding-bottom:200px }
#section2 { width: 650px; margin: 0 auto; font-size: 16px}
#mar {margin-bottom: 10px}
#mar2 {margin-bottom: 15px}
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

<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>	

<c:if test="${check == 0}">

<script type="text/javascript">
alert("��й�ȣ�� Ȯ�����ּ���");
window.location=history.go(-1);

</script>

</c:if>
	
<c:if test="${check == 1 }">	
	<section id="section">
			<section id="section2">
	
			<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px; margin-bottom: 30px">ȸ����������</div>
	
			<form method="post" action="modifyPro.cp" name="userinput" onSubmit="return checkIt()">
	
	
	
		<div style="float: left; text-align:justify; margin-right:20px; margin-left: 60px">
			<div id="mar">���̵�</div>
		
			<div id="mar">��й�ȣ</div>
		
			<div id="mar">����� �̸� </div>
		
			<div id="mar">��ȭ��ȣ</div>
		
			<div id="mar">SNS���ſ���</div>
	
			<div id="mar2">�̸���</div>
	
			<div id="mar2">�̸��� ���ſ���</div>
	
			<div id="mar">�����ȣ</div>
			
			<div id="mar2">�ּ�</div>
			
			<div id="mar">���ּ�</div>
			
		</div>
			
			
		<div>
			<div id="mar"> | | &nbsp;<c:out value="${memId}"/></div>
			<div id="mar"> | | &nbsp;<input type="password" name="mem_pw" size="15" maxlength="12" value="${data.getMem_pw()}"></div>
			<div id="mar"> | | &nbsp;${data.getMem_name()}</div>
			<div id="mar"> | | &nbsp;<input type="text" name="mem_phone" size="20" maxlength="15"> &nbsp; ('-'�� �����ϰ� �Է����ּ���.)</div>
			<div id="mar"> | | &nbsp;<input type="radio" name="mem_sms_yn" value="y">Yes
					 <input type="radio" name="mem_sms_yn" value="n">No </div>
			<div id="mar"> | | &nbsp;<input type="text" name="mem_email" size="40" maxlength="30"></div>
		
		
			<div id="mar"> | | &nbsp;<input type="radio" name="mem_email_yn" value="y">Yes
					  <input type="radio" name="mem_email_yn" value="n">No </div>
		
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_postcode" placeholder="�����ȣ" name="mem_postnum">
					  &nbsp;<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��" class="button white small"> </div>
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_address" placeholder="�ּ�" name="mem_addrs1" size="50"></div>
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_address2" placeholder="���ּ�" name="mem_addrs2" size="50"></div>
		</div>
			
			
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
		<br />
		<br />
	
		<div style="float: right; margin-right: 190px">
		<input type="submit" name="confirm" value="��   ��" class="button white small">
		<input type="reset" name="reset" value="�ٽ��Է�" class="button white small">
	<input type="button" name="delete" value="ȸ��Ż��" onclick="javascript:window.location='withdrawForm.cp'" class="button white small">
		</div>
	</form>
		</section>
		
	</section>
</c:if>	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
