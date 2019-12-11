<?php
     if ($_SERVER['SERVER_NAME'] === "localhost") {
          define('HOST','localhost');
          define('USER','root');
          define('PASS','root');
          define('DB','evoting');
     } else {
          define('HOST','us-cdbr-iron-east-05.cleardb.net');
          define('USER','b6a36022cae1da');
          define('PASS','34ad7e1a');
          define('DB','heroku_6dbf2da12e8a724');
     }
     
     $db = mysqli_connect(HOST,USER,PASS,DB) or die('could not connect to database');
?>