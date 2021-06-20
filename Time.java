import java.util.*;

class Time
{
	int hh, mm;

	Time()
	{
		hh = 0;
		mm = 0;
	}

	int get_int(String s)
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.print(s);
			int x = sc.nextInt();
			return x;
		}
		catch(Exception e)
		{
			System.out.println("Please enter an integer only");
			get_int(s);
		}
		return -1;
	}

	void input()
	{
		hh = get_int("Enter the hour : ");
		while(hh > 12 || hh < 1)
		{
			System.out.println("Hour should be between 1 to 12 both inclusive");
			hh = get_int("Enter the hour : ");
		}
		mm = get_int("Enter the minute : ");
		while(mm > 59 || mm < 0)
		{
			System.out.println("Minute should be between 0 to 59 both inclusive");
			mm = get_int("Enter the minue : ");
		}
	}

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
			case 14: return "fourteen";
			case 16: return "sixteen";
			case 17: return "seventeen";
			case 18: return "eighteen";
			case 19: return "nineteen";
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
		return "";
	}

	void display()
	{
		if(mm==0)
			System.out.println(convt(hh) + " o' clock");
		else if(mm==30)
			System.out.println("half past " + convt(hh));
		else if(mm==15)
			System.out.println("quarter past " + convt(hh));
		else if(mm < 30)
			System.out.println(convt(mm) + " minutes past " + convt(hh));
		else if(mm==45)
		{
			if(hh+1 == 13)
				System.out.println("quarter to " + convt(1));
			else
				System.out.println("quarter to " + convt(hh+1));
		}
		else
		{
			if(hh+1 == 13)
				System.out.println(convt(60-mm) + " minutes to " + convt(1));
			else
				System.out.println(convt(60-mm) + " minutes to " + convt(hh+1));
		}
	}

	public static void main(String[] args)
	{
		Time tme=new Time();
		tme.input();
		tme.display();
	}
}