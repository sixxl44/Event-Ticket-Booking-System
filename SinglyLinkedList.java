
package Group4_3w7;

public class SinglyLinkedList<E> {

    //---------------------------- Nested Class Node ----------------------------------
     static class Node<E> 
     {
        private E element; // reference to the element stored at this node
        private Node<E> next; // reference to the subsequent node in the list
   
        public Node(E e, Node<E> n) {
            element = e;
            next = n;    }
        
        public E getElement() {   return element;  }
        public Node<E> getNext() {   return next;    }
        public void setNext(Node<E> n) {   next = n;    }
        public void setelement(E element) {   this.element = element;    }
    } // End of Nested Class Node
//---------------------------------------------------------------------------
    Node<E> head; // head node of the list (or null if empty)
    Node<E> tail; // last node of the list (or null if empty)
    private int size; // number of nodes in the list

    
    public SinglyLinkedList() {// constructs an initially empty list
        head = null;
        tail = null;
        size = 0;
    } // constructs an initially empty list
    // access methods

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() { // returns (but does not remove) the first element
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() { // returns (but does not remove) the last element
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    // update methods
    public void addFirst(E e) { // adds element e to the front of the list
        Node<E> newest = new Node<E>(e, null); // create and link a new node
        newest.setNext(head);
        head = newest;
        if (size == 0) {
            tail = head; // special case: new node becomes tail also
        }
        size++;
    }

    public void addLast(E e) { // adds element e to the end of the list
        Node<E> newest = new Node<E>(e, null); // node will eventually be the tail
        if (isEmpty()) {
            head = newest; // special case: previously empty list
        } else {
            tail.setNext(newest); // new node after existing tail
        }
        tail = newest; // new node becomes the tail
        size++;
    }

    public E removeFirst() { // removes and returns the first element
        if (isEmpty()) {
            return null; // nothing to remove
        }
        E answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0) {
            tail = null; // special case as list is now empty
        }
        return answer;
    }

    public E removeLast() {  // removes and returns the last node
       
        if (size == 0) 
        {   return null;   }// nothing to remove
        
        else 
        {
            E data = tail.getElement();  // get a copy of the data stored in the last node
            if (size == 1) // only one node in the list
            {   head = tail = null;   } 
            else 
            { // at least two nodes
                Node temp1 = head;// make temp1 point to the head 
                Node temp2 = null; // make temp2 point to null
                while (temp1.getNext() != null) // find last node
                { 
                    temp2 = temp1;
                    temp1 = temp1.getNext();
                }
                temp2.setNext(null); // null out the next pointer of the last node 
                tail = temp2; // update the value of tail
            }
            size--; //decrement the size of list
            return data; // return the value stored in the removed node
        }    
    }
     
    //display method
    public void showCustomers(){
       Node<E> current = head; 
        while (current != null) { 
            System.out.println(" " + current.getElement()); 
            current = current.getNext(); 
        } 
        System.out.println();
    }
    
    //first member method
    public void removeCustomer(int custID){
        if(isEmpty()){
            System.out.println("No customers available");
            return;
        }
        
        int s = size();
        
        for (int i=0;i<s;i++){
            E elm = removeFirst();
            if(((Customer) elm).getID() != custID) {
                addLast(elm);
            }
            System.out.println("\u001B[44m"+"Customer removed successfully");
        }
    }
    
    //second member method
    public void addCustomer(Customer cust){
        addLast((E) cust);
        System.out.println("\u001B[44m"+"Customer added successfully!");
    }
    
    //third member method
    public E Sreach(int id) {
    if (isEmpty()) {
        System.out.println("No customers available");
        return null;
    }

    Node<E> temp = head;

    // Iterate through the list to find the Customer with the given ID
    while (temp != null) {
        E elem = temp.getElement();
        
        // Check if element is a Customer and has the matching ID
        if (elem instanceof Customer && ((Customer) elem).getID() == id) {
            return elem; // Return the Customer if ID matches
        }
        
        temp = temp.getNext();
    }

    System.out.println("Customer not found");
    return null;
}

    
} // End SinglyLinkedList Class