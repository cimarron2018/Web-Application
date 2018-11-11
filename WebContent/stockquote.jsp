<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Quote</title>
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
		Source: <input type="radio" name="source" value="yahoo" checked="checked"> Yahoo Financial Services
		<input type="radio" name="source" value="db"> Database
		<br>
		Interval: <input type="radio" name="interval" value="daily" checked="checked"> Daily
		<input type="radio" name="interval" value="weekly"> Weekly
		<input type="radio" name="interval" value="monthly"> Monthly
		<br>
		<input type="SUBMIT" value="OK">
		<input type="HIDDEN" name="submit" value="true">
	</form>
</body>
</html>