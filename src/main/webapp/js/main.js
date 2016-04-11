// The root URL for the RESTful services
var rootURL = "/test";

var student;

// Retrieve student list when application starts
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
    search($('#searchKey').val());
    return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e) {
    if (e.which == 13) {
        search($('#searchKey').val());
        e.preventDefault();
        return false;
    }
});

$('#btnAdd').click(function() {
    newStudent();
    return false;
});

$('#btnSave').click(function() {
    if ($('#id').val() == '')
        addStudent();
    else
        updateStudent();
    return false;
});

$('#btnDelete').click(function() {
    deleteStudent();
    location.reload();
    return false;
});

$('#studentList a').live('click', function() {
    findById($(this).data('identity'));
});

// Replace broken images with generic wine bottle
$("img").error(function() {
    $(this).attr("src", "pics/generic.jpg");

});

// signup button
$('#signupCustomer').click(function() {
    addCustomer();
    return false;
});

function addCustomer() {
    var signupData = {
        firstName : $('#fname').val(),
        lastName : $('#lname').val(),
        registration : {
            password : $('#password').val(),
            userName : $('#userName').val(),
        }
    };

    console.log('addCustomer');
    $.ajax({
        type : 'POST',
        contentType : 'application/json',
        url : '/data/customer',
        dataType : "json",
        data : JSON.stringify(signupData),
        success : function(data, textStatus, jqXHR) {
            alert('customer signup successfully');
            window.location = "/login"
        },
        error : function(jqXHR, textStatus, errorThrown) {
            var e = JSON.parse(jqXHR.responseText);
            alert(e.message);
        }
    });
}

function search(searchKey) {
    if (searchKey == '')
        findAll();
    else
        findByCity(searchKey);
}

function newStudent() {
    $('#btnDelete').hide();
    student = {};
    renderDetails(student); // Display empty form
}

function findAll() {
    console.log('findAll');
    $.ajax({
        type : 'GET',
        url : '/students',
        dataType : "json", // data type of response
        success : renderList
    });
}

function findByCity(searchKey) {
    console.log('findByCity: ' + searchKey);
    $.ajax({
        type : 'GET',
        url : rootURL + '/' + searchKey + '/students',
        dataType : "json",
        success : renderList
    });
}

function findById(id) {
    console.log('findById: ' + id);
    $.ajax({
        type : 'GET',
        url : rootURL + '/student/' + id,
        dataType : "json",
        success : function(data) {
            $('#btnDelete').show();
            console.log('findById success: ' + data.name);
            student = data;
            renderDetails(student);
        }
    });
}

function addStudent() {
    console.log('addStudent');
    $.ajax({
        type : 'POST',
        contentType : 'application/json',
        url : rootURL + '/student',
        dataType : "json",
        data : formToJSON(),
        success : function(data, textStatus, jqXHR) {
            alert('student added successfully');
            location.reload();
            $('#btnDelete').show();
            $('#id').val(data.id);
        },
        error : function(jqXHR, textStatus, errorThrown) {
            var e = JSON.parse(jqXHR.responseText);
            alert(e.message);
        }
    });
}

function updateStudent() {
    console.log('updateStudent');
    $.ajax({
        type : 'PATCH',
        contentType : 'application/json',
        url : rootURL + '/student/' + $('#id').val(),
        dataType : "json",
        data : formToJSON(),
        success : function(data, textStatus, jqXHR) {
            alert('student info updated successfully');
            location.reload();
        },
        error : function(jqXHR, textStatus, errorThrown) {
            var e = JSON.parse(jqXHR.responseText);
            alert(e.message);
        }
    });
}

function deleteStudent() {
    console.log('deleteStudent');
    $.ajax({
        type : 'DELETE',
        url : rootURL + '/student/' + $('#id').val(),
        success : function(data, textStatus, jqXHR) {
            alert('student info deleted successfully');
        },
        error : function(jqXHR, textStatus, errorThrown) {
            var e = JSON.parse(jqXHR.responseText);
            alert(e.message);
        }
    });
}

function renderList(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $('#studentList li').remove();
    $.each(list, function(index, student) {
        $('#studentList').append('<li><a href="#" data-identity="' + student.id + '">' + student.name + '</a></li>');
    });
}

function renderDetails(student) {
    $('#id').val(student.id);
    $('#name').val(student.name);
    $('#dob').val(student.dob);
    $('#city').val(student.city);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    var studentId = $('#id').val();
    return JSON.stringify({
        "id" : studentId == "" ? null : studentId,
        "name" : $('#name').val(),
        "dob" : $('#dob').val(),
        "city" : $('#city').val(),
    });
}
