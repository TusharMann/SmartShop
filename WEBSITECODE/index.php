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
        <li><a href="#">Navbar Link</a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">Navbar Link</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center orange-text">Beacathon</h1>
      <div class="row center">
        <h5 class="header col s12 light">Checkout portal for Customer</h5>
      </div>
      <div class="row center">
        <!-- <form action="showItems.php" method="post">
        Name : <input type="text" name="name"><br>
        Mobile : <input type="text" name="mobile"><br>
        <input type="submit">
        </form> -->
        <form class="col s12" method="post" action="showItems.php">
      <div class="row">
        <div class="input-field col s6">
          <input placeholder="Name" name="name" id="name" type="text">

        </div>
        <div class="input-field col s6">
          <input placeholder="Mobile" id="mobile" name="mobile" type="text">

        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Seach
    <i class="material-icons right">send</i>
  </button>
      </div>

    </form>

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
