<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="http://designmodo.com/demo/css3slider/css/slider.css" type="text/css" media="screen">
<style type="text/css">


.slide-wrapper {width:1200px; margin:0 auto; border: 1px solid black}

.slide-wrapper ul li {float: left; display: block; padding: 15px;}

ul.s-slides, ul.s-slides li, ul.s-slides a, ul.s-slides img {
    width: 500px;
    height: 350px;
    position: relative;
}

.slide-wrapper ul.s-slides li.slideLeft:target {
    
    -webkit-animation-name: slideLeft;
    -webkit-animation-duration: 1s;
    -webkit-animation-iteration-count: 1;
}
.slide-wrapper ul.s-slides {margin:0 auto}

.slider-wrapper ul, .slider-wrapper li, .slider-wrapper div, .slider-wrapper img, .slider-wrapper a {
    margin: 0;
    padding: 0;
    outline: none;
    list-style: none;
}

</style>
</head>
<body>


<div class="slide-wrapper">
<div>
	<ul class="s-slides" style="float: left;">
		<li class="slideLeft first" id="slide-1"><img src="/thk/save/1.png"></li>
		<li class="slideLeft" id="slide-2"><img src="/thk/save/10.png"></li>
		<li class="slideLeft" id="slide-3"><img src="/thk/save/black.jpg"></li>
		<li class="slideLeft" id="slide-4"><img src="/thk/save/12.png"></li>
		<li class="slideLeft" id="slide-5"><img src="/thk/save/21.png"></li>
		<li class="slideLeft" id="slide-6"><img src="/thk/save/3.png"></li>
	</ul>
	<ul class="s-thumbs" style="float: left;">
		<li><a href="#slide-1"><img src="/thk/save/1.png" width="100"></a></li>
		<li><a href="#slide-2"><img src="/thk/save/10.png" width="100"></a></li>
		<li><a href="#slide-3"><img src="/thk/save/black.jpg" width="100"></a></li>
		<li><a href="#slide-4"><img src="/thk/save/12.png" width="100"></a></li>
		<li><a href="#slide-5"><img src="/thk/save/21.png" width="100"></a></li>
		<li><a href="#slide-6"><img src="/thk/save/3.png" width="100"></a></li>
	</ul>
</div>	
	
</div>

</body>
</html>