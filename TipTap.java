import java.util.*;

class TipTap
{
	int tip, tap, mak;

	TipTap()
	{
		tip = 0;
		tap = 0;
		mak = 0;
	}

	void input()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter the number : ");
			mak = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Please enter an integer only");
			input();
		}
	}

	int rmDigit(int n, int i)
	{
		int a = 0;
		for(int j=n, k=1; j>0; j/=10, k++)
		{
			if(j%10==i)
			{
				a = k;
				break;
			}
		}

		if(a==1)
			return n/10;
		else
		{
			int p = 1;
			for(int j = 0; j < a; j++)
				p *= 10;
			return (n/p)*(p/10) + (n%(p/10));
		}

	}

	int desc(int n)
	{
		if(n<10)
			return n;

		int min = 9, dup = n;
		for(int i=n; i>0; i/=10)
		{
			if(i%10 <= min)
				min=i%10;
		}
		n = rmDigit(n, min);
		return desc(n)*10 + min;
	}

	int reverse(int n)
	{
		if(n < 10)
			return n;

		int c = 0;
		for(int i = n; i > 0; i/=10)
			c++;
		
		int p = 1;
		for(int i = 1; i < c; i++)
			p*=10;
		
		return (n%10)*p + reverse(n/10);
	}

	boolean perfectSqr(int n)
	{
		double sqrt = Math.sqrt(n);
		if((double)((int)sqrt) == sqrt)
			return true;
		else
			return false;
	}

	void display()
	{
		tap = desc(mak);
		tip = reverse(tap);
		String tp = Integer.toString(tip);
		String tP = Integer.toString(tap);
		while(tp.length()!=tP.length())
			tp = "0" + tp;

		System.out.println("New Number 1 : " + tp);
		System.out.println("New Number 2 : " + tP);
		System.out.println("The desired list is -");
		for(int i = 1; i <= tap; i++)
		{
			if(i >= tip && perfectSqr(i))
				System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		TipTap tPt=new TipTap();
		tPt.input();
		tPt.display();
	}
}