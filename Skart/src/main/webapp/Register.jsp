
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<center>
	<h1>SKART</h1>
</center>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 1px solid #f1f1f1;
}

input[type=text], input[type=password], input[type=text], input[type=text],
	input[type=text] {
	width: 30%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 50%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

img.avatar {
	width: 10%;
	border-radius: 50%;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 50%;
	}
}
</style>
</head>
<body>
	<center>
		<form action="Skart" method="post">
			<img
				src="https://p.kindpng.com/picc/s/78-785904_block-chamber-of-commerce-avatar-white-avatar-icon.png"
				alt="Avatar" class="avatar"><br> NAME :<input type="text"
				name="name"><br> AGE :<input type="text" name="age"><br>
			USERNAME :<input type="text" name="username"><br>
			PASSWORD :<input type="text" name="password"><br>
			ADDRESS :<input type="text" name="address"><br> <input
				type="submit">
		</form>
	</center>
</body>
</html>