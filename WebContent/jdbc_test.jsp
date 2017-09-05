<%@page import="java.sql.*"%>
<%@ include file = "WEB-INF/view/constants/db.jsp" %>

<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<%
	Class.forName(ORACLE_DRIVER);
	String id = request.getParameter("userId");
	String pass = request.getParameter("userPw");
	PreparedStatement pstmt = DriverManager.getConnection(ORACLE_URL, USERNAME, PASSWORD)
			.prepareStatement("SELECT userPw FROM Member WHERE userId=?");
	pstmt.setString(1, id);
%>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<h1>Hello !!</h1>
</body>
</html>