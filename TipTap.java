/**
 * Algorithm
 * 
 * Start
 * Take necessary inputs
 * Use a recursive function to make tap
 * Reverse Tap to get Tip
 * Run a loop from tip up to Tap
 * CHeck every number in between for perfect square
 * If perfect square then print it
 * End
 */


import java.util.*;

class TipTap
{
	// Declaring class variables
	int tip/* stores the lower bound */, tap/* stores the upper bound */, mak/* Stores the number user enters */;

	// Default constructor
	TipTap()
	{
		// Initialising class variables to default values
		tip = 0;
		tap = 0;
		mak = 0;
	}

	// Function that takes input
	void input()
	{
		try
		{
			// Taking input
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter the number : ");
			mak = sc.nextInt();// Getting mak
		}
		catch(Exception e)// Input mismatch
		{
			System.out.println("Please enter an integer only");
			input();// Calling function again
		}
	}

	// Function that removes a digit i from a number n
	int rmDigit(int n, int i)
	{
		int a = 0;// Stores the position of the number
		for(int j=n, k=1; j>0; j/=10, k++)
		{
			if(j%10==i)
			{
				a = k;// Storing the position of i
				break;
			}
		}

		if(a==1)
			return n/10;// if i is the first digit from right returning n/10
		else// else
		{
			int p = 1;
			for(int j = 0; j < a; j++)
				p *= 10;// Place value of i
			return (n/p)*(p/10) + (n%(p/10));// Removing i from the number and returning the new number
		}

	}

	// Sorting a number in descending order
	int desc(int n)
	{
		if(n<10)
			return n;// Returning the number if the number is only 1 digit

		int min = 9;// To store the smallest digit in n, initially the highest possible digit
		for(int i=n; i>0; i/=10)
		{
			if(i%10 <= min)
				min=i%10;// storing the smallest digit in n
		}
		n = rmDigit(n, min);// Removing the min from the number
		return desc(n)*10 + min;// Recursively returning the number in descending order
	}

	// Reversing a number
	int reverse(int n)
	{
		if(n < 10)
			return n;// Returning the number if the number is only 1 digit

		int c = 0;// Number of digits in the number
		for(int i = n; i > 0; i/=10)
			c++;
		
		int p = 1;// Highest place value of the number
		for(int i = 1; i < c; i++)
			p*=10;
		
		return (n%10)*p + reverse(n/10);// Recursively returning the reverse of the number
	}

	// Function to check if a number is a perfect square
	boolean perfectSqr(int n)
	{
		double sqrt = Math.sqrt(n);// Squareroot with decimal
		if(sqrt == Math.floor(sqrt))// Checking if there is nothing after the decimal
			return true;// If yes then it is perfect square
		else
			return false;// else it is not a perfect square
	}

	// Function to display the required things
	void display()
	{
		tap = desc(mak);// Getting tap
		tip = reverse(tap);// Getting tip

		// For better styling
		String tp = Integer.toString(tip);
		String tP = Integer.toString(tap);
		while(tp.length()!=tP.length())
			tp = "0" + tp;
		// Printing tip and tap
		System.out.println("New Number 1 : " + tp);
		System.out.println("New Number 2 : " + tP);
		// Printing the desired list
		System.out.println("The desired list is -");
		for(int i = tip; i <= tap; i++)
		{
			if(perfectSqr(i))
				System.out.print(i + " ");// Printing a number if it is a perfect square
		}
		System.out.println();// Changing the line
	}

	// Main method to create objects and call functions accordingly
	public static void main(String[] args)
	{
		TipTap tPt=new TipTap();// Creating object
		// Calling functions accordingly
		tPt.input();
		tPt.display();
	}
}