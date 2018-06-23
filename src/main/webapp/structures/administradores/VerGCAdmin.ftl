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
                                    <th scope="col">Estado</th>
                                    <th scope="col">Monto</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col">Modificar</th>
                                    <th scope="col">Eliminar</th>
                                </tr>


                    [#list gastosComunes as gastoComun]

        [#list pagos as pago]
            [#if gastoComun.fecha == pago.fecha ]
            <tr>
                <td>${pago.username}</td>
            <td>${pago.estado}</td>
            <td>${gastoComun.monto}</td>
            <td>${gastoComun.fecha?string.iso}</td>
                <td>${gastoComun.descripcion}</td>
                <td>
                    <form action="/administracion/administradores/modificarGC.xml" method="POST">
                        <input name="fecha" type="hidden" value=${gastoComun.fecha? string.iso} />
                        <input type="submit" class="btn btn-primary" value="Modificar">

    </form>
                </td>
                <td>
                        <form action="/administracion/administradores/eliminarGC.xml" method="POST">
                            <input name="fecha" type="hidden" value=${gastoComun.fecha? string.iso} />
                            <input name="username" type="hidden" value=${pago.username} />
                            <input type="submit" class="btn btn-danger" value="Eliminar">

                            </form>
                                </td>
                </tr>
            [/#if]
        [/#list]


                    [/#list]


                            </table>
                        </div>
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
<br></br>
[/@structure]