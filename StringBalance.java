import java.util.*;

class StringBalance
{
	// Declaring class variables
	String s;// String to be checked
	int l;// Length of s

	// Default constructor
	StringBalance()
	{
		// Initialising class variable to default values
		s = "";
		l = 0;
	}

	// Function to take input
	void input()
	{
		// Taking input
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a string : ");
		s = sc.nextLine();

		l = s.length();// Storing length in l
	}

	// Function to check if the string is balanced or not
	boolean isBalanced()
	{
		String str = s.toUpperCase();// Converting the string to uppercase for ease of use
		int cA = 0/* Number of a's in the string */, cZ = 0/* Number of z's in the string */;
		// Loop to count the number of a's and z's
		for(int i=0; i<l; i++)
		{
			char c = str.charAt(i);
			
			if(c!='A' && c != 'Z')
				return false;// Returning false if anything other than a or z is present

			if(c=='A')
				cA++;
			if(c=='Z')
				cZ++;
		}

		if(cA != cZ)
			return false;// Returning false if number of a's is not equal to number of z's

		String st[]=new String[l];// Store the a's and z's with positions
		int ca=1/* Number of a */, cz=1/* number of z */;
		// Loop to fill st
		for(int i = 0, a = 0; i < l; i++, a++)
		{
			char c = str.charAt(i);
			if(c=='A')
			{
				st[a] = Integer.toString(i)/* Adding position of the character */ + c + Integer.toString(ca)/* Adding the number of a */;
				ca++;// Incrementing the number of a
			}
			else
			{
				st[a] = Integer.toString(i)/* Adding position of the character */ + c + Integer.toString(cz)/* Adding the number of z */;
				cz++;// Incrementing the number of z
			}
		}
		// Loop to check if all the nth a's preceed the nth z's
		for(int i = 0; i < l; i++)
		{
			String x = st[i];
			for(int j = 0; j < l; j++)
			{
				String y = st[j];
				if(x.charAt(1)!=y.charAt(1) && x.charAt(2)==y.charAt(2))
				{
					if(x.charAt(1)=='A' && y.charAt(1)=='Z' && x.charAt(0)>y.charAt(0))
						return false;// Returning false if a comes after z
					if(x.charAt(1)=='Z' && y.charAt(1)=='A' && x.charAt(0)<y.charAt(0))
						return false;// Returning false if z comes before a
				}
			}
		}

		return true;// If no previous return statements are called the string is balanced
	}

	// Function to display the required things
	void display()
	{
		System.out.println("Entered String : " + s);// Printing input string
		// Printing if it is a well balanced string or not
		if(isBalanced())
			System.out.println("It is a \"Well Balanced String\".");
		else
			System.out.println("It is NOT a \"Well Balanced String\".");
	}

	// Main method to create objectas and call functions accordingly
	public static void main(String[] args)
	{
		StringBalance stb=new StringBalance();// Creating objects
		// Calling functions accordingly
		stb.input();
		stb.display();
	}
}