public class Heap {
    //the actual storage structure for your heap
    private int[] arr;

    //constructor for your heap
    //feel free to make one that takes in an array if you prefer for your testing purposes.
    //note that we will not be inserting more than 100 elements into our array so you need not worry about re-sizing
    //the array
    public Heap() {
        arr = new int[100];
    }

    //create this function to add elements to your heap
    //all heap properties must be preserved
    // 5 points
    public void add(int toAdd) {
        if (arr[0] == 0) {
            arr[0] = toAdd;
        } else {
            int index = 0;
            while (arr[index] != 0) {
                index++;
            }
            arr[index] = toAdd;
            siftUp(index);
        }
    }

    //remove the largest element of the heap (the root) and re-heapafy
    //5 points
    public void removeMax() {
        if (arr[0] == 0) {
            return; // Heap is empty
        }
        int lastIndex = 0;
        while (lastIndex < arr.length && arr[lastIndex] != 0) {
            lastIndex++;
        }
        lastIndex--; // Move to the last valid element
        arr[0] = arr[lastIndex];
        arr[lastIndex] = 0; // Clear the last element
        siftDown(0);
    }

    //this should check and alter the tree after an item is inserted
    //3 points
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (arr[index] > arr[parentIndex]) {
                // Swap
                int temp = arr[index];
                arr[index] = arr[parentIndex];
                arr[parentIndex] = temp;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    //this should check and alter the tree after an item is deleted.
    //3 points
    private void siftDown(int index) {
        int size = 0;
        while (size < arr.length && arr[size] != 0) {
            size++;
        }
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex < size && arr[leftChildIndex] > arr[largestIndex]) {
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < size && arr[rightChildIndex] > arr[largestIndex]) {
                largestIndex = rightChildIndex;
            }
            if (largestIndex != index) {
                // Swap
                int temp = arr[index];
                arr[index] = arr[largestIndex];
                arr[largestIndex] = temp;
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    //4 points for syntax conventions.
    
}