<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a id="main-go"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
      <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true" aria-expanded="false">회원관리<span class="caret"></span></a>
      	<ul id="navbar_drop_stu">
      		<li><a>학생추가</a></li>
      		<li><a>학생목록</a></li>
      		<li><a>학생조회</a></li>
      		<li></li>
      		<li><a>학생삭제</a></li>
      	</ul>
      </li>
      <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true" aria-expanded="false">성적관리<span class="caret"></span></a>
      	<ul id="navbar_drop_grade">
      		<li><a>성적입력</a></li>
      		<li><a>성적목록</a></li>
      		<li><a>성적조회</a></li>
      		<li></li>
      		<li><a>성적삭제</a></li>
      	</ul>
      </li>
      <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true" aria-expanded="false">게시판관리<span class="caret"></span></a>
      	<ul id="navbar_drop_board">
      		<li><a>게시글추가</a></li>
      		<li><a>게시글목록</a></li>
      		<li><a>게시글조회</a></li>
      		<li></li>
      		<li><a>게시글삭제</a></li>
      	</ul>
      </li>
    </ul>
    	<div class="user-box-container">
	    	<span class="user-box">${sessionScope.user.name} &nbsp; <a id="logout">로그아웃</a></span>
	    </div>
  </div>
</nav>
<script>
navbar.init();
</script>

