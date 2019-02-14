$(document).ready(

			function() {
				$.ajax({
					url : "http://localhost:8080/categories/all"
				}).then(
						function(data) {
							for (i = 0; i < data.length; i++) {

                                $("#dataTable").append('<tr><td align="center"><a class="btn btn-default" id=\"update\" href="' + data[i].id + '"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
                                + '<td>' + data[i].name + '</td>'
                                + '<td>' + data[i].id + '</td>'
                                + '</tr>');

							}
						});

			$("#btnAdd").click(function (event) {

                    //stop submit the form, we will post it manually.
                    event.preventDefault();

                    uploadData();

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
                     $('#categoryEditModal').modal('toggle');
                     $('#ime').val(col2);

                     var catId = $(this).attr("href");
                     $("#btnEdit").prop('value', catId);


                });

            $("#btnLogout").click(function (event) {

                    //stop submit the form, we will post it manually.
                    event.preventDefault();

                    window.location.replace("index.html");

                });

						
			});

function uploadData() {

    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);

    $("#btnAdd").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/categories/add",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", form);
            loadCategory()
            $("#btnAdd").prop("disabled", false);
            $('#categoryModal').modal('toggle');
        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#btnAdd").prop("disabled", false);

        }
    });

}

function editData(catId) {

    var form = $('#fileUploadFormEdit')[0];
    var data = new FormData(form);

//	var catId = $(this).attr("value");
    var url = "/categories/update/" + catId;

//    //pripremamo JSON koji cemo poslati
//    formData = JSON.stringify({
//        name:$("#fileUploadFormEdit [name='name']").val(),
//    });

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
//        url: url,
//		type: "PUT",
//		data: formData,
//		//saljemo json i ocekujemo json nazad
//		contentType: "application/json",
//		datatype: 'json',
        success: function (data) {
            console.log("SUCCESS : ", data);
//            loadCategory()
            $("#btnEdit").prop("disabled", false);
            $('#categoryEditModal').modal('toggle');
        },
        error: function (e) {
            console.log("ERROR : ", e);
            console.log("data : ", data);
            $("#btnEdit").prop("disabled", false);

        }
    });

}

function addCategory(data) {

        $("#dataTable").append('<tr><td align="center"><a class="btn btn-default"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="/categories/delete/' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
        + '<td>' + data[i].name + '</td>'
        + '<td>' + data[i].id + '</td>'
        + '</tr>');

}

function loadCategory() {

            $.ajax({
					url : "http://localhost:8080/categories/all"
				}).then(
						function(data) {
							for (i = 0; i < data.length; i++) {

                                if(i == data.length - 1){

                                $("#dataTable").append('<tr><td align="center"><a class="btn btn-default"><em class="fa fa-pencil"></em></a><a class="btn btn-danger" id=\"remove\" href="/categories/delete/' + data[i].id + '"><em class="fa fa-trash"></em></a></td>'
                                + '<td>' + data[i].name + '</td>'
                                + '<td>' + data[i].id + '</td>'
                                + '</tr>');

                                } else {
                                console.log("data.length: ", data.length);
                                console.log("i: ", i);
                                }

							}
						});

}

$(document).on("click", "#remove", function(event){
		//ne salji get zahtev
		event.preventDefault();

		var confirmed = confirm("Are you sure you want to delete this category?");
		if(confirmed){

			var catId = $(this).attr("href");
            var url = "/categories/delete/" + catId;

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
