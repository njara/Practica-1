<?php
include('session.php');
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>DenunciApp</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="../../dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body role="document">

      <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="">DenunciApp</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

          <form class="navbar-form navbar-right" action="logout.php">
          <div class="form-group">
            <p class="navbar-text">Bienvenido: <?php echo $login_session;?></p>
            </div>
            <button class="btn btn-success">Logout</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>


    <div class="container theme-showcase" role="main">
           <div class="jumbotron">
        <h2>DenunciApp</h2>
        <p>Aplicación para Android, para realizar denuncias en la Facultad de ciencias físicas y matemáticas de la Universidad de Chile</p>
      </div>
    <div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">Registro de Denuncias</div>
  <div class="panel-body">
    <p>Ejemplo de Dashboard del registro de denuncias, através de la aplicacion mobile DENUNCIAPP!</p>


  </div>
  <div class="table-responsive">
<table class="table" >
        <thead>
          <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Agente</th>
            <th>Fecha_actual</th>
            <th>Fecha_delito</th>
            <th>Nombre</th>
            <th>Mail</th>
            <th>Sexo</th>
            <th>Edad</th>
            <th>Direccion</th>
            <th>Comuna</th>
            <th>Estado_civil</th>
            <th>Estudios</th>
            <th>Rol</th>
            <th>Detalle</th>
            <th>Rut</th>
            <th>Fono</th>
            <th>Zona_delito</th>
            <th>Tipo_delito</th>
            <th>Avalúo</th>
            <th>Comentarios</th>
          </tr>
        </thead>
        <tbody>
      <?php 

include("conexion.php");
if ($link->connect_errno) {

  $glosa = "-1";

}

$query_caja = "SELECT * FROM Denuncia";

$result=$link->query($query_caja);
while ($rows = mysqli_fetch_assoc($result)) {


   $array[] = $rows;

}

$longitud = count($array);
if($longitud>0){
for($i=0; $i<$longitud; $i++)

      {
        $id =  $array[$i]['id'];
          $status =  $array[$i]['status'];
          $mail = $array[$i]['mail'];
        $agente =  $array[$i]['agente'];
      $fecha_actual =  $array[$i]['fecha_actual'];

      $fecha_delito =  $array[$i]['fecha_delito'];
      $nombre =  $array[$i]['nombre'];
      $sexo =  $array[$i]['sexo'];
      $edad =  $array[$i]['edad'];
      $direccion =  $array[$i]['direccion'];
      $comuna =  $array[$i]['comuna'];
      $estado_civil =  $array[$i]['estado_civil'];
      $estudios =  $array[$i]['estudios'];
      $rol =  $array[$i]['rol'];
      $detalle =  $array[$i]['detalle'];
      $rut =  $array[$i]['rut'];
      $fono =  $array[$i]['fono'];
      $zona_delito =  $array[$i]['zona_delito'];
      $tipo_delito =  $array[$i]['tipo_delito'];
      $avaluo =  $array[$i]['avaluo'];
      $comentarios =  $array[$i]['comentarios'];

 echo "
    <tr>
     <th scope='row'>".$id."</th>
     <td>".$status."</td>
      <td>".$agente."</td>
      <td>".$fecha_actual."</td>
      <td>".$fecha_delito."</td>
      <td>".$nombre."</td>
      <td>".$mail."</td>
      <td>".$sexo."</td>
      <td>".$edad."</td>
      <td>".$direccion."</td>
      <td>".$comuna."</td>
      <td>".$estado_civil."</td>
      <td>".$estudios."</td>
      <td>".$rol."</td>
      <td>".$detalle."</td>
      <td>".$rut."</td>
      <td>".$fono."</td>
      <td>".$zona_delito."</td>
      <td>".$tipo_delito."</td>
      <td>".$avaluo."</td>
      <td>".$comentarios."</td>
    </tr>";

    

      }}

?>
        </tbody>
      </table>
      </div>
</div>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
