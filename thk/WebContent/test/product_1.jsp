<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>:Thk.ment:</title>
<link href="style.css" rel="stylesheet" type="text/css">

<style>
#logon{ width: 1024px; height: 30px; margin: 0 auto; border: 1px solid black; text-align:right}
#logonAd{ width: 1024px; height: 30px; margin: 0 auto; border: 1px solid black; text-align:right; background: #002187; color: white;}
#header{ width: 1024px; height: 110px; margin: 0 auto; border: 1px solid black; }
#logo{ width: 350px; height: 100px; margin: 5px 5px; border: 1px solid black; float: left; }
#menu1{width: 650px; height: 100px; margin: 5px 5px; border: 1px solid black; float: right;}
#menu1 > ul { overflow: hidden; }
#menu1 > ul > li { float: left; position:relative;}
#menu1 > ul > li > a { display: black; padding: 10px 10px; color: black; text-transform:uppercase;}
#menu1 > ul > li > a:hover { text-decoration:uderline; }
#section { width: 1024px; margin: 0 auto; border: 1px solid black; }
#border { width: 964px; height: 800px; margin: 20px auto; border: 1px solid black; }
#article { width: 964px; margin: 20px auto; border: 1px solid black; }
#product_List {width: 300px; height: 250px; margin: 7px 7px; border: 1px solid black; float: left; }
#product { width: 900px; margin: 20px auto; border: 1px solid black; }
#footer { width: 1024px; height: 210px; margin: 0 auto; border: 1px solid black; }
#guide { width: 964px; height: 40px; margin: 5px 30px; border: 1px solid black; }
#guide > ul { overflow: hidden; }
#guide > ul > li { float: left; }
#guide > ul > li > a { display: black; padding: 10px 50px; color: black; font-size:9px; }
#company { width: 964px; height: 150px; margin: 5px 30px; border: 1px solid black;}
</style>

<script>
    function openInputCart(userinput) {
    	
        // url�� ����� �Է� id�� �����մϴ�.
        url = "inputCart.cp?pcode=" + userinput.p_code.value + "&pname=" + userinput.p_name.value 
        		+ "&psize=" + userinput.p_size.value + "&pimg=" + userinput.p_thumbnail.value 
        		+ "&pprice=" + userinput.p_price.value + "&ppricedc=" + userinput.p_price_dc.value 
        		+ "&ppoint=" + userinput.p_point.value + "&memnum=" + userinput.mem_num.value;
        
        
        // ���ο� �����츦 ���ϴ�.
        open(url, "cart", 
        "toolbar=yes, location=yes,status=yes,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
</script>

</head>
<body onload="init();">

	<c:choose>
		<c:when test="${memId == null }">
			<nav id="logon">
				<input type="button" value="login" onclick="javascript:window.location='loginForm.cp'">
				<input type="button" value="join" onclick="javascript:window.location='joinForm.cp'">
			</nav>
		</c:when>
		<c:otherwise>
			<c:if test="${memId != null && memId != 'owner'}">
				<nav id="logon">
					<c:out value="${memId}" />�� ȯ���մϴ�! &nbsp;
					<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
					<input type="button" value="My Page" onclick="javascript:window.location='memberInfo.cp'">
				</nav>
			</c:if>
			<c:if test="${memId == 'owner'}">
				<nav id="logonAd">
					<input type="button" value="admin" onclick="javascript:window.location='admin.cp'">
					<input type="button" value="logout" onclick="javascript:window.location='logout.cp'">
				</nav>
			</c:if>
		</c:otherwise>
	</c:choose>

  <header id="header">
		<div id=logo>
			<a href="/thk/main.cp">Th.K Ment</a>
		</div>
		<nav id="menu1">
			<ul>
				<li><a href="/thk/about.cp">ABOUT</a></li>
				<li><a href="/thk/lookbook.cp">LOOKBOOK</a></li>
				
				<li><a href="/thk/newsList.cp">COMMUNITY</a>
				  <div id="menu2">
				    <ul>
				      <li><a href="/thk/newsList.cp">NEWS&EVENT</a></li>
				      <li><a href="/thk/reviewList.cp">REVIEW</a></li>
				    </ul>
				  </div>
				</li>
				<li><a href="/thk/borderList.cp">SUPPORT</a>
				  <div id="menu3">
				    <ul>
				      <li><a href="/thk/borderList.cp">NOTICE</a></li>
				      <li><a href="/thk/qnaList.cp">Q&A</a></li>
				      <li><a href="/thk/faq.cp">FAQ</a></li>
				    </ul>
				  </div>
				</li>
				<li><a href="/thk/allProductList.cp">E-SHOP</a>
				  <div id="menu4">
				    <ul>
				      <li><a href="/thk/allProductList.cp">ALL PRODUCT</a></li>
				      <li><a href="/thk/footWearList.cp">FOOTWEAR</a></li>
				      <li><a href="/thk/accessoriesList.cp">ACCESSORIES</a></li>
				      <li><a href="/thk/apparelList.cp">APPAREL</a></li>
				    </ul>
				  </div>
				</li>
			</ul>
	  </nav>
  </header>
	
	<section id="section">
		<div id="article">
		
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
	<input type="hidden" name="mem_num" value="${memNum}">

	�̹���
	<div><img src="/thk/save/${code.p_thumbnail}" width="600"></div>
	��ǰ��
	<div>${code.p_name}</div>
	����
	<div>${code.p_price}</div>
	���ΰ�
	<div>${code.p_price_dc}</div>
	����Ʈ
	<div>${code.p_point}</div>
	��ǰ�ڵ�
	<div> ${code.p_code}</div>
	����
	<div>${code.p_material}</div>
	<hr>

	SIZE 
	<select name="p_size">
	<option>220</option><option>230</option><option>240</option>
	<option>250</option><option>260</option><option>270</option>
	<option>280</option></select><br />
	
	<input type="submit" value="BUY">
	<input type="button" value="CART" onClick="openInputCart(this.form)">
</form>

	���û�ǰ<div><image src="/thk/save/${code.p_img}" width="200"></div>���̳��Ž�


	<br /><hr />��ǰ����
		</div>
	</section>
	
	
	<footer id="footer">
		<nav id="guide">
		<ul>
				<li><a href="/thk/main.cp">HOME</a></li>
				<li><a href="/thk/about.cp">COMPANY</a></li>
				<li><a href="/thk/guide.cp">GUIDE</a></li>
				<li><a href="/thk/agreement.cp">AGREEMENT</a></li>
				<li><a href="/thk/private.cp">PRIVATE</a></li>
			</ul>
		</nav>
		<div id="company">
			��ȣ : Th.K Ment | �ּ� : ���ѹα�<br />
			����ڵ�Ϲ�ȣ : 777-77-77777 | ����Ǹž��Ű� : 2016-���ѹα�-7777<br />
			��ȯ �� ��ǰ ���� �¶��� ����� ������ : 7777-7777<br />
			����: thkment7@thk.com<br />
			Copyright 2016 th.k �� all right reserved<br />
		</div>
	</footer>
</body>
</html>