package Group4_3w7;

public class LLQueue<E> {

    static class Node<E> {

        private E element; // reference to the element stored at this node
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setelement(E element) {
            this.element = element;
        }
    }
    protected Node<E> front;
    protected Node<E> rear;
    protected int size;

    public LLQueue() { // constructor
        front = null;
        rear = null;
        size = 0;
    }

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
        return front.getElement();
    }

    public E last() { // returns (but does not remove) the last element
        if (isEmpty()) {
            return null;
        }
        return rear.getElement();
    }

    public void enqueue(E elem) {
        Node<E> newest = new Node<E>(elem, null);
        if (isEmpty()) {
            front = newest; // special case of a previously empty queue 
        } else {
            rear.setNext(newest); // add node at the tail of the list 
        }
        rear = newest; // update the reference to the tail node 
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = front.getElement();
        front = front.getNext();
        size--;
        if (size == 0) {
            rear = null; // the queue is now empty
        }
        return answer;
    }

    //display method
    public void Display() {
        int s = size();

        for (int i = 0; i < s; i++) {
            E elem = dequeue();
            System.out.print(elem + "");
            enqueue(elem);
        }
        System.out.println();
    }

    // first member method
    // Method to find a ticket by number
    public E findTicketByNumber(String ticketNumber) {
        E result = null;
        int s = size();

        for (int i = 0; i < s; i++) {
            E elem = dequeue();

            // Check if elem is a Ticket and compare its ticket number
            if (elem instanceof Ticket && ((Ticket) elem).getTicketNumber().equals(ticketNumber)) {
                result = elem;
            }

            enqueue(elem); 
        }
        return result; // Return the found ticket or null if not found
    }

    // second member method
    // Method to cancel a ticket by number
    public boolean cancelTicket(String ticketNumber) {
        boolean isCanceled = false;
        int s = size();

        for (int i = 0; i < s; i++) {
            E elem = dequeue();

            if (elem instanceof Ticket) {
                Ticket ticket = (Ticket) elem;

                // Check if ticket number matches and it is available
                if (ticket.getTicketNumber().equals(ticketNumber)) {
                    ticket.setIsAvailable(true); 
                    isCanceled = true; 
                    
                    continue;
                }
            }

            // Re-enqueue the element if it's not the target ticket or already canceled
            enqueue(elem);
        }

        return isCanceled; // Return 
    }

    // third member method
    // Method to count tickets by event (assumes E has a method getEventName())
    public int countTicketsByEvent(String eventName) {
        int count = 0;
        int s = size();

        for (int i = 0; i < s; i++) {
            E elem = dequeue();
            if (elem.toString().contains(eventName)) { // Replace with appropriate method
                count++;
            }
            enqueue(elem);
        }
        return count;
    }

}// End LLQueue Class

