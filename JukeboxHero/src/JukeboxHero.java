import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create a program the store, Load, search, analyse and print a file.
 * 
 * @author HeshamNatouf
 *
 */

public class JukeboxHero {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";

		ArrayList<Song> songList = new ArrayList<>();

		// this Prints the head of the menu when the program run

		System.out.println("Welcome to Jukebox Hero,");
		System.out.println("\t" + "bringing order to playLists one song at a time...");
		System.out.println("\n" + "*************************");
		System.out.println("*	Prpgram Menu	*");
		System.out.println("*************************");
		// this Prints the menu when the program run

		System.out.println("(L)oad catalog");
		System.out.println("(S)earch catalog");
		System.out.println("(A)nalyse catalog");
		System.out.println("(P)rint catalog");
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

				System.out.println("(L)oad catalog");
				System.out.println("(S)earch catalog");
				System.out.println("(A)nalyse catalog");
				System.out.println("(P)rint catalog");
				System.out.println("(Q)uit catalog");
				break;
			// case l to Load the user file everytime user enter (L)
			case "l":

				System.out.println("Load Catalog...");
				songList.clear();
				System.out.print("Please enter filename: ");
				String musicFile = scan.nextLine();

				File file = new File(musicFile);
				if (file.exists() && file.isFile()) {
					try {
						Scanner fileScan = new Scanner(file);
						while (fileScan.hasNextLine()) {
							String line = fileScan.nextLine();

							Scanner lineScan = new Scanner(line);
							lineScan.useDelimiter(",");

							String artist = lineScan.next();
							String album = lineScan.next();
							String title = lineScan.next();
							int playTime = lineScan.nextInt();
							lineScan.close();

							Song song = new Song(title, artist, album, playTime);
							songList.add(song);
						}
						System.out.println("Successfully loaded " + songList.size() + " Songs!");
						System.out.println("-----------------------------");
						fileScan.close();

					} catch (FileNotFoundException e) {
						System.out.println("Unable to open file");
					}
				}
				break;
			// case s to search in the songlist everytime user enter (S)
			case "s":
				ArrayList<Song> searchList = new ArrayList<Song>();
				System.out.println("Search Catalog... ");
				System.out.print("Please enter the search query: ");

				input = scan.nextLine();
				input = input.toLowerCase();
				for (Song s : songList) {
					String SearchListAdd = s.getTitle().toLowerCase();
					if (SearchListAdd.contains(input)) {
						searchList.add(s);
					}
				}
				System.out.println("Found " + searchList.size() + " mathces");
				System.out.println("-----------------------------");

				for (Song s : searchList) {
					System.out.println(s);
				}
				break;
			// case a to Analyse the songlist everytime user enter (A)
			case "a":
				System.out.println("Catalog Analyse...");

				if (input.equalsIgnoreCase("A")) {
					ArrayList<String> artistList = new ArrayList<String>();
					ArrayList<String> albumList = new ArrayList<String>();
					int averageTime = 0;
					
					for (Song s : songList) {
						String artistListAdd = s.getArtist().toLowerCase();
						String albumListAdd = s.getAlbum().toLowerCase();
						if (artistList.contains(s.getArtist()) == false) {
							artistList.add(artistListAdd);
						}
						if (albumList.contains(s.getAlbum()) == false) {
							albumList.add(albumListAdd);
						}
						averageTime = averageTime + s.getPlayTime();
					}
					System.out.println("Number of Artists: " + artistList.size());
					System.out.println("Number of Albums: " + albumList.size());
					System.out.println("Number of Songs: " + songList.size());
					System.out.println("Total Playtime: " + averageTime);
				}
				break;
			// case p to print out the songlist everytime user enter (P)
			case "p":
				System.out.println("Song list contains " + songList.size() + " Songs...");
				System.out.println("-----------------------------");
				for (Song s : songList) {
					System.out.println(s);
				}
				break;
			// case q to quit the program everytime user enter (Q)
			case "q":
				System.out.println("Goodbye!" + "\n");
				break;
			default:
				System.out.println("Invalid selection!");
			}
		}
		scan.close();
	}
}