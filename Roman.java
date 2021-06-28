import java.util.*;
 
class Roman
{
    // Declaring class variables
    int n;// Stores the decimal number
    String r;// Storees the roman number

    // Default constructor
    Roman()
    {
        // Initialising class variables to default values
        n = 0;
        r = "";
    }

    // Function that takes integer input from user
    int get_int(String s)
    {
        // Taking input
        Scanner sc=new Scanner(System.in);
        System.out.print(s);
        try
        {
            int x = sc.nextInt();
            return x;// Returning input integer
        }
        catch(Exception e)// Input mismatch
        {
            System.out.println("Please enter an integer only");
            get_int(s);// calling function again
        }
        return 0;
    }

    void input()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Choices :- \n\t1. Roman to Integer\n\t2. Integer to Roman");
        int choice = get_int("Enter choice : ");
        switch(choice)
        {
            case 1:
            System.out.print("Enter a roman numeral : ");
            r = sc.next();
            display('r');
            break;
            case 2:
            n = get_int("Enter an integer : ");
            while(n <= 0)
            {
                System.out.println("Integer to convert must be greater than ZERO");
                n = get_int("Enter a POSITIVE integer : ");
            }
            display('i');
            break;
            default:
            System.out.println("Wrong choice");
            input();
            break;
        }
    }

    int value(char r)
    {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    int romanToDecimal(String str)
    {
        int res = 0;
 
        for (int i = 0; i < str.length(); i++)
        {
            int s1 = value(str.charAt(i));

            if (i + 1 < str.length())
            {
                int s2 = value(str.charAt(i + 1));
 
                if (s1 >= s2)
                {
                    res = res + s1;
                }
                else
                {
                    res = res + s2 - s1;
                    i++;
                }
            }
            else
            {
                res = res + s1;
            }
        }

        return res;
    }

    String decimalToRoman(int n)
    {
        String roman = "";
        if(n >= 4000)
        {
            System.out.println("Numbers more than 3999 are not supported");
            System.exit(1);
            return "";
        }
        while(n > 0)
        {
            if(n >= 1000)
            {
                for(int i = 0; i < n/1000; i++)
                    roman = roman + "M";
                n = n%1000;
            }
            else if(n >= 500)
            {
                if(n < 900)
                {
                    roman = roman + "D";
                    n = n%500;
                }
                else
                {
                    roman = roman + "CM";
                    n = n%100;
                }
            }
            else if(n >= 100)
            {
                if(n < 400)
                {
                    for(int i = 0; i < n/100; i++)
                        roman = roman + "C";
                    n = n%100;
                }
                else
                {
                    roman = roman + "CD";
                    n = n%100;
                }
            }
            else if(n >= 50)
            {
                if(n < 90)
                {
                    roman = roman + "L";
                    n = n%50;
                }
                else
                {
                    roman = roman + "XC";
                    n = n%10;
                }
            }
            else if(n >= 10)
            {
                if(n < 40)
                {
                    for(int i = 0; i < n/10; i++)
                        roman = roman + "X";
                    n = n%10;
                }
                else
                {
                    roman = roman + "XL";
                    n = n%10;
                }
            }
            else if(n >= 5)
            {
                if(n < 9)
                {
                    roman = roman + "V";
                    n = n%5;
                }
                else
                {
                    roman = roman + "IX";
                    n = 0;
                }
            }
            else
            {
                if(n < 4)
                {
                    for(int i = 0; i < n; i++)
                        roman = roman + "I";
                    n = 0;
                }
                else
                {
                    roman = roman + "IV";
                    n = 0;
                }
            }
        }

        return roman;
    }

    void display(char choice)
    {
        switch(choice)
        {
            case 'r':
            int convt = romanToDecimal(r);
            if(convt == romanToDecimal(decimalToRoman(convt)))
                System.out.println(r + " converted to integer is " + romanToDecimal(r));
            else
            {
                System.out.println("It is not a valid Roman number");
                System.exit(1);
            }
            break;
            case 'i':
            System.out.println(n + " converted to roman is " + decimalToRoman(n));
            break;
        }
    }

    public static void main(String args[])
    {
        Roman rmn = new Roman();
        rmn.input();
    }
}