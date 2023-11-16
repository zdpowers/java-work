import java.util.ArrayList;

public class MyClass { 
    public static void main(String args[]) {
        HeapTree ht = new HeapTree();
		int[] inputArray1 = {10, 13, 1, 20, 5, 2, 17, 30, 27, 22, 7, 9, 42, 21, 50, 44, 67, 32, 41, 62, 77, 11, 16, 59, 70};
		int[] inputArray2 = {10, 13, 1, 20, 5, 2, 17, 30, 27, 22, 7, 9, 42, 21, 50, 44, 67, 32, 41, 62, 77, 11, 16, 59, 70};
		
		
		
		
		// In-Place HeapSort
		System.out.println("IN-PLACE HEAP SORT");
		System.out.println("Initial Array:");
		HeapTree.printArray(inputArray1); // Print Initial Array
		System.out.println();
		
		long startTime1 = System.nanoTime();   // Start Time
		HeapTree.inPlaceHeapSort(inputArray1);          // In-Place Heap Sort
		long endTime1 = System.nanoTime();     // End time
		long elapsed1 = endTime1 - startTime1;          // Elapsed Time
		
		System.out.println("Sorted Array:");
		HeapTree.printArray(inputArray1); // Print Sorted Array
		System.out.println();
		System.out.println("In-Place Heap Sort Runtime: " + elapsed1 + "ns"); // Print Run Time
		System.out.println();
		
		
		
		// Heap-Sort with Heap Tree
		System.out.println("HEAP SORT");
        	System.out.println("Initial Array:");
		HeapTree.printArray(inputArray2); // Print Initial Array
		System.out.println();
		
		long startTime2 = System.nanoTime(); // Start time
		
		for(int i : inputArray2) {
		    ht.addNew(i); // Fill heap Tree
		}
		
		for(int i=0; i<inputArray2.length; i++) {
		    inputArray2[i] = ht.removeMin();
		}
		
		long endTime2 = System.nanoTime();
		long elapsed2 = endTime2 - startTime2;
		
		System.out.println("Sorted Array:");
		HeapTree.printArray(inputArray2); // Print Sorted Array
		System.out.println();
		System.out.println("Heap Sort Runtime: " + elapsed2 + "ns"); // Print Run Time
		System.out.println();
		
		
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
        if(heap.isEmpty())
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
    
    
    //STATIC METHODS FOR IN-PLACE HEAP SORT
    static void heapify(int arr[], int arrSize, int root) {
        int largest = root;
        int left = (2 * root) + 1;
        int right = (2 * root) + 2;
        
        if(left < arrSize && arr[left] > arr[largest])
            largest = left;
        
        if(right < arrSize && arr[right] > arr[largest])
            largest = right;
            
        if(largest != root) {
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;
            
            heapify(arr, arrSize, largest);
        }
    }
    
    static void buildHeap(int arr[], int arrSize) {
        int startIndex = (arrSize/2) - 1;
        for(int i = startIndex; i>=0; i--) {
            heapify(arr, arrSize, i);
        }
    }
    
    static void heapSort(int arr[], int arrSize) {
        if (arrSize > 1) {
            int temp = arr[arrSize-1];
            arr[arrSize-1] = arr[0];
            arr[0] = temp;
            heapify(arr, arrSize-1, 0);
            heapSort(arr, arrSize-1);
        }
    }
    
    static void inPlaceHeapSort(int arr[]) {
        buildHeap(arr, arr.length);
        heapSort(arr, arr.length);
    }
    
    static void printArray(int arr[]) {
        for(int val : arr) {
            System.out.print(val + " ");
        }
    }
    
}
