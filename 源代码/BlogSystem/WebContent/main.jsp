<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style media="screen">
body {
	overflow: hidden;
	height: 100%;
	margin: 0;
	padding: 0;
}

img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body onload="run();">
	<audio autoplay="autoplay" controls="controls" loop="loop"
		preload="true"
		src="<%=request.getContextPath()%>/static/assets/TheMountain.mp3"></audio>
	<a href="./index.jsp"><img id="background" alt="background" src="" /></a>

</body>
<!-- 加载雨滴页面效果 -->
<script id="rainy"
	src="<%=request.getContextPath()%>/static/js/rainyday.js"></script>
<script>
	function run() {
		var image = document.getElementById('background');
		image.onload = function() {
			var engine = new RainyDay({
				image : this
			});
			engine.rain([ [ 3, 2, 2 ] ], 100);

			// 先听音乐 N秒，后即可跳转到登录注册页面。
			setTimeout(function(){
				window.location.href = "index.jsp";
			}, 38000);
		};
		image.crossOrigin = 'anonymous';
		image.src = 'http://i.imgur.com/U1Tqqdw.jpg';
	}

	// 页面一旦加载完成即显示雨滴效果
	$(document).ready(function() {
		run();
		setTimeout(function() {
			$("#background").css("display", "none");
		}, 5000);
	});
</script>
</html>