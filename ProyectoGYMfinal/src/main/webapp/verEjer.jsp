<%-- 
    Document   : editar
    Created on : 14 feb. 2023, 16:46:47
    Author     : Fer
--%>

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

<body>
    <main style="background-color: #e9ecef;">
        <!-- Navbar Start -->
        <div class="container-fluid p-0 nav-bar">
            <nav class="navbar navbar-expand-lg bg-none navbar-dark py-3">
                <a href="index.html" class="navbar-brand">
                    <img class="card-img-top" style="width: 200px;" src="img/logosinfondo2.png" alt="">
                </a>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav ml-auto p-4 bg-secondary">
                        <a href="index.php" class="nav-item nav-link me-5 fs-4">Ejercicios <i
                                class="fs-3 fa-solid fa-dumbbell"></i></a>

                    </div>
                </div>

            </nav>
        </div>
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid page-header mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center pt-0 pt-lg-5"
                style="min-height: 250px">
                <h4 class="display-4 mb-3 mt-0 mt-lg-5 text-white text-uppercase font-weight-bold">Editar Ejercicios</h4>

            </div>
        </div>
        <!-- Page Header End -->



        <div class="container py-5 team col-4">
            <div class="row shadow p-3 mb-5 bg-body rounded">
                <div class="d-grid gap-2 col-12 mx-auto ">
                    <img src="data:image/jpeg;base64,${ejercicios.fotobase64}" class="img-thumbnail border-danger mt-4 w-100">
                </div>

                <div class="d-grid gap-2 col-12 mx-auto">
                    <div class="row g-3 mt-3">
                        <div class="col">
                            <div class="form-control">${ejercicios.nombre}</div>
                            <input type="text" value="${ejercicios.idEjercicio}" name="id" hidden>
                        </div>
                        <div class="col">
                            <div class="form-control">${ejercicios.parteCuerpo}</div>
                        </div>
                    </div>
                    <div class="form-control mt-3">${ejercicios.descripcion}</div>

                    <a href="ejercicios" class="mt-3 btn btn-lg btn-outline-danger mb-4 fs-4 w-100">Volver</a>


                </div>
            </div>
        </div>

        <!-- Team End -->




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

    </main>
</body>

</html>
