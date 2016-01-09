<html>
<head>
<link rel="stylesheet" href="css/styles.css" />
<title>index</title>
</head>
<body>
    <a href="welcome">Go Home</a>
    <div class="header">
        <input type="text" id="searchKey"/>
        <button id="btnSearch">Search</button>
        <button id="btnAdd">New Student</button>
    </div>

    <div class="leftArea">
        <ul id="studentList"></ul>
    </div>

    <div class="mainArea">
    <form id="studentForm">
        <label>Id :</label> 
        <input type="text" id="id" name="id" disabled="disabled">
        
        <label>Name :</label> 
        <input type="text" id="name" name="name" required>
        
        <label>Date Of Birth : </label> 
        <input type="date" id="dob" name="dob">
        
        <label>City : </label> 
        <input type="text" id="city" name="city">

        <button id="btnSave">Save</button>
        <button id="btnDelete">Delete</button>
    </form>
    </div>

    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>