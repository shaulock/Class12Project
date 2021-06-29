/**
 * Algorithm
 * 
 * Start
 * Run a loop from 1 to 9
 * place 1 at the middle of the first row
 * now decrement the row and column by 1
 * if the row becomes -1 shift to the last row and if the column becomes -1 shift to the last column
 * try to place the next value in the loop at the new position
 * if the position is occupied already, go to the previous column and drop down 1 row and place the loop value there
 * continue the same logic until you reach 9
 * after the loop ends, print the array
 * sort the array row-wise in ascending order and print it
 * then sort the array in column-wise descending and print it
 * End
 */

import java.util.*;

class MagicSqr
{
	// Declating class variables
	int[][] msqr;// Array to store the magic square
	int n/* int to store the number of rows */, sum/* int to store the sum of each row or column */;

	// Default constructor
	MagicSqr()
	{
		// Initialising class variable to appropriate values
		n = 3;
		msqr=new int[3][3];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				msqr[i][j] = 0;
	}

	// Function to fill the magic square
	void generate()
	{
		int number = 1;// stores the current number to be placed in the magic square
    	int row = 0;// stores the row position at which number was stored
    	int column = n / 2;// stores the column position at which number was stored
    	int curr_row;// stores current row position
    	int curr_col;// stores current column position
    	while (number <= n * n)// loop to fill the magic square
    	{
	        msqr[row][column] = number;// placing number at row X column
	        number++;// incrementing number
	        curr_row = row;// updating current row position
	        curr_col = column;// updating current column position
	        row -= 1;// decrementing row position
	        column += 1;// incremeting column position

	        // Taking care of out of bounds
	        if (row == -1)
	            row = n - 1;
	        if (column == n)
	            column = 0;

	        // if next position is filled shifting to next row
	        if (msqr[row][column] != 0)
	        {
	            row = curr_row + 1;
	            column = curr_col;

	            // Taking care of out of bounds
	            if (row == -1)
	                row = n - 1;
	        }
	    }
	}

	// function to get the sum of a row or column
	void findSum()
	{
		sum = msqr[0][0] + msqr[0][1] + msqr[0][2];
	}

	// function to sort an array
	int[] sort(int arr[], char c)
	{
		switch(c)
		{
			case 'a':// Sorting in ascending order using selection sort
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
			case 'd':// Sorting in descending order using selection sort
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
		return arr;// returning the sorted array
	}

	// Function to print arrays after sorting
	void sortPrint()
	{
		System.out.println("\nRow wise sorted array : \n");
		// Row wise sorting
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
		displayArr(sorted);// printing sorted array
		
		System.out.println("\nColumn wise sorted array : \n");
		// column wise sorting
		for(int i = 0; i < 3; i++)
		{
			int temp[] = new int[3];
			for(int k = 0; k < 3; k++)
				temp[k] = msqr[k][i];
			temp = sort(temp, 'd');
			for(int k = 0; k < 3; k++)
				sorted[k][i] = temp[k];
		}
		displayArr(sorted);// printing sorted array
	}

	// Function to print an array
	void displayArr(int[][] arr)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				System.out.print(arr[i][j] + "\t");
			System.out.println();
		}
	}

	// Display function
	void display()
	{
		findSum();// calling the findSum function
		System.out.println("The 3X3 Magic Square(row-wise sum is mentioned at the end of each row" +
							+ "\nand column-wise sum is mentioned at the end of each column) : \n");

		// Printing with sum of the rows and columns mentioned
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
		
		// Printing sorted arrays
		sortPrint();
	}

	// Main method to create objects and call functions accordingly
	public static void main(String[] args)
	{
		MagicSqr msq=new MagicSqr();// Creating an object

		// Calling functions accordingly
		msq.generate();
		msq.display();
	}
}