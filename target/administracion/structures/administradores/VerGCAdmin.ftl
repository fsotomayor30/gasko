[#include "../common.ftl" /] [@structure]
<body background="/administracion/img/admin.png">
<br></br>
<nav class="navbar navbar-light bg-light">
  <span style="text-align:center" class="navbar-text">
  <!--${usuario} es el username con el cual se inicio sesion, este se obtiene en el controlador "GastoComunController", especificamente del metodo VerGCAdmin-->
    Bienvenido al Sistema de Gestion de Gastos Comunes - Modo administrador - En sesión: ${usuario}
  </span>
    <a class="form-inline" href="/administracion/logout.xml">Cerrar sesión</a>
</nav>
<br></br>
<form action="#" method="POST">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card" style="background: #EAEAEA">
                 <center><h1></span>Estado de pago</h1></center>
                <div class="card-body">
                    <div class="form-group row">
                        <div class="col-6">
                            <span class="oi oi-calendar"></span>
                            <label for="date_desde" class="col-2 col-form-label">Desde:</label>
                            <input class="form-control" type="date" value="2017-01-19" name="date_desde"
                                   id="date_desde">
                        </div>
                        <div class="col-6">
                            <span class="oi oi-calendar"></span>
                            <label for="date_hasta" class="col-2 col-form-label">Hasta: </label>
                            <input class="form-control" type="date" value="2017-01-19" name="date_hasta"
                                   id="date_hasta">
                        </div>
                    </div>
                    <center>
                        <input type="submit" class="btn btn-primary" value="Buscar">
                        <a href="VerGCAdmin.xml" class="btn btn-primary">Reestablecer</a>
                    </center>

</form>

<br></br>
<div class="col-12">
    <table class="table table-striped">
        <tr>
            <th scope="col">Nombre del miembro</th>
            <th scope="col">Estado</th>
            <th scope="col">Monto</th>
            <th scope="col">Fecha Generación Pago</th>
            <th scope="col">Tipo</th>
        </tr>

            [#list pagos as pago]
            <tr>
                [#assign x = ""]
                [#list gastosComunes as gc]
                    [#if gc.fecha?string.MM == pago.fecha?string.MM]
                        [#list tiposGastosComunes as tp]
                            [#if gc.descripcion = tp.id]
                                [#assign x= "${x} "+ tp.descripcion]
                            [/#if]
                        [/#list]

                    [/#if]
                [/#list]
                <td>${pago.username}</td>
                <td>${pago.estado}</td>
                <td>${pago.monto}</td>
                <td>${pago.fecha}</td>
                <td>${x}</td>
            </tr>
            [/#list]

    </table>
</div>
<center>

    <a href="#" class="btn btn-primary">Exportar Excel</a>
    <a href="#" class="btn btn-primary">Exportar PDF</a>
    <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
</center>
<br></br>
                </div>

            </div>

        </div>
[/@structure]