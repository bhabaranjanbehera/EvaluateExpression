<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evaluate Expression - Input Page</title>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<script type="text/javascript">
	var errorMessage = "${errorMessage}";
	$(document).ready(function(){
		if(errorMessage && errorMessage!=''){
			$("#error").html("Unable to Evaluate Expression Error Message :<br/> "+errorMessage);
		}		
	});
	function validationField() {
		try {
			var result = eval($("#expression").val());
			console.log(result);
		} catch (e) {
			$("#error").html("Please enter valid expression <br/> Only Number and arithmetic operators (+,-,*,/,(,)) allowed");
			return false;
		}

		return true;
	}
</script>
</head>
<body>

	<center>
		<h2>Assignment ${myName}</h2>
		<c:url var="actionUrl" value="/evalExp" />
		<form:form id="basic-form" action="${actionUrl}" method="POST"
			onsubmit="return validationField()">
			<table align="center">
				<tr>
					<td colspan="2"><span id="error" style="color: red;font-weight: bold;"></span></td>
				</tr>
				<tr>
					<td>Please enter an arithmetic expression :</td>
					<td><form:input path="expression" placeholder="expression"
							required="required" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Evaluate" style="float: right;"/></td>
				</tr>
			</table>
		</form:form>
	</center>
</body>
</html>