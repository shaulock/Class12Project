import java.util.*;

class StringBalance
{
	String s; int l;

	StringBalance()
	{
		s = "";
		l = 0;
	}

	void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a string : ");
		s = sc.nextLine();
		l = s.length();
	}

	boolean isBalanced()
	{
		String str = s.toUpperCase();
		int cA = 0, cZ = 0;
		for(int i=0; i<l; i++)
		{
			char c = str.charAt(i);
			
			if(c!='A' && c != 'Z')
				return false;

			if(c=='A')
				cA++;
			if(c=='Z')
				cZ++;
		}

		if(cA != cZ)
			return false;

		String st[]=new String[l];
		int ca=1, cz=1;
		for(int i = 0, a = 0; i < l; i++, a++)
		{
			char c = str.charAt(i);
			if(c=='A')
			{
				st[a] = Integer.toString(i) + c + Integer.toString(ca);
				ca++;
			}
			else
			{
				st[a] = Integer.toString(i) + c + Integer.toString(cz);
				cz++;
			}
		}
		for(int i = 0; i < l; i++)
		{
			String x = st[i];
			for(int j = 0; j < l; j++)
			{
				String y = st[j];
				if(x.charAt(1)!=y.charAt(1) && x.charAt(2)==y.charAt(2))
				{
					if(x.charAt(1)=='A' && y.charAt(1)=='Z' && x.charAt(0)>y.charAt(0))
						return false;
					if(x.charAt(1)=='Z' && y.charAt(1)=='A' && x.charAt(0)<y.charAt(0))
						return false;
				}
			}
		}

		return true;
	}

	void display()
	{
		System.out.println("Entered String : " + s);
		if(isBalanced())
			System.out.println("It is a \"Well Balanced String\".");
		else
			System.out.println("It is NOT a \"Well Balanced String\".");
	}

	public static void main(String[] args)
	{
		StringBalance stb=new StringBalance();
		stb.input();
		stb.display();
	}
}