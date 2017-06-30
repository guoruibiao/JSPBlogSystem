<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<!-- 加载招财猫广告漂浮组件 -->
<jsp:include page="advertisement.jsp"></jsp:include>


<form action="writepost.do" method="post">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h2 class="text-center">人生的乐趣不止编程，但没有编程，一定不快乐！</h2>
				<h3>
					<input type="text" style="width: 100%; height: 36px;" name="title"
						placeHolder="文章标题..." >
				</h3>
				<hr>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<textarea style="width: 100%; height: 398px;" name="content"
					placeHolder="请撰写文章正文部分"></textarea>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3 class="lead">标签</h3>
				<dic id="tags"></dic>
			</div>
		</div>

		<div class="row clearfix">
			<div class="col-md-12 column">
				<hr>
				<br> <input style='width: 100%;' type="submit"
					class="btn btn-success" value="发表" >
			</div>
		</div>
	</div>
</form>





<!-- <h1>撰写博客</h1>
<hr>
<form id="form-1" action="writepost.do" method="post">
	标题：<input type="text" name="title" placeHolder="文章标题"><br />
	正文：<br />
	<textarea rows="32" cols="100" name="content" placeHolder="文章正文部分。"></textarea>

	<hr>
	标签：<strong>打算用ajax写...</strong> <br /> <br /> <input type="reset"
		value="取消">&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
		value="发表">
</form> -->

<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="<%=request.getContextPath()%>/static/js/writepost.js"></script>
</html>