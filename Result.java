import java.util.*;

class Result extends FinalExam
{
	// Declaring class variables
	double total_marks;// To store total marks

	// constructor to initialise data members
	Result(double a, double b, double c, double d, double e)
	{
		super(a,b,c,d,e);// Calling the constructor of FinalExam
		total_marks = 0.0;
	}

	// Function to compute total marks
	void compute()
	{
		total_marks = Math.round(0.2*(ut1+ut2+ut3+ut4)+0.8*ft);// Storing total marks
	}

	// Function to display all marks
	void disp()
	{
		super.disp();// Calling display function of FinalExam
		System.out.println("Total Marks"+"\t      "+total_marks);// Displaying total marks
	}

	// Main method to create object and call functions accordingly
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		// Taking input of the marks
		System.out.println("Enter marks of unit test 1");
		double a = sc.nextDouble();
		System.out.println("Enter marks of unit test 2");
		double b = sc.nextDouble();
		System.out.println("Enter marks of unit test 3");
		double c = sc.nextDouble();
		System.out.println("Enter marks of unit test 4");
		double d = sc.nextDouble();
		System.out.println("Enter marks of final term");
		double e = sc.nextDouble();
		// Creating object
		Result obj=new Result(a,b,c,d,e);
		// Calling functions accordingly
		obj.compute();
		obj.disp();
	}
}
	
class FinalExam extends ClassTest
{
	// Declaring class variables
	double ft;// Storing final term marks

	// Constructor to initialise data members
	FinalExam(double a, double b, double c, double d, double e)
	{
		super(a,b,c,d);// Calling constructor of ClassTest
		ft=e;// Storing the final term marks
	}

	// Function to display the marks
	void disp()
	{
		super.disp();// Calling display of ClassTest
		System.out.println("Final Term"+"\t      "+ft);// Printing Fial Term marks
	}
}

class ClassTest
{
	// Declaring class variables
	double ut1, ut2, ut3, ut4;// Unit test marks

	// Constructor to initialise data members
	ClassTest(double a, double b, double c, double d)
	{
		// Storing UT marks
		ut1=a;
		ut2=b;
		ut3=c;
		ut4=d;
	}

	// Function to display the marks
	void disp()
	{
		// Printing the marks in tabular form
		System.out.println("   Test    \tMarks Obtained");
		System.out.println("Unit Test 1"+"\t      "+ut1);
		System.out.println("Unit Test 2"+"\t      "+ut2);
		System.out.println("Unit Test 3"+"\t      "+ut3);
		System.out.println("Unit Test 4"+"\t      "+ut4);
	}
}