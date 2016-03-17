<html>
<head>
<link rel="stylesheet" href="css/styles.css" />
<title>index</title>
</head>
<body>
    <%--     <jsp:include page="home.jsp"></jsp:include> --%>
    <div class="mainArea">
        <div class="header">
            <form class="search-form">
                <input type="text" id="searchKey" />
                <button id="btnSearch">Search By City</button>
                <button id="btnAdd">New Student</button>
            </form>
        </div>
        <div class="content">
            <div class="leftArea">
                <ul id="studentList"></ul>
            </div>
            <div class="rightArea" align="center">
                <form id="studentForm" class="student-registration">
                    <table>
                        <tr>
                            <td class="header" colspan="2">New Student Register</td>
                        </tr>
                        <tr>
                            <td>Id</td>
                            <td><input type="text" id="id" name="id" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td><input type="text" id="name" name="name" required></td>
                        </tr>
                        <tr>
                            <td>Date of Birth</td>
                            <td><input type="date" id="dob" name="dob"></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input type="text" id="city" name="city"></td>
                        </tr>
                        <tr>
                            <td><button id="btnSave">Save</button></td>
                            <td>
                                <button id="btnDelete">Delete</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="footer">bottom</div>
    </div>
    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>