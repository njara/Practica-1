<?php
include("conexion.php");
if ($link->connect_errno) {

  $glosa = "-1";

}

session_start(); // Starting Session
$error=''; // Variable To Store Error Message
if (isset($_POST['submit'])) {
if (empty($_POST['username']) || empty($_POST['password'])) {
$error = "Username or Password is invalid";
}
else
{
// Define $username and $password
$username=$_POST['username'];
$password=$_POST['password'];
// Establishing Connection with Server by passing server_name, user_id and password as a parameter

// To protect MySQL injection for Security purpose
$username = stripslashes($username);
$password = stripslashes($password);
//$username = mysql_real_escape_string($username);
//$password = mysql_real_escape_string($password);

$query_caja = "SELECT * FROM BackOffice WHERE password='$password' AND mail='$username'";

// SQL query to fetch information of registerd users and finds user match.
$result=$link->query($query_caja);

if ($rows = mysqli_fetch_assoc($result)) {
$_SESSION['login_user']=$username; // Initializing Session
header("location: registro.php"); // Redirecting To Other Page
} else {
$error = "Username or Password is invalid: '$rows' y '$username' y '$password' '$query_caja'";
}
//mysql_close($link); // Closing Connection
}
}
?>