import java.util.*;

class RingGame
{
    // Declaring class variables
    int MAX;// To store the Maximum number of rings possible
    int[] ring;// To store the ring stack
    int TOP;// To store the top most ring

    // Constructor to initialise data members
    RingGame(int m)
    {
        MAX = m;
        ring = new int[m];
        TOP = -1;
    }

    // Function to place a ring if possible
    void jumpin(int n)
    {
        if (TOP == MAX - 1)// If no more rings possible
        {
            System.out.println("Column full ! Start removing rings.");
        }
        else// If ring adding is possible
        {
            ring[++TOP] = n;// Adding ring
            System.out.println("Ring added successfully.");
            // Diplaying the rings and the stack
            System.out.println("\nR I N G\tG A M E");
            System.out.println("Stack\tPile");
            for(int i = 0; i < MAX; i++)
            {
                if(i < MAX - (TOP + 1))
                    System.out.print("|\t");
                else
                    System.out.print("+\t");
                if(i < TOP + 1)
                    System.out.println(" ");
                else
                    System.out.println("-");
            }
        }
        
    }

    // Function to remove a ring
    void jumpout()
    {
        if (TOP == -1)// If no more rings left
        {
            System.out.println("Congratulations ! the game is over .");
            System.exit(0);
        }
        else
        {
            ring[TOP] = ring[TOP--];// Last element removed
            System.out.println("Ring removed successfully");
            // Displaying the rings and the stack
            System.out.println("\nR I N G\tG A M E");
            System.out.println("Stack\tPile");
            for(int i = 0; i < MAX; i++)
            {
                if(i < MAX - (TOP + 1))
                    System.out.print("|\t");
                else
                    System.out.print("+\t");
                if(i < TOP + 1)
                    System.out.println(" ");
                else
                    System.out.println("-");
            }
        }
        
    }

    // Main method to create object and call the funtions accordingly
    public static void main (String[] args)
    {
        RingGame obj = new RingGame(3);// Creating object
        System.out.println("R I N G\tG A M E");
        System.out.println("Stack\tPile");
        for(int i = 0; i < obj.MAX; i++)
        {
            System.out.println("|\t-");
        }
        Scanner sc=new Scanner(System.in);
        // Calling functions accordingly
        for(int i = 1; i > 0; i++)
        {
            System.out.println("\nEnter :-\n\t1. Add Ring\n\t2. Remove ring");
            System.out.print("Enter your choice : ");
            int c = sc.nextInt();
            if(c == 1)
                obj.jumpin(i);//To add rings.
            else if(c == 2)
                obj.jumpout();//To remove rings.
            else
                System.out.println("Wrong choice!");
        }
    }
}