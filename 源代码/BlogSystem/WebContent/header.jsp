<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--导航-->
<style>
#navbar {
	margin-top: 28px;
}
</style>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"
	id="navbar">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#" alt="博客系统构建"> <!-- <img src="https://avatars0.githubusercontent.com/u/12973402?v=3&s=48"> -->
			</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">博客系统</a></li>
				<li><a href="#">冰雹工作室</a></li>
				<!-- <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Java
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">jmeter</a></li>
                    <li><a href="#">EJB</a></li>
                    <li><a href="#">Jasper Report</a></li>
                    <li class="divider"></li>
                    <li><a href="#">分离的链接</a></li>
                    <li class="divider"></li>
                    <li><a href="#">另一个分离的链接</a></li>
                </ul>
            </li> -->
			</ul>
			<ul class="nav navbar-nav navbar-right" style="float: right;">
				<%
					String username = (String) session.getAttribute("username");
					String src = request.getContextPath() + "/upload/default.jpg";
				%>
				<c:if test="${username!=null }">
					<%
						src = request.getContextPath() + "/upload/" + username + "/" + username + ".jpg";
					%>
				</c:if>

				<c:if test="${username==null }">
					<li><a href="./login.jsp">登录</a></li>
					<li><a href="./register.jsp">注册</a></li>
				</c:if>
				<c:if test="${username!=null }">
					<li><a href="person.jsp"> <img class="img img-circle" title="${username }"
							style='width: 28px; height: auto; float: left;' src="<%=src%>"></a>
					</li>
					<li><a href="logout.do">注销</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<br />
<br />
