import java.util.*;

class CircularQueue
{
    // Declaring class variables
    double Q[];// Array to be used as a Queue
    int N;// Size of the queue
    int front, rear;// Pointer to front and rear

    // Constructor to initialisize data members
    CircularQueue(int n)
    {
        N = n;
        front = rear = 0;
        Q = new double[N];
        for(int i = 0; i < N; i++)
            Q[i] = 0.0;
    }

    // Function to enqueue if possible else print error message
    void enqueue(double nm)
    {
        // Checking if enqueue is possible
        if(((rear + 1) % N) == front)
        {
            System.out.println("OVERFLOW");
            return;
        }
        Q[rear] = nm;
        rear = (rear + 1) % N;// Rear here points to the next empty cell after each entry
    }

    // Function to delqueue if possible else print error message
    double delqueue()
    {
        // Checking if delqueue is possible
        if(front == rear)//front and rear at same index position
        {
            System.out.println("UNDERFLOW");
            return -123456789.321;
        }
        double n = Q[front];
        front = (front + 1) % N;// Front here points to the next filled cell after deleting an entry
        return n;// Returning the deleted entry
    }

    // Function to display the queue
    void display()
    {
        // Checking if the queue is empty
        if(rear == front)
        {
            System.out.println("Queue is Empty");
            return;
        }
        // If not empty printing the queue
        for(int i = front; i != rear; i = (i + 1) % N)
            System.out.print(Q[i]+" ");
        System.out.println();
    }

    // Main method to create object and call functions accordingly
    public static void main(String[] args)
    {
        // Scanner initialisation
        Scanner sc=new Scanner(System.in);
        int n = 0;// Temporarily store user iput for size of array
        while(true)
        {
            // Using try-catch to get desired input
            try
            {
                System.out.print("Enter the size of the queue : ");
                n = sc.nextInt();
                // Size less than or equal to 0 is not allowed
                if(n <= 0)
                    System.out.println("Please enter a number greater than 0");
                else if(n >= 10)
                    System.out.println("Please enter a number less than 11");
                else
                    break;
            }
            catch(Exception e)
            {
                System.out.println("Please enter a size in integers");
            }
        }
        // Creating object
        CircularQueue cq=new CircularQueue(n+1);
        // Menu-driven logic for performing tasks on the queue
        String menu = "Choose: \n\t1. Enqueue\n\t2. Delqueue\n\t3. Display\n\t4. End";
        while(true)
        {
            // Using try-catch to get desired input
            try
            {
                System.out.print(menu + "\nEnter choice : ");
                // Taking commands
                int choice = sc.nextInt();
                switch(choice)
                {
                    // Performing commands
                    case 1:// Enqueue
                    while(true)
                    {
                        System.out.print("\nEnter a number to enqueue : ");
                        // Using try-catch to get desired input
                        try
                        {
                            double d = sc.nextDouble();
                            cq.enqueue(d);
                            break;
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a decimal number only");
                        }
                    }
                    break;
                    case 2:// Delqueue
                    double d = cq.delqueue();
                    // Printing if delqueue-ing took place
                    if(d!=-123456789.321)
                        System.out.println(d);
                    break;
                    case 3:// Display
                    cq.display();// Displaying
                    break;
                    case 4:// Exit
                    System.exit(0);// Exiting
                    default:
                    System.out.println("Please enter a correct choice");
                }
            }
            catch(Exception e)
            {
                System.out.println("The choice should be an integer");
            }
        }
    }
}