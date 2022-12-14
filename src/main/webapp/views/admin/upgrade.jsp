<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" type="com.ute.FinalProject.beans.User"/>
<jsp:useBean id="AuthUser" scope="session" type="com.ute.FinalProject.beans.User"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
  <style>
    @import url('https://fonts.googleapis.com/css?family=Source+Code+Pro:200');

    body  {
      background-image: url('https://static.pexels.com/photos/414171/pexels-photo-414171.jpeg');
      background-size:cover;
      -webkit-animation: slidein 100s;
      animation: slidein 100s;

      -webkit-animation-fill-mode: forwards;
      animation-fill-mode: forwards;

      -webkit-animation-iteration-count: infinite;
      animation-iteration-count: infinite;

      -webkit-animation-direction: alternate;
      animation-direction: alternate;
    }

    @-webkit-keyframes slidein {
      from {background-position: top; background-size:3000px; }
      to {background-position: -100px 0px;background-size:2750px;}
    }

    @keyframes slidein {
      from {background-position: top;background-size:3000px; }
      to {background-position: -100px 0px;background-size:2750px;}

    }



    .center
    {
      display: flex;
      align-items: center;
      justify-content: center;
      position: absolute;
      margin: auto;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      background: rgba(75, 75, 250, 0.3);
      border-radius: 3px;
    }
    #logreg-forms{
      display: flex;
      align-items: center;
      justify-content: center;
      position: absolute;
      background: rgba(175, 175, 250, 0.3);
      border-radius: 3px;
      width:412px;
      margin:10vh auto;
      box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
      transition: all 0.3s cubic-bezier(.25,.8,.25,1);
    }
    #logreg-forms form {
      width: 100%;
      max-width: 410px;
      padding: 15px;
      margin: auto;
    }
    #logreg-forms .form-control {
      position: relative;
      box-sizing: border-box;
      height: auto;
      padding: 10px;
      font-size: 16px;
    }
    #logreg-forms .form-control:focus { z-index: 2; }
    #logreg-forms .form-signin input[type="email"] {
      margin-bottom: -1px;
      border-bottom-right-radius: 0;
      border-bottom-left-radius: 0;
    }
    #logreg-forms .form-signin input[type="password"] {
      border-top-left-radius: 0;
      border-top-right-radius: 0;
    }

    #logreg-forms .social-login{
      width:390px;
      margin:0 auto;
      margin-bottom: 14px;
    }
    #logreg-forms .social-btn{
      font-weight: 100;
      color:white;
      width:190px;
      font-size: 0.9rem;
    }

    #logreg-forms a{
      display: block;
      padding-top:10px;
      color:lightseagreen;
    }

    #logreg-form .lines{
      width:200px;
      border:1px solid red;
    }


    #logreg-forms button[type="submit"]{ margin-top:10px; }

    #logreg-forms .facebook-btn{  background-color:#3C589C; }

    #logreg-forms .google-btn{ background-color: #DF4B3B; }

    #logreg-forms .form-reset, #logreg-forms .form-signup{ display: none; }

    #logreg-forms .form-signup .social-btn{ width:210px; }

    #logreg-forms .form-signup input { margin-bottom: 2px;}

    .form-signup .social-login{
      width:210px !important;
      margin: 0 auto;
    }

    /* Mobile */

    @media screen and (max-width:500px){
      #logreg-forms{
        width:300px;
      }

      #logreg-forms  .social-login{
        width:200px;
        margin:0 auto;
        margin-bottom: 10px;
      }
      #logreg-forms  .social-btn{
        font-size: 1.3rem;
        font-weight: 100;
        color:white;
        width:200px;
        height: 56px;

      }
      #logreg-forms .social-btn:nth-child(1){
        margin-bottom: 5px;
      }
      #logreg-forms .social-btn span{
        display: none;
      }
      #logreg-forms  .facebook-btn:after{
        content:'Facebook';
      }

      #logreg-forms  .google-btn:after{
        content:'Google+';
      }

    }
  </style>
  <title>Option</title>
</head>
<body>
<c:if test="${AuthUser.permission == 1}">
  <div class="center">
      <div id="logreg-forms">
        <form action="" method="post" class="form-signin">
          <h1 class="h2 mb-3 font-weight-normal" style="text-align: center">Upgrade Account</h1><br>
          <input type="text" id="txtUsername" class="form-control"  name="UserName" readonly value="${user.userName}">
          <input type="text" id="txtPassword" class="form-control"  name="Permission" value="${user.permission}" autofocus>
          <br>
          <button class="btn btn-outline-success btn-block" type="submit" formaction="${pageContext.request.contextPath}/account/update" role="button"><i class="fas fa-sign-in-alt"></i>Change</button><br>
          <hr>
          <h2 class="h3 mb-3 font-weight-normal" style="text-align: center">Delete Account</h2>
          <button class="btn btn-outline-danger btn-block" type="submit" formaction="${pageContext.request.contextPath}/account/delete" role="button"><i class="fas fa-sign-in-alt"></i>Delete</button><br>

          <a style="color: white" href="${pageContext.request.contextPath}/account/admin" id="cancel_signup"><i class="fas fa-angle-left"></i>Back</a>
        </form>
        <br>
      </div>
  </div>
</c:if>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>