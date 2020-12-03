<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link href="css/main.css"rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration System</title>
</head>
<body>
	<p class="small">
		<br />
	</p>
	<div align="center">
		<h3>Employee Registration Form</h3>
		<form:form id="regForm" modelAttribute="user" action="registerProcess"
			method="post">
			<table>
				<tr>
					<td><form:label path="username">Username</form:label></td>
					<td><form:input path="username" name="username" id="username" />
					</td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" name="password"
							id="password" />
							<input type="checkbox" onclick="showPassword()">Show Password</td>
							
				</tr>
				<tr>
					<td></td>
					<td><form:button id="register" name="register">Register</form:button>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td></td>
					<td><a href="/ems">Login</a></td>
				</tr>
			</table>
		</form:form>
		<table>
			<tr>
				<td style="font-style: italic; color: red;">${message}</td>
			</tr>
		</table>
	</div>
	<script src="js/main.js"></script>
</body>
</html>