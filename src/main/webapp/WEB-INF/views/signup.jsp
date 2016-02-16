<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
    <form id="signup">
        <div>
            <label> First Name : </label> <input type="text" id="fname" name="fname"><br />
        </div>
        <label> Last Name : </label> <input type="text" id="lname" name="lname"><br /> <label> User Name
            : </label> <input type="text" id="userName" name="userName"><br /> <label> Password : </label> <input
            type="password" id="password" name="password"><br />

        <button id=signupCustomer name="signupCustomer">Create</button>
    </form>

    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>