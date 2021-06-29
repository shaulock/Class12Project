/**
 * Algorithm
 * 
 * Start
 * Take input and check for odd characters less than 10 immediately
 * check if the alphabets and digits are repeating or alternate
 * check if the alphabets lie beyond J and T or not
 * check if any J in the password is followed by a digit or not
 * If every condition is satisfied print the validation statement
 * If any condition was found false call invalid function with respective message and do System.exit(1)
 * End
 */

import java.util.*;

class checkPass
{
	// Declaring class variables
	String pass;// String that stores the password
	int l;// Int that stores the length of the password

	// Default Constructor
	checkPass()
	{
		// Initialising class variables to default values
		pass = "";
		l = 0;
	}

	// Function that takes input
	void input()
	{
		// Taking input
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the password : ");
		pass = sc.nextLine();
		l = pass.length();
		// Checking input and calling invalid() function with appropriate message
		if(l>10 || l%2==0)
			inValid("There are even number of digits or the password is longer than 10 characters");
	}

	// Fuction that checks if the alphabets and digits are alternate and also if 'J' or 'T' are followed by an even digit
	void alternateCheck()
	{
		int what = 0;// Variable that checks alterate-ness
		char prev = '\0';// Variable that stores the previous character
		for(int i = 0; i < l; i++)
		{
			char c = pass.charAt(i);
			c = Character.toUpperCase(c);// Coverting for ease of access
			if(c >= 'A' && c <= 'Z')// Checking if the character is alphabet
			{
				if(c < 'J' || c > 'T')// Checking if thee characters beyond the range of 'J' and 'T'
					inValid("There are alphabets outside the range of 'J' to 'T'");
				else// else setting prev to that character
					prev = c;

				if(what != 1)// checking if previous character was a number or alphabet
					what = 1;
				else// If alphabet, password invalid
					inValid("There are consecutive alphabets or numbers");
			}
			else if(c >= '0' && c <= '9')// Checking is the charcetr is number
			{
				if(prev == 'J' || prev == 'T')// Checking if prev was a 'J' or a 'T'
					if(c == '1' || c == '3' || c == '5' || c == '7' || c == '9')// checking if the 'J' or the 'T' was followed by an odd digit
						inValid("Alphabet" + prev + "is followed by an even digit");
					else// else setting prev to c
						prev = c;

				if(what != 2)// Checking if previous character was an alphabet
					what = 2;
				else// If number then, password invalid
					inValid("There are consecutive alphabets or numbers");
			}
			else// if character is neither a number nor an alphabet password invalid
				inValid("There are characters other than alphabets or numbers");
		}
	}

	// Fucntion to print invalid with reason
	void inValid(String s)
	{
		System.out.println(s);
		System.exit(1);// Exiting program after printing the reason
	}

	// Main method to create object and call methods
	public static void main(String[] args)
	{
		checkPass chp=new checkPass();// Creating object

		// Calling functions accordingly
		chp.input();
		chp.alternateCheck();

		// Printing the password is valid
		System.out.println("The password is valid");
	}
}