<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div align="center">
        <form action="/login" method="post" class="login-form">
            <table>
                <tr>
                    <th colspan="2" class="login-header">Login here</th>
                </tr>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="username" placeholder="UserName" required="required" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" placeholder="Password" required="required" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit">Login</button></td>
                </tr>
            </table>


            <c:if test="${param.error ne null}">
                <div>Invalid username and password.</div>
            </c:if>
            <c:if test="${param.logout ne null}">
                <div align="center" style="color: red">You have been logged out.</div>
            </c:if>
        </form>
    </div>
    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>