[#include "common.ftl" /] [@structure]
	<body background="/administracion/img/admin.png">
    <br></br>

    <nav class="navbar navbar-light bg-light">
  <span style="text-align:center" class="navbar-text">
    Bienvenido al Sistema de Gestion de Gastos Comunes - Modo usuario - En sesión: ${usuario}
  </span>
        <a class="form-inline" href="/administracion/logout.xml">Cerrar sesión</a>
    </nav>

<br></br>
        <div class="row">
            <div class="col-md-12" style="margin-left: auto; margin-right: auto;">
                <div class="card" style="background-color: rgba(255, 255, 255, 0.5);">
                <br></br>
                    <div class="row justify-content-center">
                <div class="col-6">
                    <a href="/administracion/usuarios/pantallaInicioMiembro.xml"
                       class="btn btn-primary btn-lg btn-block">
                        <img src="/administracion/img/view.png" style="max-width: 80px;" class="img-fluid">
                        Visualizar Gastos Comunes</a>
                    </div>
                    </div>
                    <br></br>
				</div>
            </div>
            </div>
[/@structure]