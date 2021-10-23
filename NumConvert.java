import java.util.*;

class NumConvert
{
	// Declaring class variables
	int orgBase/* To store original number base */, convtBase/* To store converted number base */;
	String orgNum/* To store original number */, convtNum/* To store converted number */;
	char sign;// To store the sign of the number

	// Default constructor to initialise class variables to default values
	NumConvert()
	{
		orgBase = convtBase = 0;
		orgNum = convtNum = "";
		sign = '\0';
	}

	// Function to take input of an integer with explicit error handeling recursively
	private int get_int(String s)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print(s);
		try
		{
			int i = sc.nextInt();
			return i;
		}
		catch(Exception e) // Catching error when user enters something other than integer
		{
			System.out.println("Please enter an integer only");
			return get_int(s);
		}
	}

	// To verify the original number
	private boolean verifyNumber()
	{
		int countDecimal = 0;
		if(orgNum.length()==0)// If the user didn't enter any number
			return false;

		// Checking if there is a sign present, if yes then storing it in sign and editing the original number
		if(orgNum.charAt(0)=='-')
		{
			sign = '-';
			orgNum = orgNum.substring(1, orgNum.length());
		}
		if(orgNum.charAt(0)=='+')
		{
			sign = '\0';
			orgNum = orgNum.substring(1, orgNum.length());
		}

		// Checking the number digit by digit
		for(int i = 0; i < orgNum.length(); i++)
		{
			char c = orgNum.charAt(i);
			if(c=='.')
				countDecimal++;// Counting the number of '.' in the number
			// If the number is not of the specified base
			else if(c >= '0' && c <= '9')
			{
				if(Character.getNumericValue(c) >= orgBase)
					return false;
			}
			else
			{
				if(orgBase != 16)
					return false;
				else if(!(c >= 'A' && c <= 'F'))
					return false;
			}
		}
		if(countDecimal>1)
			return false;// If number of '.' is more than 1
		// If no error was triggered
		return true;
	}

	// Function to take input
	private void input()
	{
		int choice = 0;// To temporarily store user choice
		While:
		while(true)
		{
			System.out.println("Choose the base of your number :-\n\t1. Binary\n\t2. Octal\n\t3. Decimal\n\t4. Hexa-Decimal");
			choice = get_int("Enter choice : ");// Getting input of choice of original base
			switch(choice)// Placing the value
			{
				case 1:orgBase = 2;break While;
				case 2:orgBase = 8;break While;
				case 3:orgBase = 10;break While;
				case 4:orgBase = 16;break While;
			}
			System.out.println("Please enter correct choice");
		}
		orgNum = "";
		System.out.println();
		while(true)
		{
			// Taking input of original number
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter a number of base " + orgBase + ": ");
			orgNum = sc.nextLine();
			if(verifyNumber()==true)// Verifying the the number
				break;
			System.out.println("Please enter a number of base " + orgBase);
		}
		System.out.println();
		While:
		while(true)
		{
			// Taking input of the base to convert the original number to
			System.out.println("Choose the base in which to convert your number :-\n\t1. Binary\n\t2. Octal\n\t3. Decimal\n\t4. Hexa-Decimal");
			choice = get_int("Enter choice : ");
			switch(choice)// Placing the value
			{
				case 1:convtBase = 2;break While;
				case 2:convtBase = 8;break While;
				case 3:convtBase = 10;break While;
				case 4:convtBase = 16;break While;
			}
			System.out.println("Please enter correct choice");
		}
	}

	// Function that returns the value of a Hex character
	private int getHexCharValue(char a)
	{
		char[] arr = new char[6];// Creating an array
		for(char i = 'A', j = '0'; i <= 'F' && j < '6'; i++, j++)
			arr[Character.getNumericValue(j)] = i;// Filling the array
		for(int i = 0; i < 6; i++)
			if(arr[i]==a)
				return i+10;// Returning the required value
		return 0;
	}

	// Function to generate Hex character from a value
	private char getHexValueChar(int a)
	{
		return (char)('A'+(a-10));// Returning the required Hex character
	}

	// Funtion to get a to the power p
	private int power(int a, int p)
	{
		if(p==0)// Base case
			return 1;// Anything to the power 0 is 1
		return a*power(a, p-1);// Returning a to the power p in recursive way
	}

	// Function to get a to the power -p
	private double reversePower(int a, int p)
	{
		return (double)1.0/power(a, p);// Returning a to the power -p
	}

	// Function to get the integer part from a double number
	private int getIntPart(double a)
	{
		return (int) Math.floor(a);// Returning the integer part
	}

	// Function to change a number n of base base to decimal
	private double fromOtherToDecimal(int base, String n)
	{
		double convtd = 0.0;// Temporarily store converted number
		String intPart = n/* To store integer part */, fractPart = "";// To store the fraction part
		for(int i = 0; i < n.length(); i++)
		{
			// Separating the integer part and fractional part
			if(n.charAt(i)=='.')
			{
				intPart = n.substring(0, i);
				fractPart = n.substring(i, n.length());
				break;
			}
		}

		// Reversing the integer part
		String temp = "";
		for(int i = 0; i < intPart.length(); i++)
		{
			temp = intPart.charAt(i) + temp;
		}
		intPart = temp;

		// Converting the integer part to decimal
		for(int i = 0; i < intPart.length(); i++)
		{
			char c = intPart.charAt(i);// Getting a character at position i
			int p = power(base, i);// Raising the base to the power of the position
			if(Character.isDigit(c))
				convtd += Character.getNumericValue(c)*p;// If it is a digit then mutiplying it with base to the power position
			else
				convtd += getHexCharValue(c)*p;// If it is an alphabet then mutiplying the Hex character value to the base powered to position
		}

		// Converting the fractional part
		for(int i = 0; i < fractPart.length(); i++)
		{
			if(i==0)
				continue;// Skipping the '.'
			char c = fractPart.charAt(i);// Getting a character at position i
			double p = reversePower(base, i);// Raising the base to the power of the position
			if(Character.isDigit(c))
				convtd += Character.getNumericValue(c)*p;// If it is a digit then mutiplying it with base to the power position
			else
				convtd += getHexCharValue(c)*p;// If it is an alphabet then mutiplying the Hex character value to the base powered to position
		}
		return convtd;// Returning the decimal number
	}

	// Function to convert a Decimal Number n to base base
	private String fromDecimalToOther(int base, double n)
	{
		String convtd = "";// Temporary storage of the converted number
		if(n==0.0)
			return "0";// If the number is 0 returning 0
		// Seprating the integer and fractional parts
		int intPart = getIntPart(n);
		double fractPart = n - intPart;

		// Converting the integer part
		while(intPart > 0)
		{
			int mod = intPart % base;// Storing the mod of intPart to base
			if(mod <= 9)
				convtd = Integer.toString(mod) + convtd;// If mod is a single digit concatinating it to converted in reverse
			else
				convtd = getHexValueChar(mod) + convtd;// If mod is double digit concatenating the corresponding Hex character to converted in reverse
			intPart = intPart / base;// Dividing Integer part by base
		}

		// Converting the fractional part
		if(fractPart > 0)
			convtd = convtd + '.';// If the fractional part exists then addign a decimal point
		int lim = 10;// Set the limit of the number of decimal places
		while(fractPart > 0 && lim > 0)
		{
			int prod = getIntPart(fractPart * base);// Storing the integer part of the product of the fractional part to base
			if(prod <= 9)
				convtd = convtd + Integer.toString(prod);// Concatenating the result to the converted if it is single digit
			else
				convtd = convtd + getHexValueChar(prod);// Concatenating the corresponding hex character to the converted if it is double digit
			fractPart = fractPart*base - prod;// updating the fractional part to the fractonal part of the previous fractional part times base
			lim--;// Decreasing the limit by 1
		}
		return convtd;// Returning the converted number
	}

	// Function to convert the original number of original base to converted base
	private void convert()
	{	
		// Converting the number
		if(orgBase==10)
			convtNum = fromDecimalToOther(convtBase, Double.valueOf(orgNum));
		else if(convtBase==10)
			convtNum = Double.toString(fromOtherToDecimal(orgBase, orgNum));
		else
			convtNum = fromDecimalToOther(convtBase, fromOtherToDecimal(orgBase, orgNum));

		// Adding the sign if required
		if(sign == '-')
			convtNum = sign + convtNum;
	}

	// Function to display the original number and converted number
	private void display()
	{
		System.out.print("\n(" + (sign=='\0'?"":sign) + "" + orgNum + ")base" + orgBase);
		convert();
		System.out.println(" = (" + convtNum + ")base" + convtBase);
	}

	// Function that calls input and display
	void operate()
	{
		input();
		display();
	}

	// Main method to create object and call functions
	public static void main(String[] args)
	{
		// Creating object
		NumConvert ncv=new NumConvert();

		// Calling appropriate function
		ncv.operate();
	}
}