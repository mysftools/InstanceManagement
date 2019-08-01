<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Activate Account</title>
    <%@ include file="include/styles.jsp" %>
</head>

<body class=" login">
    <!-- BEGIN LOGO -->
    <div class="logo">

    </div>
    <!-- END LOGO -->
    <!-- BEGIN LOGIN -->
    <div class="content">
        <!-- BEGIN LOGIN FORM -->

        <!-- END LOGIN FORM -->
        <!-- BEGIN FORGOT PASSWORD FORM -->
        <form class="login-form" action="/active/setpasssword" method="post" id="set-pass-form">
            <h3>Your Account is activated</h3>
            <span>${error}</span>
            <p>Please Change Your Password</p>
             <input type="hidden" name="userid" id="userid" value="${email}"  />
             <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-user"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="User ID"
                         value="${email}" disabled="disabled" /> </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-lock"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Password"
                        name="password" id="password" required="required" /> </div>
            </div>
             <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-lock"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Confirm Password"
                        name="cpassword" id="cpassword" required="required" /> </div>
            </div>
            <div class="form-actions">
               
                <button type="submit" class="btn green pull-right" id="active-btn"> Submit </button>
                <br/>
            </div>
        </form>

        <!-- END FORGOT PASSWORD FORM -->
        <!-- BEGIN REGISTRATION FORM -->

        <!-- END REGISTRATION FORM -->
    </div>
    <%@ include file="include/scripts.jsp" %>
    <script src="resources/js/activeSetPassword.js" type="text/javascript"></script>
</body>

</html>