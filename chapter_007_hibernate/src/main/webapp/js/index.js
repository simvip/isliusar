var serverHostName = window.location.hostname;

var serverProtocolName = window.location.protocol;

var portName = window.location.port;

if (portName.length == 0) {
    portName = "80";
}

if (serverHostName !== "localhost") {
    serverPath = serverProtocolName + "//" + serverHostName;
} else {
    serverPath = serverProtocolName + "//" + serverHostName + ":" + portName;
}


$('document').ready(function () {
    updateForm();
    $('#loginUser').html(localStorage.getItem("login"));
});

function updateForm() {

    console.log("Get all items");

    var jsonData = new Object();
    jsonData.command = "FIND_ALL";

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (response) {
            if (!response.answer) return;
            var list = JSON.parse(response.list);
            var items = "";

            $.each(list, function (index, item) {
                console.log('' + index + ": " + item.decs);
                items = items + ' <div class="col-md-4">'
                    + '<div id="' + item.id + '" class="card mb-4 box-shadow">'
                    + '<img class="card-img-top" src="' + item.coverPath + '" data-holder-rendered="true" style="height: 225px; width: 100%; display: block;"/>'
                    + '<div class="card-body">'
                    + '<p class="card-text">' + item.desc + '</p>'
                    + '<div class="d-flex justify-content-between align-items-center">'
                    + '<div class="btn-group">'
                    + '<a role="button" href="item.html?itemId=' + String(item.id).trim() + '" class="btn btn-sm btn-outline-secondary">View</a>'
                    + '<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>'
                    + '</div>'
              //      + '<small class="text-muted">' + item.id + '</small>';
                if (item.done) {
                    items = items
                        + '<div id="sdf" class="btn-group">'
                        + '<button type="button" class="btn btn-danger">Car sales</button>'
                        + '</div>';
                }
                items = items
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            });
            $('#row').html(items);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

}

