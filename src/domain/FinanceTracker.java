package domain;
import java.sql.*;

import DB.JdbcConnectionFactory;

public class FinanceTracker {

	/**much of the DB code from https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-statements.html
	 * more stuff on how to USE JBMC from http://www.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		            System.out.println(id + ":" + name + ", " + pw + "," + phone);
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
		
	}
	
	
}
