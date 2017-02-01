<?php

    $dbhost = 'localhost';
                    $dbuser = 'new_user';
                    $dbpass = 'amjad1813';
                    $conn = mysql_connect($dbhost, $dbuser, $dbpass);
                    if(! $conn ) {
                        die('Could not connect: ' . mysql_error());
                    }

				    $sqlComment = "SELECT * FROM COMMENT";


                    $result = mysql_query( $sqlComment, $conn );

                    while($row2 = mysql_fetch_assoc($result)) {
                        echo "<div class=\"panel panel-info\">";
                        echo "<div class=\"panel-heading\">". date(DATE_RFC2822) . "</div>";
                        echo "<div class=\"panel-body\">" . $row2["CommentText"];
                        echo "</div>
                        	<div class=\"panel-footer\">". $row2["Author"] ."</div>";
                        echo "</div></div>";
                    } 

?>