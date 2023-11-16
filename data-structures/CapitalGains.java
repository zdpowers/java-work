public class CapitalGains { 
    
    public static void main(String args[]) {
        
	    StockTransactions ledger = new StockTransactions();
		ledger.buyShares(100, 20);
		ledger.buyShares(20, 24);
		ledger.buyShares(200, 36);
		ledger.sellShares(150, 30);
		
		// Ouput:
		// Bought 100 shares at 20 dollars per share, for a total value of $2000
        // Bought 20 shares at 24 dollars per share, for a total value of $480
        // Bought 200 shares at 36 dollars per share, for a total value of $7200
        // Sold 150 shares at $30 per share, for a capital gain of $940
		
	}
}


class StockTransactions {
    
    private LinkedQueue transactions;
    
    public StockTransactions() {
        transactions = new LinkedQueue();
    }
    
    public void buyShares(int quantity, int price) {
        for(int i=0; i < quantity; i++) {
            transactions.enqueue(new Share(price));
        }
        System.out.println("Bought " + quantity + " shares at " + price + " dollars per share, for a total value of $" + (quantity*price));
    }
    
    public int sellShares(int quantity, int price) {
        int capitalgain = 0;
        if(quantity > transactions.size())
            quantity = transactions.size();
        for(int i=0; i < quantity; i++) {
            Share s = transactions.dequeue();
            if (s != null)
                capitalgain += (price - s.getSharePrice());
        }
        System.out.println("Sold " + quantity + " shares at $" + price + " per share, for a capital gain of $" + capitalgain);
        return capitalgain;
    }

    public int shareCount() {
        return transactions.size();
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

class SinglyLinkedList {
    
    private static class Node {
        
        private Share element;
        private Node next;
        
        public Node(Share e, Node n) {
            element = e;
            next = n;
        }
        
        public Share getElement() {
            return element;
        }
        
        public Node getNext() {
            return next;
        }
        
        public void setNext(Node n) {
            next = n;
        }
    }
    
    private Node head, tail;
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
    
    public Share first() {
        if(isEmpty()) {
            return null;
        } else {
            return head.getElement();
        }
    }
    
    public Share last() {
        if(isEmpty()) {
            return null;
        } else {
            return tail.getElement();
        }
    }
    
    public void addFirst(Share e) {
        head = new Node(e, head);
        if(size == 0) {
            tail = head;
        }
        size++;
    }
    
    public void addLast(Share e) {
        Node newnode = new Node(e, null);
        if(isEmpty()) {
            head = newnode;
        } else {
            tail.setNext(newnode);
        }
        tail = newnode;
        size++;
    }
    
    public Share removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            Share retval = head.getElement();
            head = head.getNext();
            size--;
            if(size == 0) {
                tail = null;
            }
            return retval;
        }
    }
}


interface Queue {
    int size();
    boolean isEmpty();
    void enqueue(Share e);
    Share first();
    Share dequeue();
}

class LinkedQueue implements Queue {
    
    private SinglyLinkedList list;
    
    public LinkedQueue() {
        list = new SinglyLinkedList();
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public void enqueue(Share e) {
        list.addLast(e);
    }
    
    public Share first() {
        return list.first();
    }
    
    public Share dequeue() {
        return list.removeFirst();
    }
    
}


