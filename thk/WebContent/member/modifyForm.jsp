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
alert("비밀번호를 확인해주세요");
window.location=history.go(-1);

</script>

</c:if>
	
<c:if test="${check == 1 }">	
	<section id="section">
			<section id="section2">
	
			<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px; margin-bottom: 30px">회원정보수정</div>
	
			<form method="post" action="modifyPro.cp" name="userinput" onSubmit="return checkIt()">
	
	
	
		<div style="float: left; text-align:justify; margin-right:20px; margin-left: 60px">
			<div id="mar">아이디</div>
		
			<div id="mar">비밀번호</div>
		
			<div id="mar">사용자 이름 </div>
		
			<div id="mar">전화번호</div>
		
			<div id="mar">SNS수신여부</div>
	
			<div id="mar2">이메일</div>
	
			<div id="mar2">이메일 수신여부</div>
	
			<div id="mar">우편번호</div>
			
			<div id="mar2">주소</div>
			
			<div id="mar">상세주소</div>
			
		</div>
			
			
		<div>
			<div id="mar"> | | &nbsp;<c:out value="${memId}"/></div>
			<div id="mar"> | | &nbsp;<input type="password" name="mem_pw" size="15" maxlength="12" value="${data.getMem_pw()}"></div>
			<div id="mar"> | | &nbsp;${data.getMem_name()}</div>
			<div id="mar"> | | &nbsp;<input type="text" name="mem_phone" size="20" maxlength="15"> &nbsp; ('-'를 제외하고 입력해주세요.)</div>
			<div id="mar"> | | &nbsp;<input type="radio" name="mem_sms_yn" value="y">Yes
					 <input type="radio" name="mem_sms_yn" value="n">No </div>
			<div id="mar"> | | &nbsp;<input type="text" name="mem_email" size="40" maxlength="30"></div>
		
		
			<div id="mar"> | | &nbsp;<input type="radio" name="mem_email_yn" value="y">Yes
					  <input type="radio" name="mem_email_yn" value="n">No </div>
		
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_postcode" placeholder="우편번호" name="mem_postnum">
					  &nbsp;<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="button white small"> </div>
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_address" placeholder="주소" name="mem_addrs1" size="50"></div>
			<div id="mar"> | | &nbsp;<input type="text" id="sample6_address2" placeholder="상세주소" name="mem_addrs2" size="50"></div>
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
		<br />
		<br />
	
		<div style="float: right; margin-right: 190px">
		<input type="submit" name="confirm" value="등   록" class="button white small">
		<input type="reset" name="reset" value="다시입력" class="button white small">
	<input type="button" name="delete" value="회원탈퇴" onclick="javascript:window.location='withdrawForm.cp'" class="button white small">
		</div>
	</form>
		</section>
		
	</section>
</c:if>	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
