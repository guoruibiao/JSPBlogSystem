<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();/*返回站点的根路径 */
	/*依次返回当前链接使用的协议、应用的根url、端口号 */
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>大三小学期大作业--博客系统</title>
<link href="<%=basePath%>static/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>static/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="<%=basePath%>static/css/header.css" rel="stylesheet">
<script src="<%=basePath%>static/js/jquery-2.2.4.min.js"></script>

</head>
<body>