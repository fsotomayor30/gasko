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
                <center><h1></span>Modificar Gasto Comun</h1></center>

                <div class="card-body">
                    <div class="card" style="background: #EAEAEA">
                        <input type="hidden" name="id" id="id" value=${gastoComun.id}>
                        <h3>Datos Originales</h3>
                        <div class="col-12">
                            <span class="oi oi-calendar"></span>
                            <label for="date" class="col-2 col-form-label">Fecha:</label>
                            <input class="form-control" type="date" value=${gastoComun.fecha? string.iso} readonly id="date" name="date">
                        </div>
                        <div class="col-12">
                            <span class="oi oi-list"></span>
                            <label for="tipo" class="col-2 col-form-label">Tipo:</label>
                            <textarea class="form-control"  rows="5" readonly id="tipo" name="tipo"  maxlength="50">${tipoGasto}</textarea>
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
                                <label class="mr-sm-2" for="descripcion">Tipo de Gasto Común</label>
                                <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="descripcionNueva" id="descripcion">
                                    <option value="1">Luz</option>
                                    <option value="2">Agua</option>
                                    <option value="3">Gas</option>
                                    <option value="4">Salarios</option>
                                    <option value="5">Mantenciones</option>
                                    <option value="6">Otros</option>
                                </select>
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