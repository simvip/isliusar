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


function Add() {

    var row = '<tr>' +
        '<td><input type="text" class="form-control" id="ItemId" disabled></td>' +
        '<td><input type="text" class="form-control" id="ItemDesc"></td>' +
        '<td><input type="checkbox" class="form-control" id="ItemDone"></td>' +
        '<td><input type="text" class="datepicker" id="ItemDate"></td>' +
        '<td>' +
        '<a class="btnSave"   title="Add"   ><i class="material-icons">&#xE03B;</i></a>' +
        '<a class="btnDelete" title="Delete" ><i class="material-icons">&#xE872;</i></a>' +
        '</td>>' +
        '</tr>';

    $("#tblData tbody").append(row);
    $(".datepicker").datepicker();
    $(".btnSave").bind("click", Save);
    $(".btnDelete").bind("click", Delete);
};
function Save() {
    var par = $(this).parent().parent();
    var tdId = par.children("td:nth-child(1)");
    var tdDesc = par.children("td:nth-child(2)");
    var tdDone = par.children("td:nth-child(3)");
    var tdDate = par.children("td:nth-child(4)");
    var tdButtons = par.children("td:nth-child(5)")

    tdId.html(tdId.children("input[type=text]").val());
    tdDesc.html(tdDesc.children("input[type=text]").val());
    tdDone.html(tdDone.children("input[type=text]").val());
    tdDate.html(tdDate.children("input[type=text]").val());
    tdButtons.html("" +
        '<a class="btnEdit"   title="Edit"   ><i class="material-icons">&#xE254;</i></a>' +
        '<a class="btnDelete" title="Delete" ><i class="material-icons">&#xE872;</i></a>' +
        "");
    $(".btnEdit").bind("click", Edit);
    $(".btnDelete").bind("click", Delete);

    var jsonData = new Object();
    jsonData.command = "2";
    jsonData.id = tdId.html();
    jsonData.desc = tdDesc.html();
    jsonData.done = tdDone.html();
    jsonData.dateCreate = Date.parse(tdDate.html()).valueOf();

    createOrUpdateItem(jsonData);
};
function Edit() {
    var par = $(this).parent().parent();

    var tdId = par.children("td:nth-child(1)");
    var tdDesc = par.children("td:nth-child(2)");
    var tdDone = par.children("td:nth-child(3)");
    var tdDate = par.children("td:nth-child(4)");
    var tdButtons = par.children("td:nth-child(5)")

    tdId.html("<input type='text'  id='ItemId' value='" + tdId.html() + "'/>");
    tdDesc.html("<input type='text'  id='ItemDesc' value='" + tdDesc.html() + "'/>");
    tdDone.html("<input type='text'  id='ItemDone' value='" + tdDone.html() + "'/>");
    tdDate.html("<input type='text'  id='ItemDate' class='datepicker' value='" + tdDate.html() + "'/>");
    tdButtons.html(""+'<a class="btnSave"   title="Edit"   ><i class="material-icons">&#xE254;</i></a>'+"");

    $(".btnSave").bind("click", Save);
    $(".btnEdit").bind("click", Edit);
    $(".btnDelete").bind("click", Delete);
    $(".datepicker").datepicker();
};
function Delete(){
    var par = $(this).parent().parent();
    var tdId = par.children("td:nth-child(1)").html();
    deleteItem(tdId);
    par.remove();
};

$(function () {
//Add, Save, Edit and Delete functions code
    $(".btnEdit").bind("click", Edit);
    $(".btnDelete").bind("click", Delete);
    $("#btnAdd").bind("click", Add);
});


function createOrUpdateItem(jsonData) {
    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),

        dataType: 'json',
        async: true,

        success: function (answer) {
            console.log("Create or update item " + answer[0]);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}
function deleteItem(id) {
    var jsonData = new Object();
    jsonData.command = "1";
    jsonData.itemId = id;

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),

        dataType: 'json',
        async: true,

        success: function (answer) {
            console.log("Delete item " + answer[0]);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

function fillTableOnForm() {
    var jsonData = new Object();
    jsonData.command = "0";

    $.ajax({
        url: serverPath + "/items",
        type: 'POST',
        data: JSON.stringify(jsonData),

        dataType: 'json',
        async: true,

        success: function (items) {
            tbl = document.getElementById('table_names');
            $.each(items,
                function (index, item) {
                    var row = '<tr>' +

                        '<td>' + item.id + '</td>' +
                        '<td>' + item.decs + '</td>' +
                        '<td>' + item.done + '</td>' +
                        '<td>' + new Date(item.created).toString('dd.MM.yyyy')  + '</td>' +
                        '<td>' +
                        '<a class="btnEdit"   title="Edit"   ><i class="material-icons">&#xE254;</i></a>' +
                        '<a class="btnDelete" title="Delete" ><i class="material-icons">&#xE872;</i></a>' +
                        '</td>>' +
                        '</tr>';

                    $('#table_names').append(row);
                    $(".btnEdit").bind("click", Edit);
                    $(".btnDelete").bind("click", Delete);
                    $(".datepicker").datepicker();

                }
            );

        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

