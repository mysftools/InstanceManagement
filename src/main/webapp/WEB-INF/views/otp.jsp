<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Otp</title>
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
        <form class="login-form" action="/" id="otp-form" method="get">
            <h3>Verify Email</h3>
            <span>${error}</span>
            <p> Enter the otp received in your mail </p>
            <input type="hidden" name="username" id="username" value="${email}" />
            <div class="form-group">
                <div class="input-icon">
                    <i class="fa fa-envelope"></i>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="OTP"
                        name="otp" id="otp" required="required" /> </div>
            </div>
            <div class="form-actions">
                <button type="button" id="back-btn-otp" class="btn grey-salsa btn-outline" value="${email}">Re-send
                </button>
                <button type="submit" class="btn green pull-right" id="otp-btn"> Submit </button>
            </div>
        </form>

        <!-- END FORGOT PASSWORD FORM -->
        <!-- BEGIN REGISTRATION FORM -->

        <!-- END REGISTRATION FORM -->
    </div>
    <%@ include file="include/scripts.jsp" %>
    <script src="resources/js/otp.js" type="text/javascript"></script>
</body>

</html>