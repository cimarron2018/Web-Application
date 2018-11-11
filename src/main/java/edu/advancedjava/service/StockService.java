package edu.advancedjava.service;


import java.util.Calendar;
import java.util.List;

import edu.advancedjava.model.StockQuote;

/**
 * This interface describes a simple API for getting stock data.
 */
public interface StockService {

	/**
	 * Get a historical list of stock quotes.
	 * 
	 * @param symbol   the stock symbol to search for
	 * @param from     the date of the first stock quote
	 * @param until    the date of the last stock quote
	 * @param interval the number of StockQuotes to get, E.g. if Interval.DAILY was
	 *                 specified one StockQuote per day will be returned.
	 * @return a list of StockQuote instances. One for each day in the range
	 *         specified.
	 */
	List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException;

}

