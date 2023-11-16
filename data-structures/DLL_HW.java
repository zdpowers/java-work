public class DLL_HW {
    public static void main(String[] args) {
        
        int[] scoreArray = {50, 55, 57, 60, 65, 70, 72, 75, 80, 82, 85, 90, 95, 97, 100};
        
        DLL scores = new DLL();
        
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
        
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        System.out.println("Value at index 12: " + scores.getAt(12));

        scores.removeLast();

        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        System.out.println("Value at index 5: " + scores.getAt(5));
        
        System.out.println("Removing value at index 3: " + scores.removeAt(3));
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
        System.out.println("Adding 88 at index 2.");
        scores.insertAt(2, 88);
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
        System.out.println("Adding 98 at index 10.");
        scores.insertAt(10, 98);
        System.out.println("List Size: " + scores.size);
        System.out.println(scores);
        
    }
}

class Node {
    public int data;
    public Node next, prev;
    
    public Node(int D) {
        data = D;
        next = null;
        prev = null;
    }
    
    public String toString() {
        return Integer.toString(data);
    }
}

class DLL {
    
    public Node head, tail;
    public int size;
    
    
    // Constructor
    public DLL() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    public boolean isEmpty() {
        return(size==0);
    }
    
    public void insertFirst(int D) {
        Node newnode = new Node(D);
        if(isEmpty()) {
            newnode.next = tail;
            newnode.prev = head;
            head.next = newnode;
            tail.prev = newnode;
            size++;
        } else {
            newnode.next = head.next;
            newnode.prev = head;
            (head.next).prev = newnode;
            head.next = newnode;
            size++;
        }
    }
    
    public void insertLast(int D) {
        Node newnode = new Node(D);
        if(isEmpty()) {
            insertFirst(D);
        } else {
            newnode.next = tail;
            newnode.prev = tail.prev;
            (tail.prev).next = newnode;
            tail.prev = newnode;
            size++;
        }
    }
    
    public Node removeFirst() {
        if(isEmpty()) {
            return null;
        } else {
            Node temp = head.next;
            head.next = (head.next).next;
            size--;
            return temp;
        }
    }
    
    public Node removeLast() {
        if(isEmpty()) {
            return null;
        } else {
            Node temp = tail.prev;
            tail.prev = (tail.prev).prev;
            (tail.prev).next = tail;
            size--;
            return temp;
        }
    }
    
    public Node getAt(int index) {
        if(isEmpty() || size <= index) {
            return null;
        } else {
            if (size/2 > index) {
                // If the position is in the first half, start from head
                Node current = head;
                for(int i = 0; i <= index; i++) {
                    current = current.next;
                }
                return current;
            } else {
                // else start from tail
                Node current = tail;
                for(int i = size-1; i >= index; i--) {
                    current = current.prev;
                }
                return current;
            }
        }
    }
    
    public Node removeAt(int index) {
        if(isEmpty() || size <= index) {
            return null;
        } else {
            if (size/2 > index) {
                // If the position is in the first half, start from head
                Node current = head;
                for(int i = 0; i <= index; i++) {
                    current = current.next;
                }
                (current.prev).next = current.next;
                (current.next).prev = current.prev;
                size--;
                return current;
            } else {
                // else start from tail
                Node current = tail;
                for(int i = size-1; i >= index; i--) {
                    current = current.prev;
                }
                (current.prev).next = current.next;
                (current.next).prev = current.prev;
                size--;
                return current;
            }
        }
    }
    
    public void insertAt(int index, int D)  {
        Node newnode = new Node(D);
        if(isEmpty()) {
            insertFirst(D);
        } else {
            if (size/2 > index) {
                // If the position is in the first half, start from head
                Node current = head;
                for(int i = 0; i <= index; i++) {
                    current = current.next;
                }
                newnode.next = current;
                newnode.prev = current.prev;
                (current.prev).next = newnode;
                current.prev = newnode;
                size++;
                
            } else {
                // else start from tail
                Node current = tail;
                for(int i = size-1; i >= index; i--) {
                    current = current.prev;
                }
                newnode.next = current;
                newnode.prev = current.prev;
                (current.prev).next = newnode;
                current.prev = newnode;
                size++;
            }
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
// List Size: 14
// 90, 82, 75, 70, 60, 55, 50, 57, 65, 72, 80, 85, 95, 100
// Value at index 12: 95
// List Size: 13
// 90, 82, 75, 70, 60, 55, 50, 57, 65, 72, 80, 85, 95
// Value at index 5: 55
// Removing value at index 3: 70
// List Size: 12
// 90, 82, 75, 60, 55, 50, 57, 65, 72, 80, 85, 95
// Adding 88 at index 2.
// List Size: 13
// 90, 82, 88, 75, 60, 55, 50, 57, 65, 72, 80, 85, 95
// Adding 98 at index 10.
// List Size: 14
// 90, 82, 88, 75, 60, 55, 50, 57, 65, 72, 98, 80, 85, 95
