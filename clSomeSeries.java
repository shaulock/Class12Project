/**
 * Algorithm
 * 
 * Start
 * Take necessary inputs
 * create recursive factorial function
 * create recursive power function
 * set sum initially to 1.0
 * start loop from 1 up to m
 * change sum to factorial of i divided by (ith power of p + previous value of sum)
 * When the loop ends display the required things
 * End
 */

import java.util.*;

class clSomeSeries
{
	// Declaring class variables
	int m/* Storing the value of m */, p/* Storing the value of p */;
	double sum;// storing the sum of the series

	// Default constructor
	clSomeSeries()
	{
		// Initialising class variables to default values
		m = p = 0;
		sum = 0.0;
	}

	// Function that returns the factorial of a number num
	int fnFact(int num)
	{
		if(num == 1 || num == 0)// base case
			return 1;

		return num * fnFact(num-1);// recursive case
	}

	// Fuction that returns the a to the power p
	long fnPower(int a, int p)
	{
		if(p==0 || a==1)// base case
			return 1;

		return a * fnPower(a, p-1);// recursive case
	}

	// function that calculates the sum of the series
	void fnCalculate()
	{
		sum = 1.0;
		for(int i = 1; i <= m; i++)
		{
			sum = (double)fnFact(i)/(double)((fnPower(p, i)) + sum);
		}
	}

	// Function that takes input
	void fnInput()
	{
		// Taking input
		Scanner sc=new Scanner(System.in);
		while(true)// While loop used to valid input or else continue taking input
		{
			System.out.print("Enter the value of m : ");
			try
			{
				m = sc.nextInt();
				if(m > 0)// Checking if the input is valid
					break;
				else// Else continueing with an eror message
					System.out.println("m cannot be less than 1");
			}
			catch(Exception e)// Checking if the input was mismatched
			{
				System.out.println("Please enter an integer only");
			}
		}
		while(true)// While loop used to valid input or else continue taking input
		{
			System.out.print("Enter the value of p : ");
			try
			{
				p = sc.nextInt();
				if(p > 0)// Checking if the input is valid
					break;
				else// Else continueing with an eror message
					System.out.println("p cannot be less than 1");
			}
			catch(Exception e)// Checking if the input was mismatched
			{
				System.out.println("Please enter en integer only");
			}
		}
	}

	// Function to print the sum of the series
	void fnPrint()
	{
		System.out.println("The sum of the series for which m = " + m + " and p = " + p + " is : " + sum);// Printing sum
	}

	// Main method to create objects and call the functions accordingly
	public static void main(String[] args)
	{
		clSomeSeries css=new clSomeSeries();// Creating object

		// Calling functions accordingly
		css.fnInput();
		css.fnCalculate();
		css.fnPrint();
	}
}