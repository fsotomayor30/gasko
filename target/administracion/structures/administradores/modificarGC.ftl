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
<form action="modificarGCE.xml" method="POST">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card" style="background: #EAEAEA">
<<<<<<< HEAD
                <center><h1></span>Modificar Gasto Comun</h1></center>
=======
                <br></br>
                <center>
                    <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
                </center>
                <center><h1>Estamos trabajando para ud.</h1></center>
>>>>>>> c95924927bed0d375ce0f1b4b8e9861513a463bc

                <div class="card-body">
                    <div class="card" style="background: #EAEAEA">
                        <h3>Datos Originales</h3>
                        <div class="col-12">
                            <span class="oi oi-calendar"></span>
                            <label for="date" class="col-2 col-form-label">Fecha:</label>
                            <input class="form-control" type="date" value=${gastoComun.fecha? string.iso} readonly id="date" name="date">
                        </div>
                        <div class="col-12">
                            <span class="oi oi-list"></span>
                            <label for="descripcion" class="col-2 col-form-label">Descripcion:</label>
                            <textarea class="form-control"  rows="5" readonly id="descripcion" name="descripcion"  maxlength="50">${gastoComun.descripcion}</textarea>
                        </div>
                        <div class="col-12">
                            <span class="oi oi-dollar"></span>
                            <label for="monto" class="col-2 col-form-label">Monto:</label>
                            <input class="form-control" type="number" value=${gastoComun.monto?int} readonly id="monto" name="monto">
                        <br></br>
                        </div>
                    </div>
                    </div>
                    <div class="card-body">
                        <div class="card" style="background: #EAEAEA;margin-top: 30px;">
                            <h3> Nuevos Datos</h3>

                                <div class="col-12">
                                    <span class="oi oi-dollar"></span>
                                    <label for="monto" class="col-2 col-form-label">Monto:</label>
                                    <input class="form-control" type="number"  id="monto" required name="montoNuevo">
                                </div>
                            <div class="col-12">
                                <span class="oi oi-list"></span>
                                <label for="descripcion" class="col-2 col-form-label">Descripcion:</label>
                                <textarea class="form-control" rows="5" id="descripcion" name="descripcionNueva" required maxlength="50"></textarea>
                            </div>
                            <br></br>
                            <center>
                                <input type="submit" class="btn btn-primary" value="Modificar G.C">
                                <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
                            </center>
                            <br></br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</form>
[/@structure]