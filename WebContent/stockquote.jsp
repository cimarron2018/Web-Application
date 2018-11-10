<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>
		Enter Stock Symbol: <br>
	</h3>

	<form name="myform" action="servlets/ServletStockService/" method="post">
		Stock Symbol : <input type="text" name="symbol" value="">
		<br>
		From Date (mm/dd/yyyy): <input type="text" name="fromDate" value="">
		<br>
		To Date (mm/dd/yyyy): <input type="text" name="toDate" value="">
		<br>
		<input type="SUBMIT" value="OK">
		<input type="HIDDEN" name="submit" value="true">
	</form>
</body>
</html>