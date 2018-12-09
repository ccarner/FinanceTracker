package ui;
import java.util.Arrays;
import java.util.Scanner;

import domain.DataEntry;
import domain.FinanceTracker;

/**
 * this class replicates a 'UI' but in the cmd shell
 * @author ccarn
 *
 */
public class CmdUi extends Ui {
	
	private Scanner sc = new Scanner(System.in);
	// for delimiting UI list elements when converting to string for the UI, note used as a regex so can't use regexp chars
	private String delimiter = "@DELIM@";
	
	// start the UI
	public void run() {
		boolean entryComplete = false;
		String response = "";
		
		System.out.println("Please Enter a Transaction");
		
		System.out.print("Date (YYYY-MM-DD): ");
		String date = nToNull(sc.next());
		System.out.println();
		
		System.out.print("All tags currently available are: ");
		System.out.println(Arrays.toString(FinanceTracker.getAllPurchaseTypes(delimiter).split(delimiter) ));
		String type = nToNull(sc.next());
		System.out.println();
		
		System.out.print("Location/Company: ");
		String loc = nToNull(sc.next());
		System.out.println();
		
		System.out.print("Items/notes: ");
		String items = nToNull(sc.next());
		System.out.println();
		
		System.out.print("Receipt Cost (xxx.yy): ");
		String receiptCost = nToNull(sc.next());
		System.out.println();
		
		System.out.print("Actual Cost (xxx.yy / 'n' if same as Receipt Cost): ");
		String actualCost = nToNull(sc.next());;
		System.out.println();
						
		System.out.print("Receipt kept (y/n): ");
		String receiptKept = nToNull(sc.next());
		System.out.println();
		
		System.out.print("Notes/Warranty info: ");
		String notes = nToNull(sc.next());
		System.out.println();
		
		System.out.print("All tags currently available are: ");
		System.out.println(Arrays.toString(FinanceTracker.getAllTags(delimiter).split(delimiter) ));
		boolean inputtingTags = true;
		int tagCount = 0;
		
		// consume any next line
		response = sc.nextLine();
		
		String tags = "";
		while (inputtingTags) {
			System.out.print("Tag #" + (++tagCount));
			response = sc.nextLine();
			if (response.equals("n")) {
				inputtingTags = false;
			} else {
				tags += (response + delimiter);
			}
		}
		
		
				
		System.out.println("Your transaction is " +
							date + " " + type + " " + loc + " "
							+ items + " " + receiptCost + " "
							+ actualCost + " "+ receiptKept
							+ " " + notes + " " + Arrays.toString(tags.split(delimiter))
							);
		
		while (!entryComplete) {
			System.out.print("Would you like to enter into the database? (y/n): ");
			response = sc.next();
			if (response.equals("y")){
				entryComplete = true;
				DataEntry.enterNewExpenditure(date, type, loc, items, receiptCost, actualCost, receiptKept, notes, tags, delimiter);
				System.out.println("Entered into database");
			} else if (response.equals("n")) {
				System.out.println("Entry discarded");
				entryComplete = true;
			} else  {
				System.out.println("please type y/n");
			}
		}
		
		
	}
	
	/*
	 * convert any string "n" to "NULL"
	 */
	private String nToNull (String string) {
		string = string.equals("n") ? "NULL": string;
		return string;
	}
		
}
