<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type="text/css">
#nav {
display: block;
width: 280px;
margin: 0 auto;

}

#nav > li > a {
display: block;
padding: 16px 18px;
text-decoration: none;
border-bottom: 1px solid black;
background-color: white;
}

#nav > li > a:hover, #nav > li > a.open {
	color: black;
}
#nav li ul {display: none; background: blue}

#nav li ul li a{
	display: block;
	background: none;
	padding: 10px 0px;
	padding-left: 30px;
	text-decoration: none;
	color: red;
}
#nav li ul li a:hover {
	background: green;
}
</style>
</head>
<body>

<script type="text/javascript">

$(document).ready(function(){
	$("#nav > li > a").on("click", function(e){
		if($(this).parent().has("ul")){
			e.preventDefault();
		}
		
		if(!$(this).hasClass("open")){
			
			$("#nav li ul").slidup(350);
			$("#nav li a").removeClass("open");
			
			$(this).next("ul").slideDown(350);
			$(this).addClass("open");
		}
		
		else if($(this).hasClass("open")){
			$(this).removeClass("open");
			$(this).next("ul").slideUp(350);
		
		}
	});
});
		
</script>


<nav>
	<ul id="nav">
		<li><a href="#">start</a>
			<ul>
				<li><a href="#">음</a></li>
				<li><a href="#">과</a></li>
				<li><a href="#">연</a></li>
				<li><a href="#">음?</a></li>	
			</ul>
		</li>
	</ul>
</nav>




</body>
</html>