package assignment2;


public class MergeSort {

    // TASK 2.A.a
    //Method merges two sorted arrays A1 and A2 into one sorted array
    private static int[] merge(int[] A1, int[] A2) {
        int n = A1.length;//Store the length of the first array A1
        int p = A2.length;//Store the length of the second array A2
        int[] m = new int[n + p];//Create a new array to store the merged result, with size equal to the sum of the two input arrays' lengths.
        int i = 0, j = 0, k = 0; // Initialize indices for A1 (i), A2 (j), and the merged array (k).

        //while there are elements left in both A1 and A2, compare and add the smaller element to the merged array.
        while (i < n && j < p) {
            if (A1[i] <= A2[j]) {//if the element in A1 is less than or equal to element in A2, add it to the merged array
                m[k++] = A1[i++];//add the element from A1 to the merged array and move the index for A1 forward
            } else { m[k++] = A2[j++];// add the element from A2 to the merged array and move the index for A2 forward.
            }
        }
        // If there are any remaining elements in A1, add them to the merged array.
        while (i < n) {
            m[k++] = A1[i++];// Add the remaining elements from A1 to the merged array.
        }
        // If there are any remaining elements in A2, add them to the merged array.
        while (j < p) {
            m[k++] = A2[j++]; // Add the remaining elements from A2 to the merged array.
        }
        return m;//Return merged array
    }

    // TASK 2.A.b
    //Mergesort method that sorts the input array A
    public static int[] mergesort(int[] A) {
        int inputLength = A.length;//Get the length of the input array A

        //if the array has less than two elements, it's already sorted, so return the array.
        if (inputLength < 2) {
            return A;// Base case: if array has 1 or 0 elements, it is already sorted.
        }
        int midIndex = inputLength / 2;// Find the middle index of the array to divide it into two halves.

        // Create two sub-arrays: leftHalf contains the left half of the original array, rightHalf contains the right half.
        int[] leftHalf = new int[midIndex];// The left sub-array is of size midIndex.
        int[] rightHalf = new int[inputLength - midIndex];// The right sub-array contains the remaining elements.

        //Copy elements into leftHalf
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = A[i];// Copy the first half of the array into leftHalf.
        }

        //Copy elements into rightHalf
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = A[i];// Copy the second half of the array into rightHalf.
        }

        //Recursively sort the left and right halves.
        leftHalf = mergesort(leftHalf);//Sort the left half
        rightHalf = mergesort(rightHalf);//Sort the right half

        //Merge the two sorted halves and return the merged, sorted array
        return merge(leftHalf, rightHalf); // Merge the two sorted halves using the merge method.
    }

    // Method to print the elements of an array, separated by commas.
    private static void print(int[] A)
    {
        for (int i=0; i<A.length; i++)// Loop through each element of the array.
        {
            System.out.print(A[i] + ((i<A.length-1)?", ":""));// Print the element, followed by a comma if it's not the last element.
        }
        System.out.println();//print new line
    }

    public static void main(String[] args) {
        //Test the merge function by merging two sorted arrays and printing the result.
        print(merge(new int[] {1,3,5,7,9}, new int[] {2,4,6,8}));
        //Test the mergesort function by sorting an unsorted array and printing the result.
        print(mergesort(new int[] {5,2,8,1,3,9,7,4,6} ));
    }

}
