<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/scripts/custom.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person</title>
</head>
<body>
<h3>Add Person</h3>
	<form name="personForm">
		<input type="hidden" name="id" id="personId"/>
		<p>Name : <input type="text" name="name" id="personname" autocomplete="off" placeholder="write a Name here" maxlength="30"></p>
		<p>Email : <input type="email" name="email" id="personemail" autocomplete="off" placeholder="write a Email here" maxlength="30" onblur="checkAvailable()"></p>
		<p>Phone Number : <input type="text" name="phone" id="phone" autocomplete="off" placeholder="write a Phone Number here" maxlength="10"></p>
		<p>Age : <input type="number" name="age" id="age" autocomplete="off"></p>
		<p>City : <input type="text" name="city" id="city" autocomplete="off"></p>
		<button type="button" name="submitbtn" id="submitbtn" class="" onclick="addPerson()"> Add Person </button>
	</form>
</body>

<script type="text/javascript">

	$(document).ready(function(){
		$('#submitbtn').prop('disabled', true);
	});
	
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
				if(response == true){
					$('#submitbtn').prop('disabled', true);
				}else{
					$('#submitbtn').prop('disabled', false);
				}
			},
			error : function(xhr, status, error) {
				console.log("error :: " + error);
			}
		});
	}

	function addPerson() {
		var fd = new FormData();
		fd.append("personid", $('#personId').val());
		fd.append("personname", $('#personname').val());
		fd.append("email", $('#personemail').val());
		fd.append("phone", $('#phone').val());
		fd.append("age", $('#age').val());
		fd.append("city", $('#city').val());
		if($('#personname').val() !=null && $.trim($('#personname').val()).length != 0 && $('#personemail').val() !=null && $.trim($('#personemail').val()).length != 0 && $('#city').val() !=null && $.trim($('#city').val()).length != 0 && $('#phone').val() !=null && $.trim($('#phone').val()).length != 0 && $('#age').val() !=null && $.trim($('#age').val()).length != 0){
			$.ajax({
				type: 'POST',
				url:'http://localhost:8080/Event/Person/addPerson',
				data : fd,
				processData : false,
		        contentType : false,
		
				success : function(response) {
					if(response != null){
						alert("Person Added Successfully");
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