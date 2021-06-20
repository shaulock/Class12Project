import java.util.*;

class Retire extends Personal
{
	int yrs;
	double pf, grat;

	Retire(String name, String pan, long accNo, int years, double salary)
	{
		super(name, pan, accNo, salary);
		yrs = years;
		pf = 0.0;
		grat = 0.0;
	}

	void provident()
	{
		pf = (0.02 * basic_pay) * yrs;
	}

	void gratuity()
	{
		if(yrs > 10)
			grat = basic_pay * 12;
	}

	void display()
	{
		String x = "";
		for(int i = 0; i < Name.length(); i++)
			x += "-";
		System.out.println("-------------------------------" + x);
		super.display();
		System.out.println("Provident Fund               : " + pf);
		System.out.println("Gratuity                     : " + grat);
		System.out.println("-------------------------------" + x);
	}

	public static void main(String[] args)
	{
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
		Retire rtr=new Retire(n, p, a, y, b);
		rtr.provident();
		rtr.gratuity();
		rtr.display();
	}
}

class Personal
{
	String Name, Pan;
	long acc_no;
	double basic_pay;

	Personal(String n, String p, long a, double b)
	{
		Name = n;
		Pan = p;
		acc_no = a;
		basic_pay = b;
	}

	void display()
	{
		System.out.println("Employee Name                : " + Name);
		System.out.println("Employee PAN                 : " + Pan);
		System.out.println("Employee Basic Salary        : " + basic_pay);
		System.out.println("Employee Bank Account Number : " + acc_no);
	}
}