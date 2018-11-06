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
 * An implementation of the StockService interface that gets stock data from a
 * database.
 */
public class YahooStockService implements StockService {

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
        HistoricalQuote stock = null;
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        List<HistoricalQuote> historyQuote = null;
        try {
            historyQuote = YahooFinance.get(symbol, from, to, Interval.DAILY).getHistory();
        } catch (IOException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (historyQuote.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        } else {
            stock = historyQuote.get(0);
        }
        return new StockQuote(stock.getSymbol(), stock.getClose(), stock.getDate().getTime());
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
        List<StockQuote> stockQuoteList = new ArrayList<>();
        List<HistoricalQuote> historyQuote = null;
        try {
            historyQuote = YahooFinance.get(symbol, from, until, Interval.DAILY).getHistory();
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
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException {
        Interval yahooInterval = null;
        switch (interval){
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

