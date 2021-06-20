/*
Odd characters less than ten

characters alternate alphabet and digit

alphabet between j and t only

no even digit after j or t
*/


import java.util.*;

class checkPass
{
	String pass;
	int l;

	checkPass()
	{
		pass = "";
		l = 0;
	}

	void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the password : ");
		pass = sc.nextLine();
		l = pass.length();
		if(l>10 || l%2==0)
			inValid("There are even number of digits or the password is longer than 10 characters");
	}

	void alternateCheck()
	{
		int what = 0;
		for(int i = 0; i < l; i++)
		{
			char c = pass.charAt(i);
			c = Character.toUpperCase(c);
			if(c >= 'A' && c <= 'Z')
			{
				if(c < 'J' || c > 'T')
					inValid("There are alphabets outside the range of 'J' to 'T'");
				if(what != 1)
					what = 1;
				else
					inValid("There are consecutive alphabets or numbers");
			}
			else if(c >= '0' && c <= '9')
			{
				if(what != 2)
					what = 2;
				else
					inValid("There are consecutive alphabets or numbers");
			}
			else
				inValid("There are characters other than alphabets or numbers");
		}
	}

	void alphaCheck()
	{
		for(int i = 0; i < l; i++)
		{
			char c = Character.toUpperCase(pass.charAt(i));
			if(c == 'J' || c == 'T')
				if(i+1 < l)
					if(Integer.valueOf(pass.charAt(i+1))%2==0)
						inValid("Alphabet 'J' or 'T' is followed by an even digit");
		}
	}

	void inValid(String s)
	{
		System.out.println(s);
		System.exit(1);
	}

	public static void main(String[] args)
	{
		checkPass chp=new checkPass();
		chp.input();
		chp.alternateCheck();
		chp.alphaCheck();
		System.out.println("The password is valid");
	}
}