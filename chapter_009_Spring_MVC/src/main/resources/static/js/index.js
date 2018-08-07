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
    fillDropDownMenu();
    $('#loginUser').html(localStorage.getItem("login"));
});


function fillDropDownMenu() {
    console.log("fill dropdown filters");
    var date = new Date();

    var startDay = date.getDate() - 7;
    var deltaMonth = startDay < 1 ? -1 : 0;
    var firstDate = new Date(date.getFullYear(), date.getMonth() + deltaMonth, startDay).getTime().toString();
    var lastDate = new Date(
        date.getFullYear(),
        date.getMonth(),
        date.getDate(),
        23,
        59,
        59
    ).getTime().toString();

    var options = '';
    options += '<a class="dropdown-item" href="index.html?sDate=' + firstDate + '&eDate=' + lastDate + '">in today day</a>'
    $('#period').html(options);

    var jsonData = new Object();
    jsonData.command = "GET_DROPDOWN_LIST";


    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        dataType: 'json',
        async: true,

        success: function (response) {
            if (!response.answer) return;
            var list = JSON.parse(response.list);
            $.each(list, function (nameDropDown, arrayValue) {
                if (nameDropDown == 'car') {
                    var options = '';
                    $.each(arrayValue, function (index, value) {
                        options += '<a class="dropdown-item" href="index.html?carId=' + String(value.id).trim() + '">' + value.name + '</a>'
                    });
                    $('#' + nameDropDown).html(options);
                }
            });


        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
}

function updateForm() {

    console.log("Get all items");

    var keyParam = ['carId', 'sDate', 'eDate', 'withPhoto'];
    var mapParam = [];
    var valueParameter = null;
    $.each(keyParam, function (index, nameOfParam) {
        valueParameter = getUrlParameter(nameOfParam);
        if (valueParameter != null) mapParam.push({name: nameOfParam, value: valueParameter});
    });

    var jsonData = new Object();
    jsonData.command = "FIND_ALL";
    jsonData.queryParam = mapParam.length == 0 ? null : JSON.stringify(mapParam);

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
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

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
    return null;
}


function getWeeksInMonth(month, year) {
    var weeks = [],
        firstDate = new Date(year, month, 1),
        lastDate = new Date(year, month + 1, 0),
        numDays = lastDate.getDate();

    var start = 1;
    var end = 7 - firstDate.getDay();
    while (start <= numDays) {
        weeks.push({start: start, end: end});
        start = end + 1;
        end = end + 7;
        if (end > numDays)
            end = numDays;
    }
    return weeks;
}