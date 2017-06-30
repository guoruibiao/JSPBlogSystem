<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>


<style>
.form {
	/*margin-top: 80px;*/
	margin-left: 60%;
}

.panel {
	margin-left: 38px;
	align: left;
	float: left;
}
</style>

<!-- 对于登录输入的用户验证JavaScript脚本 -->
<script src="<%=request.getContextPath()%>/static/js/login.js"></script>
<div class="container lead">
	<div class="row clearfix">

		<div class="col-md-12 column">
			<div class="row panel">
				<%
					String username = (String) session.getAttribute("username");
					String imgsrc = request.getContextPath() + "/upload/" + username + "/" + username + ".jpg";
				%>
				<img class="img-rounded" style="width: 460px; height: auto;"
					src="<%=imgsrc%>">
			</div>
			<div class="col-md-6 form">
				<form action="uploadhandler.do" enctype="multipart/form-data"
					method="post">
					<br> 更换头像：<input class="btn btn-success" type="file"
						name="file1"><br> <br> <input
						class="btn btn-warning btn-lg" type="submit" value="提交">
				</form>
			</div>
			<div class="col-md-6">
				<br> <br> <br>
				<p class="text-right lead">鉴于浏览器缓存问题，下次登录即可看到最新头像咯。</p>
			</div>
		</div>
	</div>
</div>




<<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>