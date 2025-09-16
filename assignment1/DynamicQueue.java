package assignment1;

public class DynamicQueue implements Queue<Object> {
    List<Object> Q = new DoubleLinkedList();//Instance of DoubleLinkedList to manage elements


    // TASK 3.B.
    //adds element to the back of the queue
    public void enqueue(Object x) {
        Q.append(x);//adds the element to the end of the queue
    }

    // TASK 3.B.b
    //removes and returns the front element of the queue
    public Object dequeue() {
        Object top = Q.getFirst();//retrieves the front element
        Q.deleteFirst();//remove the front element from the queue
        return top;//return the removed element
    }

    // TASK 3.B.c
    //returns the front element of the queue without removing it
    public Object next() {
        return Q.getFirst();//Return the front element without removing it
    }

    // TASK 3.B.d
    //checks if the queue is empty
    public boolean empty() {
        return Q.empty();//checks if the linked list is empty
    }

    public static void main(String[] args) {
        Queue<Object> test = new DynamicQueue();//creates an Instance
        System.out.println(test.empty());//checking if the queue is empty
        for (int i=0; i<10; i++) {
            test.enqueue(i+100);//enqueues values from 100-109
        }
        System.out.println(test.empty());//checks if the queue is empty
        System.out.println(test.next());//getting the next element
        for (int i=0; i<5; i++) {
            int x = (int)test.dequeue();//dequeues the front element
            System.out.print(x + " ");//prints the dequeued element
        }
        System.out.println();//print new line
        for (int i=0; i<15; i++) {
            test.enqueue(i);//enqueues values from 0-14
        }
        while (!test.empty()) {
            int x = (int)test.dequeue();//dequeues the front element
            System.out.print(x + " ");//prints the dequeued element
        }
        System.out.println();//prints a new line
    }
}
