<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="common_head.jsp"></jsp:include>
	<div id = "gms-wrapper">
	<div class="gms-text-center">
	<header><h1>비밀번호가 틀립니다</h1></header>
		<a class="gms-gomain" href="${ctx }/index.jsp">메인으로 가기</a><hr>
	</div>
	<div id="gms-container">
		<form id="Login-box" action="${ctx }/common.do" method="get">
			<img class="gms-imgsize" src="${ctx }/resources/img/nara.jpg" alt="" /><br />
			<span class="gms-idtex">ID</span>
			<input type="text" name="userId" /><br />
			<span class="gms-tex-sty">PASSWORD</span>
			<input type="password" name="userPw" /><br />
			<br />
			<input type="submit" value="LOGIN" />
			<input type="reset" value="CANCLE"/>
			<input type="hidden" name="action" value="login" />
			<input type="hidden" name="page" value="main" />
		</form>
	</div>
	<footer id="footer-main">
		 <div>
			  <p>Posted by: 이상윤</p>
			  <p>Contact information: <a href="mailto:someone@example.com">
				 someone@hanbit.com</a>.</p>
				 <a href="util/jdbc_test.jsp"> DB 연결 테스트 </a>
				 <a href="util/update_email.jsp"> 이메일업데이트 </a>
		 </div>
	</footer>
	</div>
</body>
</html>