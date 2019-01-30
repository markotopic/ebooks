$(document).ready(
			function() {
				$.ajax({
					url : "http://localhost:8080/books/all"
				}).then(
						function(data) {
                            for (i = 0; i < data.length; i++) {

                                $("#dataTable").append('<tr><td align="center"><a class="btn btn-default" id=\"update\" href="' + data[i].id + '"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="/books/delete/' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
                                + '<td>' + data[i].title + '</td>'
                                + '<td>' + data[i].author + '</td>'
                                + '<td>' + data[i].keywords + '</td>'
                                + '<td>' + data[i].category.name + '</td>'
                                + '<td>' + data[i].language.name + '</td>'
                                + '</tr>');

							}
						});

			    $("#btnEdit").click(function (event) {


                    //stop submit the form, we will post it manually.
                    event.preventDefault();

                    var catId = $(this).attr("value");

                    editData(catId);

                        });

                $("#dataTable").on('click','.btn-default',function(event){

                    event.preventDefault();

                    // get the current row
                    var currentRow=$(this).closest("tr");
                    var col2 = currentRow.find("td:eq(1)").text();
                    var col3 = currentRow.find("td:eq(2)").text();
                    var col4 = currentRow.find("td:eq(3)").text();
                    var col5 = currentRow.find("td:eq(4)").text();
                    var col6 = currentRow.find("td:eq(5)").text();

                    $('#title').val(col2);
                    $('#author').val(col3);
                    $('#keywords').val(col5);

                    $('#opcije').val(col5);
                    $('#opcije1').val(col6);

                    $('#bookEditModal').modal('toggle');

                    var catId = $(this).attr("href");
                    $("#btnEdit").prop('value', catId);


                        });

			});

$(document).on("click", "#remove", function(event){
		//ne salji get zahtev
		event.preventDefault();

		var confirmed = confirm("Are you sure you want to delete this book?");
		if(confirmed){

			var url = $(this).attr("href")
			//red koji se treba izbrisati ako je brisanje na serveru bilo uspesno
			tr_parent = $(this).closest("tr")
			$.ajax({
	        	url: url,
	        	type: "DELETE",
	        	success: function(){
	        		//ukloni i na strani
					tr_parent.remove()
		        }
			});
		}
	});

function editData(catId) {

    var form = $('#fileEditForm')[0];
    var data = new FormData(form);

    var url = "/books/update/" + catId;

    $("#btnEdit").prop("disabled", true);

    $.ajax({
        type: "PUT",
        enctype: 'multipart/form-data',
        url: url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
//            loadCategory()
            $("#btnEdit").prop("disabled", false);
            $('#bookEditModal').modal('toggle');
        },
        error: function (e) {
            console.log("ERROR : ", e);
            console.log("data : ", data);
            $("#btnEdit").prop("disabled", false);

        }
    });

}