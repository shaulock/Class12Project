/**
 * Algorithm
 * 
 * Start
 * Take necessary inputs, and validate then and there
 * For converting a number to words use switch case to use fallthough and recursion to your advantage
 * To convert the time to words, first convert the standard times, like half past some hour, some hour o' clock quarter past some-hour, quarter to some-hour
 * For minutes less than 30, convert them as it is and print like ome-minutes past some-hour
 * for minutes more than 30, convert minutes - 30 to words and print like some-minutes to some-hour
 * End
 */

import java.util.*;

class Time
{
	// Declaring class variables
	int hh/* Store the hour */, mm/* Store the minute */;

	// Default constructor
	Time()
	{
		// Initialising class variables to default values
		hh = 0;
		mm = 0;
	}

	// Function that takes integer input from user
	int get_int(String s)
	{
		try
		{
			// Taking input
			Scanner sc=new Scanner(System.in);
			System.out.print(s);
			int x = sc.nextInt();
			return x;// Returning the input
		}
		catch(Exception e)// Input mismatch
		{
			System.out.println("Please enter an integer only");
			get_int(s);// Calling the function again
		}
		return -1;// Return is required
	}

	// Function that takes input
	void input()
	{
		// Getting hour
		hh = get_int("Enter the hour : ");
		// Validating hour
		while(hh > 12 || hh < 1)
		{
			System.out.println("Hour should be between 1 to 12 both inclusive");
			hh = get_int("Enter the hour : ");
		}
		// Getting minute
		mm = get_int("Enter the minute : ");
		// Validating minute
		while(mm > 59 || mm < 0)
		{
			System.out.println("Minute should be between 0 to 59 both inclusive");
			mm = get_int("Enter the minue : ");
		}
	}

	// Function that converts a number to words
	String convt(int i)
	{
		switch(i)
		{
			case 1: return "one";
			case 2: return "two";
			case 3: return "three";
			case 4: return "four";
			case 5: return "five";
			case 6: return "six";
			case 7: return "seven";
			case 8: return "eight";
			case 9: return "nine";
			case 10: return "ten";
			case 11: return "eleven";
			case 12: return "twelve";
			case 13: return "thirteen";
			case 14:
			case 16:
			case 17:
			case 18:
			case 19: return convt(i - 10) + "teen";
			case 20: return "twenty";
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
			case 28:
			case 29: return "twenty-" + convt(i - 20);
		}
		return "";// Return is necessary
	}

	// Function to display the required things
	void display()
	{
		if(mm==0)// Exactly an hour
			System.out.println(convt(hh) + " o' clock");
		else if(mm==30)// Half of an hour
			System.out.println("half past " + convt(hh));
		else if(mm==15)// Quarter of an hour
			System.out.println("quarter past " + convt(hh));
		else if(mm < 30)// Less than half but not a quarter
			System.out.println(convt(mm) + " minutes past " + convt(hh));
		else if(mm==45)// 3 Quarter of an hour
		{
			if(hh+1 == 13)// If the increment exceeds 12
				System.out.println("quarter to " + convt(1));
			else
				System.out.println("quarter to " + convt(hh+1));
		}
		else// More than half but not 3 quarters
		{
			if(hh+1 == 13)// If the increment exceeds 12
				System.out.println(convt(60-mm) + " minutes to " + convt(1));
			else
				System.out.println(convt(60-mm) + " minutes to " + convt(hh+1));
		}
	}

	// Main method to create objects and call functions accordingly
	public static void main(String[] args)
	{
		Time tme=new Time();// Creating object
		// Calling functions accordingly
		tme.input();
		tme.display();
	}
}