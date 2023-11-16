
public class SLL_HW {
    public static void main(String[] args) {
        
        int[] scoreArray = {50, 55, 57, 60, 65, 70, 72, 75, 80, 82, 85, 90, 95, 97, 100};
        SLL scores = new SLL();
        
        for(int i=0; i< scoreArray.length; i++) {
            if((i+1)%2==0) {
                scores.insertFirst(scoreArray[i]);
            } else {
                scores.insertLast(scoreArray[i]);
            }
        }
        
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
        scores.removeFirst();
        scores.removeLast();
        
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
        System.out.println("Adding value 88 at index 3.");
        scores.insertAt(3, 88);
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
    }
}

class Node {
    int data;
    Node next;
    
    public Node(int D) {
        data = D;
        next = null;
    }
    
    public String toString() {
        return Integer.toString(data);
    }
}

class SLL {
    
    public Node head, tail;
    public int size;
    
    
    // Constructor
    public SLL() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return(size==0);
    }
    
    public void insertFirst(int D) {
        Node newnode = new Node(D);
        if(isEmpty()) {
            head = newnode;
            tail = newnode;
            size++;
        } else {
            newnode.next = head;
            head = newnode;
            size++;
        }
    }
    
    public void insertLast(int D) {
        Node newnode = new Node(D);
        if(isEmpty()) {
            insertFirst(D);
        } else {
            tail.next = newnode;
            tail = newnode;
            size++;
        }
    }
    
    public void insertAt(int index, int D) {
        Node newnode = new Node(D);
        if(isEmpty() || D == 0) {
            insertFirst(D);
        } else {
            Node previous, current;
            previous = null;
            current = head;
            for(int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            newnode.next = current;
            previous.next = newnode;
            size++;
        }
    }
    
    public Node removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            Node temp = head;
            head = head.next;
            size--;
            return temp;
        }
    }
    
    public Node removeLast() {
        if(isEmpty()) {
            return null;
        } else {
            Node current = head, previous = null;
            while(current.next != null) {
                previous = current;
                current = current.next;
            }
            previous.next = null;
            size--;
            return current;
        }
    }
    
    public String toString() {
        String listcontents = "";
        if(isEmpty()) {
            listcontents = "EMPTY";
        } else {
            Node current = head;
            while(current.next != null) {
                listcontents += current;
                listcontents += ", ";
                current = current.next;
            }
            listcontents += current;
        }
        return listcontents;
    }
}

// Output:
// List Size: 15
// 97, 90, 82, 75, 70, 60, 55, 50, 57, 65, 72, 80, 85, 95, 100
// List Size: 13
// 90, 82, 75, 70, 60, 55, 50, 57, 65, 72, 80, 85, 95
// Adding value 88 at index 3.
// List Size: 14
// 90, 82, 75, 88, 70, 60, 55, 50, 57, 65, 72, 80, 85, 95
