$(document).ready(function(e) {

	var api_key = "3726fe9cef4ab9d506ee77d8d8f7f3d1";
	var current_weather_url = "http://api.openweathermap.org/data/2.5/weather?";
	var five_day_forecast = "http://api.openweathermap.org/data/2.5/forecast?";
	var icon_url = "http://openweathermap.org/img/w/";


	$('#searchForm').submit(function(e) {

		e.preventDefault();
		var city = $("#txtLocation").val().trim();
		if (city != "") {

			var parameters = $.param({
				q: city,
				appid: api_key
			});

			$.ajax({
				type: 'GET',
				url: current_weather_url + parameters,
				dataType: 'json',
				success: function(response) {
					alert(response.name + " - " + response.weather[0].main);
				},
				error: function(request, message, error) {
					alert('ERROR!');
				}

			});

		}

	});

});