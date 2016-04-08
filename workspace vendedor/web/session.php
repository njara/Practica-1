<?php
// Establishing Connection with Server by passing server_name, user_id and password as a parameter

include("conexion.php");
if ($link->connect_errno) {

  $glosa = "-1";

}
// Selecting Database

session_start();// Starting Session
// Storing Session
$user_check=$_SESSION['login_user'];


$query_caja = "SELECT * FROM BackOffice WHERE mail='$user_check'";

// SQL query to fetch information of registerd users and finds user match.
$result=$link->query($query_caja);

if ($rows = mysqli_fetch_assoc($result)) {

$login_session =$rows['user'];
}
if(!isset($login_session)){
//mysql_close($link); // Closing Connection
header('Location: index.php'); // Redirecting To Home Page
}
?>