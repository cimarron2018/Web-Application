package edu.advancedjava.service;

import edu.advancedjava.model.StockQuote;
import edu.advancedjava.utilities.DatabaseInitializationException;
import edu.advancedjava.utilities.DatabaseUtils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * JUNIT test for DatabaseStockService class
 * 
 * @author aperez
 *
 */
public class DatabaseStockServiceTest {

	private StockService stockServiceImplementation = null;
	private Calendar fromDate = Calendar.getInstance();
	private Calendar toDate = Calendar.getInstance();
	private String symbol = "AAPL";
	private List<StockQuote> listPrices = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @BeforeClass
    static public void firstSetup() {
        // Initialize database by running a script that will create a table and insert records
        try {
            DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        } catch (DatabaseInitializationException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
	
	@Before
	public void setup() {
		stockServiceImplementation = new DatabaseStockService();

		try {
			fromDate.setTime(dateFormat.parse("1/2/2000"));
		} catch (ParseException e1) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}
		try {
			toDate.setTime(dateFormat.parse("1/8/2000"));
		} catch (ParseException e) {
			System.out.println("Invalid date format. Correct format is mm/dd/yyyy");
		}


		try {
            listPrices = stockServiceImplementation.getQuote(symbol, fromDate, toDate);
        } catch (StockServiceException e) {
            e.printStackTrace();
        }

    }

	/**
	 * Test the getQuote method returns a BigDecimal
	 */
	@Test
	public void testGetQuote() {
        StockQuote testQuote = null;
        try {
            testQuote = stockServiceImplementation.getQuote(symbol);
        } catch (StockServiceException e) {
            e.printStackTrace();
        }
        assertTrue(testQuote.getStockPrice() instanceof BigDecimal);
	}

	@Test
	public void testGetQuoteDate() {
		assertTrue(listPrices instanceof List<?>);
	}

	@Test
	public void testGetQuoteDateFromDate() {
	    fromDate.add(Calendar.DATE, -1);
		assertTrue(listPrices.get(1).getDateRecorded().after(fromDate.getTime()));
	}

	@Test
	public void testGetQuoteDateToDate() {
		assertTrue(listPrices.get(listPrices.size()-1).getDateRecorded().equals(toDate.getTime()));
	}

	@Test
	public void testGetQuoteDateStockSymbol() {
		assertTrue(listPrices.get(0).getStockSymbol().equalsIgnoreCase(symbol));
	}

}
