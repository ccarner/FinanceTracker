package domain;
import java.util.ArrayList;

public class FinanceTracker {

	public static Ui ui;
	/**much of the DB code from https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-statements.html
	 * more stuff on how to USE JBMC from http://www.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html
	 * @param args
	 */
	public static void main(String[] args) {

		//To-do: set up as a starting preference (eg which UI type to use);
		ui = new CmdUi();
		ui.run();
		
	}
	
	
	
	/** Gets an arraylist of all tags in the sql database
	 */
	public static String getAllPurchaseTypes(String delimiter){
		return getAllSqlRows("purchaseTypes","type",delimiter);
	}
	
	/** Gets an arraylist of all transaction types in the sql database
	 */
	public static String getAllTags(String delimiter) {
		return getAllSqlRows("tags","tag", delimiter);
	}
	
	/**
	 * gets a delimited string containing all rows in a table for use with UI
	 */
	private static String getAllSqlRows(String table, String column, String delimiter) {
		String rowsString = "";
		ArrayList<String> rows = DataEntry.getAllSqlRows(table, column);
		for (String row : rows) {
			rowsString += row;
			rowsString += delimiter;
		}
		return rowsString;
	}
	
}
