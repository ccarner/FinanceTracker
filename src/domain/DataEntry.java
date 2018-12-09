package domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.JdbcConnectionFactory;

/**
 * class contains utility methods for new entries into database
 * @author ccarn
 *
 */
public class DataEntry {
	
	private static String toSqlString (String string) {
		if (string != "NULL" && string != "Null") {
			return "'" + string + "'";
		} else {
			return "NULL";
		}
	}
	/**
	 * inputs information about new expenditure into the database
	 * parts of function (eg the parts about getting autoincremented keys was from here:
	 * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-last-insert-id.html
	 * @param date
	 * @param location
	 * @param items
	 * @param receiptCost
	 * @param actualCost
	 * @param receiptKept
	 * @param notes
	 * @param tags
	 * @param delimiter delimiter used for separating lists (eg in tags list)
	 * @return
	 */
	public static boolean enterNewExpenditure(String date, String purchaseType, String location, String items, String receiptCost, String actualCost, String receiptKept, String notes, String tags, String delimiter) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection con = JdbcConnectionFactory.getConnection();
			stmt = con.createStatement();
			Boolean receiptKeptBool = receiptKept.equals("y")? true: false;
			String sqlStmt = "INSERT INTO purchases(purchaseDate, purchaseType, purchaseLocation, purchaseItems, receiptKept, receiptCost, actualCost, notesAndWarranty) VALUES ("
							+ toSqlString(date) + "," + toSqlString(purchaseType) + "," + toSqlString(location) + "," + toSqlString(items) + ","
							+ toSqlString(receiptCost) + "," + toSqlString(actualCost) + "," + receiptKeptBool.toString() 
							+ "," + toSqlString(notes) 
							+ ");";
			
			// the constant Statement.RETURN_GENERATED_KEYS tells the jdbmc that the autoincremented key must be saved
			stmt.executeUpdate(sqlStmt, Statement.RETURN_GENERATED_KEYS);
			
			int autoIncKeyFromApi = -1;
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
		        autoIncKeyFromApi = rs.getInt(1);
		    } else {

		        // throw an exception from here
		    }
			// so now we've inserted the info into the purchases table, need to 
			// link up in the purchaseTags relational-entity table
			
			String[] tagArr = tags.split(delimiter);
			
			for (String tag: tagArr) {
				sqlStmt = " INSERT INTO purchasetags(tag, purchaseId) VALUES ('" 
						+ tag + "'," + autoIncKeyFromApi + ");";
				stmt.executeUpdate(sqlStmt);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	/** Gets an arraylist of all of a single column from a table
	 */
	public static ArrayList<String> getAllSqlRows(String table, String column) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> rows = new ArrayList<>();
		
		try {
			Connection con = JdbcConnectionFactory.getConnection();
			stmt = con.createStatement();
			
			if (stmt.execute("SELECT " + column + " FROM " + table)) {
		        rs = stmt.getResultSet();
		        
		         //Process the ResultSet by scrolling the cursor forward via next().
		         //  For each row, retrieve the contents of the cells with getXxx(columnName).
		         int rowCount = 0;
		         while(rs.next()) {   // Move the cursor to the next row, return false if no more row
		            String row = rs.getString(column);
		            rows.add(row);
		            ++rowCount;
		         }
		         System.out.println("Total number of tags = " + rowCount);
		    }			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/*
	 //example of how to use SQL

	 Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Connection sqlConnector = JdbcConnectionFactory.getConnection();
			stmt = sqlConnector.createStatement();
			
			if (stmt.execute("SELECT * FROM users")) {
		        rs = stmt.getResultSet();
		        
		        //Process the ResultSet by scrolling the cursor forward via next().
		         //  For each row, retrieve the contents of the cells with getXxx(columnName).
		         System.out.println("The records selected are:");
		         int rowCount = 0;
		         while(rs.next()) {   // Move the cursor to the next row, return false if no more row
		            int id = rs.getInt("ID");
		            String name = rs.getString("name");
		            String pw = rs.getString("password");
		            String phone = rs.getString("phoneNumber");
		            System.out.println(id + ": " + name + ", " + pw + ", " + phone);
		            ++rowCount;
		         }
		         System.out.println("Total number of records = " + rowCount);
		         
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore		        
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
	 */
}
