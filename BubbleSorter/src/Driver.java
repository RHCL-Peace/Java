
public class Driver {

	public static void man(String[] args) {
		
		BubbleSorter bubble = new BubbleSorter(12);
		System.out.println(bubble);
		
		bubble.sort();
		System.out.println(bubble);
	}
}
