/*

S =     m!
	____________

	p^m  +  (m-1)!
		  __________
		  p^(m-1) + (m-2)!
		           ___________
		           ........
		           		p^(m-m)

*/

import java.util.*;

class clSomeSeries
{
	int m, p;
	double sum;

	clSomeSeries()
	{
		m = p = 0;
		sum = 0.0;
	}

	int fnFact(int num)
	{
		if(num==1 || num==0)
			return 1;
		return num * fnFact(num-1);
	}

	long fnPower(int a, int p)
	{
		if(p==0)
			return 1;
		if(a==1)
			return 1;
		return a * fnPower(a, p-1);
	}

	void fnCalculate()
	{
		for(int i = 0; i <= m; i++)
		{
			if(i==0)
				sum = (double)fnPower(p, i);
			else
			{
				sum = (double)fnFact(i)/(double)((fnPower(p, i)) + sum);
			}
		}
	}

	void fnInput()
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.print("Enter the value of m : ");
			try
			{
				m = sc.nextInt();
				if(m > 0)
					break;
				else
					System.out.println("m cannot be less than 1");
			}
			catch(Exception e)
			{
				System.out.println("Please enter an integer only");
			}
		}
		while(true)
		{
			System.out.print("Enter the value of p : ");
			try
			{
				p = sc.nextInt();
				if(p > 0)
					break;
				else
					System.out.println("p cannot be less than 1");
			}
			catch(Exception e)
			{
				System.out.println("Please enter en integer only");
			}
		}
	}

	void fnPrint()
	{
		System.out.println("The sum of the series for which m = " + m + " and p = " + p + " is : " + sum);
	}

	public static void main(String[] args)
	{
		clSomeSeries css=new clSomeSeries();
		css.fnInput();
		css.fnCalculate();
		css.fnPrint();
	}
}