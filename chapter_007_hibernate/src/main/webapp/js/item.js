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


$('document').ready(function () {
    console.log("Document READY")
    fillItem();
    fillImages();
    fillDropDownMenu();

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

    $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        url: serverPath + "/upload",
        data: data,
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

    var jsonData = new Object();
    var itemId = $('#itemId').val();
    var userId = $('#userId').val();

    jsonData.command = "CREATE_OR_UPDATE";
    jsonData.id = isNaN(itemId) ? null : itemId;
    jsonData.userId = isNaN(userId) ? null : userId;
    jsonData.desc = $('#desc').val();
    jsonData.coverPath = $('img').attr('src');
    jsonData.done = 'true';

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,
        success: function (response) {
            if (response.answer){
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
        dataType: 'json',
        async: true,

        success: function (response) {
            if (!response.answer) return;
            var list = JSON.parse(response.list);
            $.each(list, function (nameDropDown, arrayValue) {
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
    if (isNaN(itemId) || itemId==="") {
        return;
    }

    var jsonData = new Object();
    jsonData.command = "GET_ALL_FILES";
    jsonData.itemId = itemId;

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (response) {
            if (!response.answer) return;


            var count = 0;
            var filePath = "";
            var arrayvalue = JSON.parse(response.list);

            var hideBullets = "";
            var carouselInner = "";
            $.each(arrayvalue, function (index, value) {

                filePath = save_dir + value.name.trim();

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
    jsonData.command = "GET_BY_ID";
    jsonData.itemId = parseInt(itemId);

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        async: true,

        success: function (response) {
            if (!response.answer) return;

            var item = JSON.parse(response.list);
            $('#desc').val(item.desc);
            $('#userId').val(String(item.user.id));
            $('#userName').val(item.user.name.trim());
            establishVisibility();
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })

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
}

function establishVisibility() {
    var currentUserId = localStorage.getItem("userId");
    var userOnForm = $('#userId').val();
    if (currentUserId != userOnForm) {
        $('#parentFieldset').prop("disabled", true);
    }
}