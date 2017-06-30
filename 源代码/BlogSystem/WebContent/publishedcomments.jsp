<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Comment"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>

<script
	src="<%=request.getContextPath()%>/static/js/jquery-2.2.4.min.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/publishedcomments.js"></script>
<div class="container" id="postcontainer">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h1 class="text-center"><%=session.getAttribute("username")%>
				发表过的所有评论
			</h1>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table">
				<c:forEach items="${posts}" var="post">
					<tr>
						<td>${post.getId()}</td>
						<td><a href="postdetails.do?postid=${post.getId()}"
							target="_blank">${post.getTitle()}</a> <%-- 文章内容：${post.getContent()}<br /> --%>
						<td>${post.getPostdate()}</td>
						<td><a href="#">${post.getUser_id()}|待做，点进去之后可以查看作者详细展示页信息</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<%-- <h1><%=session.getAttribute("username")%>
		发表过的所有评论
	</h1>
	<c:forEach items="${comments}" var="comment">
		<%=session.getAttribute("username")%> 在博文《<a
			href="postdetails.do?postid=${comment.getPost_id() }" target="_blank">${comment.getPost_id() }</a>》发表了评论：<br>
		${comment.getContent() }
		<hr>
	</c:forEach>

 --%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>