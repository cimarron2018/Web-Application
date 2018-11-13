package edu.advancedjava.model;

import org.apache.http.annotation.Immutable;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This a simple container class.
 */
@Immutable
public final class StockQuote implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String stockSymbol;
	private final BigDecimal stockPrice;
	private final Date dateRecorded;

	/**
	 * Class constructor specifying the stockSymbol, stockPrice, and the
	 * dateRecorded.
	 * 
	 * @param stockSymbolValue  a unique series of letters representing the stock
	 *                          for trading purposes
	 * @param stockPriceValue   the price of the stock
	 * @param dateRecordedValue as-of date
	 */
	public StockQuote(@NotNull String stockSymbolValue, @NotNull BigDecimal stockPriceValue, @NotNull Date dateRecordedValue) {
		super();
		this.stockSymbol = stockSymbolValue;
		this.stockPrice = stockPriceValue;
		this.dateRecorded = dateRecordedValue;
	}

	/**
	 * 
	 * @return the stockSymbol (a unique series of letters representing the stock
	 *         for trading purposes)
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @return the stockPrice (returns the price of the stock as of dateRecorded)
	 */
	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	/**
	 * @return the dateRecorded (returns the date the price was pulled)
	 */
	public Date getDateRecorded() {
		return dateRecorded;
	}

	/**
	 * Returns all the attributes of the StockQuote object in a string: symbol,
	 * stockPrice, dateRecorded
	 * 
	 * @return String including symbol, stockPrice, and dateRecorded
	 */
	@Override
	public String toString() {

		return "Symbol: " + stockSymbol + " Date: " + new SimpleDateFormat("MM-dd-yyyy").format(dateRecorded)
				+ " Price: " + NumberFormat.getCurrencyInstance().format(stockPrice);
	}

}

