[#include "../common.ftl" /] [@structure]
<h1>Lista de usuarios</h1>
<table>
	<tr>
		<td>#</td>
		<td>Nombre</td>
		<td>Edad</td>
	</tr>
	[#list usuarios as usuario]
	<tr>
		<td>${usuario_index+1}</td>
		<td>${usuario.nombre}</td>
		<td>${usuario.edad?c}</td>
	</tr>
	[/#list]
</table>
[/@structure]
