$(document).ready(
			function() {
				$.ajax({
					url : "http://localhost:8080/books/all"
				}).then(
						function(data) {
                            for (i = 0; i < data.length; i++) {

                                $("#dataTable").append('<tr><td align="center"><a class="btn btn-default"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="/books/delete/' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
                                + '<td>' + data[i].title + '</td>'
                                + '<td>' + data[i].author + '</td>'
                                + '<td>' + data[i].category.name + '</td>'
                                + '</tr>');

							}
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