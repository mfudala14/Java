package assignment2;

public class HeapOfBinaryTries {
    private BinaryTrie[] A;//Array to store BinaryTrie objects
    private int heapsize;//Size of the heap, represents the number of elements currently in the heap

    // TASK 3.A.a
    //Method ensures that the subtree rooted at index i satisfies the heap property
    private void heapify(int i)
    {
        int left = 2 * i + 1; // Left child index of the current node
        int right = 2 * i + 2;// Right child index of the current node
        int smallest = i; // Assume the smallest element is the current node
        // Compare with the left child, if the left child is smaller, update the smallest index
        if (left < heapsize && A[left].compare(A[smallest])) {
            smallest = left;
        }
        // Compare with the right child, if the right child is smaller, update the smallest index
        if (right < heapsize && A[right].compare(A[smallest])) {
            smallest = right;
        }
        // If the smallest element is not the current element, swap and recursively heapify the affected subtree
        if (smallest != i) {
            BinaryTrie temp = A[i];//Temporarily store the current node
            A[i] = A[smallest];//Move the smallest child to the current node's position
            A[smallest] = temp;//Place the current node in the position of the smallest child
            heapify(smallest);//Recursive call to ensure the heap property is satisfied for the swapped subtree
        }
    }


    // TASK 3.A.b
    // Constructor that builds the heap from an unsorted array of BinaryTries.
    public HeapOfBinaryTries(BinaryTrie[] A)
    {
        this.A = A;//Store the input Array
        this.heapsize = A.length;//Set the initial heap size based on the array length
        for(int i = heapsize / 2 - 1; i >= 0; i--){//Perform heapify on all non-leaf nodes starting from the last non-leaf node
            heapify(i);// Ensure each subtree satisfies the heap property
        }
    }

    // TASK 3.A.c
    // Extracts the minimum element (root) from the heap, then restores the heap property.
    public BinaryTrie extractMin()
    {
        if (heapsize < 1) {//if the heap is empty return null
            return null;
        }
        BinaryTrie min = A[0];// The root element is the minimum element in a min-heap
        A[0] = A[heapsize - 1];// Replace the root with the last element
        heapsize--;// Decrease the heap size
        heapify(0);// Restore the heap property by heapifying from the root
        return min;// Return the extracted minimum element
    }

    // TASK 3.A.d
    //Inserts a new BinaryTrie element into the heap and restores the heap property.
    public void insert(BinaryTrie x) {
        //check if the heap is already at capacity. If the heap is full
        if (heapsize == A.length) {
            throw new RuntimeException("Heap overflow");//prevent overflow if the heap is full
        }
        heapsize++;//Increase heap size
        int i = heapsize - 1;// The index where the new element will be inserted
        A[i] = x;// Place the new element at the end of the heap // The element `x` is inserted at index `i`.

        // Now, we need to restore the heap property by "bubbling up" the new element.
        // We compare the new element with its parent. If the new element is smaller than its parent, we swap them.
        // This process repeats until the heap property is restored as we reach the root.
        while (i > 0 && A[(i - 1) / 2].compare(A[i])) {
            BinaryTrie temp = A[i];// Temporarily store the new element.
            A[i] = A[(i - 1) / 2]; // Move the parent element to the current position.
            A[(i - 1) / 2] = temp; // Move the new element to its parent’s position.
            // After swapping, we need to check if the new position of the element still violates the heap property.
            // So, we move up to the parent and repeat the comparison.
            i = (i - 1) / 2;//Move to the parent index
        }
    }
    //returns the number of the elements currently in the heap
    public int size()
    {
        return heapsize;
    }
}
