import java.util.*;

class Routes
{
	// Declraing class variables
	int n/* To store the number of cities */, rn/* To store the route number */;
	String cities[]/* To store the cities */, start/* To store the starting city */;

	// Default Constructor to initialize the class variables to default values;
	Routes()
	{
		n = 0;
		rn = 1;
		start = "";
	}

	// Function to validate ang get the number of cities
	void get_number(String s)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print(s);
		try
		{
			n = sc.nextInt();
			if(n < 4) // Validating input
			{
				System.out.println("At least 4 cities are required");
				get_number(s); // Calling the function again when user entered an invalid input
			}
		}
		catch(Exception e) // Handeling error when user enters something other than an integer
		{
			System.out.println("\nPlease enter an integer only");
			get_number(s); // Calling the function again when user entered an invalid input
		}

		cities = new String[n]; // Initializing the cities array
	}

	// Function to validate and get the names of cities from the user
	void get_cities(String str)
	{
		// Taking input
		Scanner sc=new Scanner(System.in);
		System.out.println(str);
		String s = sc.nextLine();
		s = s.trim(); // Trimming extra spaces
		int l = s.length();
		String ns=""; // temporary variable
		int a = 0, e = 0;
		for(int i = 0; i < l; i++)
		{
			char ch = s.charAt(i);
			if(ch==',' || ch==';')
			{
				try
				{
					cities[a] = ns.trim(); // Placing the name of the city into the cities
					if(ch==';')
						break; // breaking from loop when semi colon detected
					a++;
				}
				catch(ArrayIndexOutOfBoundsException ex) // catching error when user enters more cities
				{
					System.out.println("Please only enter as many cities as you specified");
					e = 1;
					break;
				}
				ns = "";
			}
			else if(a<n && i==l-1) // Checking if the user entered less number of cities
			{
				System.out.println("Please enter as many cities as you specified");
				e = 1;
				break;
			}
			else
			{
				if(i == l-1) // Checking if the user ended the list with a semi colon or not
				{
					System.out.println("Please make sure you placed a semi-colon(;) at the end");
					e = 1;
					break;
				}

				else
					ns = ns + ch;
			}
		}
		if(cities[n-1]==null && e==0) // Final checking if the user entered less number fo cities
		{
			System.out.println("Please enter as many cities as you specified");
			get_cities(str);
		}
		else if(e!=0) // if there was any error
			get_cities(str); // calling the function again
		else
		{
			// Checking if the user entered the same city twice
			For:
			for(int i = 0; i < n; i++)
			{
				String city = cities[i];
				for(int j = i + 1; j < n; j++)
					if(city==cities[j])
					{
						System.out.println("You entered one city two times");
						get_cities(str); // calling fuction when invalid input found
						break For;
					}
			}
			return;
		}
	}

	// Function to get the name of the starting sity
	void get_starting_city()
	{	
		// Taking input
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the city you want to start with : ");
		start = sc.nextLine().trim(); // trimming extra spaces
		//Checking if the name of the city exists in the list entered
		boolean found = false; // setting found as false
		int i = 0; // LCV
		while(i < n)
		{
			if(found==true)
				break; // breaking if already found
			if(cities[i].equals(start))
				found=true; // setting found as true if found
			i++;
		}
		if(found)
		{
			// if found, removing the city from the array to make things easy
			String newarr[]=new String[n-1]; // initialising a temporary array with size 1 less than the original array
			int a = 0;
			for(int j = 0; j < n; j++)
			{
				if(j==i-1)
					continue; // continuing if the city is the starting city
				else
				{
					newarr[a] = cities[j];
					a++;
				}
			}
			cities=new String[n-1]; // re-initialising the original array with size 1 less than before
			for(int j = 0; j < n-1; j++)
				cities[j] = newarr[j]; // re-filling the array using the temporary array we just filled
			return;
		}
		else
		{
			System.out.println("The city was not found in the list");
			get_starting_city(); // If the city was not found re-running the function
		}
	}

	// Displaying the possible routes
	void display(String s1[], String s2[])
	{
		if (s2.length <= 1)
		{
			int l = s1.length + s2.length;
			int l1 = s1.length;
			System.out.print("Route " + rn + ": " + start + " => "); // Printing the route generated
			for(int i = 0; i < l; i++)
			{
				// The if-ese block is for printing the route generated
				if(i < l1)
				{
					if(i<l-1)
						System.out.print(s1[i] + " => ");
					else
						System.out.println(s1[i]);
				}
				else
				{
					if(i == l-1)
						System.out.println(s2[i-l1]);
					else
						System.out.print(s2[i-l1] + " => ");
				}
			}
			rn++; // incrementing the oute number
		}
		else
		{
			// Loop to generate routes
			for (int i = 0; i < s2.length; i++)
			{
				String x[]=new String[1]; // Temporary array to store the ith element of s2 array
				x[0] = s2[i]; // initialising the only term in the array to the ith element in the s2 array
				String y[]=new String[i]; // Temporary array to store all the elements before the ith element in the s2 array
				for(int j = 0; j < i; j++)
					y[j] = s2[j]; // initialising the elements of the array
				String z[]=new String[s2.length - (i+1)]; // Temporary array to store all elements in s2 array after the ith element
				for(int j = 0; j < z.length; j++)
				 	z[j] = s2[i+j+1]; // initialising the elements of the array
				String p[]=new String[s1.length + x.length]; // Temporary array to store the elements of s1 and x arrays respectively
				for(int j = 0; j < p.length; j++) // Filling the array p
					if(j < s1.length)
						p[j] = s1[j];
					else
						p[j] = x[j-s1.length];
				String q[]=new String[y.length + z.length]; // Temporary array to store the elements of y and z arrays respectively
				for(int j = 0; j < q.length; j++) // Filling the array q
					if(j < y.length)
						q[j] = y[j];
					else
						q[j] = z[j-y.length];
				display(p, q); // Calling the function with p and q as the parameters
			}
		}
	}

	// Function that calls the display function because display function is recursive
	void displayRoutes()
	{
		System.out.println("Possible routes are :-");
		String x[]=new String[0]; // initialising a null array
		display(x, cities); // calling the display function with null array and cities array respectively
	}

	// Main methpd to create object and call the required functions
	public static void main(String[] args)
	{
		Routes rts=new Routes();
		rts.get_number("Enter the number of cities you want to travel to : ");
		rts.get_cities("Enter the names of the cities SEPERATED BY COMMAS(,) and END THE LIST BY A SEMICOLON(;)");
		rts.get_starting_city();
		rts.displayRoutes();
	}
}