import java.util.*;

class Primes
{
    double sum;

    int primeCheck(int n, int i)
    {
    	if(n==3)
    		return n;
        if(n%i==0)
            return 0;
        if(i>=(int)Math.sqrt(n))
            return n;
        return primeCheck(n, i+1);
    }

    double sumTwinPrime(int n)
    {
        return (1.0/n) + (1.0/(n+2));
    }

    void BrunConstant(int n)
    {
        sum = 0.0;
        int a = 1;
        int p = 3;
        while(a <= n)
        {
            int q = p + 2;
            if(primeCheck(p, 2)==p && primeCheck(q, 2)==q)
            {
            	a++;
            	sum += sumTwinPrime(p);
            }
            p++;
        }
    }

    public static void main(String[] args)
    {
        Primes prm=new Primes();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of terms : ");
        int n = 0;
        try
        {
        	n = sc.nextInt();
        	if(n<=0)
        	{
        		System.out.println("Number of terms should be more than 0");
        		main(args);
        		return;
        	}
        	else
        	{
				prm.BrunConstant(n);
				System.out.println("The Brun's Constant for " + n + " is " + prm.sum);
        	}
        }
        catch(Exception e)
        {
        	System.out.println("Enter an integer only please");
        	main(args);
        	return;
        }
    }
}