package edu.advancedjava.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import edu.advancedjava.model.StockQuote;

/**
 * JUNIT test for YahooStockService class
 *
 * @author aperez
 */
public class YahooStockService2Test {

	static Calendar fromDate = Calendar.getInstance();
	static Calendar toDate = Calendar.getInstance();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	static String startDateString = "10/01/2018";
	static String endDateString = "10/15/2018";

	static String stockSymbol = "AAPL";
	static BigDecimal expectedPrice = new BigDecimal(100);
	static StockService stockServiceMock = Mockito.mock(StockService.class);
	static StockQuote quote;
	static StockQuote returnQuote = new StockQuote(stockSymbol, expectedPrice, Calendar.getInstance().getTime());
	static long numberOfDayData = 0;

	@BeforeClass
	public static void init() {

		try {
			fromDate.setTime(dateFormat.parse(startDateString));
		} catch (ParseException e1) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}

		try {
			toDate.setTime(dateFormat.parse(endDateString));
		} catch (ParseException e) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}

		numberOfDayData = TimeUnit.MILLISECONDS.toDays(Math.abs(fromDate.getTimeInMillis() - toDate.getTimeInMillis()));

		try {
			when(stockServiceMock.getQuote(any(String.class))).thenReturn(returnQuote);
		} catch (StockServiceException e) {
			e.printStackTrace();
		}

		try {
			quote = stockServiceMock.getQuote(stockSymbol);
		} catch (StockServiceException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method verifies that the correct price is returned
	 */
	@Test
	public void testGetPrice() {
		assertTrue("StockQuote.getPrice() is not returning BigDecimal", quote.getStockPrice() instanceof BigDecimal);
	}

	@Test
	public void testGetDateRecorded() {
		assertTrue("StockQuote.getDateRecorded() is not returning an instance of Date",
				quote.getDateRecorded() instanceof Date);
	}

	@Test
	public void testGetStockSymbol() {
		assertEquals("StockQuote.getStockSymbol() did not match the tets value", quote.getStockSymbol(), "AAPL");
	}

	@Test
	public void testNumberOfRecords() {
		List<StockQuote> listQuotes = new ArrayList<>();
		List<StockQuote> returnListQuotes = new ArrayList<>();
		Date date = fromDate.getTime();
		Calendar calendar = Calendar.getInstance();

		while (date.before(toDate.getTime())) {
			returnListQuotes.add(new StockQuote(stockSymbol, expectedPrice, date));
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			date = calendar.getTime();
		}

		try {
			when(stockServiceMock.getQuote(any(String.class), any(Calendar.class), any(Calendar.class)))
					.thenReturn(returnListQuotes);
		} catch (StockServiceException e) {
			e.printStackTrace();
		}
		try {
			listQuotes = stockServiceMock.getQuote(stockSymbol, fromDate, toDate);
		} catch (StockServiceException e) {
			e.printStackTrace();
		}
		assertTrue("StockQuote.getQuote(symbol,fromDate,toDate) did not match expected number of records",
				listQuotes.size() == numberOfDayData);
	}

}
