<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
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
        <form class="login-form" action="authenticate" method="post">
            <h3 class="form-title">Login to your account</h3>
            <span>${error}</span>
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span> Enter any username and password. </span>
            </div>
            <div class="form-group">
                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                <label class="control-label visible-ie8 visible-ie9">Username</label>
                <div class="input-icon">
                    <i class="fa fa-user"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username"
                        name="username" /> </div>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">Password</label>
                <div class="input-icon">
                    <i class="fa fa-lock"></i>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                        placeholder="password" name="password" /> </div>
            </div>
            <div class="form-actions">

                <button type="submit" class="btn green pull-right"> Login </button>
                <br />
            </div>
            <div class="forget-password">
                <h4>Forgot your password ?</h4>
                <p> no worries, click
                    <a href="javascript:;" id="forget-password"> here </a> to reset your password. </p>
            </div>
            <div class="create-account">
                <p> Don't have an account yet ?&nbsp;
                    <a href="javascript:;" id="register-btn"> Create an account </a>
                </p>
            </div>
        </form>
        <!-- END LOGIN FORM -->
        <!-- BEGIN FORGOT PASSWORD FORM -->
        <form class="forget-form" action="#">
            <h3>Forget Password ?</h3>
            <p> Enter your e-mail address below to reset your password. </p>
            <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-envelope"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                        name="email" id="email-r" required="required" /> </div>
            </div>
            <div class="form-actions">
                <button type="button" id="back-btn" class="btn grey-salsa btn-outline"> Back </button>
                <button type="submit" class="btn green pull-right" id="forget-pass-btn"> Submit </button>
            </div>
        </form>


        <!-- END FORGOT PASSWORD FORM -->
        <!-- BEGIN REGISTRATION FORM -->
        <form class="register-form">
            <h3>Sign Up</h3>
            <p> Enter your details below: </p>
            <div class="form-group">

                <div class="input-icon">
                    <i class="fa fa-user"></i>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="User Id" name="username"
                        id="username" />
                </div>
            </div>
            <div class="form-group">

                <div class="input-icon">
                    <i class="fa fa-lock"></i>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                        placeholder="Password" name="password" id="password" /> </div>
            </div>
            <div class="form-group">

                <div class="controls">
                    <div class="input-icon">
                        <i class="fa fa-check"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                            placeholder="Re-type Your Password" name="rpassword" id="rpassword" /> </div>
                </div>
            </div>

            <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-exchange"></i>
                    <input class="form-control placeholder-no-fix" type="number" placeholder="no of calls" name="calls"
                        id="calls" />
                </div>
            </div>
            <div class="form-actions">
                <button id="register-back-btn" type="button" class="btn grey-salsa btn-outline"> Back </button>
                <button type="submit" id="register-submit-btn" class="btn green pull-right"> Sign Up </button>
            </div>
        </form>
        <!-- END REGISTRATION FORM -->
    </div>
    <%@ include file="include/scripts.jsp" %>
    <script src="/resources/assets/pages/scripts/login.js" type="text/javascript"></script>
</body>

</html>