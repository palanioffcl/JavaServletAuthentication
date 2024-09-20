<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h3 style="color: red">Welcome to Online Registration</h3>

	<form method="post" action="Login">
		<div style="display: flex;">
			<div>
				<input type=text name=username required placeholder="username">
			</div>
			<div>
				<input name=password type=password required placeholder="password">
			</div>
			<input type=Submit value=Submit> <input type=text
				name=req_type value="login" style="display: none">
		</div>
	</form>
<a href="Register.jsp">Register Here</a>
</body>
</html>