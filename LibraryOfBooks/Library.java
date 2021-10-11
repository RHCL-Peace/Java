import java.util.ArrayList;
/**
 * Create a Library class for LibraryOfBooks Peogram
 * 
 * @author HeshamNatouf
 *
 */
public class Library implements LibraryInterface {
	// Private ArrayList
	private ArrayList<Book> bookList = new ArrayList<>();

	public ArrayList<Book> getBooks() {
		ArrayList<Book> bookCopy = new ArrayList<>();
		bookList.clone();
		return bookCopy;
	}
	// Add book
	public void addBook(Book newBook) {
		bookList.add(newBook);
	}
	// Remove book index
	public void removeBook(int index) {
		if (index >= 0 && index < bookList.size()) 
			bookList.remove(index);
		 else 
			System.out.println("Invalid index");
		
	}
	// Get book index
	public Book getBook(int index) {

		if (index < 0 || index >= bookList.size()) 
			return null;

		else
			return bookList.get(index);
		}
	// toString
	public String toString() {
		if (bookList.isEmpty()) 
			return "No books in library.";
		
		String bookText = "";
		for (Book b : bookList) 
			 bookText += b.toString() + "\n";
		
		return bookText;
	}
}
