
 // Converts a fahrenheit value to celsius using the formula C = 9/5(F - 32)

public class MyTempConverter 

{
   public static void main(String[] args)
   {
		final int BASE = 32;
		final double CONVERSION_FACTOR = 5.0 / 9;

		double celsiusTemp ;
		int fahrenheitTemp = 75;  // value to convert

		celsiusTemp = CONVERSION_FACTOR * (fahrenheitTemp  - BASE);

		System.out.println("Celsius Temperature: " + fahrenheitTemp);
		System.out.println("Fahrenheit Equivalent: " + celsiusTemp );
	}
}
