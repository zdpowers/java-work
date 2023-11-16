
import java.util.ArrayList;

public class HelloWorld {
    public static void main(String[] args) {
        
        
        HeapTree ht = new HeapTree();
        
        int[] inputArray = {10, 13, 1, 20, 5, 2, 17, 30, 27, 22, 7, 9};
        int[] outputArray = new int[12];
        
        for(int val : inputArray) {
            ht.addNew(val);
        }
        System.out.println(ht);
        
        for(int i=0; i<outputArray.length; i++) {
            outputArray[i] = ht.removeMin();
        }
        System.out.println();
        for(int val : outputArray) {
            System.out.print(val + " ");
        }
    }
}

class HeapTree {
    
    private ArrayList<Integer> heap = new ArrayList<>();
    public HeapTree() {}
    
    private int getParentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }
    private int getRightChildIndex(int currentIndex) {
        return (2*currentIndex) + 2;
    }
    private int getLeftChildIndex(int currentIndex) {
        return (2*currentIndex) + 1;
    }
    
    private Boolean hasLeftChild(int currentIndex) {
        return ((2*currentIndex) + 1) < heap.size();
    }
    private Boolean hasRightChild(int currentIndex) {
        return ((2*currentIndex) + 2) < heap.size();
    }
    
    public Boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public void addRoot(int v) {
        if(isEmpty()) {
            heap.add(0, v);
        } else {
            System.out.println("Root already exists.");
        }
    }
    
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    private void upheap(int j) {
        while (j>0) {
            int p = getParentIndex(j);
            if(heap.get(j) >= heap.get(p))
                break;
            swap(j, p);
            j = p;
        }
    }
    
    private void downheap(int j) {
        while(hasLeftChild(j)) {
            int leftIndex = getLeftChildIndex(j);
            int smallestChildIndex = leftIndex;
            if(hasRightChild(j)) {
                int rightIndex = getRightChildIndex(j);
                if(heap.get(leftIndex) > heap.get(rightIndex))
                    smallestChildIndex = rightIndex;
            }
            if(heap.get(smallestChildIndex) >= heap.get(j))
                break;
            swap(j, smallestChildIndex);
            j = smallestChildIndex;
        }
    }
    
    public void addNew(int v) {
        heap.add(v);
        upheap(heap.size()-1);
    }
    
    public int removeMin() {
        if(isEmpty())
            return -1;
        int minval = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return minval;
    }
    
    public String toString() {
        String output = "";
        for(int val : heap) {
            output += val + " ";
        }
        return output;
    }
    
    
    
    
}
