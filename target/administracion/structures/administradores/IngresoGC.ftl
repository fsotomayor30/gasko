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
            <center><h1>Gasto Comun Mes ${mes}</h1></center>

            <div class="card-body">

                <table class="table table-striped">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Monto</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Eliminar</th>
                        <th scope="col">Modificar</th>
                    </tr>
            [#assign x = 0]
            [#assign aDateTime = .now]
            [#assign aDate = aDateTime?date]
            [#list gastosComunes as gc]
                [#if gc.fecha?string.MM == aDate?string.MM]
                    <tr>
                        <td>${gc.id}</td>
                        <td>${gc.fecha}</td>
                        <td>${gc.monto}</td>
                    [#list tiposGastos as tp]
                        [#if gc.descripcion = tp.id]
                            <td>${tp.descripcion}</td>
                        [/#if]
                    [/#list]
                    [#if gc.generado=0]
                        [#assign x = x + gc.monto]
                    [/#if]
                        [#if gc.generado=1]
                            <td>El Gasto Comun ya está generado</td>
                        [#else]
                        <form action="eliminarGC.xml" method="POST">
                            <td><input type="hidden" name="id" id="id" value=${gc.id}> <input type="submit"
                                                                                              class="btn btn-primary"
                                                                                              value="Eliminar"></td>
                        </form>
                        [/#if]
                    [#if gc.generado=1]
                        <td>El Gasto Comun ya está generado</td>
                    [#else]
                        <form action="modificarGC.xml" method="POST">
                            <td><input type="hidden" name="id" id="id" value=${gc.id}> <input type="submit"
                                                                                              class="btn btn-primary"
                                                                                              value="Modificar"></td>
                        </form>
                    [/#if]
                    </tr>
                [/#if]

            [/#list]
                    <tr style="text-align: right">
                        <td colspan="6"><h6>Total Gastos Común no generados: ${x}</h6></td>
                    </tr>
                </table>

                <center>
                    <div class="row">
                        <div class="col-md-4 offset-md-4">
                            <a href="/administracion/administradores/generarPago.xml"
                               class="btn btn-primary btn-lg btn-block">Generar
                                gastos comunes</a>
                        </div>
                    </div>
                </center>
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
                [#assign aDateTime = .now]
                [#assign aDate = aDateTime?date]
                <div class="card-body">

                    <div class="form-group row">

                        <div class="col-12">
                            <span class="oi oi-calendar"></span>
                            <label for="date" class="col-2 col-form-label">Fecha:</label>
                            <input class="form-control" type="date" value=${aDate} id="date" name="date" required>
                        </div>

                        <div class="col-12">
                            <span class="oi oi-dollar"></span>
                            <label for="monto" class="col-2 col-form-label">Monto:</label>
                            <input class="form-control" type="number" value="0" id="monto" name="monto" required>
                        </div>

                        <div class="col-12">
                            <span class="oi oi-list"></span>
                            <label class="mr-sm-2" for="descripcion">Tipo de Gasto Común</label>
                            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="descripcion" id="descripcion">
                                <option value="1">Luz</option>
                                <option value="2">Agua</option>
                                <option value="3">Gas</option>
                                <option value="4">Salarios</option>
                                <option value="5">Mantenciones</option>
                                <option value="6">Otros</option>
                            </select>
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