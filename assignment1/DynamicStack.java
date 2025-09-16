package assignment1;

public class DynamicStack implements Stack<Object> {
    List<Object> S = new DoubleLinkedList();//instance of Double linked list that stores the stack elements.


    // TASK 2.B.
    //adds an element to the top of the stack
    public void push(Object x) {
        S.prepend(x);//adds the element to the front of the list
    }

    // TASK 2.B.b
    //removes and returns the top element of the stack
    public Object pop() {
        Object top = S.getFirst();//retrieve the top element
        S.deleteFirst();//remove the top element
        return top;//return the removed element
    }

    // TASK 2.B.c
    //returns the top element of the stack without removing it
    public Object peek() {
        return  S.getFirst();//returns the top element without removing it
    }

    // TASK 2.B.d
    //checks if the stack is empty
    public boolean empty() {
        return S.empty();//check if the list is empty
    }

    public static void main(String[] args) {
        Stack<Object> test = new DynamicStack();//creating new instance
        System.out.println(test.empty());//check if list is empty
        for (int i=0; i<10; i++) {
            test.push(i+100);//pushes values 100-109
        }
        System.out.println(test.empty());//checks if stack is empty
        System.out.println(test.peek());//prints last value pushed onto the stack
        for (int i=0; i<5; i++) {
            int x = (int)test.pop();//pops the top element
            System.out.print(x + " ");//prints the popped element
        }
        System.out.println();//prints a line
        for (int i=0; i<15; i++) {
            test.push(i);//pushes values 0-14 onto the stack
        }
        while (!test.empty()) {
            int x = (int)test.pop();//pops the top element
            System.out.print(x + " ");//prints the popped element
        }
        System.out.println();
    }
}
