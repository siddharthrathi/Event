<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/scripts/custom.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Spread Sheet</title>
</head>
<body>
<h3>Add Spread Sheet</h3>
	<form name="uploadForm" id="uploadForm">
	<p>Spread Sheet: <input type="file" name="imageupload" value="Choose File" style="display: none" id="browse-one" accept=".xls,.xlsx">
		<input type="button" value="Choose File" onClick="ImageBrowseClick()" id="fakeBrowse-one"></p></br>
		<input type="button" value="Upload file" onclick="uploadFile()"/>
	</form>
</body>

<script type="text/javascript">
	function uploadFile() {
		var fd = new FormData();
		fd.append("file", $('#browse-one')[0].files[0]);
		if($('#browse-one')[0].files[0]){
			$.ajax({
				type: 'POST',
				url:'http://localhost:8080/Event/SpreadSheet/uploadSpreadSheet',
				data : fd,
				processData : false,
		        contentType : false,
		
				success : function(response) {
					if(response == "Success"){
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
		}
		else{
			alert("Please Select Spread Sheet");
		}
	}

</script>
</html>