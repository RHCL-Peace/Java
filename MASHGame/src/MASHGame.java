import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Lesson 12: Activity - ArrayLists
 *
 * @author CS121 Instructors
 * @version [semester]
 * @author [your name]
 */
public class MASHGame {

	public static void main(String[] args) {

		/* Define list of options */
		ArrayList<String> homeList = new ArrayList<String>();
		ArrayList<String> femaleSpouseList = new ArrayList<String>();
		ArrayList<String> maleSpouseList = new ArrayList<String>();
		ArrayList<String> occupationList = new ArrayList<String>();
		ArrayList<String> transportationList = new ArrayList<String>();
		ArrayList<String> hometownList = new ArrayList<String>();

		/* Add items to home list */
		homeList.add("mansion");
		homeList.add("apartment");
		homeList.add("shack");
		homeList.add("house");

		/* TODO: 1. Add items to femaleSpouseList */
		femaleSpouseList.add("Lola");
		femaleSpouseList.add("Evie");
		femaleSpouseList.add("Adalyn");
		femaleSpouseList.add("Esme");

		/* TODO: 2. Add items to maleSpouseList */
		maleSpouseList.add("Steven,");
		maleSpouseList.add("Cruz,");
		maleSpouseList.add("Maximo,");
		maleSpouseList.add("Payton");

		/* TODO: 3. Add items to occupationList */

		occupationList.add("Cost Estimator,");
		occupationList.add("Computer Programmer,");
		occupationList.add("Executive Assistant,");
		occupationList.add("Preschool Teacher");

		/* TODO: 4. Add items to transportationList */

		transportationList.add("Toyota Sienna,");
		transportationList.add("Honda Civic,");
		transportationList.add("Hyundai Sonata,");
		transportationList.add("Mercedes Benz");

		/* TODO: 5. Add items to hometownList */

		hometownList.add("Boise, Idaho");
		hometownList.add("Twin Falls, Idaho");
		hometownList.add("Chicago, Illinois ");
		hometownList.add("Los Angelos, California");

		/* Print the database */
		System.out.println("--------------------- Future Possibilities Database ------------------------");
		System.out.print("Home List: ");
		for (String item : homeList) {
			System.out.print(item + " ");
		}
		System.out.println();

		/*
		 * TODO: 6. Print the items in the femaleSpouseList using the ArrayList
		 * toString() method
		 */

		System.out.println(femaleSpouseList);

		/*
		 * TODO: 7. Print the items in the maleSpouseList using a for loop (IE: use
		 * indexes to print items )
		 */

		for (int i = 0; i < maleSpouseList.size(); i++) {
			System.out.print(maleSpouseList.get(i) + " ");
		}
		System.out.println();

		/*
		 * TODO: 8. Print the items in the occupationList using the ArrayList iterator
		 * and a while loop
		 */

		Iterator<String> it = occupationList.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		/* TODO: 9. Print the items in the transportationList using a foreach loop */

		for (String s : transportationList) {
			System.out.print(s + " ");
		}
		System.out.println();

		/*
		 * TODO: 10. Print the items in the hometownList using the method of your choice
		 */

		System.out.println(hometownList);

		System.out.println("----------------------------------------------------------------------------");
		System.out.println("\n");

		/* Ask the player for their name */
		Scanner kbd = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String name = kbd.nextLine();
		kbd.close();
		System.out.println("\n\n");

		/* Randomly choose items from each List */
		Random rand = new Random();
		String firstHome = homeList.get(rand.nextInt(homeList.size()));

		/*
		 * TODO: 11. Use rand object to select each of the following future components
		 * from above lists.
		 */
		String secondHome = homeList.get(rand.nextInt(homeList.size()));
		String occupation = occupationList.get(rand.nextInt(occupationList.size()));
		String transportation = transportationList.get(rand.nextInt(transportationList.size()));
		String hometown = hometownList.get(rand.nextInt(hometownList.size()));

		/*
		 * TODO: 12. Use the coin to determine whether player's spouse is male or
		 * female, then randomly select spouse from the cooresponding list.
		 */
		boolean coin = rand.nextBoolean();
		String spouse = coin ? femaleSpouseList.get(rand.nextInt(femaleSpouseList.size()))
				: maleSpouseList.get(rand.nextInt(maleSpouseList.size()));

		/* Randomly choose number of years player will be married */
		int yearsOfMarriage = rand.nextInt(30) + 1;

		/* Print the player's future */
		System.out.println("Welcome " + name + ", this is your future...");
		System.out.println("You will marry " + spouse + " and live in a " + firstHome + ".");
		System.out.println("After " + yearsOfMarriage + (yearsOfMarriage == 1 ? " year" : " years")
				+ " of marriage, you will finally get your dream job of being a " + occupation + ".");
		System.out.println("Your family will move to a " + secondHome + " in " + hometown + " where you will "
				+ transportation + " to work each day.");

	}

}
