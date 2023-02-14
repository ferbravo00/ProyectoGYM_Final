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
                <h1>Login</h1>
            </div>
            <form action="usuarios?accion=login" method="POST">
                <div class="login-form">
                    <div class="control-group my-4">
                        <input type="email" class="login-field" name="correo" placeholder="correo" id="login-name">
                        <label class="login-field-icon fui-user" for="exampleInputEmail1"></label>
                    </div>

                    <div class="control-group my-4">
                        <input type="password" class="login-field" name="pass" placeholder="password" id="login-pass">
                        <label class="login-field-icon fui-lock" for="login-pass"></label>
                    </div>

                    <input class="btn btn-primary btn-large btn-block my-4" type="submit" name="enviar"
                        value="Iniciar sesion">
                    <p class="pt-2 message py-2">No tienes una cuenta? <a href="registrar.jsp">Registrate</a></p>
                </div>
            </form>
        </div>
    </div>
</body>

</html>