<?php
    error_reporting(-1);
    ini_set('display_errors', 'On');
    ?>

    <?php
    require_once __DIR__ . '/demo.php';
    $demo = new Demo();
    $admin_id = $demo->getDemoUser();
    ?>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

  <title>Smart Shop</title>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
  <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Smart Shop</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="index.php">Search Page</a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="index.php">Search Page</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center orange-text">Beacathon</h1>
      <div class="row center">
        <!--h5 class="header col s12 light">Items in User cart are </h5-->
      </div>
      <div class="row center">
        <!-- <form action="showItems.php" method="post">
        Name : <input type="text" name="name"><br>
        Mobile : <input type="text" name="mobile"><br>
        <input type="submit">
        </form> -->
        <?php


            $name = $_POST['name'];
            $mobile = $_POST['mobile'];
            echo '<h4>Customer Name &nbsp&nbsp : ' . $name .'</h4>';
            echo '<h4>Customer Mobile &nbsp&nbsp : ' . $mobile .'</h4>';
            echo "<br><br>";
            //echo $name . "<br>";
            //echo $mobile . "<br>";
            $results = $demo->getAllItemsOfUser($name,$mobile);
            while($row = $results->fetch_assoc()) {
              $list =  $row['list'];

              echo '
          <table class="centered">
            <thead>
              <tr>
                  <th data-field="id">Item Name</th>
                  <th data-field="name">Item Price</th>
                  <th data-field="price">Item Quantity</th>
              </tr>
            </thead><tbody>';

              $total = 0;
              $indItemsQtyArray = explode("-",$list);
              foreach ($indItemsQtyArray as $itemsQtyValue) {
                  $itemQtyArrayPair = explode('|',$itemsQtyValue);
                  $itemID = $itemQtyArrayPair[0];
                  $orderedQty =  $itemQtyArrayPair[1];
                  //echo $orderedQty;

                  // get item details now and calculate price and maintain total so FilesystemIterator

                  $results1 = $demo->getItemById($itemID);
                  $itemName = '';
                  $itemPrice = '';
                  while($row1 = $results1->fetch_assoc()){
                    $itemName = $row1['itemName'];
                    $itemPrice = $row1['itemPrice'];
                    $total = floatval($total) + floatval($itemPrice)*floatval($orderedQty);
                  }



        echo '
          <tr>
            <td>' . $itemName . '</td>
            <td> &#8377 ' .  $itemPrice .'</td>
            <td>' . $orderedQty . '</td>
          </tr>
    ';



                  //echo "ItemName  : " . $itemName . " &nbsp&nbsp&nbsp ItemPrice : " .$itemPrice . " &nbsp&nbsp&nbsp ItemQty : " . $orderedQty;
                  //echo "<br>";

              }
              echo '</tbody></table>';
              echo "<br>";

              echo '<h4>Total : &#8377 ' . $total .'</h4>';
              //$numbersArray = explode(",",$valuesArray[1]);
            }

            echo '<br><button class="btn waves-effect waves-light" type="submit" name="action">Checkout
        <i class="material-icons right">send</i>
      </button>';
            ?>



      </div>
      <br><br>

    </div>
  </div>







  <footer class="page-footer orange">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Taliban</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Team Members</h5>
          <ul>
            <li><a class="white-text" href="#!">Lovepreet Singh</a></li>
            <li><a class="white-text" href="#!">Chetan Mann</a></li>
            <li><a class="white-text" href="#!">Tushar</a></li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Facebook</a></li>
            <li><a class="white-text" href="#!">Twitter</a></li>
            <li><a class="white-text" href="#!">Github</a></li>
            <li><a class="white-text" href="#!">LinkedIn</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="">Taliban</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>

  </body>
</html>
