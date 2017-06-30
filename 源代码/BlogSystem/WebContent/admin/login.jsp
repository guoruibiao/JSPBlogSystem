<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../dependency.jsp"></jsp:include>
<jsp:include page="../header.jsp"></jsp:include>


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
				<img class="img-rounded" style="width:640px;height:auto;"
					src="<%=request.getContextPath()%>/static/img/admin.gif">
			</div>
			<div class="col-md-6 form">
					<h2 class="page-header text-center">管理员登录</h2>
				<form role="form" action="../login.do?flag=admin" method="POST">
					<div class="form-group">
						<label for="username" class="lead">用户名：</label><input type="text"
							class="form-control" id="username" name="username"
							placeHolder="用户名  / username" />
					</div>
					<div class="form-group">
						<label for="password" class="lead">密&nbsp;&nbsp;码：</label><input
							type="password" class="form-control" id="password"
							name="password" placeHolder="密码  / password" />
					</div>

					<div class="checkbox">
						<label class="lead"><input type="checkbox"
							class="checkbox-lg" />记住密码</label>
					</div>
					<div class="btn-groupbtn-group-lg">
						<button type="submit" class="btn btn-success">登 录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>