package assignment1;

import java.util.EmptyStackException;

public class ArrayStack implements Stack<Object> {
    private Object[] S;  //An array holds the element of the stack
    private int top; //index that keeps track of the position of the top element in the stack
    private int size; //keeps track of the number of element currently in the stack


    // TASK 2.A.a
    public ArrayStack(int capacity) {
        this.size = 0; //size is 0
        this.S = new Object[capacity]; //create an array with given capacity
        this.top = -1;  //starts at -1 to indicate the stack is empty
    }

    // Task 2.A.b
    //adds an element to top of the stack
    public void push(Object x)  {
        if(isFull()){
            return; //if the stack is full the method exists without adding element
        }
        S[++top] = x; // increment top and add the element to the stack
        size++;  //increase the size of the stack
    }

    // TASK 2.A.c
    //removes and returns the top element of the stack
    public Object pop() {
        if(!empty()) {//if the stack is not empty
            Object removedData = peek(); //gets the top element
            top--;//decrease top
            size--;//decrease size
            return removedData; //returns the removed element
        }
        else{
            System.out.println("Stack is empty"); //prints stack is empty
            return -1;//returns -1
        }
    }
    // TASK 2.A.d
    //returns the top element of the stack without changing it
    public Object peek() {
        if(!empty()){//if stack is not empty
            return S[top];//return the top element without removing it
        }
        else{
            System.out.println("Stack is empty");//prints stack is empty
            return -1;//returns -1
        }
    }
    // TASK 2.A.e
    //checks is the stack is empty
    public boolean empty() {
        return top < 0;//returns true if the top is less than 0, saying stack is empty
    }

    //checks if the stack is full
    public boolean isFull() {
        return size == S.length; //returns true if size equals the length of the array
    }

    public static void main(String[] args) {
        Stack<Object> test = new ArrayStack(20); //creating an instance of ArrayStack with capacity 20
        System.out.println(test.empty());//checks if stack is empty
        for (int i=0; i<10; i++) {//loop runs 10 times and each time it adds a new value to the list
            test.push(i+100);//pushes values from 100-109 tp stack
        }
        System.out.println(test.empty());//checking if stack is still empty
        System.out.println(test.peek());//prints last pushed element without removing it
        for (int i=0; i<5; i++) {//loop runs 5 times, each time it aims  pop one element from the stack
            int x = (int)test.pop();//pop the element from the stack
            System.out.print(x + " ");//prints the popped elements
        }
        System.out.println();//prints new line
        for (int i=0; i<15; i++) {//loop runs 15 times
            test.push(i);//push values 0-14 onto the stack
        }
        while (!test.empty()) {
            int x = (int)test.pop();//pop the top element
            System.out.print(x + " ");//prints the popped element
        }
        System.out.println();//prints new line
    }
}

