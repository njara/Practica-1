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
if($longitud>0){
for($i=0; $i<$longitud; $i++)

      {
        $cliente =  $array[$i]['cliente'];
          $pasillo =  $array[$i]['pasillo'];
          $estado = $array[$i]['estado'];    

      }}
$json = new stdClass();
$json->array = $array;
//print_r($arreglo);
echo json_encode($json);
?>