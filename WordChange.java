import java.util.*;

class WordChange
{
	// Declaring class variables
	String s/* To store the string entered by user */, r/* To store the changed string */;

	// Parameterized constructor to initialise the class variables
	WordChange(String str)
	{
		s = str;
		r = "";
	}

	// Fuction to change the passed word
	String change(String word)
	{
		int l = word.length(); // Length of the word passed
		String chWord = ""; // To store the changed word
		for(int i = 0; i < l; i++)
		{
			char ch = word.charAt(i); // getting the character at position i in the word
			char cH = Character.toUpperCase(ch); // Getting the uppercase version as well to make the work easy

			if(cH >= 'A' && cH <= 'Z') // Checking if the charcater is an alphabet or not
			{
				if(cH=='A'||cH=='E'||cH=='I'||cH=='O'||cH=='U') // Checking if the character is a vowel or not
				{
					chWord = chWord + (char)(ch+1); // Changing the vowel with the next alphabet and adding it to chWord
				}
				else
				{
					chWord = chWord + (char)(ch-1); // Changing the consonant with the previous alphabet and adding it to chWord
				}
			}

			else
				chWord = chWord + ch; // Adding the non-alphabet character as it is
		}
		return chWord; // Returning the changed word
	}

	// Function to extract each word and pass it to the change function then add the changed word to new string
	void extract()
	{
		String arrS[] = s.split(" "); // Getting the array of words
		int l = arrS.length; // Length of the array

		for(int i = 0; i < l; i++)
		{
			r = r + change(arrS[i]) + " "; // Adding the changed words to new string
		}

		r = r.trim(); // Trimming extra spaces
	}

	// Function to display the original string and changed string
	void display()
	{
		extract();
		System.out.println("Entered string : " + s);
		System.out.println("Changed String : " + r);
	}

	// Main method to create object and call required functions
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		// Taking input of the string to change
		System.out.print("Enter a string : ");
		String str = sc.nextLine();
		WordChange wch=new WordChange(str);
		wch.display();
	}
}