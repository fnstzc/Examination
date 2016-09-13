<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Admin Login</title>
    <!-- Bootstrap -->
    <meta charset="UTF-8" /> 
  <!-- Bootstrap --> 
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" /> 
  <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen" /> 
  <link href="assets/styles.css" rel="stylesheet" media="screen" /> 
  <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen" /> >
  </head>
  <body id="login">

   <div class="navbar navbar-fixed-top"> 
     <div class="navbar-inner"> 
        <div class="container-fluid"> 
           <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
           <a class="brand" href="#">Fujitsu试卷生成系统</a>            
       </div> 
   </div> 
</div> 
 <div class="container">
  <div class="row-fluid"> 
    <h2></h2>
  </div>
   <div class="row-fluid"> 
    <h2></h2>
  </div>
   <div class="row-fluid"> 
    <h2></h2>
  </div>
      <form action="login" class="form-signin" onsubmit="return loginCheck();">
        <h2 class="form-signin-heading">Please sign in</h2>
        <div class="control-group">
          <div class="controls">
            <input id="username" name="name" type="text" class="input-block-level" placeholder="Username"><span class="help-inline"></span>
          </div>
        </div>
        <div class="control-group">
          <div class="controls">
            <input id="password" name="password" type="password" class="input-block-level" placeholder="Password"><span class="help-inline"></span>
          </div>
        </div>
        <!-- <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label> -->
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      </form>
  </div><!-- /container -->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="vendors/jquery-1.9.1.js"></script> 
    <script src="bootstrap/js/bootstrap.min.js"></script> 
    <script src="vendors/datatables/js/jquery.dataTables.min.js"></script> 
    <script src="assets/login_scripts.js"></script> 
    <script src="assets/DT_bootstrap.js"></script> 
    <script>
$(function() {

});
</script>  
</body>
</html>