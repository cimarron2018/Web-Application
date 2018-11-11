package edu.advancedjava.service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.advancedjava.model.StockQuote;
import edu.advancedjava.utilities.DatabaseInitializationException;
import edu.advancedjava.utilities.DatabaseUtils;

/**
 * 
 * JUNIT test for DatabaseStockService class
 *
 */
public class DatabaseStockServiceTest {

	private StockService stockServiceImplementation = null;
	private Calendar fromDate = Calendar.getInstance();
	private Calendar toDate = Calendar.getInstance();
	private String symbol = "AAPL";
	private List<StockQuote> testStockQuotesList = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Initial setup. Run script to initialize database
	 */
	@BeforeClass
	static public void firstSetup() {
		// Initialize database by running a script that will create a table and populate
		// it with records
		try {
			DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
		} catch (DatabaseInitializationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 * Create an instance of StockService an calls the getQuote method. The Stock
	 * Quotes list returned is used for testing.
	 * 
	 */
	@Before
	public void setup() {
		stockServiceImplementation = new DatabaseStockService();

		try {
			fromDate.setTime(dateFormat.parse("1/2/2018"));
		} catch (ParseException e1) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}
		try {
			toDate.setTime(dateFormat.parse("1/8/2018"));
		} catch (ParseException e) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}

		try {
			testStockQuotesList = stockServiceImplementation.getQuote(symbol, fromDate, toDate, IntervalEnum.DAILY);
		} catch (StockServiceException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test to verify that the getQuote method is returning a List
	 */
	@Test
	public void testStockQuoteListInstance() {
		assertTrue("StockService.getQuote failed to return a list", testStockQuotesList instanceof List<?>);
	}

	/**
	 * Test to verify the first date in the stock quotes list is correct
	 */
	@Test
	public void testGetQuoteDateFromDate() {
		assertTrue("StockQuote returned the wrong 'From' date",
				testStockQuotesList.get(0).getDateRecorded().equals(fromDate.getTime()));
	}

	/**
	 * Test to verify last date in the stock quotes list is correct
	 */
	@Test
	public void testGetQuoteDateToDate() {
		assertTrue("StockQuote returned the wrong 'Until' date",
				testStockQuotesList.get(testStockQuotesList.size() - 1).getDateRecorded().equals(toDate.getTime()));
	}

	/**
	 * Test to verify the stock quote is returning the correct symbol
	 */
	@Test
	public void testGetQuoteDateStockSymbol() {
		assertTrue("StockQuote returned wrong symbol",
				testStockQuotesList.get(0).getStockSymbol().equalsIgnoreCase(symbol));
	}

}
