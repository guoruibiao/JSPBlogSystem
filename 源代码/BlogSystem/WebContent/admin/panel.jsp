<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Post"%>
<jsp:include page="../dependency.jsp"></jsp:include>
<jsp:include page="../header.jsp"></jsp:include>


<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-tabs">
				<li class="active"><a href="index.jsp" target="_blank">文章管理</a>
				<li><a href="#" target="_blank">作者管理</a></li>
				<li><a href="../getusercomments.do" target="_blank">评论管理</a></li>
				<li><a href="../profile.jsp" target="_blank">个人信息</a></li>
				<li class="dropdown pull-right"><a href="../writepost.jsp"
					data-toggle="dropdown" class="dropdown-toggle" target="_blank">写文章<strong
						class="caret"></strong></a></li>
			</ul>
		</div>
	</div>
	<!-- 为了区分后台和前台页面，可以加上一个隐藏域，让ajax来分别实现不同的功能。 -->
	<!-- <input id="topnpath" type="hidden" value="../"> -->
	<div class="row clearfix">
		<div class="col-md-12 column">

			<img alt="hahaha" style="width: 890px; height: auto;"
				class="img img-rounded"
				src="<%=request.getContextPath()%>/static/img/admin.gif">
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">

			<img alt="hahaha" style="width: 890px; height: auto;"
				class="img img-rounded"
				src="<%=request.getContextPath()%>/static/img/logo1.jpg">
		</div>
	</div>

	<hr>
</div>


<script src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/person.js"></script>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>