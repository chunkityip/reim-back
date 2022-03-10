package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	/*
	 * Make the factory to singleton so nobody can get connection outside from our
	 * factory The reason of making private static reference is the connections will
	 * become one and only copy
	 */

	// cf = connection Connection Function
	private static DBUtil cf = new DBUtil(1);

	// Giving the single point to access the Connection Function
	public static DBUtil getConnectionFactory() {
		return cf;
	}

	/*
	 * It is our connection pool that will hold all of our connections , and
	 * implements a single connection.
	 */
	private Connection[] connection;

	/*
	 * Only this single param can configure. If the singleton pattern is used , all
	 * constructors must be private , else make a new instances and no longer be a
	 * singleton pattern.
	 */
	private DBUtil(int numbersOfConnection) {

		// step 1
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// step 2
		String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/ers";
        String userName = "postgres";
        String password = "0917";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		this.connection = new Connection[numbersOfConnection];

		// singleton pattern
		try {
			for (int i = 0; i < this.connection.length; i++) {
				Connection connection = DriverManager.getConnection(url, userName, password);
				this.connection[i] = connection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// It should be multiple threading , but no need for project 0
	public Connection getConnection() {
		return this.connection[0];
	}

	public void releaseConnection(Connection connection) throws SQLException {
	}

}
