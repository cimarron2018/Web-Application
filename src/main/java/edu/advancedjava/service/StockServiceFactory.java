package edu.advancedjava.service;

/**
 * A StockService factory class that returns a concrete implementation of the
 * interface
 * 
 *
 */


public class StockServiceFactory {

	public static enum ServiceType {
		DB, Yahoo
	}


	/**
	 * Constructor is private
	 */
	private StockServiceFactory() {

	}

	/**
	 * Returns an implementation of the StockService interface
	 * 
	 * @return a StockService implementation
	 */
	public static StockService getSockService(ServiceType type) {

		StockService implementationStockService = null;

		switch (type) {
		case DB:
			implementationStockService = new DatabaseStockService();
			break;
		case Yahoo:
			implementationStockService = new YahooStockService();
			break;
		}
		
		return implementationStockService;

	}
}
