<?php

include("conexion.php");

if ($link->connect_errno) {

  $glosa = "-1";
 // print("error_al_conectar");

}
if ($link->query("INSERT INTO Registro VALUES ('$_REQUEST[mail]','$_REQUEST[password]','$_REQUEST[nombre]','$_REQUEST[apellido]','$_REQUEST[sexo]','$_REQUEST[edad]','$_REQUEST[estado_civil]','$_REQUEST[estudios]','$_REQUEST[rol]','$_REQUEST[detalle]','$_REQUEST[rut]','$_REQUEST[fono]','$_REQUEST[direccion]','$_REQUEST[comuna]')") === TRUE) {

	$glosa ="1";

}
else{
	$glosa = "0";
}
$json = new stdClass();
$json->glosa = $glosa;
//print_r($arreglo);
echo json_encode($json);
// Link de prueba de servicio
// http://denunciapp.njara.cl/serv/serv_denunciar.php?id=&status=Revisando&agente=victima&fecha_actual=2015-02-03&fecha_delito=2015-02-03&nombre=Sujeto1&sexo=masculino&edad=30&direccion=Santiago&comuna=Santiago&estado_civil=soltero&estudios=sin%20info&carrera=-&oficio=-&rut=1234-9&fono=0221234432&zona_delito=zona1&tipo_delito=Robo%20con%20violencia&descripcion=-----&comentarios=Sin%20Comentarios
?>