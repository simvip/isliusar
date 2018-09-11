var serverHostName = window.location.hostname;
var serverProtocolName = window.location.protocol;
var portName = window.location.port;
//from addResourceHandler webconfig
var rootDirectoryForImages = "/image/";
var serverPath = serverProtocolName + "//" + serverHostName;
if (portName.length === 0) {
    portName = "80";
}

if (serverHostName == "localhost") {
    serverPath = serverPath + ":" + portName;
}


$('document').ready(function () {
    console.log("Document READY")
    fillItem();
    fillDropDownMenu();
    fillImages();

    $('#myCarousel').carousel({
        interval: 5000
    });

    //Handles the carousel thumbnails
    $('[id^=carousel-selector-]').click(function () {
        var id_selector = $(this).attr("id");
        try {
            var id = /-(\d+)$/.exec(id_selector)[1];
            console.log(id_selector, id);
            jQuery('#myCarousel').carousel(parseInt(id));
        } catch (e) {
            console.log('Regex failed!', e);
        }
    });
    // When the carousel slides, auto update the text
    $('#myCarousel').on('slid.bs.carousel', function (e) {
        var id = $('.item.active').data('slide-number');
        $('#carousel-text').html($('#slide-content-' + id).html());
    });
});


$('#fileUploadForm').submit(function () {
    console.log("Add images");

    //stop submit the form, we will post it manually.
    event.preventDefault();

    // Get form
    var form = $('#fileUploadForm')[0];

    // Create an FormData object
    var data = new FormData(form);
    data.append('itemId', $('#itemId').val());
    data.append('file', file.files);

    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: serverPath + "/upload",
        data: data,
        contentType: "application/json",
        dataType: 'text',
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,

        success: function (answer) {
            console.log('Upload complete');
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
});

$('#updateItem').on('click', function () {
    console.log("Add/Update ITEM");

    // Car
    var jsonCar = new Object()
    var carId = $('#car :selected').val();
    jsonCar.id = isNaN(carId) ? null : parseInt(carId);

    // User
    var jsonUser = new Object()
    var userId = $('#userId').val();
    jsonUser.id = isNaN(userId) ? null : userId;

    // Item
    var itemId = $('#itemId').val();
    var item = new Object()
    item.id = isNaN(itemId) ? null : itemId;
    item.user = jsonUser;
    item.car = jsonCar;
    item.desc = $('#desc').val();
    item.done = 'true';
    item.created = new Date();
    item.coverPath = $('img').attr('src');

    // JSON request
    var jsonData = new Object();
    jsonData.command = "CREATE_OR_UPDATE";
    jsonData.item = item;

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        contentType: "application/json",
        async: true,
        success: function (response) {
            if (response.answer) {
                var itemId = $('#itemId').val(response.itemId);
            }
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
});


function fillDropDownMenu() {
    console.log("fill dropdown menu");

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
            $.each(response, function (nameDropDown, arrayValue) {
                var options = '';
                $.each(arrayValue, function (index, value) {
                    options += '<option value="' + value.id + '">' + value.name + '</option>';
                });
                $('#' + nameDropDown).attr('disabled', false);
                $('#' + nameDropDown).html(options);
            });


        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
}
function fillImages() {
    console.log("Fill carousel image");
    var itemId = $('#itemId').val();
    if (isNaN(itemId) || itemId === "") {
        return;
    }

    var jsonData = new Object();
    var item = new Object()
    item.id = itemId;
    jsonData.command = "GET_ALL_FILES";
    jsonData.item = item;

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        dataType: 'json',
        async: true,

        success: function (response) {
            var count = 0;
            var filePath = "";

            var hideBullets = "";
            var carouselInner = "";
            $.each(response, function (index, value) {
                filePath = rootDirectoryForImages + value.name.trim();

                hideBullets = hideBullets + '' +
                    '<li class="col-sm-3">' +
                    '<a class=thumbnail id=carousel-selector-' + "" + count + '><img src=' + filePath + '></a>' +
                    '</li>'

                if (count == 0)
                    carouselInner = carouselInner +
                        '<div class="active item" data-slide-number=0>' +
                        '<img src=' + filePath + '></div>';
                else
                    carouselInner = carouselInner +
                        '<div class="item" data-slide-number=' + "" + count + '>' +
                        '<img src=' + filePath + '></div>';
                count++;
            });
            $('#hide-bullets').html(hideBullets);
            $('#carousel-inner').html(carouselInner);
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
}

function fillItem() {
    console.log("Fill Item");
    var itemId = getUrlParameter('itemId');
    if (isNaN(itemId)) {
        $('#userName').val(localStorage.getItem("login"));
        $('#userId').val(localStorage.getItem("userId"));
        return;
    }

    $('#itemId').val(parseInt(itemId));

    var jsonData = new Object();
    var item = new Object()
    item.id = itemId;

    jsonData.command = "GET_BY_ID";
    jsonData.item = item;

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        dataType: 'json',
        async: true,

        success: function (response) {
            $('#desc').val(response.desc);
            $('#userId').val(String(response.user.id));
            $('#userName').val(response.user.name.trim());
            establishVisibility();
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })

}

function getUrlParameter(sParam) {
    return window.location.pathname.replace("/item/", "");
}

function establishVisibility() {
    var currentUserId = localStorage.getItem("userId");
    var userOnForm = $('#userId').val();
    if (currentUserId != userOnForm) {
        $('#parentFieldset').prop("disabled", true);
    }

}