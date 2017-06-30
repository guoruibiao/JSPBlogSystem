<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>


<style>
.panel {
	margin-left: 350px;
	width: 80%;
}
</style>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<style>
.panel {
	margin-right: 38px;
	float: right;
}
</style>
			<div class="row panel form-group">
				<form action="uploadhandler.do" enctype="multipart/form-data"
					method="post">
					<br> 上传头像：<input class="btn btn-success" type="file" name="file1"><br>
					
					<br> <input class="btn btn-warning btn-lg" type="submit" value="提交">
				</form>
			</div>
			<form class="form-lg panel" role="form" action="register.do"
				method="POST">
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label lead">用户名：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username"
							name="username" placeHolder="长度6~12,字母数字" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label lead">密&nbsp;&nbsp;码：</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password"
							name="password" placeHolder="长度6~12字母数字" />
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="col-sm-2 control-label">确&nbsp;&nbsp;认：</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="confirm"
							name="comfirm" placeHolder="确保与之前密码一致" />
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label lead">邮&nbsp;&nbsp;箱：</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="email" name="email"
							placeHolder="格式：XX@YY.ZZ" />
					</div>
				</div>

				<div class="form-group">
					<label for="sex" class="col-sm-2 control-label lead">性&nbsp;&nbsp;别：</label>
					<div class="col-sm-10">
						<input type="radio" class="form-control" id="radio" name="sex"
							value="1" checked/>男 &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
							class="form-control" id="radio" name="sex" value="0" />女
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>


<script src="<%=request.getContextPath()%>/static/js/register.js"></script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>