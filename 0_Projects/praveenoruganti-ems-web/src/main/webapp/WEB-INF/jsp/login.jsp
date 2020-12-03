<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/main.css"rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management System Login</title>
</head>
<body>

	<p class="small">
		<br />
	</p>
	<div align="center">
		<h3>Employee Login Form</h3>
		<form:form method="post" id="loginForm" modelAttribute="login"
			action="loginProcess">
			<table align="center">
				<tr>
					<td><form:label path="username">Username: </form:label></td>
					<td><form:input path="username" name="username" id="username" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password:</form:label></td>
					<td><form:password path="password" name="password"
							id="password" /><input type="checkbox" onclick="showPassword()">Show Password</td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><form:button id="login" name="login">Login</form:button></td>	
				</tr>
				<tr>
					<td></td>
					<td><a href="/ems/register">Register</a></td>
				</tr>				
			</table>
		</form:form>
		<table align="center">
			<tr>
				<td style="font-style: italic; color: red;">${message}</td>
			</tr>
		</table>
	</div>
	<script src="js/main.js"></script>
</body>
</html>