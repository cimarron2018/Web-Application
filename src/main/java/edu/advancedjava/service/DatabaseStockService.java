package edu.advancedjava.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import edu.advancedjava.model.StockQuote;
import edu.advancedjava.utilities.DatabaseConnectionException;
import edu.advancedjava.utilities.DatabaseUtils;

/**
 * An implementation of the StockService interface that gets stock data from a
 * database.
 */
public class DatabaseStockService implements StockService {

	/**
	 * Get a historical list of stock quotes from database
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

		List<StockQuote> stockQuotes = null;
		List<StockQuote> returnStockQuotes = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Connection connection = DatabaseUtils.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select * from quotes where symbol = '" + symbol + "'" + " and time between '"
					+ dateFormat.format(from.getTime()) + " 00:00:00' and '" + dateFormat.format(until.getTime())
					+ " 23:59:59'";

			ResultSet resultSet = statement.executeQuery(queryString);
			stockQuotes = new ArrayList<>(resultSet.getFetchSize());
			while (resultSet.next()) {
				String symbolValue = resultSet.getString("symbol");
				Date time = resultSet.getDate("time");
				BigDecimal price = resultSet.getBigDecimal("price");
				stockQuotes.add(new StockQuote(symbolValue, price, time));
			}

		} catch (DatabaseConnectionException | SQLException exception) {
			throw new StockServiceException(exception.getMessage(), exception);
		}

		if (stockQuotes.isEmpty()) {
			throw new StockServiceException("There is no stock data for:" + symbol);
		}

		returnStockQuotes = getStockQuoteInterval(stockQuotes, interval);

		return returnStockQuotes;
	}

	/**
	 * 
	 * Returns a list of Stock Quotes separated by an interval.
	 * 
	 * @param stockQuotes list of StockQuote instances
	 * @param interval    he number of StockQuotes to get, E.g. if Interval.DAILY
	 *                    was specified one StockQuote per day will be returned.
	 * @return a list of StockQuote instances. One for each day in the range
	 *         specified.
	 */
	private List<StockQuote> getStockQuoteInterval(List<StockQuote> stockQuotes, IntervalEnum interval) {

		List<StockQuote> finalStockQuotes = new ArrayList<>();

		Calendar c = Calendar.getInstance();
		c.setTime(stockQuotes.get(0).getDateRecorded());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		int i = 0;
		for (StockQuote quote : stockQuotes) {
			c.setTime(quote.getDateRecorded());
			if (interval == IntervalEnum.WEEKLY) {
				if (c.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
					finalStockQuotes.add(i, quote);
					i++;
				}
			} else if (interval == IntervalEnum.MONTHLY) {
				if (c.get(Calendar.DAY_OF_MONTH) == 1) {
					finalStockQuotes.add(i, quote);
					i++;
				}
			} else {
				finalStockQuotes.add(i, quote);
				i++;

			}
		}
		return finalStockQuotes;
	}
}
