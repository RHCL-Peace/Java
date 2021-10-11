import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Create a Library class for LibraryOfBooks Peogram
 * 
 * @author HeshamNatouf
 *
 */
public class Book implements BookInterface {

	private String title;
	private String author;
	private String genre;
	private String fileName;

	// Constructor 
	public Book(String title, String author) {
		setTitle(title);
		setAuthor(author);
		setGenre(null);
		setFilename(null);
	}
	// This is the setter and getter of a program
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFilename() {
		return fileName;
	}

	public void setFilename(String filename) {
		this.fileName = filename;
	}
	// toString 
	public String toString() {
		return  "Book Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Filename: " + fileName + "";
	}
	// isValid
	public boolean isValid() {
		if (fileName == null)
			return false;
		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());

		if (file.exists() && title != null && author != null && genre != null && fileName != null) {
			return true;
		}
		return false;
	}
	// getText
	public String getText() {
		String bookText = "";
		try {

			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			String textLine;
			while ((textLine = reader.readLine()) != null) {
				bookText += textLine + "\n";
			}
		} catch (FileNotFoundException e) {
			return "The file could not be opened";
		} catch (IOException i) {
			return "The file could not be readed";
		}
		return bookText;
	}

}
