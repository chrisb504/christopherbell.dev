<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>IAmBell - I Love Trash</title>
        <link rel="stylesheet" type="text/css" href="../../../css/style.css">
        <link rel="shortcut icon" href="../../../images/tab_icons/yui-peeking.jpg">
    </head>

    <body>

		<div id="content">

        	<article>
          	<h1 style="font-size: 70px;">I Love Trash</h1>
        	</article>

          <div id="header-container">
                <div id="header">
                
                    <nav>
                        <ul>
                            <li><a href="../../../index.html">Home</a></li>
                            <li><a href="../../../tony.html">Cats</a></li>
                            <li><a href="#Coming_Soon">Tech</a></li>
                            <li><a href="#Coming_Soon">World News</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

        	<div class= "article">
            	<iframe width="420" height="315" src="//www.youtube.com/embed/rxgWHzMvXOY" frameborder="0" allowfullscreen></iframe>
            	<div class="article-text">
                	<div class="article-title">
                    	<p>
                        	Friday Jan 24, 2014 9:47 PM
                        	<hr>
                    	</p>
                	</div>
                	<p>
                    	I know it's been a while guys but, you have to check out this video. It may just be better than Stranger Danger. It's called I love trash and it's from Seasame Street. Enjoy your giggles. 
                	</p>
                    
            	</div>
                <h2>Comments</h2>
                <form action="comments.php" method="post">
                    Username: <input type="text" name="username"><br>
                    <textarea name="comment" ></textarea>
                    <br>
                    <input type="submit" name="submit" value="Submit"> 
                </form>


                <div id="comment_section">
                    
                    <?php

                    $dbhost = 'localhost';
                    $dbuser = 'new_user';
                    $dbpass = 'amjad1813';
                    $conn = mysql_connect($dbhost, $dbuser, $dbpass);
                    if(! $conn ) {
                        die('Could not connect: ' . mysql_error());
                    }

                    $sql = 'SELECT * FROM Comments';

                    mysql_select_db('Article_Content');

                    $result = mysql_query( $sql, $conn );

                    while($row = mysql_fetch_assoc($result)) {
                        echo '<div class="comment">';
                        echo '<b>';
                        echo $row['Author'];
                        echo '&emsp; &ensp; &emsp; &ensp; &emsp;';
                        echo $row['Date'];
                        echo '&emsp; &emsp; &ensp; &emsp; &ensp;';
                        echo $row['Comment_ID_Number'];
                        echo '</b>';
                        echo '<hr>';
                        echo '<br>';
                        echo $row['Comment_Text'];
                        echo '<br>';
                        echo '</div>';
                        echo '<br>';
                    }

                    mysql_close($link);
                    ?>
                </div>
        	</div>
		</div>
        

	</body>
</html>