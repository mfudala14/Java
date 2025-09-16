package assignment2;

public class DivideAndConquer {

    // TASK 1.A.a
    // Method calculates the nth Fibonacci number using recursion.
    public static int fibonacci(int n) {
        if(n == 0){ //if n is 0, return 0 (first Fibonacci number)
            return 0;
        } else if(n == 1){//if n is 1, return 1 (second Fibonacci number)
            return 1;
        }
        //n is the sum of the Fibonacci of (n-1) and (n-2).
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // TASK 1.A.b
    //This method has a binary search on a sorted array A trying to find value v.
    public static int search(int[] A, int v)
    {
        int low = 0;//Initialize lower bound of search range.
        int high = A.length - 1; //Initialize the upper bound of search range

        // Continue the search while the lower bound is less than or equal to the upper bound.
        while(low <= high){
            //middlePosition is the average of the low and high
            int middlePosition = (low + high) / 2;
            int middleNumber = A[middlePosition]; //Get the value of the middle position.
            // If the value at the middle position is equal to v, return the middle position.
            if(v == middleNumber){
                return middlePosition;
            }
            // If the value at the middle position is greater than v, narrow the range to the lower half.
            if(v < middleNumber){
                high = middlePosition - 1;
            } else{// If the value at the middle position is less than v, narrow the range to the upper half.
                low =  middlePosition + 1;
            }

        }
        // If the value v is not found, return -1.
        return -1;
    }

    // TASK 1.A.c
    //This method solves the Tower of Hanoi problem for n disks.
    //A,B,C are the names of the rods.
    public static void hanoi(int n, char A, char B, char C)
    {
        //If there is only one disk, move it from A to C
       if(n == 1){
           System.out.println(A + "->" + C);
       }else {
           //Move n-1 disks from A to B using C as an extra
           hanoi(n - 1, A, C, B);
           //Move the nth disk from A to C
           hanoi(1, A, B, C);
           //Move the n-1 disk from B to C using A as an extra
           hanoi(n - 1, B, A, C);
       }
    }

    public static void main(String[] args) {
        //print the first 10 Fibonacci numbers
        for (int i=0; i<10; i++) {
            //print the i-th fibonacci number
            System.out.println(fibonacci(i));
        }
        System.out.println();
        // Perform a binary search for the values 0 to 9 in a sorted array
        for (int i=0; i<10; i++) {
            System.out.println(search(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, i));
        }
        System.out.println();
        //Solve the Tower of Hanoi problem for 4 disks
        hanoi(4, 'A', 'B', 'C');
    }
}
