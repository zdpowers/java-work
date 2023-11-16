import java.util.ArrayList;

public class MyClass { 
    public static void main(String args[]) {
        
        
        int[] inputArray = {10, 13, 1, 20, 5, 2, 17, 30, 27, 22, 7, 9};
        HeapSortInPlace.printHeap(inputArray);
        System.out.println();
        HeapSortInPlace.buildHeap(inputArray, inputArray.length);
        HeapSortInPlace.printHeap(inputArray);
        System.out.println();
        HeapSortInPlace.heapSort(inputArray, inputArray.length);
        HeapSortInPlace.printHeap(inputArray);
		
		
	}
}


class HeapSortInPlace {
    
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
    
    static void printHeap(int arr[]) {
        for(int val : arr) {
            System.out.print(val + " ");
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
    
    
    
    
    
    
    
}