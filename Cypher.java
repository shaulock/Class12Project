import java.util.*;

class Cypher
{
	int keyV, keyC, keyO, l;
	String s, f;
	char[] V_arr, C_arr, O_arr, letarr;
	int[] Vpos, Cpos, Opos, posarr;

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

	void input(int choice)
	{
		Scanner sc=new Scanner(System.in);
		switch(choice)
		{
			case 1:
			System.out.println("\n\nEnter a string to encrypt :");
			s = sc.nextLine();
			break;
			case 2:
			System.out.println("\n\nEnter an encrypted string to decrypt :");
			s = sc.nextLine().trim();
			break;
		}
		l = s.length();
	}

	char[] append(char[] arr, char a)
	{
		char[] temp = new char[arr.length + 1];
		for (int i = 0; i < arr.length; i++)
		{
			temp[i] = arr[i];
		}
		temp[arr.length] = a;
		return temp;
	}

	int[] append(int[] arr, int a)
	{
		int[] temp = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++)
		{
			temp[i] = arr[i];
		}
		temp[arr.length] = a;
		return temp;
	}

	void generateKey()
	{
		int key = (int)(Math.random()*1000000)%1000000;
		keyV = key / 10000;
		keyC = (key / 100) % 100;
		keyO = key % 100;
	}

	void extractKey()
	{
		String key[] = (s.substring(0, 4) + s.substring(l-4, l)).split("\\s+");
		keyV = Integer.valueOf(key[0]);
		keyC = Integer.valueOf(key[1]);
		keyO = Integer.valueOf(key[2]);
		s = s.substring(4, l-4);
		l = s.length();
	}

	char[] increment(char[] arr, int k)
	{
		int len = arr.length;
		char[] newarr = new char[len];
		for(int i = 0; i < len; i++)
		{
			newarr[(i+k)%len] = arr[i];
		}
		return newarr;
	}

	char[] decrement(char[] arr, int k)
	{
		int len = arr.length;
		char[] newarr = new char[len];
		for(int i = 0; i < len; i++)
		{
			newarr[i] = arr[(i+k)%len];
		}
		return newarr;
	}

	void fillArrs()
	{
		for (int i = 0; i < l; i++)
		{
			char c = s.charAt(i);
			char ch = Character.toUpperCase(c);
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

	void joinArrs()
	{
		letarr = new char[V_arr.length + C_arr.length + O_arr.length];
		posarr = new int[Vpos.length + Cpos.length + Opos.length];
		for(int i = 0; i < V_arr.length; i++)
		{
			letarr[i] = V_arr[i];
			posarr[i] = Vpos[i];
		}
		for(int i = 0; i < C_arr.length; i++)
		{
			letarr[i + V_arr.length] = C_arr[i];
			posarr[i + Vpos.length] = Cpos[i];
		}
		for(int i = 0; i < O_arr.length; i++)
		{
			letarr[i + V_arr.length + C_arr.length] = O_arr[i];
			posarr[i + Vpos.length + Cpos.length] = Opos[i];
		}
	}

	void sortArrs()
	{
		int len = posarr.length;
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

	void formStringFromArrs()
	{
		for(int i = 0; i < letarr.length; i++)
			f = f + letarr[i];
	}

	void encrypt()
	{
		generateKey();
		fillArrs();
		V_arr = increment(V_arr, keyV);
		C_arr = increment(C_arr, keyC);
		O_arr = increment(O_arr, keyO);
		joinArrs();
		sortArrs();
		formStringFromArrs();
		f = (keyV<10?"0"+Integer.toString(keyV):Integer.toString(keyV)) + " " + Integer.toString(keyC/10) 
			 + f + (keyC%10) + " " + (keyO<10?"0"+Integer.toString(keyO):Integer.toString(keyO));
		Cypher cph=new Cypher();
		cph.s = f;
		cph.l = f.length();
		cph.decrypt();
		if(f.equals(cph.f))
			encrypt();
	}

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


	void display(int choice)
	{
		switch(choice)
		{
			case 1:
			input(choice);
			encrypt();

			System.out.println("\n\nEncrypted String Is :-");
			break;
			case 2:
			input(choice);
			decrypt();
			System.out.println("\n\nDecrypted String Is :-");
			break;
			default:
			System.out.println("\n\nWrong choice... Let's run through the input again, shall we?");
			operate();
			System.exit(0);
		}
		System.out.println(f + "\n");
	}

	int input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\n\nEnter :\n\t1 For Encryption\n\t2 For Decryption");
		System.out.print("\n\nEnter choice : ");
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

	void operate()
	{
		int choice = input();
		display(choice);
	}

	public static void main(String[] args)
	{
		Cypher cyp=new Cypher();
		cyp.operate();
	}
}