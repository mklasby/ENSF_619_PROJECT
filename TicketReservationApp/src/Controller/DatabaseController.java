package Controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController implements Password {
	
	/**
	 * Object of type Connection, the jdbc connection used to make PreparedStatements
	 */
	private Connection jdbc_connection;//Object of type connection from the JDBC class that deals with connecting to 

	/**
	 * Object of type PreparedStatement from JDBC class that enables the creation of Prepared Query Statements
	 */
	private PreparedStatement pStat;
	
	/**
	 * Object of type ResultSet from the JDBC class that stores the result of the query
	 */
	private ResultSet resultSet;//object of type ResultSet from the JDBC class that stores the result of the query
	
	/**
	 * Name of the database that stores all the tool shop information
	 */
	private String databaseName = "ticketRegistrationDatabase";
	//Unused Code
	//the database 
	//private Statement stmt; //object of type statement from JDBC class that enables the creation "Query 
	//statements"
	
	public DatabaseController() {
		initializeConnection();
	
	}
	
	public void initializeConnection() {
		try {
            //Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            //Open a connection
			jdbc_connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			useToolShopCommand();
			
			System.out.println("MySQLJDBC: Connection made with SQL Server");
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}

	}
	
	public void useToolShopCommand() {
		
		try {
			String query = "USE "+databaseName;
			pStat = jdbc_connection.prepareStatement(query);
			pStat.executeUpdate();
			System.out.println("Using " + databaseName + " SQL database");
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
			//stmt.close();
			//conn.close();
			//rs.close();
			pStat.close();
			jdbc_connection.close();
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getMoviesList() {
		
		try {
			String query = "SELECT * FROM MOVIE ORDER BY MovieName";
			pStat = jdbc_connection.prepareStatement(query);
			resultSet = pStat.executeQuery();
			
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, movieName);
			resultSet = pStat.executeQuery();
			
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, movieName);
			pStat.setString(1, theatreName);
			resultSet = pStat.executeQuery();
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, movieName);
			pStat.setString(1, theatreName);
			resultSet = pStat.executeQuery();
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Dont really need this????????
	
	public ResultSet searchTicket(String movieName, String theatreName, int ShowTimeID, int SeatNumber) {
		
		try {
			String query = "SELECT * FROM TICKET AS T WHERE T.MovieName = ? AND T.TheatreName = ? AND T.ShowTimeID = ? ";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, movieName);
			pStat.setString(1, theatreName);
			resultSet = pStat.executeQuery();
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, userName);
			resultSet = pStat.executeQuery();
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, userName);
			resultSet = pStat.executeQuery();
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, voucherID);
			resultSet = pStat.executeQuery();
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
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, voucherID);
			resultSet = pStat.executeQuery();
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public ResultSet getReceipt(int receiptID) {
		
		try {
			//String query = "SELECT * FROM RECEIPT AS R, VOUCHER AS V, WHERE R.ReceiptID = ? AND V.VoucherID = R.VoucherID";
			String query = "SELECT * FROM RECEIPT AS R WHERE ReceiptID = ?";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, receiptID);
			resultSet = pStat.executeQuery();
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public void main(String args) {
		
		DatabaseController dbCtrl = new DatabaseController();
		
		
	}
	
	
	
	
	
}
