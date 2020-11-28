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
	
	public ResultSet getTheatreList(String MovieName) {
		
		try {
			String query = "SELECT ThreatreName FROM HOSTED_BY WHERE MovieName = ?";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, MovieName);
			resultSet = pStat.executeQuery();
			
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public ResultSet getShowTimeList(String MovieName, String TheatreName) {
		
		try {
			String query = "SELECT * FROM SHOWTIME AS ST, SHOWINGS AS SW WHERE SW.MovieName = ? AND SW.TheatreName = ? AND SW.ShowTimeID = ST.ShowTimeID  ";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, MovieName);
			pStat.setString(1, TheatreName);
			resultSet = pStat.executeQuery();
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}
	
	public ResultSet getShowTimeList(String MovieName, String TheatreName, int ShowTimeID) {
		try {
			String query = "SELECT * FROM TICKET AS T WHERE T.MovieName = ? AND T.TheatreName = ? AND ShowTimeID = ?  ";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, MovieName);
			pStat.setString(1, TheatreName);
			resultSet = pStat.executeQuery();
			return resultSet;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	public ResultSet getToolList() {
		
		try {
			//String tableName  = "TOOL";
			//String query = "SELECT * FROM " + tableName + " WHERE ToolID IS NOT NULL";
			String query = "SELECT T.ToolID, T.Name AS 'ToolName', T.Type AS 'ToolType', T.Quantity, T.Price, T.SupplierID, "
					+ "S.Name as 'SupplierName', S.Type AS 'SupplierType', S.Address, S.Cname, S.PhoneNum, "
					+ "I.ImportTax, E.PowerType "
					+ "FROM SUPPLIER as S, TOOL as T "
					+ "LEFT JOIN INTERNATIONAL as I USING (SupplierID) "
					+ "LEFT JOIN ELECTRICAL as E USING (ToolID) "
					+ "WHERE T.SupplierID = S.SupplierID "
					+ "ORDER BY T.ToolID;";
			
			
			
			pStat = jdbc_connection.prepareStatement(query);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
