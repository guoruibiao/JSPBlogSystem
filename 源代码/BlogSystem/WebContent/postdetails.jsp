<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Post"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<!-- 加载招财猫广告漂浮组件 -->
<jsp:include page="advertisement.jsp"></jsp:include>



<%
	Post post = (Post) session.getAttribute("postdetails");
%>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h1>
					《<%=post.getTitle()%>》<small> -- </small><small class="lead"
						id="author"></small> <small><font id="watches"></font></small>
				</h1>
				<h6 class="green">
					发布时间：<%=post.getPostdate()%></h6>
				<p id="tags">标签：</p>
				<!-- 智障的引入脚本方式...... -->
				<input id="btn-md" type='button' class='btn btn-danger' value='Markdown'>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<p class="lead" id="content-raw">
				<%=post.getContent()%>
			</p>
			<xmp id="content-md" theme="united" style="display:none;" style="display:none;">
				<%=post.getContent()%>
			</xmp>
			
		</div>
	</div>

	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="60"
					aria-valuemin="0" aria-valuemax="100" style="width: 20%;"></div>
			</div>
			<h2>查看评论</h2>
			<div id="comments"></div>
		</div>
	</div>

	<div class="row clearfix">
		<div class="col-md-12 column">
			<hr>
			<%
				String username = (String) request.getAttribute("username");
			%>
			<c:if test="${username!=null }">
				<h3>随心所欲，畅所欲评...</h3>
				<form class="form-horizontal" role="form" action="writecomment.do"
					method="post">
					<input type="hidden" value="<%=post.getId()%>" name="post_id"
						id="post_id"> <input type="hidden" value="" name="posturl">
					<textarea style="width: 500px; height: 100px;" name="content"
						placeHolder="随心所欲，畅所欲评！">
    	</textarea>
					<p>
						<small>* 以上用户言论只代表其个人观点，不代表本站的观点或立场</small>
					</p>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;<input class="btn btn-success"
						type="submit" value="发表">
				</form>
			</c:if>
			<c:if test="${username==null }">
				您还没有登录,请
				<a href="login.jsp">[登录]</a>
				或
				<a href="register.jsp">[注册]</a>
			</c:if>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
<!-- 引入Markdown语法支持 -->
<script id="mdjs" src=""></script>
<script
	src="<%=request.getContextPath()%>/static/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/postdetails.js"></script>
</html>