<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.Post"%>
<jsp:include page="dependency.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<h2 class="text-center green page-header"><%=session.getAttribute("username")%>的文章列表
					</h2>
				</div>
			</div>
			<table class="table">
				<tbody>
					<c:forEach items="${posts }" var="post">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<h3>
									<a href="postdetails.do?postid=${post.getId()}" target="_blank">${post.getTitle()}</a>
									&nbsp;&nbsp;<a href="postmodify.do?postid=${post.getId() }"
										target="_blank"><small>修改</small></a> &nbsp;&nbsp;<a
										href="postdelete.do?postid=${post.getId() }" target="_blank"><small>删除</small></a>
								</h3>
								<p>${post.getDigest()}</p>
								<p>
									<a class="btn-btn-success"
										href="postdetails.do?postid=${post.getId()}" target="_blank">查看全文»</a>
								</p>
							</div>
						</div>
						<hr>
					</c:forEach>

				</tbody>
			</table>
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
				<a href="postlist.do?currpage=<%=prevpage%>&next=-1&pagesize=2">上一页</a>
				| 当前页：<%=currpage%>/2 | <a
					href="postlist.do?currpage=<%=prevpage%>&next=1&pagesize=2">下一页</a>
			</h5>
		</div>
	</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>