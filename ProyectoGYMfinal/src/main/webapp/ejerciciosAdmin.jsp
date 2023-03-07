<%-- 
    Document   : ejercicios
    Created on : 13 feb. 2023, 19:13:01
    Author     : Fer
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="lb/css/bootstrap.min.css">
    <link rel="stylesheet" href="font/fontawesome-free-6.2.1-web/css/all.min.css">
    <link href="css/style.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <style>
    .searchbar {
        margin-bottom: auto;
        margin-top: auto;
        height: 60px;
        background-color: #353b48;
        border-radius: 30px;
        padding: 10px;
    }

    .search_input {
        color: white;
        border: 0;
        outline: 0;
        background: none;
        width: 0;
        caret-color: transparent;
        line-height: 40px;

        padding: 0 10px;
        width: 450px;
        caret-color: red;

    }

    .searchbar .search_icon:hover {
        color: white;
        background: #e74c3c;
    }

    .search_icon {
        height: 40px;
        width: 40px;
        float: right;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 50%;
        text-decoration: none;
        background: white;
        color: #e74c3c;
    }
    </style>
    

</head>

<body style="background-color: #e9ecef;">
    <main>
        <!-- Navbar Start -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.php" class="navbar-brand">
                    <img class="card-img-top" style="width: 200px;" src="img/logosinfondo2.png" alt="">
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav ml-auto p-4 bg-secondary">
                        <a href="pdf.php" class="nav-item nav-link me-5 fs-4">Ejercicios <i class="fs-3 fa-solid fa-list-dropdown"></i></a>
                        <a href="AdminUsu" class="nav-item nav-link me-5 fs-4">Usuarios <i class="fs-3 fa-solid fa-user-group"></i></a>
                        <a href="usuarios?accion=editar" class="nav-item nav-link me-5 fs-4">Perfil <i
                                class="fs-3 fa-solid fa-circle-user"></i></a>

                    </div>
                </div>

            </nav>
        </div>
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid page-header mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center pt-0 pt-lg-5"
                style="min-height: 250px">
                <h4 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase font-weight-bold">Ejercicios</h4>

            </div>
        </div>
        <!-- Page Header End -->



        <div class="container h-100 py-4">
            <div class="d-flex justify-content-center h-100">
                <div class="searchbar ">
                    <form action="AdminEjer?accion=buscar" method="post">
                        <input class="search_input " type="text" name="texto" placeholder="Buscar...">
                        <button class="search_icon"><i class='fas fa-search'></i></button>
                    </form>
                </div>
            </div>
        </div>




        <div class="container pt-5 team">
            <div class="row">
                <c:forEach items="${ejercicios}" var="ejercicios">
                    <div class="col-lg-3 col-md-6 mb-5">
                        <div class="card border-0 bg-secondary text-center text-white shadow p-2 bg-body rounded">
                            <img class="card-img-top" src="data:image/jpeg;base64,${ejercicios.fotobase64}" alt="">
                            <div class="card-social d-flex align-items-center justify-content-center">
                                <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0"
                                    style="width: 50px; height: 50px;"
                                    href="AdminEjer?accion=editar&idEjercicio=${ejercicios.idEjercicio}"><i
                                        class="fs-3 fa-solid fa-pen-to-square"></i></a>
                                <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0"
                                    style="width: 50px; height: 50px;"
                                    href="AdminEjer?accion=eliminar&idEjercicio=${ejercicios.idEjercicio}"><i
                                        class="fs-3 fa-solid fa-trash"></i></a>
                            </div>
                            <div class="card-body bg-secondary">
                                <h4 class="card-title text-primary">${ejercicios.nombre}</h4>
                                <p class="card-text">${ejercicios.parteCuerpo}</p>

                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>



        <div class="container pt-5 team">
            <div class="row">

                <div class="d-grid gap-2 col-6 mx-auto shadow p-3 mb-5 bg-body rounded">
                    <form action="AdminEjer?accion=insertar" method="POST" enctype="multipart/form-data">
                        <div class="row g-3 mb-2">
                            <div class="col">
                                <input type="text" REQUIRED class="form-control" name="nombre" placeholder="Nombre">
                            </div>
                            <div class="col">
                                <input type="text" REQUIRED class="form-control" name="parte"
                                    placeholder="Parte del cuerpo">
                            </div>
                        </div>
                        <input type="text" REQUIRED class="form-control" name="descri" placeholder="Descripcion">
                        
                        <input class="form-control my-2" REQUIRED type="file" name="foto" id="formFile">

                        <input type="submit" name="subir" value="Añadir Ejercicio"
                            class="btn-lg btn-outline-danger fs-4 w-100">

                    </form>
                </div>
            </div>
        </div>
        <!-- Team End -->
    </main>
    <div class=" pt-4 px-5 w-90">
        <div class="row shadow p-5 mb-5 bg-body rounded mx-5 " id="contenedor">

        </div>
    </div>


    <!-- Footer Start -->
    <div class="footer container-fluid mt-5 py-5 px-sm-3 px-md-5 text-white">
        <div class="row pt-2 align-items-center">
            <div class="d-grid gap-2 col-4 mx-auto ">
                <div class="d-flex justify-content-evenly">
                    <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0 fs-4"
                        style="width: 50px; height: 50px;" href="#"><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0 fs-4"
                        style="width: 50px; height: 50px;" href="#"><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0 fs-4"
                        style="width: 50px; height: 50px;" href="https://github.com/ferbravo00"><i
                            class="fa-brands fa-github"></i></a>
                    <a class="btn btn-outline-light rounded-circle text-center mr-3 px-0 fs-4"
                        style="width: 50px; height: 50px;" href="https://www.instagram.com/ferbravo_00/"><i
                            class="fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
        <div class="container border-top border-dark pt-5">
            <p class="m-0 text-center text-white">
                &copy; <a class="text-white font-weight-bold" href="#">GymBros</a>. Autor Fernando Bravo
            </p>
        </div>

    </div>
    <!-- Footer End -->


    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
</body>

</html>