<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.mnet Join:</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto; padding-bottom:100px  }
#section2 {width: 600px; margin: 0 auto;  }

#section tr td{height: 80px; padding: 20px; font-size: 18px}
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
<script>
    
    function checkIt() {
        var userinput = eval("document.userinput");
        if(!userinput.mem_id.value) {
            alert("ID�� �Է��ϼ���");
            return false;
        }
        
        if(!userinput.mem_pw.value ) {
            alert("��й�ȣ�� �Է��ϼ���");
            return false;
        }
       
        if(!userinput.mem_name.value) {
            alert("����� �̸��� �Է��ϼ���");
            return false;
        }
    }

    // ���̵� �ߺ� ���θ� �Ǵ�
    function openConfirmid(userinput) {
        // ���̵� �Է��ߴ��� �˻�
        if (userinput.mem_id.value == "") {
            alert("���̵� �Է��ϼ���");
            return;
        }
        // url�� ����� �Է� id�� �����մϴ�.
        url = "confirmId.cp?mem_id=" + userinput.mem_id.value ;
        
        // ���ο� �����츦 ���ϴ�.
        open(url, "confirm", 
        "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
</script>



<body onload="init();">

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	<section id="section">
		
	

	<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">ȸ������</div>
	
	<form method="post" action="joinPro.cp" name="userinput" onSubmit="return checkIt()">
	<table style="margin-right: 300px; width: 1000px" >
		<tr>
		<td align="right">���̵�</td>
		<td>
		<input type="text" name="mem_id" size="10" maxlength="15">
		<input type="button" name="confirm_id" value="ID�ߺ�Ȯ��" onClick="openConfirmid(this.form)">(���� ���� ����)
		</td>
		</tr>
		<tr>
		<td align="right">��й�ȣ</td>
		<td><input type="password" name="mem_pw" size="18" maxlength="12">(���� ���� ����)</td>
		</tr>
		<tr>
		<td align="right">������̸�</td>
		<td><input type="text" name="mem_name" size="15" maxlength="10"></td>
		</tr>
		<tr>
		<td align="right">��ȭ��ȣ</td>
		<td><input type="text" name="mem_phone" size="12" maxlength="11">('-'�� �����ϰ� �Է����ּ���.)</td>
		</tr>
		<tr>
		<td align="right">SNS���ſ���</td>
		<td>
		<input type="radio" name="mem_sms_yn" value="y">Yes
		<input type="radio" name="mem_sms_yn" value="n">No
		</td>
		</tr>
		<tr>
		<td align="right">�̸���</td>
		<td><input type="text" name="mem_email" size="40" maxlength="30"></td>
		</tr>
		<tr>
		<td align="right">�̸��� ���ſ���</td>
		<td>
		<input type="radio" name="mem_email_yn" value="y">Yes
		<input type="radio" name="mem_email_yn" value="n">No
		</td>
		</tr>
		<tr>
		<td align="right" style="height: 100px">�ּ�</td>
		<td style="height: 100px">
		<input type="text" id="sample6_postcode" placeholder="�����ȣ" name ="mem_postnum" style="padding: 3px">
		<input type="button" onclick="sample6_execDaumPostcode()" value="�����ȣ ã��" >
		<div>
		<input type="text" id="sample6_address" placeholder="�ּ�" name="mem_addrs1" style="padding: 3px" size="25">
		<input type="text" id="sample6_address2" placeholder="���ּ�" name="mem_addrs2" style="padding: 3px; margin-top:10px " size="20">
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
		</td>
		</tr>
	</table>			
<div style="text-align: center;">
		<input type="submit" name="confirm" value="���" class="button white small">
		<input type="reset" name="reset" value="�ٽ��Է�" class="button white small">
</div>
	</form>
	
		
	</section>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>