<?php

include("conexion.php");

if ($link->connect_errno) {

  $glosa = "-1";

}
if ($link->query("UPDATE Solicitudes SET Estado = '$_REQUEST[estado]' WHERE id ='$_REQUEST[id]'") === TRUE) {

printf("1");

}

?>