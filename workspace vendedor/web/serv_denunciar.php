<?php

include("conexion.php");

if ($link->connect_errno) {

  $glosa = "-1";

}
$query_caja = "SELECT * FROM Solicitudes";

$result=$link->query($query_caja);
while ($rows = mysqli_fetch_assoc($result)) {


   $array[] = $rows;

}

$longitud = count($array);
$id = 0;
if($longitud>0){
for($i=0; $i<$longitud; $i++)

      {
        if ($id<$array[$i]['id']) {
          	 $id =  $array[$i]['id'];
          }  

      }}
if ($link->query("INSERT INTO Solicitudes VALUES (null,'$_REQUEST[cliente]','$_REQUEST[pasillo]','$_REQUEST[estado]')") === TRUE) {

//printf($link->affected_rows." Filas creadas");
	 
	printf($id+1);

}

?>