<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Grade Management System</title>
<link rel="stylesheet" href="${css}member.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="${js }/member.js"></script>
</head>
<body>
   <div id = "gms-wrapper">
   <div class="gms-text-center">
   <header><h1>Grade Management System</h1></header>
   </div>
   <div id="gms-container">
      <form id="login-box">
         <img class="gms-imgsize" src="${img}/nara.jpg" alt="" /><br />
         <span class="gms-idtex">ID</span>
         <input type="text" id="input_id" name="input_id" /><br />
         <span class="gms-tex-sty" >PASSWORD</span>
         <input type="password" id="input_pass" name="input_pass" /><br />
         <br />
         <input id="loginBtn" type="submit" value="LOGIN" />
         <input type="reset" value="CANCLE"/>
         <input type="hidden" name="action" value="login" />
         <input type="hidden" name="page" value="main" />
      </form>
   </div>
   <footer id="footer-main">
       <div>
           <p>Posted by: hanbit</p>
           <p>Contact information: <a href="mailto:someone@example.com">
             someone@hanbit.com</a>.</p>
             <a href="jdbc_test.jsp"> DB 연결 테스트 </a>
             <a href="util/update_email.jsp"> 이메일업데이트 </a>
       </div>
   </footer>
   </div>
<script>
member.init();
</script>
</body>
</html>