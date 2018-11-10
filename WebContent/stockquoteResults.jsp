<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%-- get the Mood instance out of the session context --%>
<jsp:useBean id="stockQuotes"
	type="java.util.List<edu.advancedjava.model.StockQuote>"
	scope="session">
</jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Quote Results</title>
</head>
<body>
	<h3>Stock Quotes</h3>

	<table border="1">
		<tr>
			<th>Symbol</th>
			<th>Price</th>
			<th>Date</th>
		</tr>
		<c:forEach items="${stockQuotes}" var="quote">
			<tr>
				<td>${quote.stockSymbol}</td>
				<td><fmt:setLocale value = "en_US"/><fmt:formatNumber value = "${quote.stockPrice}" type = "currency"/></td>
				<td><fmt:formatDate pattern = "MM/dd/yyyy" value = "${quote.dateRecorded}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>