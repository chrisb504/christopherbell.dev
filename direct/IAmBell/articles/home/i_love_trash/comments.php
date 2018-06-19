<?php

	$dbhost = 'localhost';
	$dbuser = 'new_user';
	$dbpass = 'amjad1813';
	$conn = mysql_connect($dbhost, $dbuser, $dbpass);
	if(! $conn ) {
		die('Could not connect: ' . mysql_error());
	}
	else {
		echo 'connected';
	}


	$comment = $_POST['comment'];
	$author  = $_POST['username'];
	if(!$comment) {
		echo 'No comment.'; 
		exit;
	}

	//$link = mysql_connect('localhost', 'masterg1813', 'Ilikehalo1813') or die(mysql_error());

	mysql_select_db('Article_Content');

	$SQL = "INSERT INTO COMMENT (Author, CommentText) VALUES ('$author','$comment')";
	if(!mysql_query($SQL)) {
		die('Error: '. mysql_error($conn));
	};

	$sql = 'SELECT * FROM COMMENT';
	$result = mysql_query( $sql, $conn );

	

	//header( 'Location: http://www.iambell.co/articles/home/i_love_trash/i_love_trash.html' );

	//print "Executing SQL.......<br>";

	//print  $comment;




    //$result = mysql_query($link, "SELECT comment_text FROM Comments");


	while($row = mysql_fetch_assoc($result)){
		print $row['comment_text'];
		print "<br>";
	}

	mysql_close($link);
?>