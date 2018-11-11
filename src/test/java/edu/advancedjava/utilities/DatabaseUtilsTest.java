package edu.advancedjava.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.advancedjava.model.StockQuote;
import edu.advancedjava.service.StockService;
import edu.advancedjava.service.StockServiceException;

/**
 * 
 * JUnit tests for DatabaseUtils class
 * 
 */
public class DatabaseUtilsTest {

	private StockService stockServiceImplementation = null;
	private Calendar fromDate = Calendar.getInstance();
	private Calendar toDate = Calendar.getInstance();
	private String symbol = "AAPL";
	private List<StockQuote> testStockQuotesList = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Test to verify that DatabaseUtils returns a connection
	 */
	@Test
	public void testGetConnection() {
		Connection testConnection = null;
		try {
			testConnection = DatabaseUtils.getConnection();
		} catch (DatabaseConnectionException e) {
			fail("Not able to obtain database connection. " + e.getMessage());
		}
		assertTrue("DatabaseUtils did not return a database connection", testConnection != null);
	}

	/**
	 * Test to verify that DatabaseUtils DB initialization method works
	 */
	@Test
	public void testInitializeDatabase() {
		try {
			DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
		} catch (DatabaseInitializationException e) {
			fail("Not able to initialize database. " + e.getMessage());
		}
		int numberOfRows = 0;
		try {
			Connection connection = DatabaseUtils.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select * from quotes";
			ResultSet resultSet = statement.executeQuery(queryString);
			numberOfRows = resultSet.next() ? 1 : 0;
			System.out.println("number of rows: " + numberOfRows);


		} catch (DatabaseConnectionException | SQLException exception) {
			fail("Failed to verify database initialization completed. " + exception.getMessage());
		}
		assertTrue("DatabaseUtils database initialization method did not complete succesfully.", numberOfRows > 0);
	}

	/**
	 * Verifies that if the wrong script location is pass to the
	 * DatabaseUtils.initializeDatabase method is not correct it will throw an
	 * exception
	 * 
	 * @throws DatabaseInitializationException
	 */
	@Test(expected = DatabaseInitializationException.class)
	public void testDatabaseInitializationException() throws DatabaseInitializationException {
		DatabaseUtils.initializeDatabase("");
	}

}
