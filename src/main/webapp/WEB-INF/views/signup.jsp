<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div align="center">
        <form id="signup" class="login-form">
            <table>
                <tr>
                    <td colspan="2" class="login-header">SignUp</td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" id="fname" name="fname" placeholder="First Name" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" id="lname" name="lname" placeholder="Last Name" /></td>
                </tr>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" id="userName" name="userName" placeholder="User Name"
                        required="required" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" id="password" name="password" placeholder="Password"
                        required="required" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button id=signupCustomer name="signupCustomer" type="submit">Register</button></td>
                </tr>
            </table>
        </form>
    </div>
    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>