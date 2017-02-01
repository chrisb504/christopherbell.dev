<?php

	$dbhost = 'localhost';
	$dbuser = 'masterg1813';
	$dbpass = 'Ilikehalo1813';
	$conn = mysql_connect($dbhost, $dbuser, $dbpass);
	if(! $conn ) {
		die('Could not connect: ' . mysql_error());
	}

	$sql = 'SELECT * FROM Comments';

	mysql_select_db('Content');

	$result = mysql_query( $sql, $conn );

	while($row = mysql_fetch_assoc($result)){
		print $row['comment_text'];
		print "<br>";
	}

	mysql_close($link);
?>