import java.util.*;

class Primes
{
    // Declaring class variables
    double sum;// To store the brun constant

    // Function to check if the number is prime or not
    int primeCheck(int n, int i)
    {
        // Base cases
    	if(n==3)
    		return n;
        if(n%i==0)
            return 0;
        if(i>=(int)Math.sqrt(n))
            return n;

        // Recursive case
        return primeCheck(n, i+1);
    }

    // Function to calculate the sum to the reciprocal of twin primes
    double sumTwinPrime(int n)
    {
        return (1.0/n) + (1.0/(n+2));
    }

    // Function to calculate the brun's constant
    void BrunConstant(int n)
    {
        sum = 0.0;// Sum at the start
        int a = 1;// Term number
        int p = 3;// Number to check

        // Calculating the constant
        while(a <= n)
        {
            if(primeCheck(p, 2)==p && primeCheck(p+2, 2)==p+2)
            {
            	a++;
            	sum += sumTwinPrime(p);
            }
            p++;
        }
    }

    // Main method to create object and call functions accordingly
    public static void main(String[] args)
    {
        Primes prm=new Primes();// Creating object

        // Taking input and calling functions
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of terms : ");
        int n = 0;
        try
        {
        	n = sc.nextInt();
            // Validating the input
        	if(n<=0)
        	{
        		System.out.println("Number of terms should be more than 0");
        		main(args);// Calling function again if invalid input detected
        		return;
        	}
        	else
        	{
				prm.BrunConstant(n);
				System.out.println("The Brun's Constant for " + n + " is " + prm.sum);
        	}
        }
        catch(Exception e)// Input mismatch
        {
        	System.out.println("Enter an integer only please");
        	main(args);// Calling function again if invalid input detected
            return;
        }
    }
}