<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/day17/regServlet" method="POST" onsubmit="return run()">
		<p>账号：<input type="text" name="username" id="username"><span id="usernull"></span>${ requestScope.usernamed }</p>
		<p>密码：<input type="password" name="password" id="password"><span id="passnull"></span></p>
		<p>确认密码：<input type="password" name="repassword" id="repassword"></p>
		<p>昵称：<input type="text" name="nickname" id="nickname"><span id="nicknull"></span>${ requestScope.nicknamed }</p>
		<input type="submit" value="注册">&nbsp;<span id="ifempty"></span>
	</form>
	<form action="/day17/login/Login.jsp"><p><input type="submit" value="去登录" onsubmit="return run2()"></p></form>
</body>
<script type="text/javascript">
	function run() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var nickname = document.getElementById("nickname").value;
		var repassword = document.getElementById("repassword").value;
		var span = document.getElementById("ifempty");
		var usernull = document.getElementById("usernull");
		var passnull = document.getElementById("passnull");
		var nicknull = document.getElementById("nicknull");
		if(username.length==0||password.length==0||nickname.length==0||repassword.length==0){
			span.innerHTML = "以上信息都不能为空！";
			return false;
		}else if(username.length<6){
			usernull.innerHTML = "账号长度不能小于6个字符";
		}else if(password.length<6){
			passnull.innerHTML = "密码长度不能小于6个字符";
		}else if(nickname.length>20){
			passnull.innerHTML = "昵称长度不能大于20个字符";
		}
		else{
			if(password==repassword){
				return true;
			}else{
				span.innerHTML = "两次密码不一致！";
				return false;
			}
		}
		span.innerHTML = "注册失败！";
		return false;
	}
	function run2() {
		return true;
	}
</script>
</html>