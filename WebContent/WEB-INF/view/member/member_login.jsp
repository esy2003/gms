<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>LOGIN</title>
<link rel="stylesheet" href="${css}member.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div id = "gms-wrapper">
	<div class="gms-text-center">
	<header><h1>Member_detail</h1></header><hr>
	</div>
	<div id="gms-container">
		<form id="login-box" name="login-box">
			<img class="gms-imgsize" src="${img }nara.jpg" alt="" /><br />
			<span class="gms-idtex">ID</span>
			<input id="userId" type="text" name="userId" /><br />
			<span class="gms-tex-sty">PASSWORD</span>
			<input id="userPw" type="password" name="userPw" /><br />
			<br />
			<input type="submit" value="LOGIN" onclick="loginAlert()" />
			<input type="reset" value="CANCLE"/>
		</form>
	</div>
	<footer id="footer-main">
		 <div>
			  <p>Posted by: 이상윤</p>
			  <p>Contact information: <a href="mailto:someone@example.com">
				 someone@hanbit.com</a>.</p>
				 <a href="jdbc_test.jsp"> DB 연결 테스트 </a>
				 <a href="util/update_email.jsp"> 이메일업데이트 </a>
		 </div>
	</footer>
	</div>
<script>
	function loginAlert() {
		var input_id = document.getElementById("userId").value;
		var input_pass = document.getElementById("userPw").value;
		if (input_id==="") {
			alert("ID를 입력해주세요");
			return false;
		}
		else if (input_pass==="") {
			alert("PASSWORD를 입력해주세요");
			return false;
		}
		else {
			var form = document.getElementById("login-box");
			form.action="${ctx}/common.do?action=login&page=main";
			form.method="post";
			return true;
		}
	}
</script>
</body>
</html>