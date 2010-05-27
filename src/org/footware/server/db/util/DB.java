package org.footware.server.db.util;

import java.sql.*;

/**
 * Helper Class to access the DB in a transparent fashion.
 */
public final class DB {
	/** Database file to use */ 
	private final String db = "./footware";
	/** DB User which will connect */
	private final String user = "sa";
	/** DB Password for given user */
	private final String pw = "";
	
	/** lock object so that a DB is not set up twice */
	private static final Object lock = new Object();
	/** Singleton instance of the helper class */
	private static DB inst = null;
	/** Connection used throughout the helper class instance */
	private Connection conn = null;
	
	/** Class disposer responsible to disconnect the DB and free the singleton instance */
	private void dispose() {
		try {
			if (conn == null || conn.isClosed())
				return;
			conn.close();
		}
		catch (Exception e) {}
		finally {
			DB.inst = null;
		}
	}
	
	/**
	 * Method responsible for setting up the initial connection
	 * @throws Exception Exception may be thrown upon connection errors
	 */
	private void init() throws Exception {
		//this.conn = getServletContext().getAttribute("connection");

        Class.forName("org.h2.Driver");
        this.conn = DriverManager.
            getConnection("jdbc:h2:"+ this.db, this.user, this.pw);
	}
	
	/**
	 * Static method returning the connection used throughout the singleton class
	 * @return JDBC Connection
	 * @throws Exception will throw up on initialization errors
	 */
	public static Connection getConnection() throws Exception {
		if (DB.inst == null || DB.inst.conn == null) {
			synchronized (DB.lock) {
				if (DB.inst == null)
					DB.inst = new DB();
				if (DB.inst.conn == null) {
					inst.init();
				}
			}
		}
		return inst.conn;
	}
	
	/**
	 * Close the JDBC connection
	 */
	public static void close() {
		if (DB.inst != null)
			inst.dispose();
	}
	
	/**
	 * Will connect to the DB if not done already and
	 * create a JDBC statement on the DB connection
	 * @return a new Statement
	 * @throws Exception throws up in case of errors
	 */
	public static Statement createStatement() throws Exception {
		return DB.getConnection().createStatement();
	}
	
	/**
	 * Executes a native SQL statement.
	 * @param qry Native SQL Query to be executed
	 * @throws Exception throws up in case of errors
	 */
	public static void execute(String qry) throws Exception {
		System.out.println(qry);
		Statement stmt = DB.getConnection().createStatement();
		stmt.execute(qry);
		stmt.close();
	}
	
	/**
	 * Query the DB
	 * @param qry native SQL query string
	 * @return ResultSet for given query. Make sure to free it when not used any more.
	 * @throws Exception throws up in case of errors.
	 */
	public static ResultSet query(String qry) throws Exception {
		System.out.println(qry);
		Statement stmt = DB.getConnection().createStatement();
		return stmt.executeQuery(qry);
	}

	/**
	 * Query a single String
	 * @param qry Query
	 * @return DB result
	 * @throws Exception throws up in case of errors.
	 */
	public static String queryString(String qry) throws Exception {
		System.out.println(qry);
		ResultSet res = DB.query(qry);
		if (res.first()) {
			String str = res.getString(1);
			DB.freeResult(res);
			return str;
		} else {
			throw new Exception("Empty result");
		}
	}

	/**
	 * Query a single integer
	 * @param qry Query
	 * @return DB result
	 * @throws Exception throws up in case of errors.
	 */
	public static int queryInt(String qry) throws Exception {
		System.out.println(qry);
		ResultSet res = DB.query(qry);
		if (res.first()) {
			int i = res.getInt(1);
			DB.freeResult(res);
			return i;
		} else {
			throw new Exception("Empty result");
		}
	}

	/**
	 * Query a single double value
	 * @param qry Query
	 * @return DB result
	 * @throws Exception throws up in case of errors.
	 */
	public static double queryDbl(String qry) throws Exception {
		System.out.println(qry);
		ResultSet res = DB.query(qry);
		if (res.first()) {
			double d = res.getDouble(1);
			DB.freeResult(res);
			return d;
		} else {
			throw new Exception("Empty result");
		}
	}

	public static Timestamp queryTimestamp(String qry) throws Exception {
		System.out.println(qry);
		ResultSet res = DB.query(qry);
		if (res.first()) {
			Timestamp t = res.getTimestamp(1);
			DB.freeResult(res);
			return t;
		} else {
			throw new Exception("Empty result");
		}
	}
	
	/**
	 * Free a ResultSet and its associated Statement
	 * @param r ResultSet that is to be freed
	 * @throws Exception throws up in case of errors.
	 */
	public static void freeResult(ResultSet r) throws Exception {
		if (r == null)
			return;
		
		r.close();
	}
	
	/**
	 * Update the database with a native SQL command
	 * @param cmd Command to be executed
	 * @return The number of affected rows
	 * @throws Exception throws up in case of errors
	 */
	public static int update(String cmd) throws Exception {
		System.out.println(cmd);
		Statement stmt = DB.getConnection().createStatement();
		int res = stmt.executeUpdate(cmd);
		stmt.close();
		return res;
	}
	
	/**
	 * Insert a new row into the database with a native SQL command
	 * @param cmd Insert command to be executed
	 * @return The id of the newly created row
	 * @throws Exception throws up in case of errors
	 */
	public static int insert(String cmd) throws Exception {
		System.out.println(cmd);
		Statement stmt = DB.getConnection().createStatement();
		int res = stmt.executeUpdate(cmd, 1);
		if (res != 1) {
			stmt.close();
			return -1;
		}
		ResultSet rs = stmt.getGeneratedKeys();
		if (!rs.first()) {
			stmt.close();
			return -1;
		}
		res = rs.getInt(1);
		stmt.close();
		return res;
	}
}
