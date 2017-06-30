<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Post"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>
<!-- 加载招财猫广告漂浮组件 -->
<jsp:include page="advertisement.jsp"></jsp:include>



<!-- 站内文章信息 -->
<div class="container" id="postcontainer">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h1 class="text-center">本站优秀文章一览</h1>
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
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${posts}" var="post">
						<tr>
							<td>${post.getId()}</td>
							<td><a href="postdetails.do?postid=${post.getId()}"
								target="_blank">${post.getTitle()}</a> <%-- 文章内容：${post.getContent()}<br /> --%>
							<td>${post.getPostdate()}</td>
							<td><a href="#">${post.getUser_id()}|待做，点进去之后可以查看作者详细展示页信息</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="row clearfix">
		<div class="col-md-12 column">
			<h5 class="page-header text-center">
				<%
					Integer prevpage = (Integer) (session.getAttribute("prevpage"));
					Integer currpage = prevpage + 1;
					if (prevpage == 1) {
						currpage = 1;
					}
				%>
				<a href="getpagination.do?currpage=<%=prevpage%>&next=-1&pagesize=7">上一页</a>
				| 当前页：<%=currpage%>/2 | <a
					href="getpagination.do?currpage=<%=prevpage%>&next=1&pagesize=7">下一页</a>
			</h5>
		</div>
	</div>
</div>


<%-- 
<h2>全站优秀博文展示</h2>
<c:forEach items="${posts}" var="post">
		文章编号：${post.getId()}<br /> 
		文章标题：<a href="postdetails.do?postid=${post.getId()}" target="_blank">${post.getTitle()}</a>
		<br />
		 文章摘要：${post.getDigest()}<br />
		文章内容：${post.getContent()}<br />
		 文章发表时间：${post.getPostdate()}<br />
		文章作者编号：${post.getUser_id()}<br />
		<hr>
	</c:forEach> --%>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>