<%-- 
    Document   : registrar
    Created on : 13 feb. 2023, 17:36:53
    Author     : Fer
--%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="stylesheet" href="lb/css/bootstrap.min.css">
	<link rel="stylesheet" href="estiloLogin.css">
</head>

<body>
	<div class="login">
		<div class="login-screen">
			<div class="app-title pb-2">
				<h1>Sign Up</h1>
			</div>

			<div class="login-form">
				<form action="usuarios?accion=insertar" method="POST">
					<div class="control-group my-4">
						<input type="text" class="login-field" name="nombre" placeholder="Nombre" id="singup-name">
						<label class="login-field-icon fui-user" for="login-name"></label>
					</div>

					<div class="control-group my-4">
						<input type="email" class="login-field" name="correo" placeholder="Correo" id="singup-email">
						<label class="login-field-icon fui-user" for="exampleInputEmail1"></label>
					</div>
                                    
                                        <div class="control-group my-4">
						<input type="number" class="login-field" name="edad" placeholder="edad" id="singup-email">
						<label class="login-field-icon fui-user" for="exampleInputEmail1"></label>
					</div>
                                    
                                        <div class="control-group my-4">
						<input type="number" class="login-field" name="altura" placeholder="altura" id="singup-email">
						<label class="login-field-icon fui-user" for="exampleInputEmail1"></label>
					</div>
                                    
                                        <div class="control-group my-4">
						<input type="number" class="login-field" name="peso" placeholder="peso" id="singup-email">
						<label class="login-field-icon fui-user" for="exampleInputEmail1"></label>
					</div>

					<div class="control-group my-4">
						<input type="password" class="login-field" name="pass" placeholder="Clave" id="singup-pass">
						<label class="login-field-icon fui-lock" for="login-pass"></label>
					</div>
					<input type="submit" class="btn btn-primary btn-large btn-block my-4" value="Registrar"></input>
					
				</form>
			</div>
		</div>
	</div>
</body>

</html>
