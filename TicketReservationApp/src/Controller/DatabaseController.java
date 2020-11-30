package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import Model.TheatreModel.Ticket;

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
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getTheatreList(String movieName) {

		try {
			String query = "SELECT * FROM HOSTED_BY AS H WHERE H.MovieName = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			resultSet = stmt.executeQuery();
			resultSet.next();
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
			stmt.setString(2, theatreName);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getSeatList(String movieName, String theatreName, int showTimeID) {
		try {
			String query = "SELECT  T.SeatNumber, T.IsSeatReserved FROM TICKET AS T WHERE T.MovieName = ? AND T.TheatreName = ? AND T.ShowTimeID = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(2, theatreName);
			stmt.setInt(3, showTimeID);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				return resultSet;
			}
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// Dont really need this????????

	// public ResultSet searchTicket(String movieName, String theatreName, int
	// ShowTimeID, int SeatNumber) {
	//
	// try {
	// String query = "SELECT * FROM TICKET AS T WHERE T.MovieName = ? AND
	// T.TheatreName = ? AND T.ShowTimeID = ? ";
	// stmt = conn.prepareStatement(query);
	// stmt.setString(1, movieName);
	// stmt.setString(2, theatreName);
	// resultSet = stmt.executeQuery();
	// resultSet.next();
	// return resultSet;
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	//
	// }

	public ResultSet setVoucher(int voucherID, double voucherValue, String voucherExp, boolean voucherActive) {

		try {
			String query = "INSERT INTO VOUCHER (VoucherID, VoucherValue, VoucherExpiraryDate, VoucherActive) VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, voucherID);
			stmt.setDouble(2, voucherValue);
			stmt.setString(3, voucherExp);
			stmt.setBoolean(4, voucherActive);
			stmt.executeUpdate();
			resultSet = this.getVoucher(voucherID);
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
			resultSet.next();
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
			resultSet.next();
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
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getReceipt(int ticketID) {

		try {
			// String query = "SELECT * FROM RECEIPT AS R, VOUCHER AS V, WHERE R.ReceiptID =
			// ? AND V.VoucherID = R.VoucherID";
			String query = "SELECT * FROM RECEIPT WHERE TicketID = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ticketID);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void insertReceipt(int receiptID, String receiptType, int ticketID, int creditCardNumber, int voucherID,
			double price) {
		try {
			String query = "INSERT INTO RECEIPT (ReceiptID, ReceiptType, TicketID, CreditCardNumber, VoucherID, Price  ) VALUES (?, ? , ?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, receiptID);
			stmt.setString(2, receiptType);
			stmt.setInt(3, ticketID);
			stmt.setInt(4, creditCardNumber);
			stmt.setInt(5, voucherID);
			stmt.setDouble(6, price);
			stmt.executeUpdate();

			if (receiptType.equals("Ticket")) {
				buyTicket(ticketID);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void buyTicket(int ticketID) {
		try {
			String query = "UPDATE TICKET SET Paid=TRUE, IsSeatReserved=TRUE WHERE TicketID=?;";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ticketID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet deleteReceipt(int ticketID) {

		try {
			// String query = "SELECT * FROM RECEIPT AS R, VOUCHER AS V, WHERE R.ReceiptID =
			// ? AND V.VoucherID = R.VoucherID";
			String query = "DELETE FROM RECEIPT WHERE TicketID = ?"; // cascade delete voucher i think?

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ticketID);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void payAnnualDues(String userName) {
		try {
			String query = "UPDATE REGISTERED_USERS SET isMembershipPaid = true WHERE Username=?;";
			stmt.setString(1, userName);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getTicket(int ticketID) {
		try {
			// String query = "SELECT * FROM RECEIPT AS R, VOUCHER AS V, WHERE R.ReceiptID =
			// ? AND V.VoucherID = R.VoucherID";
			String query = "SELECT * FROM TICKET AS T, SHOWTIME AS SH, THEATRE AS TH, MOVIE AS M, SEATS AS S WHERE SH.ShowTimeID = T.ShowTimeID AND  TH.TheatreName =  T.TheatreName AND "
					+ " M.MovieName = T.MovieName AND S.SeatNumber = T.SeatNumber AND T.TicketID = ?"; // cascade delete
																										// voucher i
																										// think?

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ticketID);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet resetTicket(int ticketID) {
		try {
			String query = "UPDATE TICKET SET IsSeatReserved=FALSE, Paid=FALSE WHERE TicketID = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ticketID);
			stmt.executeUpdate();
			resultSet = getTicket(ticketID);
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getRegisteredUser(String userName) {

		try {
			// Readjust here to get your credit info as well.
			String query = "SELECT * FROM REGISTERED_USERS WHERE Username = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				query = "SELECT * FROM REGISTERED_USERS AS R , CREDIT_INFORMATION AS C WHERE R.Username = ? AND R.CreditCardNumber = C.CreditCardNumber";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, userName);
				resultSet = stmt.executeQuery();
				resultSet.next();
				return resultSet;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet setRegisteredUser(boolean isMemberPaid, String userType, String username, String userPassword,
			String name, String address, String email, int creditCardNumber) {

		try {
			String query = "INSERT INTO CREDIT_INFORMATION (CreditCardNumber, CreditCardType) VALUES (?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, creditCardNumber);
			stmt.setString(2, userType);
			stmt.executeUpdate();
			query = "INSERT INTO REGISTERED_USERS (isMembershipPaid, UserType, Username, UserPassword, Name, Email, CreditCardNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setBoolean(1, isMemberPaid);
			stmt.setString(2, userType);
			stmt.setString(3, username);
			stmt.setString(4, userPassword);
			stmt.setString(5, name);
			stmt.setString(6, email);
			stmt.setInt(7, creditCardNumber);
			stmt.executeUpdate();
			resultSet = getRegisteredUser(username);
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public ResultSet registerMyUser(boolean isMemberPaid, String userType, String username, String userPassword,
			String name, String address, String email, int creditCardNumber, String creditCardType) {

		this.setRegisteredUser(isMemberPaid, userType, username, userPassword, name, address, email, creditCardNumber);
		this.setCreditInformation(creditCardNumber, creditCardType);
		resultSet = this.getRegisteredUser(username);
		return resultSet;

	}

	public ResultSet getCreditInformation(int creditCardNumber) {

		try {
			String query = "SELECT * FROM CREDIT_INFORMATION WHERE CreditCardNumber = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, creditCardNumber);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public ResultSet setCreditInformation(int creditCardNumber, String creditCardType) { // check up with mike to see
																							// where checking should
																							// happen.
		;
		try {
			String query = "INSERT INTO CREDIT_INFORMATION (CreditCardNumber, CreditCardType) VALUES (?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, creditCardNumber);
			stmt.setString(2, creditCardType);
			stmt.executeUpdate();
			resultSet = getCreditInformation(creditCardNumber);
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

	public boolean isValidVoucher(int cardNum) {
		try {
			String query = "SELECT * FROM VOUCHER WHERE VoucherID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, cardNum);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				query = "UPDATE VOUCHER SET VoucherActive=false WHERE VoucherId =?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, cardNum);
				stmt.executeUpdate();
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean reservationsRemaining(String movieName, String theatreName, int showTimeID, int seatNumber) {
		try {
			String query = "SELECT * FROM TICKET WHERE MovieName=? AND TheatreName=? AND ShowTimeID=? AND SeatNumber=?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(2, theatreName);
			stmt.setInt(3, showTimeID);
			stmt.setInt(4, seatNumber);
			resultSet = stmt.executeQuery();
			if (!resultSet.next()) {
				// something wrong in query
				return false;
			} else {
				double unReserved = 0;
				double reserved = 0;
				do {
					if (resultSet.getBoolean("IsSeatReserved")) {
						reserved++;
					} else {
						unReserved++;
					}
				} while (resultSet.next());

				if (unReserved / (unReserved + reserved) >= 0.1) {
					return false;
				} else {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getTicketId(JSONObject ticket) {
		try {
			String movieName = ticket.getString("movieName");
			String theatreName = ticket.getString("theatreName");
			int showTimeId = ticket.getInt("showTimeId");
			int seatNumber = ticket.getInt("seatNumber");
			String query = "SELECT TicketID FROM TICKET WHERE MovieName=? AND TheatreName=? AND ShowTimeID=? AND SeatNumber=?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, movieName);
			stmt.setString(2, theatreName);
			stmt.setInt(3, showTimeId);
			stmt.setInt(4, seatNumber);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("TicketID");
			} else {
				return -10;
			}
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
}