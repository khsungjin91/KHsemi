<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#section { width: 1300px; margin: 0 auto;  padding-top: 40px;}
#review {width:1200px; magin-top:50px;  margin: 0 auto}
#review td { height: 20px;  text-align: center;}
.table{margin-left: 100px; float: left;}
.table tr td {height: 30px; font-size: 18px}

#write {width:1200px; text-align: center; padding: 30px}

#care { margin-bottom: 60px}
#care td {height: 40px}
.img{width: 500px; height: 400px; margin-bottom: 300px}
.button{
	display: inline-block;
	zoom: 1;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none; 
	line-height: 100 %;
	font-family: 'Jeju Gothic', sans-serif;
	text-shadow: 0 1px 1px rgba(0,0,0,.3);
	border-radius:6px;
	box-shadow:0 1px 2px #444;
}
.white{
    color: black;
    border: solid 1px #444;
    background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#fff));
}

.small{
	font-size:20px;
	padding:2em 2em 2em

}
under{text-decoration: none}
</style>



</head>
<body>
<script>

	function idCheck(){
		
		alert("�α��� �� ��밡���մϴ�.");
		window.location="/thk/loginForm.cp";
	}

    function openInputCart(userinput) {
    	
        // url�� ����� �Է� id�� �����մϴ�.
        url = "/thk/inputCart.cp?p_code=" + userinput.p_code.value + "&p_name=" + userinput.p_name.value 
        		+ "&p_size=" + userinput.p_size.value + "&p_img=" + userinput.p_thumbnail.value 
        		+ "&p_price=" + userinput.p_price.value + "&p_price_dc=" + userinput.p_price_dc.value 
        		+ "&p_point=" + userinput.p_point.value + "&mem_num=" + userinput.mem_num.value;
        
        
        // ���ο� �����츦 ���ϴ�.
        open(url, "cart", 
        "toolbar=yes, location=yes,status=yes,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
    

</script>

<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>

	<section id="section">
	
		
		<c:set var="code" value="${code}"/>
<form action="/thk/orderForm.cp" method="post" name="userinput">
	<input type="hidden" name="p_code" value="${code.p_code}" />
	<input type="hidden" name="p_name" value="${code.p_name}" />
	<input type="hidden" name="p_thumbnail" value="${code.p_thumbnail}" />
	<input type="hidden" name="p_img" value="${code.p_img}" />
	<input type="hidden" name="p_price" value="${code.p_price}" />
	<input type="hidden" name="p_price_dc" value="${code.p_price_dc}" />
	<input type="hidden" name="p_point" value="${code.p_point}" />
	<input type="hidden" name="p_category1" value="${code.p_category1}" />
	<input type="hidden" name="p_category2" value="${code.p_category2}" />
	<input type="hidden" name="p_category3" value="${code.p_category3}" />
	<input type="hidden" name="mem_num" value="${mem_num}">
	<input type="hidden" name="id" value="${id}">
	<input type="hidden" name="url" value="showinfo">
	
	
<div style="margin: 0 auto; height: 500px">

	<div style="margin-left: 150px; margin-top:20px ; float:left;"><img src="/thk/save/${code.p_thumbnail}" width="400" ></div>
	
		<div class="table">
	
	<table width="400">
	<tr>
	<td>NAME</td><td>| | &nbsp; ${code.p_name}</td>
	</tr>
	<tr>
	<td>CODE</td><td>| | &nbsp; ${code.p_code}</td>
	</tr>
	<tr>
	<td>PRICE</td><td>| | &nbsp; <span style="text-decoration: line-through;">
								<fmt:formatNumber value="${code.p_price}" pattern="#,##0"/>��</span></td>
	</tr>
	<tr>
	<td>DISCOUNT</td><td>| | &nbsp;	<fmt:formatNumber value="${code.p_price_dc}" pattern="#,##0"/> ��</td>
	</tr>
	<tr>
	<td>POINT</td><td>| | &nbsp; ${code.p_point} <span style="color: red">Point</span></td>
	</tr>
	<tr>
	<td>MATERIAL</td><td>| | &nbsp; ${code.p_material}</td>
	</tr>
	<tr>
	<td colspan="2">
	<c:if test="${code.p_category1 == 'FOOTWEAR'}">
	SIZE &nbsp;
	<select name="p_size">
	<option value="220">220</option>
	<option value="230">230</option>
	<option value="240">240</option>
	<option value="250">250</option>
	<option value="260">260</option>
	<option value="270">270</option>
	<option value="280">280</option>
	</select>
	</c:if>
	<c:if test="${code.p_category1 == 'ACCESSORIES' }">
	
	</c:if>
	<c:if test="${code.p_category1 == 'APPAREL' }">
	SIZE &nbsp; FREE
	</c:if>
	
	</td>
	</tr> 
	</table>
				<div style="float: right; margin-top: 30px">
				<c:if test="${sessionScope.memId != null }">
					<input type="submit" value="BUY"  class="button white small" >
				</c:if>
				<c:if test="${sessionScope.memId == null }">
					<input type="button" value="BUY"  class="button white small" 
							onclick="return idCheck();">
				</c:if>
				
				<c:if test="${sessionScope.memId != null }">
					<input type="button" value="CART" onClick="openInputCart(this.form)" class="button white small">	
				</c:if>
				<c:if test="${sessionScope.memId == null }">
					<input type="button" value="CART" onClick="return idCheck();" class="button white small">
				</c:if>
				</div>
		
		</div>
</div>

</form> 

<hr color="black" >

<div style="text-align: center; margin-top: 100px">

<div><img src="/thk/save/${imgdto.img1}"  class="img"></div>
<div><img src="/thk/save/${imgdto.img2}"  class="img"></div>
<div><img src="/thk/save/${imgdto.img3}"  class="img"></div>
<div><img src="/thk/save/${imgdto.img4}"  class="img"></div>
<div><img src="/thk/save/${imgdto.img5}"  class="img"></div>

</div>

<div id="care" >
<table border="1">
	<tr>
	<td>��ǰ�� �ּ���</td><td> ${code.p_material}</td><td></td><td></td> 
	</tr>
	<tr>
	<td></td><td></td><td>���Կ���</td><td>��� ���������� ��</td> 
	</tr>
	<tr>
	<td>������</td><td>�ܱ����</td><td>å����</td><td>�׷��� ����.</td> 
	</tr>
	<tr>
	<td>ǰ������ ����</td><td colspan="3">����ǰ�� �����ŷ� ����ȸ�� ����� �Һ��ں��� �ذ� ���߿� �ǰ�, ������ �Һ��� ���ؿ� ���� ����, 
						��ȯ, ȯ���� �帳�ϴ�. �����Ⱓ 6���� ��ȯ��� : ������</td>
	</tr>
	<tr>
	<td>��޽� ���ǻ��� </td><td colspan="3">��õ�������� ���-������ų ������ ���޿��� �����ּ���. - ������ ������ �����ϰ��̳� �ҷ� ���� ��Ű�� ���ʽÿ�.
						-������ �ε巯�� �ַ� �۰�, ��ũ���ʸ� ����Ͻʽÿ�. -�����ÿ��� ������ �µ��� �������� �����Ͻʽÿ�.
						 �ؼ�����ǰ�Ǵ� �ռ������� ���-������ų ������ ���޿��� �����ֽʽÿ�.-�⸧�Ⱑ �ִ� ��ҿ����� ����� ���Ͻʽÿ�
						 -�ҿ��� ������ ��ȭ �ϰų� ������ �Ͼ �� ������ �����Ͻñ� �ٶ��ϴ�. </td>
	</tr>
	</table>
</div>

<hr color="black">

<div id="review">

<div style="text-align: center">
	<div style="padding: 10px;">��ǰ���� (���� : ${replecount})</div>
	
	<a href="/thk/reviewList.cp" class="button white small under">����Խ�������</a>
</div>
	<br/>
	<c:if test="${replecount == 0}">
	
	<div>�ۼ��� ���䰡 �����ϴ�.</div>
	
	</c:if>

	<c:if test="${replecount != 0}">
	
	<table width="1100" border="1" >
	
	<tr>
	<td style="height:40px; width:200px">����</td>
	<td style="height:30px; width:700px">����</td>
	<td style="height:40px; width:150px">�ۼ���</td>
	<td style="height:40px; width:70px">��ȸ��</td>
	<td style="height:40px; width:300px">�ۼ�����</td>
	<td style="height:40px; width:200px"> </td>
	</tr>
	
	<c:forEach var="reple" items="${p_reple}">
	
	<tr>
	<td style="height:40px; width:200px">
	<a href="/thk/imgView.cp?img=${reple.img}" onclick=""><img src="/thk/save/${reple.img}" width="100"></a>
	</td>
	<td style="height:30px; width:700px">${reple.content}</td>
	<td style="height:40px; width:150px">${reple.mem_id}</td>
	<td style="height:40px; width:70px">${reple.readcount}</td>
	<td style="height:40px; width:300px">${reple.reg_date}</td>
	<td style="height:40px; width:200px">
	<c:if test="${sessionScope.memId == reple.mem_id}">
	
	<a href="/thk/reviewFix.cp?num=${reple.num}" class="button white small under">����</a>
	<a href="/thk/reviewDeletePro.cp?num=${reple.num}&url=showinfo&p_code=${code.p_code}" class="button white small under">����</a>
		<br/>
	</c:if>
	</td>
	</tr>
		<%-- <img src="/thk/save/${reple.img}" width="400">
			${reple.content}
		��${reple.star}--%>
	</c:forEach>
	</table>
	</c:if>
	
</div>



<div id="write">


	<div>����ۼ�</div>
	
	<c:if test="${compare == -1}">
		<c:if test ="${sessionScope.memId == 'owner' }">
		������ �α��� ��....
		</c:if>
		<c:if test ="${sessionScope.memId == null }">
		<p>�α��� �� �̿�ٶ��ϴ�.</p>
		<a href="/thk/loginForm.cp" class="button white small under">�α���</a>
		</c:if>
		
	</c:if>
	
	<c:if test="${compare == 0}">
	���� �� �����ۼ��� �����մϴ�.
	</c:if>	
		
	<c:if test="${compare == 1}">
	
	
	<form action="/thk/reviewWritePro.cp" method="post" enctype = "multipart/form-data">
	
	<input type="hidden" name="subject" value="${sessionScope.memId}���� ${code.p_name}�����Դϴ�.">
	<input type="hidden" name="p_code" value="${code.p_code}">
	<input type="hidden" name="mem_num" value="${mem_num}">
	<input type="hidden" name="mem_id" value="${id}">

<div>����<input type="file" name="save"></div>

	<textarea rows="5" cols="80" name="content"></textarea>
	<div>���� 
	<c:forEach var="i" begin="1" step="1" end="5">
    <input type="radio" name="star" value="${i}" title="${i}��"/>${i}��
    </c:forEach>
	</div>
	<input type="submit" value="�ۼ�">
		
	</form>
	</c:if>
	
</div>	

	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>