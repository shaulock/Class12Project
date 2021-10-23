import java.util.*;

class Cypher
{
	// Declaring class variables
	int keyV/* Key for vowels */, keyC/* Key for Consonants */, keyO/* Key for everything else*/, l/* length of the string */;
	String s/* Original string */, f/* Final string */;
	char[] V_arr/* Array of vowels */, C_arr/* Array of consonants */, O_arr/* Array of everything else */, letarr/* Array of everything combined */;
	int[] Vpos/* Array of position of vowels */, Cpos/* Array of position of consonants */, Opos/* Array of position of everything else */, posarr/* Array of position of everything combined */;

	// Default constructor to initialise data members
	Cypher()
	{
		keyV = keyC = keyO = l = 0;
		s = f = "";
		V_arr = new char[0];
		Vpos = new int[0];
		C_arr = new char[0];
		Cpos = new int[0];
		O_arr = new char[0];
		Opos = new int[0];
		letarr = new char[0];
		posarr = new int[0];
	}

	// Function to take input according to choice
	void input(int choice)
	{
		Scanner sc=new Scanner(System.in);
		switch(choice)
		{
			case 1:// For encryption
			System.out.println("Enter a string to encrypt :");
			s = sc.nextLine();
			break;
			case 2:// For decryption
			System.out.println("Enter an encrypted string to decrypt :");
			s = sc.nextLine().trim();
			break;
		}
		l = s.length();// Storing length of string
	}

	// Function to add an elemnt a to an array arr
	char[] append(char[] arr, char a)
	{
		char[] temp = new char[arr.length + 1];// Temporary array with size 1 more than arr
		// Filling to array
		for (int i = 0; i < arr.length; i++)
			temp[i] = arr[i];
		temp[arr.length] = a;
		return temp;// Returning the array
	}

	// Funtion to an element a to an array arr
	int[] append(int[] arr, int a)
	{
		int[] temp = new int[arr.length + 1];// Temporary array with size 1 more than arr
		// Filling the array
		for (int i = 0; i < arr.length; i++)
			temp[i] = arr[i];
		temp[arr.length] = a;
		return temp;// Returning the array
	}

	// Function to generate keys
	void generateKey()
	{
		int key = (int)(Math.random()*1000000)%1000000;
		keyV = key / 10000;
		keyC = (key / 100) % 100;
		keyO = key % 100;
	}

	// Function to extract keys
	void extractKey()
	{
		// Try-catch used to see if the keys are present unaltered
		try
		{
			String key[] = (s.substring(0, 4) + s.substring(l-4, l)).split("\\s+");
			keyV = Integer.valueOf(key[0]);
			keyC = Integer.valueOf(key[1]);
			keyO = Integer.valueOf(key[2]);
			s = s.substring(4, l-4);
			l = s.length();
		}
		catch(Exception e)
		{
			System.out.println("The encrypted string has been tampered or the key is incomplete... try again after checking what you entered");
			System.exit(0);
		}
	}

	// Functon to check the array if it is encryptable
	boolean checkArray(char[] arr)
	{
		for(int i = 0; i < arr.length - 1; i++)
			for(int j = i + 1; j < arr.length; j++)
				if(arr[j] != arr[i])
					return true;// If at least 1 element is different than the others
		return false;// If all elements same or array is empty
	}

	// Function to shift position of all elements in an array arr to the right by a number k
	char[] increment(char[] arr, int k)
	{
		int len = arr.length;// Length of the array
		char[] newarr = new char[len];// To store the new array
		// Filling the new array with elements having new positions
		for(int i = 0; i < len; i++)
			newarr[(i+k)%len] = arr[i];
		return newarr;// Returning the new array
	}

	// Function to shift position of all elements in an array arr to the left by a number k
	char[] decrement(char[] arr, int k)
	{
		int len = arr.length;// Length of the array
		char[] newarr = new char[len];// To store the new array
		// Filling the new array with elements having new positions
		for(int i = 0; i < len; i++)
			newarr[i] = arr[(i+k)%len];
		return newarr;// Returning the new array
	}

