import java.util.*;

class NumConvert
{
	int orgBase, convtBase;
	String orgNum, convtNum;
	char sign;

	NumConvert()
	{
		orgBase = convtBase = 0;
		orgNum = convtNum = "";
		sign = '\0';
	}

	private int get_int(String s)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print(s);
		try
		{
			int i = sc.nextInt();
			return i;
		}
		catch(Exception e)
		{
			System.out.println("Please enter an integer only");
			return get_int(s);
		}
	}

	private boolean verifyNumber()
	{
		int countDecimal = 0;
		if(orgNum.length()==0)
			return false;
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
		for(int i = 0; i < orgNum.length(); i++)
		{
			if(orgNum.charAt(i)=='.')
				countDecimal++;
		}
		if(countDecimal>1)
			return false;
		for(int i = 0; i < orgNum.length(); i++)
		{
			char c = orgNum.charAt(i);
			if(c=='.')
				continue;
			if(c >= '0' && c <= '9')
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
		return true;
	}

	private void input()
	{
		int choice = 0;
		while(true)
		{
			System.out.println("Choose the base of your number :-\n\t1. Binary\n\t2. Octal\n\t3. Decimal\n\t4. Hexa-Decimal");
			choice = get_int("Enter choice : ");
			if(choice >= 1 && choice <= 4)
				break;
			System.out.println("Please enter correct choice");
		}
		switch(choice)
		{
			case 1:orgBase = 2;break;
			case 2:orgBase = 8;break;
			case 3:orgBase = 10;break;
			case 4:orgBase = 16;break;
		}
		orgNum = "";
		System.out.println();
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter a number of base " + orgBase + ": ");
			orgNum = sc.nextLine();
			if(verifyNumber()==true)
				break;
			System.out.println("Please enter a number of base " + orgBase);
		}
		System.out.println();
		while(true)
		{
			System.out.println("Choose the base in which to convert your number :-\n\t1. Binary\n\t2. Octal\n\t3. Decimal\n\t4. Hexa-Decimal");
			choice = get_int("Enter choice : ");
			if(choice >= 1 && choice <= 4)
				break;
			System.out.println("Please enter correct choice");
		}
		switch(choice)
		{
			case 1:convtBase = 2;break;
			case 2:convtBase = 8;break;
			case 3:convtBase = 10;break;
			case 4:convtBase = 16;break;
		}
	}

	private int getHexCharValue(char a)
	{
		char[] arr = new char[6];
		for(char i = 'A', j = '0'; i <= 'F' && j < '6'; i++, j++)
			arr[Character.getNumericValue(j)] = i;
		int i = 0;
		for(i = 0; i < 6; i++)
		{
			if(arr[i]==a)
				break;
		}
		return i+10;
	}

	private char getHexValueChar(int a)
	{
		return (char)('A'+(a-10));
	}

	private int power(int a, int p)
	{
		if(p==0)
			return 1;
		return a*power(a, p-1);
	}

	private double reversePower(int a, int p)
	{
		return (double)1.0/power(a, p);
	}

	private int getIntPart(double a)
	{
		return (int) Math.floor(a);
	}

	private double fromOtherToDecimal(int base, String n)
	{
		double convtd = 0.0;
		String intPart = "", fractPart = "";
		boolean decimal=true;
		for(int i = 0; i < n.length(); i++)
		{
			if(n.charAt(i)=='.')
			{
				intPart = n.substring(0, i);
				fractPart = n.substring(i, n.length());
				decimal = true;
				break;
			}
			else
				decimal = false;
		}
		if(!decimal)
			intPart = n;
		String temp = "";
		for(int i = 0; i < intPart.length(); i++)
		{
			temp = intPart.charAt(i) + temp;
		}
		intPart = temp;
		for(int i = 0; i < intPart.length(); i++)
		{
			char c = intPart.charAt(i);
			int p = power(base, i);
			if(Character.isDigit(c))
				convtd += Character.getNumericValue(c)*p;
			else
				convtd += getHexCharValue(c)*p;
		}
		for(int i = 0; i < fractPart.length(); i++)
		{
			if(i==0)
				continue;
			char c = fractPart.charAt(i);
			double p = reversePower(base, i);
			if(Character.isDigit(c))
				convtd += Character.getNumericValue(c)*p;
			else
				convtd += getHexCharValue(c)*p;
		}
		return convtd;
	}

	private String fromDecimalToOther(int base, double n)
	{
		String convtd = "";
		if(n==0.0)
			return "0";
		int intPart = getIntPart(n);
		double fractPart = n - intPart;
		while(intPart > 0)
		{
			int mod = intPart % base;
			if(mod <= 9)
				convtd = Integer.toString(mod) + convtd;
			else
				convtd = getHexValueChar(mod) + convtd;
			intPart = intPart / base;
		}
		if(fractPart > 0)
			convtd = convtd + '.';
		int lim = 10;
		while(fractPart > 0 && lim > 0)
		{
			int prod = getIntPart(fractPart * base);
			if(prod <= 9)
				convtd = convtd + Integer.toString(prod);
			else
				convtd = convtd + getHexValueChar(prod);
			fractPart = fractPart*base - prod;
			lim--;
		}
		return convtd;
	}

	private void convert()
	{
		if(orgBase==10)
			convtNum = fromDecimalToOther(convtBase, Double.valueOf(orgNum));
		else if(convtBase==10)
			convtNum = Double.toString(fromOtherToDecimal(orgBase, orgNum));
		else
			convtNum = fromDecimalToOther(convtBase, fromOtherToDecimal(orgBase, orgNum));

		if(sign == '-')
			convtNum = sign + convtNum;
	}

	private void display()
	{
		System.out.print("\n(" + (sign=='\0'?"":sign) + "" + orgNum + ")base" + orgBase);
		convert();
		System.out.println(" = (" + convtNum + ")base" + convtBase);
	}

	void operate()
	{
		input();
		display();
	}

	public static void main(String[] args)
	{
		NumConvert ncv=new NumConvert();
		ncv.operate();
	}
}