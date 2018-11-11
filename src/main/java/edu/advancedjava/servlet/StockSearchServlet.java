package edu.advancedjava.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.advancedjava.model.StockQuote;
import edu.advancedjava.service.IntervalEnum;
import edu.advancedjava.service.StockService;
import edu.advancedjava.service.StockServiceException;
import edu.advancedjava.service.StockServiceFactory;
import edu.advancedjava.service.StockServiceFactory.ServiceType;

/**
 * 
 * Servlet class that retrieves list of stock quotes.
 *
 */
public class StockSearchServlet extends HttpServlet {

	private static final String SYMBOL_PARAMETER_KEY = "symbol";
	private static final String FROMDATE_PARAMETER_KEY = "fromDate";
	private static final String TODATE_PARAMETER_KEY = "toDate";
	private static final String SOURCE_PARAMETER_KEY = "source";
	private static final String INTERVAL_PARAMETER_KEY = "interval";

	/**
	 * Retrieve stock quotes using the StockService from StockServiceFactory
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String symbol = request.getParameter(SYMBOL_PARAMETER_KEY);
		String fromDateString = request.getParameter(FROMDATE_PARAMETER_KEY);
		String toDateString = request.getParameter(TODATE_PARAMETER_KEY);
		String stockServiceSource = request.getParameter(SOURCE_PARAMETER_KEY);
		String stockQuotesInterval = request.getParameter(INTERVAL_PARAMETER_KEY);

		StockService stockServiceImplementation = null;
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		List<StockQuote> listPrices = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		IntervalEnum interval = IntervalEnum.DAILY;
		if (stockQuotesInterval.equalsIgnoreCase("weekly")) {
			interval = IntervalEnum.WEEKLY;
		} else if (stockQuotesInterval.equalsIgnoreCase("monthly")) {
			interval = IntervalEnum.MONTHLY;
		}

		ServiceType serviceType = ServiceType.Yahoo;
		if (stockServiceSource.equalsIgnoreCase("DB")) {
			serviceType = ServiceType.DB;
		}

		stockServiceImplementation = StockServiceFactory.getStockService(serviceType);

		try {
			fromDate.setTime(dateFormat.parse(fromDateString));
			toDate.setTime(dateFormat.parse(toDateString));
		} catch (ParseException e1) {
			throw new ServletException("Invalid date format. Correct format is mm/dd/yyyy");
		}

		try {
			listPrices = stockServiceImplementation.getQuote(symbol, fromDate, toDate, interval);
		} catch (StockServiceException e) {
			throw new ServletException("Not able to retrieve stock quote. " + e.getMessage());
		}

		HttpSession session = request.getSession();
		session.setAttribute("stockQuotes", listPrices);
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/stockquoteResults.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * We are going to perform the same operations for POST requests as for GET
	 * methods, so this method just sends the request to the doPost method.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
