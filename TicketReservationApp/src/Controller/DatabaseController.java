package Controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController implements Password {

	/**
	 * Object of type Connection, the jdbc connection used to make
	 * PreparedStatements
	 */
	private Connection conn;// Object of type connection from the JDBC class that deals with connecting to

	/**
	 * Object of type PreparedStatement from JDBC class that enables the creation of
	 * Prepared Query Statements
	 */
	private PreparedStatement stmt;

	/**
	 * Object of type ResultSet from the JDBC class that stores the result of the
	 * query
	 */
	private ResultSet resultSet;// object of type ResultSet from the JDBC class that stores the result of the
								// query

	/**
	 * Name of the database that stores all the tool shop information
	 */
	private String databaseName = "ticketRegistrationDatabase";
	// Unused Code
	// the database
	// private Statement stmt; //object of type statement from JDBC class that
	// enables the creation "Query
	// statements"

	public DatabaseController() {
		initializeConnection();
	}

	public void initializeConnection() {
		try {
			// JDBC driver name and database URL
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			useTicketRegistrationDatabase();

			System.out.println("MySQLJDBC: Connection made with SQL Server");
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}

	}

	public void useTicketRegistrationDatabase() {
		try {
			String query = "USE ticketRegistrationDatabase;";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Closes the PreparedStatement, Connection and ResultSet objects
	 */
	public void close() {
		try {
			stmt.close();
			conn.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getMoviesList() {

		try {
			String query = "SELECT * FROM MOVIE ORDER BY MovieName";
			stmt = conn.prepareStatement(query);
			resultSet = stmt.executeQuery();

			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getTheatreList(String movieName) {

		try {
			String query = "SELECT TheatreName FROM HOSTED_BY AS H WHERE H.MovieName = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			resultSet = stmt.executeQuery();

			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getShowTimeList(String movieName, String theatreName) {

		try {
			String query = "SELECT  ST.ShowTimeID, ST.StartTime, ST.EndTime FROM SHOWTIME AS ST, SHOWINGS AS SW WHERE SW.MovieName = ? AND SW.TheatreName = ? AND SW.ShowTimeID = ST.ShowTimeID";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(1, theatreName);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getSeats(String movieName, String theatreName, int showTimeID) {
		try {
			String query = "SELECT  T.SeatNumber, T.isSeatReserved FROM TICKET AS T WHERE T.MovieName = ? AND T.TheatreName = ? AND T.ShowTimeID = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(1, theatreName);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// Dont really need this????????

	public ResultSet searchTicket(String movieName, String theatreName, int ShowTimeID, int SeatNumber) {

		try {
			String query = "SELECT * FROM TICKET AS T WHERE T.MovieName = ? AND T.TheatreName = ? AND T.ShowTimeID = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(1, theatreName);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getRegisteredUser(String userName) {

		try {
			String query = "SELECT * FROM REGISTERED_USERS WHERE Username = ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getVoucher(String userName) {

		try {
			String query = "SELECT * FROM REGISTERED_USERS WHERE Username = ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getVoucher(int voucherID) {

		try {
			String query = "SELECT * FROM Voucher WHERE VoucherID = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, voucherID);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet deactivateVoucher(int voucherID) {

		try {
			String query = "UPDATE VOUCHER SET isVoucherActive = 0 WHERE VoucherID = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, voucherID);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getReceipt(int receiptID) {

		try {
			// String query = "SELECT * FROM RECEIPT AS R, VOUCHER AS V, WHERE R.ReceiptID =
			// ? AND V.VoucherID = R.VoucherID";
			String query = "SELECT * FROM RECEIPT AS R WHERE ReceiptID = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, receiptID);
			resultSet = stmt.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		DatabaseController dbCtrl = new DatabaseController();
		System.out.print("Hello world");
		ResultSet test = dbCtrl.getMoviesList();
		dbCtrl.printResultSet(test);
	}

	private void printResultSet(ResultSet test) {
		try {
			test.next();
			do {
				System.out.println(test.getString("MovieName"));
			} while (test.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
