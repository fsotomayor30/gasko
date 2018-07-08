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

    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card" style="background: #EAEAEA">
                <center><h1></span>Pago Gasto Comun</h1></center>
                <div class="card-body">
                    <table class="table table-striped">
                        <tr>
                            <th scope="col">Nombre del miembro</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Monto</th>
                            <th scope="col">Fecha Generación Pago</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Pagar</th>
                        </tr>

            [#list pagos as pago]
            <tr>
                <form action="PagoGCE.xml" method="POST">

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

                [#if pago.estado = "Pagado"]
                    <td>Ya fue pagado este gasto común</td>
                [#else]
                    <td><input type="hidden" name="id" id="id" value=${pago.id_pagar}> <input type="submit"
                                                                                              class="btn btn-primary"
                                                                                              value="Pagar"></td>
                [/#if]
            </tr>
            [/#list]
                    </table>
                    <center>
                        <a href="/administracion/indexAdmin.xml" class="btn btn-success">Volver</a>
                    </center>
                    <br></br>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
[/@structure]