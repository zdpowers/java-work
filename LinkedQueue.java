import java.util.Scanner;

public class MyClass {
    
    public static void main(String args[]) {
     
		//LinkedQueue<Share> StockTransactions = new LinkedQueue<>();
		StockTransactions ledger = new StockTransactions();
		ledger.buyShares(100, 20);
		ledger.buyShares(20, 24);
		ledger.buyShares(200, 36);
		ledger.sellShares(150, 30);
    }	

}


class StockTransactions {
    
    private LinkedQueue<Share> transactions;
    
    public StockTransactions() {
        transactions = new LinkedQueue<>();
    }
    
    public void buyShares(int quantity, int price) {
        for(int i=0; i < quantity; i++) {
            transactions.enqueue(new Share(price));
        }
        System.out.println("Bought " + quantity + " shares at " + price + " dollars per share, for a total value of $" + (quantity*price));
    }
    
    public int sellShares(int quantity, int price) {
        int capitalgain = 0;
        for(int i=0; i < quantity; i++) {
            Share s = transactions.dequeue();
            if (s != null)
                capitalgain += (price - s.getSharePrice());
        }
        System.out.println("Sold " + quantity + " shares at $" + price + " per share, for a capital gain of $" + capitalgain);
        return capitalgain;
    }
    
}


class Share {
    
    private int shareprice;
    
    public Share(int price) {
        shareprice = price;
    }

    public int getSharePrice() {
        return shareprice;
    }
}


class SinglyLinkedList<E> {
    
    private static class Node<E> {
        
        private E element;
        private Node<E> next;
        
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
        
    }
    
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public E first() {
        if(isEmpty()) {
            return null;
        } else {
            return head.getElement();
        }
    }
    
    public E last() {
        if(isEmpty()) {
            return null;
        } else {
            return tail.getElement();
        }
    }
    
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }
    
    public void addLast(E e) {
        Node<E> newnode = new Node<>(e, null);
        if(isEmpty()) {
            head = newnode;
        } else {
            tail.setNext(newnode);
        }
        tail = newnode;
        size++;
    }
    
    public E removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            E retval = head.getElement();
            head = head.getNext();
            size--;
            if(size == 0){
                tail = null;
            }
            return retval;
        }
    }
    
}


interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();
}


class LinkedQueue<E> implements Queue<E> {
    
    private SinglyLinkedList<E> list;
    
    public LinkedQueue() {
        list = new SinglyLinkedList<>();
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public void enqueue(E element) {
        list.addLast(element);
    }
    
    public E first() {
        return list.first();
    }
    
    public E dequeue() {
        return list.removeFirst();
    }
    
}


