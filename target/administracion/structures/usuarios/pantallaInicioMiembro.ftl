[#include "../common.ftl" /] [@structure]
<br></br>
        <div class="row">
            <div class="col-md-10 offset-md-1">
                <div class="card">
                    <h1></span>Estado de pago</h1>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-6">
                                <span class="oi oi-calendar"></span>
                                <label for="date_desde" class="col-2 col-form-label">Desde:</label>
                                <input class="form-control" type="date" value="2011-08-19" id="date_desde">
                            </div>
                            <div class="col-6">
                                <span class="oi oi-calendar"></span>
                                <label for="date_hasta" class="col-2 col-form-label">Hasta: </label>
                                <input class="form-control" type="date" value="2011-08-19" id="date_hasta">
                            </div>
                        </div>
                        <center>
                            <a href="#" class="btn btn-primary">Buscar</a>
                        </center>
                        <br></br>
                        <div class="col-12">
                            <table class="table">

                                <tr>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Monto</th>
                                </tr>


                    [#list gastosComunes as gastoComun]
    <tr>
        <td>${gastoComun.fecha}</td>
        <td>Pagada</td>
        <td>${gastoComun.monto}</td>
    </tr>
    [/#list]


                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
[/@structure]