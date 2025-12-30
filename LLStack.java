package Group4_3w7;

public class LLStack<E> {

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

        public void setElement(E element) {
            this.element = element;
        }
    }

    private Node<E> top;
    private int size;

    public LLStack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return top.getElement();
    }

    public void push(E elem) {
        Node<E> v = new Node<>(elem, top);
        top = v;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E temp = top.getElement();
        top = top.getNext();
        size--;
        return temp;
    }

    // display method 
    public void Display() {
        LLStack<E> temp = new LLStack<>();
        while (!isEmpty()) {
            E elem = pop();
            System.out.println(elem+"");
            temp.push(elem);
        }
        System.out.println();

        while (!temp.isEmpty()) {
            push(temp.pop());
        }
    }

    
    // first member method display only booking for specific user
    public void displayUserBookingHistory(int userId) {
        LLStack<E> temp = new LLStack<>();
        while (!isEmpty()) {
            E elem = pop();
            if (elem instanceof Booking && ((Booking) elem).getCustomer().getID() == userId) {
                System.out.println(elem);
            }
            temp.push(elem);
        }

        while (!temp.isEmpty()) {
            push(temp.pop());
        }
    }
    

    // second member method
    public Booking findBookingByID(int bookingID) {
        LLStack<E> tempStack = new LLStack<>();
        Booking foundBooking = null;

        while (!isEmpty()) {
            E elem = pop();
            if (elem instanceof Booking) {
                Booking currentBooking = (Booking) elem;
                if (currentBooking.getBooking_ID() == bookingID) {
                    foundBooking = currentBooking;
                }
            }
            tempStack.push(elem);
            if (foundBooking != null) {
                break;
            }
        }

        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }

        return foundBooking;
    }
    

    // third member method
    public Booking findMostBookedEvent() {
        LLStack<Booking> tempStack = new LLStack<>();
        Booking mostBooked = null;
        int maxCount = 0;

        // Outer loop to pick each booking
        while (!isEmpty()) {
            Booking currentBooking = (Booking) this.pop();
            int currentCount = 1; 

            // Inner loop to count occurrences of the current booking
            LLStack<Booking> comparisonStack = new LLStack<>();
            while (!isEmpty()) {
                Booking comparisonBooking = (Booking) this.pop();
                if (currentBooking.equals(comparisonBooking)) {
                    currentCount++;
                }
                comparisonStack.push(comparisonBooking);
            }
 
            while (!comparisonStack.isEmpty()) {
                push((E) comparisonStack.pop());
            }

            // Update the most booked event
            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostBooked = currentBooking;
            }

            tempStack.push(currentBooking); // Preserve order
        }

        while (!tempStack.isEmpty()) {
            push((E) tempStack.pop());
        }

        return mostBooked;
    }

}
