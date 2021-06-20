import java.util.*;

class Number
{
	double d;
	char choice;
	String converted;

	Number()
	{
		d = 0.0;
		choice = '\0';
		converted = "";
	}

	void inputNum()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter a decimal number : ");
			d = sc.nextDouble();
		}
		catch(Exception e)
		{
			System.out.println("Enter a decimal NUMBER only");
			inputNum();
		}
	}

	void inputChoice()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("choices :-\n\t1. Decimal to Binary\n\t2. Decimal to Octal\n\t3. Decimal to Hexadecimal");
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
				default:
				System.out.println("Enter a valid choice");
				inputChoice();
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Enter a valid choice");
			inputChoice();
		}
	}

	int get_int_part(double n)
	{
		String temp = "";
		String dec = Double.toString(n);
		int l = dec.length();
		for(int i = 0; i < l; i++)
		{
			if(dec.charAt(i)=='.')
				break;
			else
				temp = temp + dec.charAt(i);
		}
		return Integer.valueOf(temp);
	}

	void convert()
	{
		char sign = '+';
		if(d < 0)
		{
			sign = '-';
			d *= -1;
		}
		int intPart = get_int_part(d);
		double fractPart = d - Math.floor(d);
		int base = choice=='B'?2:choice=='O'?8:16;

		while(intPart > 0)
		{
			int mod = intPart % base;
			if(mod<=9)
				converted = Integer.toString(mod) + converted;
			else
				converted = (char)((int)'A' + mod%10) + converted;
			intPart = intPart/base;
		}

		if(fractPart == 0.0)
			return;
		converted = converted + ".";
		
		int f = 0;
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

	void display()
	{
		System.out.println(d + " Converted to " + (choice=='B'?"Binary":choice=='O'?"Octal":"Hexadecimal") + " is " + converted);
	}

	public static void main(String[] args)
	{
		Number nbr=new Number();
		nbr.inputNum();
		nbr.inputChoice();
		nbr.convert();
		nbr.display();
	}
}