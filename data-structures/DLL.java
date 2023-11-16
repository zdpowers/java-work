public class MyClass {
    public static void main(String args[]) {
        
        DLL list = new DLL();
        list.insertFirst(20);
        list.insertFirst(10);
        list.insertLast(30);
        list.insertAtIndex(40, 3);
        System.out.println(list);
    }
}

class DLL {
    
    class Node {
        
        public Node prev, next;
        public int val;
        
        public Node(int v, Node p, Node n) {
            val = v;
            prev = p;
            next = n;
        }
        
        public String toString() {
            return Integer.toString(val);
        }
    }
    
    public Node head, tail;
    public int size;
    
    public DLL() {
        head = new Node(0, null, null);
        tail = new Node(0, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    public boolean isEmpty() {
        return(size==0);
    }
    
    private void insertBetween(int d, Node previous, Node successor) {
        Node newnode = new Node(d, null, null);
        newnode.next = successor;
        newnode.prev = previous;
        previous.next = newnode;
        successor.prev = newnode;
        size++;
    }
    
    public void insertFirst(int d) {
        if(isEmpty()) {
            insertBetween(d, head, tail);
        } else {
            insertBetween(d, head, head.next);
        }
    }
    
    public void insertLast(int d) {
        if(isEmpty()) {
            insertBetween(d, head, tail);
        } else {
            insertBetween(d, tail.prev, tail);
        }
    }
    
    public void insertAtIndex(int d, int index) {
        if(index > size) {
            System.out.println("Index out of bounds");
        } else if(index == size) {
            insertLast(d);
        } else {
            Node current = head;
            for(int i=0; i<=index; i++) {
                current = current.next;
            }
            insertBetween(d, current.prev, current);
        }
    }
    
    public String toString() {
        String listcontents = "";
        if(isEmpty()) {
            listcontents = "EMPTY";
        } else {
            Node current = head.next;
            while(current.next != tail) {
                listcontents += current;
                listcontents += " ";
                current = current.next;
            }
            listcontents += current;
        }
        return listcontents;
    }
}
