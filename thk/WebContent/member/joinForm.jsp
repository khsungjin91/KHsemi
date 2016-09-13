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
            alert("ID를 입력하세요");
            return false;
        }
        
        if(!userinput.mem_pw.value ) {
            alert("비밀번호를 입력하세요");
            return false;
        }
       
        if(!userinput.mem_name.value) {
            alert("사용자 이름을 입력하세요");
            return false;
        }
    }

    // 아이디 중복 여부를 판단
    function openConfirmid(userinput) {
        // 아이디를 입력했는지 검사
        if (userinput.mem_id.value == "") {
            alert("아이디를 입력하세요");
            return;
        }
        // url과 사용자 입력 id를 조합합니다.
        url = "confirmId.cp?mem_id=" + userinput.mem_id.value ;
        
        // 새로운 윈도우를 엽니다.
        open(url, "confirm", 
        "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
</script>



<body onload="init();">

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	<section id="section">
		
	

	<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px;">회원가입</div>
	
	<form method="post" action="joinPro.cp" name="userinput" onSubmit="return checkIt()">
	<table style="margin-right: 300px; width: 1000px" >
		<tr>
		<td align="right">아이디</td>
		<td>
		<input type="text" name="mem_id" size="10" maxlength="15">
		<input type="button" name="confirm_id" value="ID중복확인" onClick="openConfirmid(this.form)">(영문 숫자 조합)
		</td>
		</tr>
		<tr>
		<td align="right">비밀번호</td>
		<td><input type="password" name="mem_pw" size="18" maxlength="12">(영문 숫자 조합)</td>
		</tr>
		<tr>
		<td align="right">사용자이름</td>
		<td><input type="text" name="mem_name" size="15" maxlength="10"></td>
		</tr>
		<tr>
		<td align="right">전화번호</td>
		<td><input type="text" name="mem_phone" size="12" maxlength="11">('-'를 제외하고 입력해주세요.)</td>
		</tr>
		<tr>
		<td align="right">SNS수신여부</td>
		<td>
		<input type="radio" name="mem_sms_yn" value="y">Yes
		<input type="radio" name="mem_sms_yn" value="n">No
		</td>
		</tr>
		<tr>
		<td align="right">이메일</td>
		<td><input type="text" name="mem_email" size="40" maxlength="30"></td>
		</tr>
		<tr>
		<td align="right">이메일 수신여부</td>
		<td>
		<input type="radio" name="mem_email_yn" value="y">Yes
		<input type="radio" name="mem_email_yn" value="n">No
		</td>
		</tr>
		<tr>
		<td align="right" style="height: 100px">주소</td>
		<td style="height: 100px">
		<input type="text" id="sample6_postcode" placeholder="우편번호" name ="mem_postnum" style="padding: 3px">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" >
		<div>
		<input type="text" id="sample6_address" placeholder="주소" name="mem_addrs1" style="padding: 3px" size="25">
		<input type="text" id="sample6_address2" placeholder="상세주소" name="mem_addrs2" style="padding: 3px; margin-top:10px " size="20">
		</div>
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
			function sample6_execDaumPostcode() {
				new daum.Postcode({
            		oncomplete: function(data) {
                	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                		// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                		// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                		var fullAddr = ''; // 최종 주소 변수
                		var extraAddr = ''; // 조합형 주소 변수

                		// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                		if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    		fullAddr = data.roadAddress;

                		} else { // 사용자가 지번 주소를 선택했을 경우(J)
                    		fullAddr = data.jibunAddress;
                		}

                		// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                		if(data.userSelectedType === 'R'){
                    		//법정동명이 있을 경우 추가한다.
                    		if(data.bname !== ''){
                        		extraAddr += data.bname;
                    		}
                    		// 건물명이 있을 경우 추가한다.
                    		if(data.buildingName !== ''){
                        		extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    		}
                    		// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    		fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                		}

               			// 우편번호와 주소 정보를 해당 필드에 넣는다.
                		document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                		document.getElementById('sample6_address').value = fullAddr;

                		// 커서를 상세주소 필드로 이동한다.
                		document.getElementById('sample6_address2').focus();
            		}
				}).open();
			}
			
		</script>
		</td>
		</tr>
	</table>			
<div style="text-align: center;">
		<input type="submit" name="confirm" value="등록" class="button white small">
		<input type="reset" name="reset" value="다시입력" class="button white small">
</div>
	</form>
	
		
	</section>
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>