	// Function to fill the arrays with respective characters from the string
	void fillArrs()
	{
		for (int i = 0; i < l; i++)
		{
			char c = s.charAt(i);// Extracting characters
			char ch = Character.toUpperCase(c);// Changing them to upper case for ease of computation
			// Filling arrays accordingly
			if(ch >= 'A' && ch <= 'Z')
			{
				if(ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
				{
					V_arr = append(V_arr, c);
					Vpos = append(Vpos, i);
				}
				else
				{
					C_arr = append(C_arr, c);
					Cpos = append(Cpos, i);
				}
			}
			else
			{
				O_arr = append(O_arr, c);
				Opos = append(Opos, i);
			}
		}
	}

	// Function to join all arrays into a single array
	void joinArrs()
	{
		letarr = new char[V_arr.length + C_arr.length + O_arr.length];
		posarr = new int[letarr.length];
		for(int i = 0; i < letarr.length; i++)
		{
			if(i >= V_arr.length + C_arr.length)
			{
				letarr[i] = O_arr[i - V_arr.length - C_arr.length];
				posarr[i] = Opos[i - V_arr.length - C_arr.length];
			}
			else if(i >= V_arr.length)
			{
				letarr[i] = C_arr[i - V_arr.length];
				posarr[i] = Cpos[i - V_arr.length];
			}
			else
			{
				letarr[i] = V_arr[i];
				posarr[i] = Vpos[i];
			}
		}
	}

	// Fuction to sort the arrays
	void sortArrs()
	{
		int len = posarr.length;// Storing the length
		// Insertion sort on both the arrays based on the position array at once
		for(int i = 1; i < len; i++)
		{
			int key = posarr[i];
			char keyc = letarr[i];
			int j = i - 1;
			while(j >= 0 && posarr[j] > key)
			{
				posarr[j + 1] = posarr[j];
				letarr[j + 1] = letarr[j];
				j--;
			}
			posarr[j + 1] = key;
			letarr[j + 1] = keyc;
		}
	}

	// Function to make a string from the array
	void formStringFromArrs()
	{
		f = "";
		for(int i = 0; i < letarr.length; i++)
			f = f + letarr[i];
	}

	// Function to encrypt the string
	void encrypt()
	{
		generateKey();
		fillArrs();
		if(!(checkArray(V_arr) || checkArray(C_arr) || checkArray(O_arr)))
		{
			System.out.println("Sorry the string cannot be encrypted through this program");
			System.exit(0);
		}
		V_arr = increment(V_arr, keyV);
		C_arr = increment(C_arr, keyC);
		O_arr = increment(O_arr, keyO);
		joinArrs();
		sortArrs();
		formStringFromArrs();
		// Checking if the encrypted string is the same as the previous string
		if(f.equals(s))
		{
			encrypt();
		}
		// Placing the key in the encrypted string
		else
			f = (keyV<10?"0"+Integer.toString(keyV):Integer.toString(keyV)) + " " + Integer.toString(keyC/10) 
			 + f + (keyC%10) + " " + (keyO<10?"0"+Integer.toString(keyO):Integer.toString(keyO));
	}

	// Funtion to decrypt the string
	void decrypt()
	{
		extractKey();
		fillArrs();
		V_arr = decrement(V_arr, keyV);
		C_arr = decrement(C_arr, keyC);
		O_arr = decrement(O_arr, keyO);
		joinArrs();
		sortArrs();
		formStringFromArrs();
	}

	// Function to display the final string with message according to choice
	void display(int choice)
	{
		switch(choice)
		{
			case 1:// Encryption
			input(choice);
			encrypt();
			System.out.println("Encrypted String Is :-");
			break;
			case 2:// Decryption
			input(choice);
			decrypt();
			System.out.println("Decrypted String Is :-");
			break;
			default:// Wrong choice handeling
			System.out.println("Wrong choice... Let's run through the input again, shall we?");
			operate();
			System.exit(0);
		}
		System.out.println(f + "");// Printing final string
	}

	// Funtction to take input of choice
	int input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter :\n\t1 For Encryption\n\t2 For Decryption");
		System.out.print("Enter choice : ");
		// Try-catch to get an integer only
		try
		{
			int c = sc.nextInt();
			return c;
		}
		catch(Exception e)
		{
			System.out.println("Enter an integer choice only");
			return input();
		}
	}

	// Function that calls the display funtion and the choice input function
	void operate()
	{
		int choice = input();
		display(choice);
	}

	// Main method to create object and call appropriate functions
	public static void main(String[] args)
	{
		// Creating object
		Cypher cyp=new Cypher();

		// Calling appropriate functions
		cyp.operate();
	}
}