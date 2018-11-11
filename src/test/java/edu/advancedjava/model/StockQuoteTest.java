package edu.advancedjava.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * JUnit tests for StockQuote class
 * 
 */
public class StockQuoteTest {

	static StockQuote testStockQuote;

	/**
	 * Create an instance of StockQuote class to use for testing
	 */
	@BeforeClass
	public static void setup() {
		testStockQuote = new StockQuote("LLL", new BigDecimal(21.22), new Date(8099));
	}

	/**
	 * Verifies the price is an instance of BigDecimal
	 */
	@Test
	public void testGetStockPriceInstance() {
		assertTrue("StockQuote.getPrice() is not returning BigDecimal",
				testStockQuote.getStockPrice() instanceof BigDecimal);
	}

	/**
	 * Verifies the price is correct
	 */
	@Test
	public void testGetStockPriceValue() {
		BigDecimal price = testStockQuote.getStockPrice();
		assertTrue("StockQuote.getPrice() returning incorrect price (21.22)", price.doubleValue() == 21.22);
	}

	/**
	 * Verifies StockQuote date is an instance of Date
	 */
	@Test
	public void testGetDateRecordedInstance() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertTrue("StockQuote.getDateRecorded() is not returning an instance of Date", dateValue instanceof Date);
	}

	/**
	 * Verifies the StockQuote date is correct
	 */
	@Test
	public void testGetDateRecorded() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertTrue("StockQuote.getDateRecorded() not equal to test date", dateValue.equals(new Date(8099)));
	}

	/**
	 * Verifies the StockQuote symbol is correct
	 */
	@Test
	public void testGetStockSymbol() {
		String testSymbol = testStockQuote.getStockSymbol();
		assertEquals("StockQuote.getStockSymbol() did not match the tets value", testSymbol, "LLL");
	}

	/**
	 * Verifies the StockQuote price is correct
	 */
	@Test
	public void negTestGetStockPrice() {
		assertFalse("StockQuote.getStockPrice() returned wrong value",
				testStockQuote.getStockPrice().doubleValue() != 21.22);
	}

	/**
	 * Verifies the StockQuotes date is correct
	 */
	@Test
	public void negTestGetDateRecorded() {
		Date dateValue = testStockQuote.getDateRecorded();
		assertFalse("StockQuote.getDateRecorded returned wrong date", !dateValue.equals(new Date(8099)));
	}

	/**
	 * Verifies the StockQuote symbol is correct
	 */
	@Test
	public void negTestGetStockSymbol() {
		String testSymbol = testStockQuote.getStockSymbol();
		assertFalse("StockQuote.getStockSymbol() returned wrong symbol", testSymbol != "LLL");
	}

	
	/**
	 * Verifies the StockQuote toString method returns a string
	 */
	@Test
	public void testToString() {
		String testToString = testStockQuote.toString();
		assertTrue("StockQuote.toString() returned wrong text", testToString.contains("LLL"));
	}
}
