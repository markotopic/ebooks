$(document).ready(
			function() {
				$.ajax({
					url : "http://localhost:8080/categories/all"
				}).then(
						function(data) {
							for (i = 0; i < data.length; i++) {

                                $("#opcije").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');

							}
						});

				$.ajax({
                	url : "http://localhost:8080/langs/all"
                }).then(
                		function(data) {
                			for (i = 0; i < data.length; i++) {

                                $("#opcije1").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');

                			}
                		});

			});

