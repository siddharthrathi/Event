<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/scripts/custom.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events</title>
</head>
<body>
<h3>Add an Event</h3>
	<form name="eventForm">
		<input type="hidden" name="id" id="eventid"/>
		<p>Event Name : <input type="text" name="eventname" id="eventname" autocomplete="off" placeholder="write a Event Name here" maxlength="30"></p>
		<p>City : <select name="city" id="city"></select></p>
		<p>Start Date : <input type="text" name="startdate" id="startdate"></p>
		<p>End Date : <input type="text" name="enddate" id="enddate"></p>
		<p>Capacity : <input type="number" name="capacity" id="capacity"></p>
		<button type="button" name="submitbtn" id="submitbtn" class="" onclick="addEvent()"> Add Event </button>		
	</form>
</body>

<script type="text/javascript">
	var startDate;
	var endDate;
	$( function() {
		$( "#startdate" ).datepicker({
			dateFormat: 'dd/MM/yy',
			onClose: function(dateText) { 
				startDate = dateText; 
		    }
		});
	});
	$( function() {
		$( "#enddate" ).datepicker({
			dateFormat: 'dd/MM/yy',
			onClose: function(dateText) { 
				endDate = dateText; 
		    }
		});
	});
	
	$(document).ready(function(){
		$.ajax({
			type: 'GET',
			url:'http://localhost:8080/Event/Event/listCity',
			async : false,
			dataType : "json",
	
			success : function(response) {
				var cityOption = '<option value="">Select City</option>';
				for(i=0;i<response.length;i++){
					cityOption += '<option value='+response[i].cityId+'>'+response[i].cityName+'</option>'
				}
				$('#city').empty().append(cityOption);
			},
			error : function(xhr, status, error) {
				console.log("error :: " + error);
			}
		});
	});
	
	function addEvent() {
		var fd = new FormData();
		fd.append("eventid", $('#eventid').val());
		fd.append("eventname", $('#eventname').val());
		fd.append("city", $('#city').val());
		fd.append("startdate", startDate);
		fd.append("enddate", endDate);
		fd.append("capacity", $('#capacity').val());
		if($('#eventname').val() !=null && $.trim($('#eventname').val()).length != 0 && $('#city').val() !=null && $('#capacity').val() != null && $.trim($('#startdate').val()).length != 0 && $.trim($('#enddate').val()).length != 0){
			$.ajax({
				type: 'POST',
				url:'http://localhost:8080/Event/Event/addEvent',
				data : fd,
				processData : false,
		        contentType : false,
		
				success : function(response) {
					if(response == "Success"){
						alert("Event Added Successfully");
						setTimeout(function(){
							window.location.href="http://localhost:8080/Event/";
						}, 2500);
					}
				},
				error : function(xhr, status, error) {
					console.log("error :: " + error);
				}
			});
		}else{
			alert("Please Fill All Details");
		}
	}
	
</script>

</html>