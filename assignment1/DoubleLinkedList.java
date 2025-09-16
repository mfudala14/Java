package assignment1;

public class DoubleLinkedList implements List<Object> {
    private class ListNode {
        public ListNode(Object x){key = x;} //listNode represents a single node in the list
        public Object key; //key stores the value of the node
        public ListNode prev = null; //reference to the previous node, set to null
        public ListNode next = null; //reference to the next node, set to null
    }
    private ListNode head; //reference the first node in the list
    private ListNode tail; //reference the last node in the list

    //Task 1.A
    //creating an empty list
    public DoubleLinkedList()
    {
        this.head = null;   //Initialize an empty list
        this.tail = null;   //tail is set to null as the list is empty
    }

    //Task 1.B
    //adds a new node in front of the list
    public void prepend(Object x) {
        ListNode newNode = new ListNode(x); //New node is created
        if(head == null){   //checks if the list is empty
            head = newNode; //head is set to newNode
            tail = newNode; // tail is set to newNode
        }
        else{
            newNode.next = head;   //set newNode to next reference and equal it to head
            head.prev = newNode;   //set previous reference to head and equal it to a newNode.
            head = newNode;        //move head to newNode
        }
    }
    //Task 1.C
    //returns first value of the first node
    public Object getFirst()  {
        if (head == null) { //checks if list is empty
            return null;
        }
        return head.key; // returns the value of first node
    }
    //Task 1.D
    //removes the first node from the list
    public void deleteFirst() {
        if(head == null){ // if the list is empty, it is returned
            return;
        }
        if(head == tail){ //if there is only one node
            head = null; //head is set to null
            tail = null; //tail is set to null
            return;
        }
        head = head.next; //head is set to a next node
        head.prev = null; //and the new previous head is set to null.

    }
    //Task 1.E
    //adds a new node at the end of the list
    public void append(Object x) {
        ListNode n = new ListNode(x); // creates a new node
        if (head == null){ //if list is empty
            head = n; //head is set to newNode
            tail = n; //tail is set  to newNode
        }
        else {
            tail.next  = n; //the current tail.next is set to the new node
            n.prev = tail; //the new node previous is set to current tail
            tail = n; //tail is set to updated new node.
        }
    }
    //Task 1.F
    //return the value of the last node
    public Object getLast()  {
        if (tail == null) {//if the list is empty
            return null;   //return null
        }
        return tail.key; //if not return the last node
    }

    //Task 1.G
    //removed the last node from the list
    public void deleteLast() {
        if(tail == null){//check if list is empty
            return;
        }
        if(head == tail){ //if there is only one node
            head = null; //head is set to null
            tail = null; //tail is set to null
            return;
        }
        tail = tail.prev; //tail is set to the previous node
        tail.next = null; //the new tail.next is set to nul

    }
    //Task 1.H
    public boolean empty() {
        return head == null; //returns true is the list is empty
    }

    public static void main(String[] args) {
        List<Object> test = new DoubleLinkedList();  //creates a new instance
        System.out.println(test.empty()); //checks if the list is empty
        for (int i=0; i<10; i++) { //loop runs 10 times and each time it adds a new value to the list
            test.prepend(i + 100); //100 - 109
        }
        System.out.println(test.empty()); //checks again if the loop is empty, it's false
        for (int i=0; i<5; i++) {//loop iterates five times
            int x = (int)test.getFirst(); // gets the value of first node, witch returns the key of current head.
            System.out.print(x + " ");//the value is printed
            test.deleteFirst();//removes the first node from list
        }
        System.out.println();//prints new line
        for (int i=0; i<10; i++) {//loop goes ten times 200-209
            test.append(i + 200);
        }
        while (!test.empty()) {//the loop continues until list is empty
            int x = (int)test.getLast();//it gets the value of last node, which returns the key of the current tail
            System.out.print(x + " ");//the value is printed
            test.deleteLast();//remove the last node from list
        }
    }
}
