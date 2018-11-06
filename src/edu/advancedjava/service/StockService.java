package edu.advancedjava.service;


import java.util.Calendar;
import java.util.List;

import edu.advancedjava.model.StockQuote;

/**
 * This interface describes a simple API for getting stock data.
 */
public interface StockService {

	/**
	 * Return the <CODE>StockData</CODE> for the given symbol for the given date.
	 * Used to get history data for the stock.
	 *
	 * @param symbol the stock symbol of the company you want a quote for. e.g. APPL
	 *               for APPLE
	 *
	 * @return a StockQuote instance
	 */
	StockQuote getQuote(String symbol) throws StockServiceException;

	/**
	 * 
	 * Get a historical list of stock quotes for the provided symbol
	 * 
	 * @param symbol the stock symbol to search for
	 * @param from   the date of the first stock quote
	 * @param until  the date of the last stock quote
	 * @return a list of StockQuote instances. One for each day in the range
	 *         specified.
	 */
	List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException;

	/**
	 * Get a historical list of stock quotes for the provided symbol This method
	 * will return an StockQuote per interval specified
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

