import java.util.*;

class MagicSqr
{
	int msqr[][], n, sum;

	MagicSqr()
	{
		n = 3;
		msqr=new int[3][3];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				msqr[i][j] = 0;
	}

	void generate()
	{
		int number = 1;
    	int row = 0;
    	int column = n / 2;
    	int curr_row;
    	int curr_col;
    	while (number <= n * n)
    	{
	        msqr[row][column] = number;
	        number++;
	        curr_row = row;
	        curr_col = column;
	        row -= 1;
	        column += 1;
	        if (row == -1)
	            row = n - 1;
	        if (column == n)
	            column = 0;
	        if (msqr[row][column] != 0)
	        {
	            row = curr_row + 1;
	            column = curr_col;
	            if (row == -1)
	                row = n - 1;
	        }
	    }
	}

	void findSum()
	{
		sum = msqr[0][0] + msqr[1][1] + msqr[2][2];
	}

	int[] sort(int arr[], char c)
	{
		switch(c)
		{
			case 'a':
			for(int i = 0; i < arr.length; i++)
			{
				int min = i;
				for(int j = i + 1; j < arr.length; j++)
				{
					if(arr[j] < arr[min])
						min = j;
				}
				int temp = arr[min];
				arr[min] = arr[i];
				arr[i] = temp;
			}
			break;
			case 'd':
			for(int i = 0; i < arr.length; i++)
			{
				int max = i;
				for(int j = i + 1; j < arr.length; j++)
				{
					if(arr[j] > arr[max])
						max = j;
				}
				int temp = arr[max];
				arr[max] = arr[i];
				arr[i] = temp;
			}
			break;
		}
		return arr;
	}

	void sortPrint()
	{
		System.out.println("\nRow wise sorted array : \n");
		int sorted[][] = new int[3][3];
		for(int i = 0; i < 3; i++)
		{
			int temp[] = new int[3];
			for(int k = 0; k < 3; k++)
				temp[k] = msqr[i][k];
			temp = sort(temp, 'a');
			for(int k = 0; k < 3; k++)
				sorted[i][k] = temp[k];
		}
		displayArr(sorted);
		System.out.println("\nColumn wise sorted array : \n");
		for(int i = 0; i < 3; i++)
		{
			int temp[] = new int[3];
			for(int k = 0; k < 3; k++)
				temp[k] = msqr[k][i];
			temp = sort(temp, 'd');
			for(int k = 0; k < 3; k++)
				sorted[k][i] = temp[k];
		}
		displayArr(sorted);
	}

	void displayArr(int[][] arr)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				System.out.print(arr[i][j] + "\t");
			System.out.println();
		}
	}

	void display()
	{
		findSum();
		System.out.println("The 3X3 Magic Square(row-wise sum is mentioned at the end of each row\nand column-wise sum is mentioned at the end of each column) : \n");
		for(int i = 0; i <= 3; i++)
		{
			for(int j = 0; j <= 3; j++)
			{
				if(i==3 && j != 3)
					System.out.print("_\t");
				else if(j==3 && i != 3)
					System.out.print("|" + sum + "\t");
				else if(i==3 && j==3)
					continue;
				else
					System.out.print(msqr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println(sum + "\t" + sum + "\t" + sum);
		sortPrint();
	}

	public static void main(String[] args)
	{
		MagicSqr msq=new MagicSqr();
		msq.generate();
		msq.display();
	}
}