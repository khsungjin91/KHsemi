<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>:Thk.ment:</title>

<style>

#section { width: 1200px; margin: 0 auto;  padding-top: 30px}
#section2 {width: 1000px; margin: 0 auto;   font-size: 20px}
#section2 td {font-size: 25px; padding: 50px; border: 6px solid white;}
.subject { text-align: center; font-size: 20px; font-weight: bold; margin-top: 30px; margin-bottom: 60px}

</style>

</head>
<body>

<jsp:include page="/WEB-INF/CssForm/membertop.jsp"></jsp:include>	

	<section id="section">
		<section id="section2">
	
		<table>
			<tr>
				<td rowspan="2" >
				<div style="margin-bottom: 80px; ">
					<div style=" margin-left:20px; font-size: 30px ">WELCOME</div>

					<div style="margin-top:20px; padding-left:130px;  font-size: 30px">THREE.KH</div>
				</div>
				<div>
					<div style="margin-left: 20px; font-size: 20px">${id} �� ȯ���մϴ�.</div>
					<br/>
					<div style="margin-left: 200px; font-size: 15px" >������ ${indate}</div>
				</div>
				</td>
				<td bgcolor="purple" style="color: white">
					<div>�� ���� ����</div>
					<br/>
					<div style="float:right; font-size: 17px">${allproduct}�� </div>
				</td>
				<td bgcolor="yellow">
					<div>MY POINT</div> 
					<br/>
					<div style="float:right; font-size: 17px">${info.mem_point} point</div>
				</td>
			</tr>
			
			<tr>
				<td bgcolor="silver">
				<div>�� ���� �ݾ�</div>
				<br/>
				<div style="float:right; font-size: 17px">
				<fmt:formatNumber value="${allpay}" pattern="#,##0"/>��</div>
				</td>
				<td bgcolor="gray" style="color: white">
				<div>MY CART </div>
				<br/>
				<div style="float:right; font-size: 15px"> ${cartcount} ��</div>
				 </td>
			</tr>
			<tr>
			<td bgcolor="#6B66FF"><div>ȸ�����</div>
			<div style="float:right; font-size: 18px">
			<c:if test="${info.mem_rate == '�ǹ�'}">
			<img src="/thk/save/level1.jpg" width="80">
			</c:if>
			<c:if test="${info.mem_rate == '¤��'}">
			<img src="/thk/save/level2.jpg" width="80">
			</c:if>
			<c:if test="${info.mem_rate == '����'}">
			<img src="/thk/save/level4.jpg" width="80">
			</c:if>
			<c:if test="${info.mem_rate == '�ȭ'}">
			<img src="/thk/save/level3.jpg" width="80">
			</c:if>
			<c:if test="${info.mem_rate == '�ɽ�'}">
			<img src="/thk/save/level5.jpg" width="80">
			</c:if>
				${info.mem_rate}</div></td>
			</tr>		
		</table>
			
			
		</section>
	</section>
	
	
<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>
</html>