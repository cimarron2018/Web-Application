package edu.advancedjava.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for StockQuote class
 * 
 * @author aperez
 *
 */
public class StockQuoteTest {

	private StockQuote testStockQuote;

	@Before
	public void setup() {
		testStockQuote = new StockQuote("LLL", new BigDecimal(21.22), new Date(8099));
	}

	@Test
	public void testGetStockPriceInstance() {
		assertTrue("StockQuote.getPrice() is not returning BigDecimal",
				testStockQuote.getStockPrice() instanceof BigDecimal);
	}

	@Test
	public void testGetStockPriceValue() {
		BigDecimal price = testStockQuote.getStockPrice();
		assertTrue("StockQuote.getPrice() returning incorrect price (21.22)", price.doubleValue() == 21.22);
	}

	@Test
	public void testGetDateRecordedInstance() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertTrue("StockQuote.getDateRecorded() is not returning an instance of Date", dateValue instanceof Date);
	}

	@Test
	public void testGetDateRecorded() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertTrue("StockQuote.getDateRecorded() not equal to test date", dateValue.equals(new Date(8099)));
	}

	@Test
	public void testGetStockSymbol() {
		String testSymbol = testStockQuote.getStockSymbol();
		assertEquals("StockQuote.getStockSymbol() did not match the tets value", testSymbol, "LLL");
	}

	@Test
	public void negTestGetStockPrice() {
		assertFalse("StockQuote.getStockPrice() returned wrong value",
				testStockQuote.getStockPrice().doubleValue() != 21.22);
	}

	@Test
	public void negTestGetDateRecorded() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertFalse("StockQuote.getDateRecorded returned wrong date", !dateValue.equals(new Date(8099)));
	}

	@Test
	public void negTestGetStockSymbol() {
		String testSymbol = testStockQuote.getStockSymbol();
		assertFalse("StockQuote.getStockSymbol() returned wrong symbol", testSymbol != "LLL");
	}

}
