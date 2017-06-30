<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<!-- 加载招财猫广告漂浮组件 -->
<jsp:include page="advertisement.jsp"></jsp:include>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">站内公告</a></li>
				<li><a href="index.jsp" target="_blank">全站好文</a>
				<li><a href="postlist.do?currpage=1&next=0&pagesize=3" target="_blank">我的文章</a></li>
				<li><a href="getusercomments.do" target="_blank">评论列表</a></li>
				<li><a href="profile.jsp" target="_blank">个人信息</a></li>
				<li class="dropdown pull-right"><a href="writepost.jsp" target="_blank"
					data-toggle="dropdown" class="dropdown-toggle">写文章<strong
						class="caret"></strong></a></li>
			</ul>
		</div>
	</div>
	
	
	<!-- 为了区分后台和前台页面，可以加上一个隐藏域，让ajax来分别实现不同的功能。 -->
	<!-- <input id="topnpath" type="hidden" value="./"> -->
	<div class="row clearfix">
		<div class="col-md-12 column">

			<h2>TopN浏览量文章</h2>
			<div id="topnposts" style="width: 1200px; height: 600px;"></div>
			<script>
				// 用ajax实现数据获取并进行填充
			</script>
		</div>
	</div>

	<hr>
	<div class="row clearfix">
		<div class="col-md-12 column">

			<h2>TopN博主</h2>
			<div id="topnauthors" style="width: 1200px; height: 600px;"></div>
			<script>
				// 用ajax实现数据获取并进行填充
			</script>
		</div>
	</div>
</div>



<script src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/person.js"></script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>