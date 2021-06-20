import java.util.*;

class clTravel
{
	// Declaring class variables
	int arAge[]/* To store the ages of the tourists */, arFreqDist[]/* To store the age distribution requency table */;

	// Default constructor to initialise class variables to default values
	clTravel()
	{
		arAge=new int[200];
		arFreqDist=new int[5];
		for (int i = 0;i < 5;i++)
		{
			arFreqDist[i] = 0; // Initialising each element to 0
		}
	}

	// Function to take input of the ages of all the tourists
	void fnReadAge()
	{
		Scanner sc=new Scanner(System.in);
		int i = 0/* LCV */, x/* Temporary variable */;
		while(i < arAge.length)
		{
			System.out.print("Enter the age of tourist number " + (i+1) + ": ");
			x = sc.nextInt();

			// Validating input
			if(x <= 0)
			{
				System.out.println("Age should be greater than zero");
			}
			else
			{
				arAge[i] = x; // Putting the validated input in the array
				i++;
			}
		}
	}

	// Function to fill the distribution array
	void fnFrequency()
	{
		for(int i = 0; i < arAge.length; i++)
		{
			// Incrementing the respective age group's slot by 1 each time there is a age inside that age proup
			if(arAge[i] <= 20)
				arFreqDist[0]++;
			else if(arAge[i] <= 40)
				arFreqDist[1]++;
			else if(arAge[i] <= 60)
				arFreqDist[2]++;
			else if(arAge[i] <= 80)
				arFreqDist[3]++;
			else
				arFreqDist[4]++;
		}
	}

	// Function to print the results in tabular form
	void fnShowFreq()
	{
		System.out.println("Age groups:\t1-20\t21-40\t41-60\t61-80\t80<");
		System.out.println("Frequency :\t"+arFreqDist[0]+"\t"+arFreqDist[1]+"\t"+arFreqDist[2]+"\t"+arFreqDist[3]+"\t"+arFreqDist[4]);
	}

	// Main method to create object and call the required functions
	public static void main(String[] args)
	{
		clTravel clt=new clTravel();
		clt.fnReadAge();
		clt.fnFrequency();
		clt.fnShowFreq();
	}
}