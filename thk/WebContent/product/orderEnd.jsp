<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�ֹ��Ϸ�</title>
<style type="text/css">
#section { width: 1200px; margin: 0 auto;  padding-top: 40px;}


</style>
</head>
<body>
<jsp:include page="/WEB-INF/CssForm/top.jsp"></jsp:include>
<h1>�ֹ��Ϸ�</h1><br />
	<div>
	<h3>�ֹ��������</h3>
	</div>
			<c:if test="${param.sndPaymethod==1000000000}">
					<div>
					==�ſ�ī�����==<br/>
					�ֹ���ȣ ${param.sndOrdernumber} <br/>
					�ֹ��ڸ� ${param.sndOrdername} <br/>
					�ֹ����� ${strdate} 			<br/>
					�ֹ��ݾ� ${param.sndAmount} <br/>
					���� ����Ʈ ${param.p_point} <br />
					</div>
				</c:if>
				
				
				<c:if test="${param.sndPaymethod==0010000000}">
					<div>
					==������ü����==<br/>
					�ֹ���ȣ ${param.sndOrdernumber} <br/>
					�ֹ��ڸ� ${param.sndOrdername} <br/>
					�ֹ����� ${trddt} <br/>
					�ֹ��ݾ� ${amt} <br/>
					�Ա����� ${authno}<br/>
					���¹�ȣ ${isscd} <br/>
					���� ����Ʈ ${param.p_point} <br />
					</div>
				</c:if>
				
				
				<c:if test="${param.sndPaymethod==0100000000}">
					<div>
					==������°���==<br/>
					�ֹ���ȣ ${param.sndOrdernumber} <br/>
					�ֹ��ڸ� ${param.sndOrdername} <br/>
					�ֹ����� ${trddt} <br/>
					�ֹ��ݾ� ${amt} <br/>
					�Ա����� ${authno}<br/>
					���¹�ȣ ${isscd} <br/>
					���� ����Ʈ ${param.p_point}
					</div>
				</c:if>
	
</body>

</html>
		
	<jsp:include page="/WEB-INF/CssForm/bottom.jsp"></jsp:include>
</body>

</html>