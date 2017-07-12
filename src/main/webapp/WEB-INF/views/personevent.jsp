<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/scripts/custom.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>
</head>
<body>

<h3>Registration For Event</h3>
	<form name="personEventForm" id="personEventForm">
		<input type="hidden" name="id" id="personeventid"/>
		<input type="hidden" name="personid" id="person"/>
		<p>Person : <input type="email" name="email" id="personemail" autocomplete="off" placeholder="write a Email here" maxlength="30" onblur="checkAvailable()"></p>
		<p id="cityDiv">City : <select name="city" id="city"></select></p>
		<p id="eventDiv">Event : <select name="event" id="event"><option value="">Select Event</option></select></p>
		<a href="http://localhost:8080/Event/Person/" id="register" style="display: none;">Go to Register</a>
		<button type="button" name="submitbtn" id="submitbtn" class="" onclick="saveEvent()"> Save Event </button>		
	</form>
</body>

<script type="text/javascript">
	
	var personid;
	
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
	
	$('#city').on('change', function() {
		var fd = new FormData();
		fd.append("city", this.value);
		$.ajax({
			type: 'POST',
			url:'http://localhost:8080/Event/Event/getEvent',
			data : fd,
			processData : false,
	        contentType : false,
	
			success : function(response) {
				var eventOption = '<option value="">Select Event</option>';
				for(i=0;i<response.length;i++){
					eventOption += '<option value='+response[i].eventId+'>'+response[i].eventName+'</option>'
				}
				$('#event').empty().append(eventOption);
			},
			error : function(xhr, status, error) {
				console.log("error :: " + error);
			}
		});
	})
	
	function checkAvailable(){
		var fd = new FormData();
		fd.append("email", $('#personemail').val());
		$.ajax({
			type: 'POST',
			url:'http://localhost:8080/Event/Person/checkAvailable',
			data : fd,
			processData : false,
	        contentType : false,
	
			success : function(response) {
				if(response == false){
					alert("please do Register First");
					$('#cityDiv').hide();
					$('#eventDiv').hide();
					$('#register').show();
					$('#submitbtn').hide();
				} else{
					getPersonByEmail($('#personemail').val());
				}
			},
			error : function(xhr, status, error) {
				console.log("error :: " + error);
			}
		});
	}
		
		
	function getPersonByEmail(email){
		var fd = new FormData();
		fd.append("email", email);
		$.ajax({
			type: 'POST',
			url:'http://localhost:8080/Event/Person/getPersonByEmail',
			data : fd,
			processData : false,
	        contentType : false,
	
			success : function(response) {
				if(response != null){
					personid = response;
				}
			},
			error : function(xhr, status, error) {
				console.log("error :: " + error);
			}
		});
	}
	
	function saveEvent() {
		var fd = new FormData();
		fd.append("personeventid", $('#personeventid').val());
		fd.append("personid", personid);
		fd.append("eventid", $('#event').val());
		if($('#personemail').val() !=null && $.trim($('#personemail').val()).length != 0 && $('#event').val() !=null && $('#city').val() != null){
			$.ajax({
				type: 'POST',
				url:'http://localhost:8080/Event/Event/addPersonEvent',
				data : fd,
				processData : false,
		        contentType : false,
		
				success : function(response) {
					if(response == "Success"){
						alert("You have Successfully Registered to This Event");
						setTimeout(function(){
							window.location.href="http://localhost:8080/Event/";
						}, 2500);
					}else if(response == "Used"){
						alert("You are already Registered to This Event");
						setTimeout(function(){
							location.reload();
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