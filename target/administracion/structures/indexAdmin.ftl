[#include "common.ftl" /] [@structure]

<head><meta charset="UTF-8"></head>       
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
            <div class="col-md-12" style="margin-left: auto; margin-right: auto;">
                <div class="card" style="background-color: rgba(255, 255, 255, 0.5);">
                <br></br>
                <div class="row">
                <div class="col-md-6">
                    <a  href="/administracion/administradores/IngresoGC.xml" class="btn btn-primary btn-lg btn-block">Ingresar Cuenta de gasto común</a>
                </div>

                    <div class="col-md-6">
                        <a href="/administracion/administradores/PagoGC.xml" class="btn btn-primary btn-lg btn-block">Ingresar Pago de gasto común</a>
                    </div>
                </div>
                <br></br>
                <div class="row">
                <div class="col-md-6">
                    <a href="/administracion/administradores/generarPago.xml" class="btn btn-primary btn-lg btn-block">Generar gastos comunes</a>
                </div>
                <br></br>
                <div class="col-md-6">
                    <a href="/administracion/administradores/VerGCAdmin.xml" class="btn btn-primary btn-lg btn-block">Visualizar  Cuenta de gasto común</a>
                </div>
                </div>
                <br></br>

                <br></br>
            </div>
        </div> 
    </div>
[/@structure]