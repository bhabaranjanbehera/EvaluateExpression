<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="m" uri="../../WEB-INF/displaytag.tld" %>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evaluate Expression - Result Page</title>
</head>
<body>
	<center>
		<a href="/EvaluateExpression">Home</a>
		<br>
		<h2>Result</h2>
		<m:result/>
		
		<c:if test="${fn:length(list) gt 1}">
			<br>
			<h2>History</h2>
			<table border="2">
				<tr>
					<th><strong>Expression</strong></th>
					<th><strong>Result</strong></th>
				</tr>
			<c:forEach var="eachRow" items="${list}" begin="1">
				<tr>
					<td>${eachRow.expression}</td>
					<td>${eachRow.result}</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</center>

</body>
</html>