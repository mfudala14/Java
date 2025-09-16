package assignment1;

public class ArrayQueue implements Queue<Object> {
    private Object[] Q;//array that stores the element of the queue
    private int head; //index pointing to the front of the queue
    private int tail; //index pointing to the end of the queue
    private int size; //the current number of the elements in the queue
    private int capacity; //the maximum number of the element of the queue can hold.

    // TASK 3.A.a
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.Q = new Object[capacity];//initialize the array with the given capacity
        this.head = 0;//front index
        this.tail = -1;//no element in the queue initially
        this.size = 0;//no elements in the queue
    }

    // TASK 3.A.b
    //adds an element to the back of the queue
    public void enqueue(Object x) {
        if (size == capacity){//checks if the queue is full
            System.out.println("Queue is full");//prints statement
            return;
        }
        tail = (tail + 1) % capacity;//move tail forward circularly
        Q[tail] = x;//place the new item at the tail
        size++;//increase the size
    }

    // TASK 3.A.c
    //removes and returns the front element of the queue
    public Object dequeue() {
        if (empty()){//checks if the queue is empty
            System.out.println("Queue is empty");//prints statement
            return null;
        }
        Object item = Q[head];//retrieve the item at the head
        head = (head + 1)% capacity;//move head forward circularly
        size--;//decrease the size
        return item;//returns the dequeued item
    }

    // TASK 3.A.d
    //returns the front element without removing it
    public Object next() {
        if (empty()) {//checks if queue is empty
            System.out.println("Queue is empty");
            return null;
        }
        return Q[head];//return the item at the head without removing it
    }

    // TASK 3.A.e
    //checks if queue is empty
    public boolean empty() {
        return  size == 0;//returns true if size is zero
    }

    public static void main(String[] args) {
        Queue<Object> test = new ArrayQueue(20);//creating an instance with capacity 20
        System.out.println(test.empty());//checks if queue is empty
        for (int i=0; i<10; i++) {
            test.enqueue(i+100);//enqueue values from 100-109
        }
        System.out.println(test.empty());//check if queue is empty
        System.out.println(test.next());//getting front element of the queue without removing it
        for (int i=0; i<5; i++) {
            int x = (int)test.dequeue();//dequeues the front element
            System.out.print(x + " ");//prints the dequeued element
        }
        System.out.println();//print a line
        for (int i=0; i<15; i++) {
            test.enqueue(i);//enqueues from 0-14
        }
        while (!test.empty()) {
            int x = (int)test.dequeue();//dequeues the front element
            System.out.print(x + " ");//prints the dequeued element
        }
        System.out.println();//prints a line
    }
}
