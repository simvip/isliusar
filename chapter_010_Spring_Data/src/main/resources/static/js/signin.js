var serverHostName = window.location.hostname;
var serverProtocolName = window.location.protocol;
var portName = window.location.port;
var save_dir = "uploadFiles\\";
var serverPath = serverProtocolName + "//" + serverHostName;
if (portName.length === 0) {
    portName = "80";
}

if (serverHostName == "localhost") {
    serverPath = serverPath + ":" + portName;
}


function login() {

    var jsonData = new Object();
    jsonData.command = "1";
    jsonData.email = $('#email').val();


    localStorage.setItem("email", "");


    $.ajax({
        url: serverPath + "/signin",
        type: 'POST',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        dataType: 'json',
        async: true,

        success: function (event) {
            console.log("event: " + event["validate"]);

            switch (event["validate"]) {
                case "true":
                    var user = JSON.parse(event.user);
                    localStorage.setItem("login",user.email.trim())
                    localStorage.setItem("userId",user.id)
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