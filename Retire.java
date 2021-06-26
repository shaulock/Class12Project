import java.util.*;

class Retire extends Personal
{
	// Declaring class variables
	int yrs;// To store the years of service
	double pf/* To store the provident fund */, grat/* To stre the gratituity */;

	// Parameterised constructor
	Retire(String name, String pan, long accNo, int years, double salary)
	{
		super(name, pan, accNo, salary);// Calling the constructor of the super class

		// Initialising the class variables
		yrs = years;
		pf = 0.0;
		grat = 0.0;
	}

	// Function to calculate provident fund	
	void provident()
	{
		pf = (0.02 * basic_pay) * yrs;
	}

	// Function to calculate gratuity
	void gratuity()
	{
		if(yrs > 10)
			grat = basic_pay * 12;
	}

	// Function to display the necessary things
	void display()
	{
		String x = "";
		for(int i = 0; i < Name.length(); i++)
			x += "-";
		System.out.println("-------------------------------" + x);
		super.display();// Calling the display function of the super class
		System.out.println("Provident Fund               : " + pf);
		System.out.println("Gratuity                     : " + grat);
		System.out.println("-------------------------------" + x);
	}

	// Main method to take input, create object and call functions
	public static void main(String[] args)
	{
		/*
		The try-catch blocks are so that input mis-match can be handled
		and the while loops help int re-asking the user for input as long as he does not input a valid input
		*/
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Employee Name                : ");
		String n = sc.nextLine();
		System.out.print("Enter Employee PAN number          : ");
		String p = sc.nextLine();
		double b = 0.0;
		while(true)
		{
			try
			{
				System.out.print("Enter Employee Basic Salary        : ");
				b = sc.nextDouble();
				if(b > 0)
					break;
				else
					System.out.println("Basic salary can't be negative");
			}
			catch(Exception e)
			{
				System.out.println("Please enter a number only");
			}
		}
		long a = 0;
		while(true)
		{
			try
			{
				System.out.print("Enter Employee Bank Account Number : ");
				a = sc.nextLong();
				if(a > 0)
					break;
				else
					System.out.println("Bank Account number should be a positive number");
			}
			catch(Exception e)
			{
				System.out.println("Please enter a number only");
			}
		}
		int y = 0;
		while(true)
		{
			try
			{
				System.out.print("Enter Employee Years Of Service    : ");
				y = sc.nextInt();
				if(y > 0)
					break;
				else
					System.out.println("Number of years should be more than Zero");
			}
			catch(Exception e)
			{
				System.out.println("Please enter an integer only");
			}
		}
		Retire rtr=new Retire(n, p, a, y, b);// Creating object

		// Calling functions accordingly
		rtr.provident();
		rtr.gratuity();
		rtr.display();
	}
}

class Personal
{
	// Declaring class variables
	String Name/* To store name of the employee */, Pan/* To store the PAN number of the employee */;
	long acc_no;// To store the account number of the employee
	double basic_pay;// To store the Basic salary of the employee

	// Parameterized constructor
	Personal(String n, String p, long a, double b)
	{
		// Initialising the class variables
		Name = n;
		Pan = p;
		acc_no = a;
		basic_pay = b;
	}

	// Function to display the details
	void display()
	{
		System.out.println("Employee Name                : " + Name);
		System.out.println("Employee PAN                 : " + Pan);
		System.out.println("Employee Basic Salary        : " + basic_pay);
		System.out.println("Employee Bank Account Number : " + acc_no);
	}
}