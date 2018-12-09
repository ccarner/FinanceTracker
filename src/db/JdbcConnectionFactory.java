package db;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
//Notice, do not import com.mysql.cj.jdbc.*
//or you will have problems!

/**
 * class is based on the documentation for the JDBC MySQL Connector/J page,
 * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html#connector-j-examples-connection-drivermanager
 * @author ccarn
 *
 */
public class JdbcConnectionFactory {

	private static Connection conn = null;
	private static Properties connectionProps;
	
	/**Function for getting a database connection
	 * Modified from https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		// make new connection, else recycle
		if (conn == null) {
			initializeProperties();
			// username and password are read directly from properties file by the Driver class
			String dbms = connectionProps.getProperty("dbms");
			String port = connectionProps.getProperty("port");
			String url = connectionProps.getProperty("url");
			String dbSchema = connectionProps.getProperty("dbSchema");
				   		
		    try {
		    	// first, initialise the driver (force it to register itself so java knows how to handle
				// the db connections strings
		    	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				
				// now to the connection
		    	    	
		    	conn = DriverManager.getConnection(
                   "jdbc:" + dbms + "://" +
                   url +
                   ":" + port + "/" + dbSchema,
                   connectionProps);
		 	   
		 	    
		 	    System.out.println("Connected to database");
		 	    
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		
	    return conn;
	}
	
	private static void initializeProperties() {
		connectionProps = new Properties();
		// Defaults
		connectionProps.put("user", "root");
	    connectionProps.put("password", "M@Colton96");
	    connectionProps.put("dbms", "mysql");
	    connectionProps.put("port","3306");
	    connectionProps.put("url","127.0.0.1");
	    connectionProps.put("dbSchema","financetracker");
	    
	    // now read in the properties file
	    try (FileReader inStream = new FileReader("assets/database.properties")) {
	    	connectionProps.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} 
	}
}
