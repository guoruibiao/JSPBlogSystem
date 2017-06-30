<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示调试信息的页面</title>
</head>
<body>
    <h2>调试信息</h2>
          后台打印的信息为：恭喜<%=session.getAttribute("username") %>, 登录状态为：<%= session.getAttribute("isLogined") %>
          <br />
          发表文章的状态：<%=session.getAttribute("msg") %>
          
          <hr>
          <a href="person.jsp">返回主页面</a>
</body>
</html>