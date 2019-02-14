$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        uploadData();

    });

    $("#btnSubmitLuceneQueryLanguage").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneQueryLanguage();

    });

    $("#btnSubmitLuceneTermQuery").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneTermQuery();

    });

    $("#btnLogin").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        logIn();

    });

});

function uploadData() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/index/add",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#result').empty();
            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
            $("#alert").append('<div class="alert alert-success">'
                                  + '<strong>Success!</strong> File has been successfully uploaded.'
                                  + '</div>');

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            $("#alert").append('<div class="alert alert-danger">'
                                + '<strong>Error!</strong> Upload Failed.'
                                + '</div>');

        }
    });

}

function searchLuceneQueryLanguage() {

    var value = $('#luceneQueryLanguage input[name=value]').val();
    var data = JSON.stringify({"value":value});
    $("#btnSubmitLuceneQueryLanguage").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/queryParser",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        }
    });

}

function searchLuceneTermQuery() {

    var field = $('#luceneTermQuery input[name=field]').val();
    var field1 = $( "#field" ).val();
    var value = $('#luceneTermQuery input[name=value]').val();

    var data = JSON.stringify({"field":field1, "value":value});

    $("#btnSubmitLuceneTermQuery").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/term",
        data: data,
        contentType: 'application/json',
        success: function (data) {

            $('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
                $('#result').append('<li>' + "Download link" + ': ' + "You have to be logged-in to download this file!" + '</li>');

            }
            myFunction1();
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        },
        error: function (e) {
            $('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        }
    });

}

function logIn() {

    var uname = $("#uname").val();
    var pass = $("#pass").val();
    var url = "users/login/" + uname + "/" + pass;

    $.ajax({
        url: url,
        type: "GET",
        success: function(){
            console.log("Successfuly logged in!")
            console.log("URL:", url);
            window.location.replace("ebooks.html");
        },
        error: function (e){
            console.log(e);
            console.log("Error!");
            console.log("URL:", url);
        }
    });

}

function myFunction() {
  var x = document.getElementById("myDIV");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
//    x.style.display = "none";
  }
}

function myFunction1() {
  var x = document.getElementById("resultDiv");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
//    x.style.display = "none";
  }
}
