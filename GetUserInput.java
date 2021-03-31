import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Abstrcat: Gives a prompt with two options: 
 * 		1-View Customers and 
 * 		2-Create Modified Customers File. *  
 * 	The View Customers creates a listing of customers. The Create Modified Customers File generates an updated xml.
 * 
 * @author Devin Watters
 * @version 2020
 */
public class GetUserInput {
	
	/**
	 * Abstract: Reads input from the user
	 * 
	 * @return strBuffer - string entered by user
	 */
	public static String ReadStringFromUser() {
		String strBuffer = "";		
		try	{			
			// Input stream
			BufferedReader burInput = new BufferedReader(new InputStreamReader(System.in));
			// Read a line from the user
			strBuffer = burInput.readLine();
		} catch(Exception excError){System.out.println(excError.toString());}
		// Return integer value
		return strBuffer;
	}	
	
	/**
	 * Abstract: where the execution of the program happens
	 * 
	 * @param args user defined parameters
	 */	
	public static void main(String[] args) {
		// User input variable
		String strInput = "";
		
		// repeats question until an input is entered 
		do {
			 System.out.println("Enter 1 to view customers");
			 System.out.println("Enter 2 to modify customers");
			 System.out.print("Option: ");
			 strInput = ReadStringFromUser();
		}while(strInput.length() < 0);
		
		// Switch that decides the branch taken
		switch (strInput) {		
		
		case "1": // 1 = View Customers
			ViewCustomers.ReadFile("customers.xml");			
			break;
		
		case "2":  // 2 = Create Modified file
			ModifyCustomers.WriteToFile();
			break;			
		}
		
		System.out.println("All commands complete.");
		
	}	
	
}
