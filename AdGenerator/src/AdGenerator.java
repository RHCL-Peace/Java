import java.text.NumberFormat;
import java.util.Scanner;

public class AdGenerator {

	public static void main(String[] args) {
		
		String welcome = "Welcome ro AdGenerator";
		String info = "Please enter your profile information.";
		final String BORDER = "===========================================";
		
		System.out.println(welcome);
		System.out.println(info);
		System.out.println(BORDER);
	 
	Scanner scan = new Scanner(System.in);
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	// First Name
	System.out.print("First Name: ");
	String firstName = scan.nextLine();
	// Middle Name
	System.out.print("Middle Name: ");
	String middleName = scan.nextLine();
	// Last Name
	System.out.print("Last Name: ");
	String lastName = scan.nextLine();
	// Job title
	System.out.print("Job title: ");
	String jobTitle = scan.nextLine();
	// Phone Number
	System.out.print("Phone number (10 digit): ");
	String phoneNumber = scan.nextLine();
	// Hourly rate
	System.out.print("Hourly rate: ");
	double hourlyRate = scan.nextDouble();
	
		// Generate ad
		System.out.println (BORDER + "\n" +
							"Need a " + jobTitle + "?" + "\n" + 
							"CALL NOW! (" + phoneNumber.substring(0, 3) + ") " + phoneNumber.substring(3, 6) +
							"-" + phoneNumber.substring(6, 10) + "\n" +
							"Ask for " + firstName + " " + middleName.charAt(0) + ". " + lastName + "\n" + 
							"(Rates start at " + fmt.format(hourlyRate) + "/hr)" +"\n" + 
							BORDER);
	
		scan.close();
	}

}
