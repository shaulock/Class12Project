import java.util.*;

class Number
{
	// Declaring class variables
	double d;// To store the original decimal number
	char choice;// To store the base to convert in
	String converted;// To store the converted number

	// Default Constructor
	Number()
	{
		// Initialising the class variables to their default values
		d = 0.0;
		choice = '\0';
		converted = "";
	}

	// Function to take input of the decimal number
	void inputNum()
	{
		try
		{
			// Taking input
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter a decimal number : ");
			d = sc.nextDouble();
		}
		catch(Exception e)// Catching input mismath exception and other exceptions
		{
			System.out.println("Enter a decimal NUMBER only");
			inputNum();// Calling the function again if any exception was caught
		}
	}

	// Function to take input of choice of base to convert to
	void inputChoice()
	{
		// Taking input
		Scanner sc=new Scanner(System.in);
		System.out.println("Choices :-\n\t1. Decimal to Binary\n\t2. Decimal to Octal\n\t3. Decimal to Hexadecimal");
		System.out.print("Enter choice : ");
		try
		{
			int n = sc.nextInt();
			switch(n)
			{
				case 1:
				choice = 'B';
				break;
				case 2:
				choice = 'O';
				break;
				case 3:
				choice = 'H';
				break;
				default:// Defaut case for unavailable choice
				System.out.println("Enter a valid choice");
				inputChoice();// Calling the function again if there was a wrong choice
				break;
			}
		}
		catch(Exception e)// Catching input mismath exception and other exceptions
		{
			System.out.println("Enter a valid choice");
			inputChoice();// Calling the function again if any exception was caught
		}
	}

	// Function to return the integer part of any double number n
	int get_int_part(double n)
	{
		return (int)Math.floor(n);
	}

	// Function to convert the decimal number to desired base
	void convert()
	{
		// Taking absolute value and checking sign.
		char sign = '\0';
		if(d < 0)
		{
			sign = '-';
			d *= -1;
		}

		// Storing a few values necessary for the conversion
		int intPart = get_int_part(d);// The integer part of the entered number
		int base = choice=='B'?2:choice=='O'?8:16;// Base of conversion

		// Converting the integer part of the number
		while(intPart > 0)
		{
			int mod = intPart % base;
			if(mod<=9)
				converted = Integer.toString(mod) + converted;
			else
				converted = (char)((int)'A' + mod%10) + converted;
			intPart = intPart/base;
		}
		converted = sign + converted;// Adding the sign to the converted number

		if(d - intPart == 0.0)
			return;// Returning if the entered number was an integer
		else
		{
			double fractPart = d - Math.floor(d);// Fractional part of the given number
			converted = converted + ".";// Adding the decimal point
			int f = 0;// Count of the number of decimal places

			// Converting the fractional part up to 10 decimal places
			while(f<10 && fractPart!=0.0)
	        {
	            f++;
	            fractPart *= base;
	            int prod = get_int_part(fractPart);
	            if(prod <= 9)
					converted += prod + "";
				else
					converted += (char)((int)'A' + (prod % 10)) +"";
	            fractPart=fractPart-Math.floor(fractPart);
	        }
		}
	}

	// Function to display the original and converted number
	void display()
	{
		if(d - get_int_part(d) == 0)
			System.out.print(get_int_part(d));
		else
			System.out.print(d);
		System.out.println(" Converted to " + (choice=='B'?"Binary":choice=='O'?"Octal":"Hexadecimal") + " is " + converted);
	}

	// Main method to create objects and call functions accordingly
	public static void main(String[] args)
	{
		Number nbr=new Number();// Creating object

		// Calling functions accordingly
		nbr.inputNum();
		nbr.inputChoice();
		nbr.convert();
		nbr.display();
	}
}