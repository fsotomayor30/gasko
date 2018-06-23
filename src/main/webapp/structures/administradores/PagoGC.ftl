[#include "../common.ftl" /] [@structure]
<body background="/administracion/img/admin.png">
<br></br>
<nav class="navbar navbar-light bg-light">
  <span style="text-align:center" class="navbar-text">
    Bienvenido al Sistema de Gestion de Gastos Comunes - Modo administrador - En sesión: ${usuario}
  </span>
    <a class="form-inline" href="/administracion/logout.xml">Cerrar sesión</a>
</nav>
<br></br>
<form action="PagoGCE.xml" method="POST">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card" style="background: #EAEAEA">
                <center><h1></span>Pago Gasto Comun</h1></center>
                <div class="card-body">
                    <div class="form-group row">
                        <div class="col-12">
                            <span class="oi oi-calendar"></span>
                            <label for="date" class="col-2 col-form-label">Fecha:</label>
                            <input class="form-control" type="date" value="2018-06-06" id="date" name="date" required>
                        </div>

                        <div class="col-12">
                            <span class="oi oi-person"></span>
                            <label for="username" class="col-2 col-form-label">Username:</label>
                            <input class="form-control" type="text" id="username" name="username" required>
                        </div>

                    </div>
                    <center>
                        <input type="submit" class="btn btn-primary" value="Pagar G.C">
                        <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
                    </center>
                    <br></br>
                </div>
            </div>
        </div>
    </div>
</form>
[/@structure]