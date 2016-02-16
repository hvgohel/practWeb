<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
</head>
<body>
    <c:if test="${param.error ne null}">
        <div>Invalid username and password.</div>
    </c:if>
    <c:if test="${param.logout ne null}">
        <div>You have been logged out.</div>
    </c:if>
    <form action="/login" method="post">
        <div>
            <label> User Name : <input type="text" name="username" />
            </label>
        </div>
        <div>
            <label> Password: <input type="password" name="password" />
            </label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div>
            <input type="submit" value="Sign In" /> <input type="button" value="Sign Up">
        </div>
    </form>

    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>