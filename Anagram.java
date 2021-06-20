import java.util.*;

class Anagram
{
	// Declaring class variables
	String word; // To store the word entered by user
	int count; // To store the number of anagrams generated
	String anag[]; // To store the anagrams generated

	// Default constructor to initialise class variables to default values
	Anagram()
	{
		count = 0;
		word = "";
		anag = new String[0];
	}

	// Function to take input
	void input()
	{
		// Taking input for the word with whose letters we need to print the anagrams
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a word : ");
		word = sc.next();
	}

	// Function to display the possible anagrams recursively
	void display(String s1, String s2)
	{
		if (s2.length() <= 1)
		{
			// Checking if the anagram generated is already printed or not
			String newanag = (s1 + s2).toUpperCase();
			boolean found = false; // Initialising found to false to denote not found
			for(int i = 0; i < anag.length; i++)
			{
				if(anag[i].equalsIgnoreCase(newanag)) // Checking if found or not
				{
					found = true; // Changing the found to true
					break; // Breaking out of loop if found
				}
			}
			if(found==false) // When not printed already
			{
				count++; // Increase the count of the anagrams by 1
				System.out.println(newanag); // Print the anagram

				// Add the new anagram to the previous list
				String temp[]=new String[anag.length]; // Initialising a temporary array
				for(int i = 0; i < temp.length; i++)
					temp[i] = anag[i]; // Filling the temporary array
				anag=new String[temp.length + 1]; // Re-initialising the original array
				anag[0] = newanag; // Adding the angaram just printed
				for(int i = 1; i < anag.length; i++)
				{
					anag[i]=temp[i-1]; // Re-filling the original array using the temporary array
				}
			}
		}
		else
		{
			// Loop to generate anagrams
			for (int i = 0; i < s2.length(); i++)
			{
				String x = s2.substring(i, i + 1); // Getting the ith character as a String
				String y = s2.substring(0, i);
				String z = s2.substring(i + 1);
				display(s1 + x, y + z); // Calling the function again with changed parameters
			}
		}
	}

	// Function to display whatever is required
	void display()
	{
		System.out.println("The Anagrams of the word "+ word +" are : ");
		display("", word);
		System.out.println("Total Number of Anagrams = " + count);
	}

	// Main method to declare object and call required functions
	public static void main(String args[])
	{
		Anagram ang = new Anagram();
		ang.input();
		ang.display();
	}
}