<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div class="header">
        <form class="search-form">
            <c:if test="${userName eq null}">
                <button>
                    <a href="/login">Login</a>
                </button>
            </c:if>
            <c:if test="${userName ne null}">
                Welcome <b style="color: green">${userName}</color></b>
                <button>
                    <a href="/logout">SignOut</a>
                </button>
            </c:if>
        </form>
    </div>
    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>