<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Post"%>
<jsp:include page="../dependency.jsp"></jsp:include>
<jsp:include page="../header.jsp"></jsp:include>

<style>
#loading {
	margim-left: 430px;
	height: 480px;
	width: 600px;
	height: 480px;
}
</style>
<script
	src="<%=request.getContextPath()%>/static/js/jquery.shCircleLoader-min.js"></script>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div id="loading">
				<script>
					function loading() {
						$('#loading')
								.shCircleLoader(
										{
											color : "green",
											dots : 72,
											dotsRadius : 64,
											keyframes : "0%   {background: red;    {prefix}transform: scale(1)}"
													+ "20%  {background: orange; {prefix}transform: scale(.3)}"
													+ "40%  {background: red;    {prefix}transform: scale(0)}"
													+ "50%  {background: red;    {prefix}transform: scale(1)}"
													+ "70%  {background: orange; {prefix}transform: scale(.3)}"
													+ "90%  {background: red;    {prefix}transform: scale(0)}"
													+ "100% {background: red;    {prefix}transform: scale(1)}"
										});
					}

					function hiddenLoading() {
						$("#loading").css("display", "none");
						$("#postcontainer").css("display", "block");
						// 直接访问全站文章链接，来避免posts重名问题导致的文章不可见情况。
						// window.location.href = "../getallposts.do";
					}

					// 页面加载效果
					$(document).ready(function() {
						$("#postcontainer").css("display", "none");
						// 先显示进度条5秒，然后真正显示页面详细文章信息。
						loading();
						setTimeout(hiddenLoading, 1000);
					});
				</script>
			</div>
		</div>
	</div>

</div>


<!-- 站内文章信息 -->
<div class="container" id="postcontainer">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h1 class="text-center">本站优秀文章一览</h1>
			<hr>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">

			<table class="table">
				<thead>
					<tr>
						<th>编号</th>
						<th>博文标题</th>
						<th>发表日期</th>
						<th>作者编号(<small>点击查看详情</small>)
						</th>
						<th>管理员操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${posts}" var="post">
						<tr>
							<td>${post.getId()}</td>
							<td><a href="<%=request.getContextPath() %>/postdetails.do?postid=${post.getId()}"
								target="_blank">${post.getTitle()}</a> <%-- 文章内容：${post.getContent()}<br /> --%>
							<td>${post.getPostdate()}</td>
							<td><a href="#">${post.getUser_id()}|点击操作博主信息</a></td>
							<td>
							<a href="<%=request.getContextPath() %>/postmodify.do?postid=${post.getId()}" target="_blank">编辑</a>  |
							<a href="<%=request.getContextPath() %>/postdelete.do?postid=${post.getId()}" target="_blank">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>