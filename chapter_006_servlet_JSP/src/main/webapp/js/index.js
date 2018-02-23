var serverHostName = window.location.hostname;

var serverProtocolName = window.location.protocol;

var portName = window.location.port;

if (portName.length == 0) {
    portName = "80";
}

if (serverHostName === "localhost") {
    serverPath = serverProtocolName + "//" + serverHostName + ":" + portName;
}
else {
    serverPath = serverProtocolName + "//" + serverHostName;
}

function createUser() {

    var jsonData = new Object();
    jsonData.command = 1;
    jsonData.login = $('#n_username').val();
    jsonData.role = "USER";
    jsonData.email = $('#n_email').val();
    jsonData.country = $('#country').val();
    jsonData.region = $('#region').val();
    jsonData.password = $('#n_password').val();

    $.ajax({
        url: serverPath + "/users",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (event) {
            switch (event["create"]) {
                case "true":
                    location.href = serverPath + "/index.html";
                    break;
                case "false":
                    alert("Not correct input data");
                    break;
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

function updateUser() {

    var jsonData = new Object();
    jsonData.command = 3;
    jsonData.login = $('#u_username').val();
    jsonData.role = "USER";
    jsonData.email = $('#u_email').val();
    jsonData.country = $('#country').val();
    jsonData.region = $('#region').val();
    jsonData.password = $('#u_password').val();

    $.ajax({
        url: serverPath + "/users",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (event) {
            switch (event["create"]) {
                case "true":
                    location.href = serverPath + "/index.html";
                    break;
                case "false":
                    alert("Not correct input data");
                    break;
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

function openAndFillUpdateForm() {

    var user = JSON.parse(sessionStorage.getItem('deleteUser'));
    console.log("delete user " + user.login.trim());

    document.getElementById('u_username').value = user.login.trim();
    $('#u_email').val(user.email.trim());
}

function deleteUser(login) {
    var jsonData = new Object();
    jsonData.command = "2";
    jsonData.login = login;

    $.ajax({
        url: serverPath + "/users",
        type: 'POST',
        data: JSON.stringify(jsonData),

        dataType: 'json',
        async: true,

        success: function (event) {
            console.log("event: " + event[""]);
            switch (event["delete"]) {
                case "true":
                    location.href = serverPath + "/index.html";
                    break;
                case "false":
                    alert("Not correct input data");
                    break;
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}



function login() {

    var jsonData = new Object();
    jsonData.command = "1";
    jsonData.login = $('#username').val();
    jsonData.password = $('#password').val();

    sessionStorage.setItem("login", "");
    sessionStorage.setItem("role", "");

    $.ajax({
        url: serverPath + "/signin",
        type: 'POST',
        data: JSON.stringify(jsonData),

        dataType: 'json',
        async: true,

        success: function (event) {
            console.log("event: " + event["validate"]);
            switch (event["validate"]) {
                case "true":
                    var user = JSON.parse(event["user"]);
                    sessionStorage.setItem("login", user.login.trim());
                    sessionStorage.setItem("role", user.role.trim());
                    location.href = serverPath + "/index.html";
                    break;
                case "false":
                    alert("Not correct input data");
                    break;
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

function updateTable() {

    var cLoginUser = sessionStorage.getItem("login");
    var currentUserAdmin = sessionStorage.getItem("role")==="ADMIN";

    $.ajax({
        url: serverPath + "/users",
        type: 'GET',
        dataType: 'json',
        async: true,

        success: function (users) {
            tbl = document.getElementById('table_names');
            $.each(users,
                function (index, user) {
                    var tr = tbl.insertRow();

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.login));
                    tr.appendChild(td);

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.name));
                    tr.appendChild(td);

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.email));
                    tr.appendChild(td);

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.role));
                    tr.appendChild(td);

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.country));
                    tr.appendChild(td);

                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode(user.region));
                    tr.appendChild(td);

                    //Button
                    var itsCurrentUser = user.login.trim() === cLoginUser;

                    // UPDATE
                    var td = tr.insertCell();
                    var buttonnode = document.createElement('input');
                    if (!(currentUserAdmin || itsCurrentUser)){
                        buttonnode.disabled = true;
                    }
                    buttonnode.setAttribute('type', 'button');
                    buttonnode.setAttribute('value', 'update');
                    td.appendChild(buttonnode);

                    buttonnode.addEventListener("click", function () {
                        sessionStorage.setItem("deleteUser", JSON.stringify(user));
                        location.href = serverPath + "/update.html";
                    });

                    // DELETE
                    var td = tr.insertCell();
                    var buttonnode = document.createElement('input');
                    if (!(currentUserAdmin || itsCurrentUser)){
                        buttonnode.disabled = true;
                    }
                    buttonnode.setAttribute('type', 'button');
                    buttonnode.setAttribute('value', 'Delete');
                    td.appendChild(buttonnode);

                    buttonnode.addEventListener("click", function () {
                        deleteUser(user.login);
                    });

                    tbl.appendChild(tr)
                }
            );

        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}


function selectRegion() {

    var id_country = $('#country').val();

    if (id_country == '0') {
        $('#region').html('<option>- выберите страну -</option>');
        $('#region').attr('disabled', true);
        $('#region').html('<option>- выберите регион -</option>');
        $('#region').attr('disabled', true);
        return (false);
    }

    $('#region').attr('disabled', true);
    $('#region').html('<option>загрузка...</option>');

    var jsonData = new Object();
    jsonData.command = 0;
    jsonData.id_county = id_country;

    $.ajax({
        url: serverPath + "/users",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (answer) {
            var options = '';
            $.each(answer, function (index, itemDislocation) {
                console.log("INDEX: " + index + " VALUE: " + itemDislocation.name);
                options += '<option value="' + itemDislocation.id + '">' + itemDislocation.name + '</option>';
            });

            $('#region').attr('disabled', false);
            $('#region').html(options);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

$('.message a').click(function () {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});