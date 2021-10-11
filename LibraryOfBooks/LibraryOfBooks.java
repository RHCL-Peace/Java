import java.util.Scanner;
/**
 * Create a program the Print, Add, Delete, Read a book the was stored in the library.
 * 
 * @author HeshamNatouf
 *
 */
public class LibraryOfBooks {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";

		Library newLibrary = new Library();
		// Print out a program menu
		System.out.println("Welcome to your Library of Books!");
		System.out.println("\n" + "*************************");
		System.out.println("*	Prpgram Menu	*");
		System.out.println("*************************");

		System.out.println("(P)rint Contents");
		System.out.println("(A)dd Book");
		System.out.println("(D)elete a book");
		System.out.println("(R)ead a book");
		System.out.println("(Q)uit");
		// this where the while loop is displaying the command prompt
		while (!input.toLowerCase().equals("q")) {
			System.out.print("\n" + "Please enter a command (press 'm' for Menu): ");
			input = scan.nextLine();
			// this is the the switch statment that goning to read user input
			switch (input.toLowerCase()) {
			// case m to display the menu every time user enter (M)
			case "m":
				System.out.println("\n" + "*************************");
				System.out.println("*	Prpgram Menu	*");
				System.out.println("*************************");

				System.out.println("(P)rint Contents");
				System.out.println("(A)dd Book");
				System.out.println("(D)elete a book");
				System.out.println("(R)ead a book");
				System.out.println("(Q)uit");
				break;
				// case p to display a list of books was stored in the library every time user enter (P)
			case "p":
				System.out.println("Your library\n");
				System.out.println(newLibrary.toString());
				System.out.println();
				break;
				// case a to add book to the library every time user enter (A)
			case "a":
				System.out.print("Please enter Title: ");
				String title = scan.nextLine();
				System.out.print("Please enter Author: ");
				String author = scan.nextLine();
				System.out.print("Please enter Genre: ");
				String genre = scan.nextLine();
				System.out.print("Please enter Filename: ");
				String filename = scan.nextLine();

				Book newBook = new Book(title, author);
				newBook.setGenre(genre);
				newBook.setFilename(filename);
				newLibrary.addBook(newBook);

				System.out.println(newBook.getTitle() + " has been added to your library!");
				break;
				// case d to delete a book from library every time user enter (D)
			case "d":
				System.out.print("Please enter a book index number: ");
				newLibrary.removeBook(Integer.parseInt(scan.nextLine()));
				System.out.println();

				System.out.println("Book has Been successfully removed!");
				break;
				// case r to read a book from library every time user enter (R)
			case "r":
				System.out.print("Please enter a book index number: ");
				int index = Integer.parseInt(scan.nextLine());
				if (index < 0 || index >= newLibrary.getBooks().size())
					System.out.println("Invalid index!");
				else {
					if (newLibrary.getBook(index).isValid()) {
						String bookText = newLibrary.getBook(index).getText();
						System.out.println(bookText);
					} else {
						System.out.println("The book is currently unavailable.");
					}
				}
				break;
			case "q":
				// case q to quit the program everytime user enter (Q)
				System.out.println("Goodbye!" + "\n");
				break;
			default:
				System.out.println("Invalid selection!");
			}
		}
	}
}