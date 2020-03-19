<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/day17/loginServlet" method="post" onsubmit="return run()">
		<p>账号：<input type="text" name="username" id="username">${ requestScope.userError }</p>
		<p>密码：<input type="password" name="password" id="password">${ requestScope.passError }</p>
		<p>验证码：<input type="text" name="code" id="code">&nbsp;&nbsp;<img id="img" src="/day17/checkCode" onclick="run1()"></p>
		<input type="submit" value="登录">&nbsp;&nbsp;<span id="span" ></span>${ requestScope.codeError }
	</form>
	<form action="/day17/register/Register.jsp"><p><input type="submit" value="去注册" onsubmit="return run2()"></p></form>
</body>
<script type="text/javascript">
	function run() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var code = document.getElementById("code").value;
		var span = document.getElementById("span");
		if(username.length==0||password.length==0||code.length==0){
			span.innerHTML = "以上信息都不能为空！";
			return false;
		}else{
			return true;
		}
	}
	function run1() {
		var img = document.getElementById("img");
		img.src = "/day17/checkCode?" + new Date().getTime();
	}
	function run2() {
		return true;
	}
</script>
</html>