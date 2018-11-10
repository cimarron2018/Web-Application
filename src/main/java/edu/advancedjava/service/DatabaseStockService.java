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
	 * Return the current price for a share of stock for the given symbol
	 *
	 * @param symbol the stock symbol of the company you want a quote for. e.g. APPL
	 *               for APPLE
	 * @return a <CODE>BigDecimal</CODE> instance
	 * @throws StockServiceException if using the service generates an exception. If
	 *                               this happens, trying the service may work,
	 *                               depending on the actual cause of the error.
	 */
	@Override
	public StockQuote getQuote(String symbol) throws StockServiceException {
		// todo - this is a pretty lame implementation why?
		List<StockQuote> stockQuotes = null;
		try {
			Connection connection = DatabaseUtils.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select * from quotes where symbol = '" + symbol + "' order by time desc";

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
		return stockQuotes.get(0);
	}

	/**
	 * Get a historical list of stock quotes for the provide symbol
	 *
	 * @param symbol the stock symbol to search for
	 * @param from   the date of the first stock quote
	 * @param until  the date of the last stock quote
	 * @return a list of StockQuote instances
	 * @throws StockServiceException if using the service generates an exception. If
	 *                               this happens, trying the service may work,
	 *                               depending on the actual cause of the error.
	 */
	@Override
	public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException {
		List<StockQuote> stockQuotes = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Connection connection = DatabaseUtils.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select * from quotes where symbol = '" + symbol + "'" + " and time between '"
					+ dateFormat.format(from.getTime()) + " 00:00:00' and '" + dateFormat.format(until.getTime()) + " 23:59:59'";

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
		return stockQuotes;
	}

	@Override
	public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) {
		// TODO Auto-generated method stub
		return null;
	}
}
