import java.util.*;

class Alphagram
{
	// Declaring class variables
	String s_arr[]; // To store the sentences in array
	int n /* To store number of sentences user will enter */, choice/* To store number of sentence user wants a graph for */;

	// Default constructor to initialise class variables to default values
	Alphagram()
	{
		n = 0;
	}

	// Function to take input of an integer with explicit error handeling recursively
	int get_int(String s)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print(s);
		try
		{
			int x = sc.nextInt();
			return x;
		}
		catch(Exception e) // Catching error when user enters something other than integer
		{
			System.out.println("\nPlease enter an integer only");
			get_int(s);
		}
		return 0;
	}

	// Function to take neccesary inputs and checking them as well
	void input()
	{
		while(true)
		{
			n = get_int("Enter the number of sentences for the string : ");
			if(n > 1) // Checking if the number complies with the rules
				break; // Breaking when true
			System.out.println("number of sentences should be at least 2");
		}
		s_arr = new String[n]; // Initialise the array
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			// Taking input of the sentences
			System.out.println("Enter a string having " + n + " sentences ended with either '?' or '.' or '!'");
			String s = sc.nextLine();
			s = s.trim();
			int l = s.length();
			// Filling the array with the sentences
			String ns="";
			int a = 0;int e = 0;
			for(int i = 0; i < l; i++)
			{
				char ch = s.charAt(i);
				if(ch=='?'||ch=='.'||ch=='!')
				{
					try
					{
						s_arr[a] = ns + ch;
						a++;
					}
					catch(ArrayIndexOutOfBoundsException ex) // catching error when user enters more sentences
					{
						System.out.println("Please only enter as many sentences as you specified");
						e = 1;
						break;
					}
					ns = "";
				}
				else if(a<n && i==l-1) // Checking if user entered less sentences as required
				{
					System.out.println("Please enter as many sentences as you specified");
					e = 1;
					break;
				}
				else
				{
					if(i == l-1) // Checking if the string does not follow the rules
					{
						System.out.println("Please make sure you sentences are properly punctuated");
						e = 1;
						break;
					}

					else // Else appending sentence
						ns = ns + ch;
				}
			}
			if(s_arr[n-1]==null) // Final check if the user entered less sentences
				System.out.println("Please enter as many sentences as you specified");
			else if(e==0)
			{
				break; // finally breaking out of the infinite loop
			}
		}
		while(true)
		{
			// Taking input of the choice of sentence from user
			choice = get_int("Enter the sentence number of your choice : ");
			if(choice >= 1 && choice <= n)
				break; // breaking from infinite loop if the input is valid
			System.out.println("The choice is not wihin the limits of number of sentences");
		}
	}

	// Function to get the frequency of every alphabet in the sentence of user's choice
	int[] AlphaFrequency(String s)
	{
		int freq[]=new int[26]; // initialising the array for frequencies
		for(int i = 0;i < 26;i++)
			freq[i] = 0; // initialising every element to 0
		s = s.toUpperCase(); // Converting to uppercase to make things easy
		int l = s.length();
		for(int i = 0; i < l; i++)
		{
			int p = (int)(s.charAt(i)) - 65;
			if(p >= 0 && p < 26)
				freq[p]++; // increasing the frequency of the alphabet found by 1
		}
		return freq; // returning the array containg the frequencies
	}

	// Function to create a 2-dimensional array to print
	char[][] ArrToPrint(int max, int arr[])
	{
		char ch[][] = new char[max+1][26]; // The array to be printed is initialised
		for(int i = 0; i < max; i++)
			for(int j = 0; j < 26; j++)
				ch[i][j] = ' '; // Initialising each element of the array to a space
		char c = 'A';
		for(int i = 0; i < 26; i++)
		{
			ch[max][i] =(char)(c + i); // Initialising the last row of the array to each alphabet respectively
		}

		// Filling the array with * to indicate 1 instance of the respective alphabet
		for(int i = 0; i < 26; i++)
		{
			int a  = max - 1;
			while(arr[i] > 0)
			{
				ch[a][i] = '*';
				arr[i]--;
				a--;
			}
		}
		return ch; // Returning the array to be printed
	}

	// Function to print the graph
	void display()
	{
		input(); // Calling input
		String s = s_arr[choice - 1]; // Getting the sentence of choice
		int array[] = AlphaFrequency(s); // Getting the frequency of each alphabet in the sentence of choice

		// Getting the maximum frequency shown by any alphabet
		int max = 0;
		for(int i = 0; i < array.length; i++)
			if(array[i] > max)
				max = array[i];
		char arr[][] = ArrToPrint(max, array); // Getting the array to be printed

		// Printing the things required
		System.out.println("The sentence of your choice is : " + s);
		for(int i = 0; i < max+1; i++)
		{
			for(int j = 0; j < 26; j++)
			{
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Main method to create object and call required functions
	public static void main(String[] args)
	{
		Alphagram alpg=new Alphagram();
		alpg.display();
	}
}