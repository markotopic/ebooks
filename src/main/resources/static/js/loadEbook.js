$(document).ready(
			function() {
				$.ajax({
					url : "http://localhost:8080/books/all"
				}).then(
						function(data) {
                            for (i = 0; i < data.length; i++) {

                                $("#dataTable").append('<tr><td hidden="" align="center"><a class="btn btn-default" id=\"update\" href="' + data[i].id + '"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="/books/delete/' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
                                + '<td>' + data[i].title + '</td>'
                                + '<td>' + data[i].author + '</td>'
                                + '<td>' + data[i].keywords + '</td>'
                                + '<td>' + data[i].category.name + '</td>'
                                + '<td>' + data[i].language.name + '</td>'
                                + '</tr>');

							}
						});

				$("#btnLogin").click(function (event) {

                    //stop submit the form, we will post it manually.
                    event.preventDefault();

                    logIn();

                });

			});

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