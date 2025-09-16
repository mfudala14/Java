package assignment2;

public class QuickSort {

    // TASK 2.B.a
    //Method swaps two elements in the array and returns the pivot index.
    private static int partition(int[] A, int p, int r)
    {
        int temp = A[p]; // Store the value of the element at index 'p' (the pivot element).
        A[p] = A[r]; // Swap the pivot element (A[p]) with the element at index 'r' (last element).
        A[r] = temp; // Put the element at index 'p' into the position of 'r' to complete the swap.
        return p;  // Return the pivot index
    }

    // TASK 2.B.b
    //main quicksort recursive function that sorts the sub arrays.
    private static void quicksort(int[] A, int p, int r)
    {
        if(p >= r){  // If the starting index is greater than or equal to the ending index, the array is already sorted or empty.
            return;
        }
        int pivot = A[r]; // Select the pivot element (last element in the current sub array).
        int leftPointer = p;  // Initialize a pointer for the left side of the sub array at index 'p'.
        int rightPointer = r; // Initialize a pointer for the right side of the sub array at index 'r' (pivot).

        // Start rearranging elements in the array such that elements smaller than the pivot are on the left, and elements larger are on the right.
        while(leftPointer < rightPointer){

            // Move the left pointer to the right until we find a value greater than the pivot.
            while(A[leftPointer] <= pivot && leftPointer < rightPointer){
                leftPointer++;// Increment the left pointer to move towards the right.
            }

            // Move the right pointer to the left until we find a value smaller than the pivot.
            while(A[rightPointer] >= pivot && leftPointer < rightPointer){
                rightPointer--;// Decrement the right pointer to move towards the left.
            }
            // Swap the values at the left and right pointers to maintain the partitioning.
            partition(A, leftPointer, rightPointer);// Call partition method to swap elements.
        }
        partition(A, leftPointer, r);// Perform final partition between left pointer and right boundary (r).

        // call quicksort for the left portion of the array, from p to leftPointer - 1.
        quicksort(A, p, leftPointer - 1);

        //call quicksort for the right portion of the array, from leftPointer + 1 to r.
        quicksort(A, leftPointer + 1, r );
    }

    public static void quicksort(int[] A)
    {
        //Call quicksort with the full range of the array
        quicksort(A, 0, A.length-1);
    }

    private static void print(int[] A)
    {
        //Loop through the array to print all elements
        for (int i=0; i<A.length; i++)
        {
            //Print the element and if it's not the last element print a comma after it
            System.out.print(A[i] + ((i<A.length-1)?", ":""));
        }
        System.out.println();//Print a newline
    }

    public static void main(String[] args) {
        int[] A = new int[] {5,2,8,1,3,9,7,4,6};// Initialize the array to be sorted.
        quicksort(A);// Call quicksort to sort the array.
        print(A);// Print the sorted array.
    }

}
