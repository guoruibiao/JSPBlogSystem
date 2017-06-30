<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<h1>成功注销登录，3秒后返回到主页面。</h1>
<script>
	setTimeout(function() {
		window.location.href = "index.jsp";
	}, 3000);
</script>
</body>
</html>