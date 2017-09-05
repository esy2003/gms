<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="<%=application.getContextPath()%>" />
<c:set var="img" value="${ctx }/resources/img/" />
<c:set var="css" value="${ctx }/resources/css/" />
<c:set var="js" value="${ctx }/resources/js/" />
<script src="${js}member.js"></script>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>MEMBER_DETAIL</title>
<link rel="stylesheet" href="${css}member.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div id = "gms-wrapper">
	<header>
	<jsp:include page="navbar.jsp"></jsp:include>
		<div class="jumbotron text-center">
			<h1>Grade Management System</h1>
		</div>
	</header>