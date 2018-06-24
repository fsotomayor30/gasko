[#include "../common.ftl" /] [@structure]
<body background="/administracion/img/admin.png">
<br></br>
<nav class="navbar navbar-light bg-light">
  <span style="text-align:center" class="navbar-text">
    Bienvenido al Sistema de Gestion de Gastos Comunes - Modo administrador - En sesión: ${usuario}
  </span>
    <a class="form-inline" href="/administracion/logout.xml">Cerrar sesión</a>
</nav>
<br>
<div class="row">
    <div class="col-md-10 offset-md-1">
        <div class="card" style="background: #EAEAEA">
            <center><h1>Gasto Comun Mes Actual</h1></center>

            <div class="card-body">

                <table class="table table-striped">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Monto</th>
                        <th scope="col">Descripcion</th>
                    </tr>
            [#assign x = 0]
            [#list gastosComunes as gc]
            <tr>
                <td>${gc.id}</td>
                <td>${gc.fecha}</td>
                <td>${gc.monto}</td>
                <td>${gc.descripcion}</td>
                [#if gc.generado=0]
                    [#assign x = x + gc.monto]
                [/#if]

            </tr>

            [/#list]
                    <tr style="text-align: right">
                        <td colspan="5"><h3>Total Gastos Común no generados: ${x}</h3></td>
                    </tr>
                </table>


                <br></br>
            </div>
        </div>
    </div>
</div>
<br>
<form action="ingresoGCE.xml" method="POST">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card" style="background: #EAEAEA">
                <center><h1>Ingreso Gasto Comun</h1></center>

                <div class="card-body">

                    <div class="form-group row">

                        <div class="col-12">
                            <span class="oi oi-calendar"></span>
                            <label for="date" class="col-2 col-form-label">Fecha:</label>
                            <input class="form-control" type="date" value="2018-06-06" id="date" name="date" required>
                        </div>

                        <div class="col-12">
                            <span class="oi oi-dollar"></span>
                            <label for="monto" class="col-2 col-form-label">Monto:</label>
                            <input class="form-control" type="number" value="0" id="monto" name="monto" required>
                        </div>

                        <div class="col-12">
                            <span class="oi oi-list"></span>
                            <label for="descripcion" class="col-2 col-form-label">Descripcion:</label>
                            <textarea class="form-control" rows="5" id="descripcion" name="descripcion" required maxlength="50"></textarea>
                        </div>
                    </div>
                    <center>
                        <input type="submit" class="btn btn-primary" value="Ingresar G.C">
                        <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
                    </center>
                    <br></br>
            </div>
            </div>
        </div>
    </div>
</form>
[/@structure]