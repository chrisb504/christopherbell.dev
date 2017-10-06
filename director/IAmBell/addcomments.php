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


        <h1 style="font-size: 70px; text-align:center;">IAmBell</h1>



		<?php

			$dbhost = 'localhost';
			$dbuser = 'new_user';
			$dbpass = '';
			$conn = mysql_connect($dbhost, $dbuser, $dbpass);
			if(! $conn ) {
				die('Could not connect: ' . mysql_error());
			}

			mysql_select_db('Article_Content');

			$comment = $_POST['comment'];
			$author  = $_POST['username'];
			$id      = $_POST['id'];

			$sql = "SELECT ArticleId FROM ARTICLE
					WHERE AuthorName= '$author'
					AND ArticleText= '$comment'";

			$articleID = mysql_query($SQL);


			$sql = "SELECT * FROM COMMENT
					WHERE ArticleId = '$id'";
			

			$result = mysql_query($sql, $conn);

			$SQL = "INSERT INTO COMMENT (ArticleId, Author, CommentText) VALUES ('$id','$author','$comment')";
			if(!mysql_query($SQL)) {
				die('Error: '. mysql_error($conn));
			}
			else {
				echo 'Comment added.';
			}
		?>
	</body>
</html>