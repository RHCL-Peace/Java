import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Creates a playlist of three diffrent song and does some computations.
 * 
 * @author HeshamNatouf
 *
 */
public class PlayList {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String tableSet = "========================================================================================";
		String tableHead = "Title" + "\t\t\t" + "       " + "Artist" + "\t\t" + "    " + "Album" + "\t\t\t" + "PlayTime";
		String closestTime = "\n" + "Song with play time closest to 4 min: ";
		
		// This Print's Out Song Number One
		System.out.print("Enter the 1st song title: ");
		String title = scan.nextLine();

		System.out.print("Enter the 1st song artist: ");
		String artist = scan.nextLine();

		System.out.print("Enter the 1st song album: ");
		String album = scan.nextLine();

		System.out.print("Enter the 1st song Playtime (mm:ss): ");
		String Playtime = scan.nextLine();

		int timeformat = Playtime.indexOf(':');
		String min = Playtime.substring(0, timeformat);
		String sec = Playtime.substring(timeformat + 1, Playtime.length());
		int minutes = Integer.parseInt(min);
		int seconds = Integer.parseInt(sec);
		seconds = seconds + (minutes * 60);

		Song mySong = new Song(title, artist, album, seconds);

		// This Print's Out Song Number Two
		System.out.print("Enter the 2nd song title: ");
		String title2 = scan.nextLine();

		System.out.print("Enter the 2nd song artist: ");
		String artist2 = scan.nextLine();

		System.out.print("Enter the 2nd song album: ");
		String album2 = scan.nextLine();

		System.out.print("Enter the 2nd song Playtime (mm:ss): ");
		Playtime = scan.nextLine();

		timeformat = Playtime.indexOf(':');
		min = Playtime.substring(0, timeformat);
		sec = Playtime.substring(timeformat + 1, Playtime.length());
		minutes = Integer.parseInt(min);
		seconds = Integer.parseInt(sec);
		seconds = seconds + (minutes * 60);

		Song mySong2 = new Song(title2, artist2, album2, seconds);

		// This Print's Out Song Number Three
		System.out.print("Enter the 3nd song title: ");
		String title3 = scan.nextLine();

		System.out.print("Enter the 3nd song artist: ");
		String artist3 = scan.nextLine();

		System.out.print("Enter the 3nd song album: ");
		String album3 = scan.nextLine();

		System.out.print("Enter the 3nd song Playtime (mm:ss): ");
		Playtime = scan.nextLine();

		timeformat = Playtime.indexOf(':');
		min = Playtime.substring(0, timeformat);
		sec = Playtime.substring(timeformat + 1, Playtime.length());
		minutes = Integer.parseInt(min);
		seconds = Integer.parseInt(sec);
		seconds = seconds + (minutes * 60);

		Song mySong3 = new Song(title3, artist3, album3, seconds);

		// Calculate playTime Average

		double playTimeAverage = (mySong.getPlayTime() + mySong2.getPlayTime() + mySong3.getPlayTime()) / 3.0;
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println("\n" + "Average Play Time: " + df.format(playTimeAverage) + " seconds");

		int song1 = Math.abs(240 - mySong.getPlayTime());
		int song2 = Math.abs(240 - mySong2.getPlayTime());
		int song3 = Math.abs(240 - mySong3.getPlayTime());

		// Print the Play List with the given format
		
		if (song1 <= song2 && song1 <= song3) {
			System.out.println(closestTime + mySong.getTitle());
		} else if (song2 <= song1 && song2 <= song3) {
			System.out.println(closestTime + mySong2.getTitle());
		} else {
			System.out.println(closestTime + mySong3.getTitle());
		}

		// Build a Sorted Playlist

		System.out.println("\n" + tableSet);
		System.out.println(String.format(tableHead));
		System.out.println(tableSet);

		// Order the play list from smallest to largest
		if (mySong.getPlayTime() <= mySong2.getPlayTime() && mySong2.getPlayTime() <= mySong3.getPlayTime()) {

			System.out.println(mySong);
			System.out.println(mySong2);
			System.out.println(mySong3);
		} else if (mySong.getPlayTime() <= mySong3.getPlayTime() && mySong3.getPlayTime() <= mySong2.getPlayTime()) {

			System.out.println(mySong);
			System.out.println(mySong3);
			System.out.println(mySong2);
		} else if (mySong2.getPlayTime() <= mySong.getPlayTime() && mySong.getPlayTime() <= mySong3.getPlayTime()) {

			System.out.println(mySong2);
			System.out.println(mySong);
			System.out.println(mySong3);
		} else if (mySong2.getPlayTime() <= mySong3.getPlayTime() && mySong3.getPlayTime() <= mySong.getPlayTime()) {

			System.out.println(mySong2);
			System.out.println(mySong3);
			System.out.println(mySong);
		} else if (mySong3.getPlayTime() <= mySong.getPlayTime() && mySong.getPlayTime() <= mySong2.getPlayTime()) {

			System.out.println(mySong3);
			System.out.println(mySong);
			System.out.println(mySong2);
		} else {
			System.out.println(mySong3);
			System.out.println(mySong2);
			System.out.println(mySong);
		}
		System.out.println(tableSet);

		scan.close();
	}

}