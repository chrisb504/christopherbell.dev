<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>IAmBell</title>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
        <link rel="shortcut icon" href="images/tab_icons/yui-peeking.jpg">
    </head>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

    <body>
    
        <!-- Navagation Bar -->
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                    </button>
                    <a class="navbar-brand" href="index.php">IAMBELL</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.php">Home</a></li>
                        <li><a href="cats.html">Cats</a></li>
                    </ul>
                    
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="post.html"> <span class="glyphicon glyphicon-plus"></span>Post</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav><!-- End Nav Bar -->

		<?php

			function loadPostPage() {
				$doc = new DOMDocument();

				$doc->loadHTMLFile($file_name);
				echo $doc->saveHTML();
			}

			function loadCommentsOnPostPage() {
				
			}

			function retrieveArticle() {
				$sqlComment = "SELECT * FROM COMMENT
							WHERE ArticleId = '$articleID'";
							
				$result = mysql_query( $sqlComment, $conn );
				while($row2 = mysql_fetch_assoc($result)) {
					echo '<div class="panel panel-info">';
					echo '<div class="panel-heading">'. date(DATE_RFC2822) . '</div>';
					echo '<div class="panel-body">' . $row2["CommentText"];
					echo '</div>
						<div class="panel-footer">' . $row2["Author"] . '</div>';
					echo '</div>';
				}
			}


			/*
			*	Retrieves all the comments from the database that relate to
			*	the given article.
			*
			*/
			function retrieveCommments() {
				$sqlComment = "SELECT * FROM COMMENT
                        WHERE ArticleId = '$id'";

            	$result = mysql_query( $sqlComment, $conn );

            	while($row2 = mysql_fetch_assoc($result)) {
                	echo '<div class="panel panel-info">';
                	echo '<div class=panel-heading>'. date(DATE_RFC2822) . '</div>';
                	echo '<div class="panel-body">' . $row2["CommentText"];
                	echo '</div>
                		<div class="panel-footer">'. $row2["Author"] . '</div>';
                	echo '</div></div>';
            	} 
				echo '</div>';
			}




			$dbhost = 'localhost';
			$dbuser = 'new_user';
			$dbpass = 'amjad1813';
			$conn = mysql_connect($dbhost, $dbuser, $dbpass);
			if(! $conn ) {
				die('Could not connect: ' . mysql_error());
			}

			$title   = $_POST['title'];
			$comment = $_POST['comment'];
			$author  = $_POST['username'];
			if(!$comment) {
				echo '<p>Please add a comment.</p>'; 
				exit;
			}
			elseif(!$title) {
				echo '<p>Please add a title.</p>';
				exit;
			}

			mysql_select_db('Article_Content');

			$SQL = "INSERT INTO ARTICLE (ArticleTitle, AuthorName, ArticleText) 
					VALUES ('$title','$author','$comment')";
			if(!mysql_query($SQL)) {
				die('Error: '. mysql_error($conn));
			}
			else{
				echo 'Post Added. ';
			}


			$sql = "SELECT ArticleId FROM ARTICLE
					WHERE AuthorName= '$author'
					AND ArticleText= '$comment'
					OR ArticleTitle= '$title'";

			$articleID = mysql_query($sql);
			$row = mysql_fetch_assoc($articleID);
			$articleID = $row['ArticleId'];


			$titleWithNoSpace = str_replace(' ', '', $title);

			$file_name = 'articles/home/';
			$file_name .= $titleWithNoSpace;
			$file_name .= '.php';

			echo  '<h1 style="font-size: 70px; text-align:center;">';
			echo $title;
			echo '</h1>';
			echo '<div class= "article">
			            <div class="panel panel-primary">
			                <div class="panel-heading">';
			echo $row['ArticleId'];
			echo '</div>
			      <div class="panel-body">';
			echo $comment . '</div>
					<div class="panel-footer">';
			echo $author;
			echo'</div>
			            </div>     
			            <h2>Comments</h2>
			            <form action="../../addcomments.php" method="post">
			                Username<br> <input type="text" name="username"><br>
			                ID<br><input type="text" name="id">
			                <textarea name="comment" rows="5" cols="100"></textarea> <br>
			                <button name="submit">Submit</button>
			            </form>



			            <br>'; 
	

			retrieveArticle();

            retrieveCommments();
			            
		             
			//file_put_contents($file_name, ob_get_contents());

			mysql_close($link);
		?>

	</body>
</html>