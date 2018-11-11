package edu.advancedjava.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.advancedjava.model.StockQuote;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * An implementation of the StockService interface that gets stock information
 * through the Yahoo Finance API.
 */
public class YahooStockService implements StockService {

	/**
	 * Get a historical list of stock quotes from Yahoo Financial API.
	 *
	 * @param symbol   the stock symbol to search for
	 * @param from     the date of the first stock quote
	 * @param until    the date of the last stock quote
	 * @param interval the number of StockQuotes to get, E.g. if Interval.DAILY was
	 *                 specified one StockQuote per day will be returned.
	 * @return a list of StockQuote instances. One for each day in the range
	 *         specified.
	 */
	@Override
	public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval)
			throws StockServiceException {
		Interval yahooInterval = null;
		switch (interval) {
		case WEEKLY:
			yahooInterval = Interval.WEEKLY;
			break;
		case MONTHLY:
			yahooInterval = Interval.MONTHLY;
			break;
		default:
			yahooInterval = Interval.DAILY;
		}
		List<StockQuote> stockQuoteList = new ArrayList<>();
		List<HistoricalQuote> historyQuote = null;
		try {
			historyQuote = YahooFinance.get(symbol, from, until, yahooInterval).getHistory();
		} catch (IOException exception) {
			throw new StockServiceException(exception.getMessage(), exception);
		}
		if (historyQuote.isEmpty()) {
			throw new StockServiceException("There is no stock data for:" + symbol);
		} else {
			for (HistoricalQuote stock : historyQuote) {
				stockQuoteList.add(new StockQuote(stock.getSymbol(), stock.getClose(), stock.getDate().getTime()));
			}
		}
		return stockQuoteList;
	}
}
