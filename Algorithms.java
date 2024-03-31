import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class Algorithms {
}
class MinHeap {

    // To store array of elements in heap
    private final int[] heapArray;

    // max size of the heap
    private final int capacity;

    // Current number of elements in the heap
    private int current_heap_size;

    // Constructor
    public MinHeap(int n) {
        capacity = n;
        heapArray = new int[capacity];
        current_heap_size = 0;
    }

    // Swapping using reference
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    // Get the Parent index for the given index
    private int parent(int key) {
        return (key - 1) / 2;
    }

    // Get the Left Child index for the given index
    private int left(int key) {
        return 2 * key + 1;
    }

    // Get the Right Child index for the given index
    private int right(int key) {
        return 2 * key + 2;
    }


    // Inserts a new key
    public void insertKey(int key) {
        if (current_heap_size == capacity) {

            // heap is full
            return;
        }

        // First insert the new key at the end
        int i = current_heap_size;
        heapArray[i] = key;
        current_heap_size++;

        // Fix the min heap property if it is violated
        while (i != 0 && heapArray[i] < heapArray[parent(i)]) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
    }

    // Decreases value of given key to new_val.
    // It is assumed that new_val is smaller
    // than heapArray[key].
    public void decreaseKey(int key, int new_val) {
        heapArray[key] = new_val;

        while (key != 0 && heapArray[key] < heapArray[parent(key)]) {
            swap(heapArray, key, parent(key));
            key = parent(key);
        }
    }

    // Returns the minimum key (key at
    // root) from min heap
    public int getMin() {
        return heapArray[0];
    }


    // Method to remove minimum element
    // (or root) from min heap
    public void extractMin() {
        if (current_heap_size <= 0) {
            return;
        }

        if (current_heap_size == 1) {
            current_heap_size--;
            return;
        }

        // Store the minimum value,
        // and remove it from heap
        int root = heapArray[0];

        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        MinHeapify(0);

    }

    // This function deletes key at the
    // given index. It first reduced value
    // to minus infinite, then calls extractMin()
    public void deleteKey(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }
    public void print() {
        for (int num : heapArray) {
            System.out.print(num + " ");
        }
    }

    // A recursive method to heapify a subtree
    // with the root at given index
    // This method assumes that the subtrees
    // are already heapified
    private void MinHeapify(int key) {
        int l = left(key);
        int r = right(key);

        int smallest = key;
        if (l < current_heap_size && heapArray[l] < heapArray[smallest]) {
            smallest = l;
        }
        if (r < current_heap_size && heapArray[r] < heapArray[smallest]) {
            smallest = r;
        }

        if (smallest != key) {
            swap(heapArray, key, smallest);
            MinHeapify(smallest);
        }
    }

    // Increases value of given key to new_val.
    // It is assumed that new_val is greater
    // than heapArray[key].
    // Heapify from the given key
    public void increaseKey(int key, int new_val) {
        heapArray[key] = new_val;
        MinHeapify(key);
    }

    // Changes value on a key
    public void changeValueOnAKey(int key, int new_val) {
        if (heapArray[key] == new_val) {
            return;
        }
        if (heapArray[key] < new_val) {
            increaseKey(key, new_val);
        } else {
            decreaseKey(key, new_val);
        }
    }
}

// Driver Code
class MinHeapTest {
    public static void main(String[] args) {
        MinHeap h = new MinHeap(11);
        h.insertKey(3);
        h.insertKey(2);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        h.print();
    }
}

 class MergeSort {
    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        int[] test = new int[]{1,3,7,10,2,5,6,13};
        merge(test, 0,3,7 );
        System.out.println(Arrays.toString(test));

        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l + r)/2;
        mergeSort(arr, l, mid); // sort left half
        mergeSort(arr, mid+1, r); //sort right half
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r){
        int[] temp = new int[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);

        int i = l, j = mid+1, k = l;
        while(i <= mid && j <= r){
            arr[k++] = temp[i] < temp[j] ? temp[i++] : temp[j++];
        }
        while(i <= mid) arr[k++] = temp[i++];
        while(j <= r) arr[k++] = temp[j++];
    }
}


 final class CountSort {
    public static int[] countSort(int[] inputArray) {
        int N = inputArray.length;

        // Finding the maximum element of array inputArray[].
        int MAX = inputArray[0];
        int MIN = inputArray[0];
        for (int j : inputArray) {
            MAX = Math.max(MAX, j);
            MIN = Math.min(MIN, j);
        }

        // Initializing countArray[] with 0
        int[] countArray = new int[MAX - MIN + 1];

        // Mapping each element of inputArray[] as an index
        // of countArray[] array
        for (int j : inputArray) countArray[j - MIN]++;

        // Calculating prefix sum at every index
        // of array countArray[]
        for (int i = 1; i <= MAX - MIN; i++)
            countArray[i] += countArray[i - 1];

        // Creating outputArray[] from countArray[] array
        int[] outputArray = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            outputArray[countArray[inputArray[i] - MIN] - 1] = inputArray[i];
            if (countArray[inputArray[i] - MIN] > 0) countArray[inputArray[i] - MIN]--;
            else countArray[inputArray[i] - MIN]++;
        }

        return outputArray;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Input array
        int n = scan.nextInt();
        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) inputArray[i] = (int)(Math.random() * n);
            else inputArray[i] = (int)-(Math.random() * n);
        }
        long startTimeCountingSort = System.currentTimeMillis();
        int[] countingSortResult = countSort(inputArray.clone());
        long endTimeCountingSort = System.currentTimeMillis();
        // Measure time for Quick Sort
        long startTimeQuickSort = System.currentTimeMillis();
        int[] quickSortArray = inputArray.clone();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);

        long endTimeQuickSort = System.currentTimeMillis();
        System.out.println("Count sort Result: " + Arrays.toString(countingSortResult));
        System.out.println("Quick Sort Result: " + Arrays.toString(quickSortArray));
        System.out.println("Time taken by Quick Sort: " + (endTimeQuickSort - startTimeQuickSort) + " milliseconds");
        System.out.println("Time taken by Counting Sort: " + (endTimeCountingSort - startTimeCountingSort) + " milliseconds");
    }

     public static void quickSort(int[] array, int low, int high) {
         if (low < high) {
             int partitionIndex = partition(array, low, high);

             quickSort(array, low, partitionIndex - 1);
             quickSort(array, partitionIndex + 1, high);
         }
     }

     private static int partition(int[] array, int low, int high) {
         int pivot = array[high];
         int i = low - 1;

         for (int j = low; j < high; j++) {
             if (array[j] < pivot) {
                 i++;
                 swap(array, i, j);
             }
         }

         swap(array, i + 1, high);
         return i + 1;
     }

     private static void swap(int[] array, int i, int j) {
         int temp = array[i];
         array[i] = array[j];
         array[j] = temp;
     }

 }













































class BucketSort {

    public static void bucketSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int maxValue = getMaxValue(array);
        int numberOfBuckets = (maxValue / 10) + 1;

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(numberOfBuckets);

        // Initialize buckets
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute elements into buckets
        for (int num : array) {
            int bucketIndex = num / 10;
            buckets.get(bucketIndex).add(num);
        }

        // Sort each bucket using a simple sorting algorithm (e.g., Collections.sort)
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenate the sorted buckets to get the final sorted array
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }

    private static int getMaxValue(int[] array) {
        int maxValue = array[0];
        for (int num : array) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        fill(array);
        System.out.println("Original Array: " + arrayToString(array));

        bucketSort(array);

        System.out.println("Sorted Array: " + arrayToString(array));
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    static void fill(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * arr.length);
        }
    }
}


















 class NextPermutation {

    public static void nextPermutation(int[] permutation) {
        int n = permutation.length;
        int j = n - 1;

        // Find the largest subscript with a[j] < a[j+1]
        while (j > 0 && permutation[j - 1] >= permutation[j]) {
            j--;
        }

        // If j is negative, the input permutation is the last permutation
        if (j == 0) {
            reverse(permutation, 0, n - 1);
            return;
        }

        int k = n - 1;

        // Find the smallest integer greater than a[j-1] to the right of a[j-1]
        while (permutation[j - 1] >= permutation[k]) {
            k--;
        }

        // Swap a[j-1] and a[k]
        swap(permutation, j - 1, k);

        int r = n - 1;
        int s = j;

        // Reverse the tail end of the permutation to make it increasing
        while (r > s) {
            swap(permutation, r, s);
            r--;
            s++;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        int[] permutation = {1,2,3,4};


        // Display the next permutation
        for (int i = 0; i < factorial(permutation.length); i++) {
            System.out.println(Arrays.toString(permutation));
            nextPermutation(permutation);
        }
    }
    static int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}

 class NextCombination {

    public static void nextCombination(int[] combination, int n, int r) {
        int i = r - 1;

        // Find the rightmost element that can be incremented
        while (i >= 0 && combination[i] == n - r + i + 1) {
            i--;
        }

        // If no such element is found, the combination is the last one
        if (i < 0) {
            return;
        }

        // Increment the found element
        combination[i]++;

        // Update the remaining elements
        for (int j = i + 1; j < r; j++) {
            combination[j] = combination[i] + j - i;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        int n = 5; // Size of the set {1, 2, ..., n}
        int r = 3; // Size of the combination
        int[] combination = {1, 2, 3}; // Initial combination

        // Display the initial combination


        // Generate and display the next combination

        System.out.print("Next combination: ");
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(combination));
            nextCombination(combination, n, r);
        }
    }
}
 class BinaryTreeDrawing extends JFrame {

    private static final int LEVEL_HEIGHT = 50;
    private static final int LEVELS = 8;
    private static final int DELAY = 10;

    public BinaryTreeDrawing() {
        setTitle("Recursive Binary Tree Drawing");
        setSize(800, 30 + LEVELS * LEVEL_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void drawTree(Graphics2D g, double x, double y, double dx, int level) {
        if (level > 0) {
            drawTree(g, x - dx, y + LEVEL_HEIGHT, dx / 2, level - 1);
            g.draw(new Line2D.Double(x, y, x - dx, y + LEVEL_HEIGHT));
            g.draw(new Line2D.Double(x, y, x + dx, y + LEVEL_HEIGHT));
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drawTree(g, x + dx, y + LEVEL_HEIGHT, dx / 2, level - 1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawTree(g2d, getWidth() / 2.0, 10, getWidth() / 5.0, LEVELS);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BinaryTreeDrawing binaryTreeDrawing = new BinaryTreeDrawing();
            binaryTreeDrawing.setVisible(true);
        });
    }
}
 class ESquaresDrawing extends JFrame {

    private static final double MW = 2.9;

    public ESquaresDrawing() {
        setTitle("Recursive E-Squares");
        setSize(750, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void eSquares(Graphics2D g, int n, int x, int y, int w) {
        int w1 = (int) Math.round(w / MW);
        int h = (w - 2 * w1) / 3;
        g.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
        g.fillRect(x, y, w, w);
        if (n > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eSquares(g, n - 1, x + h, y + h, w1);
            eSquares(g, n - 1, x + w - h - w1, y + h, w1);
            eSquares(g, n - 1, x + h, y + w - h - w1, w1);
            eSquares(g, n - 1, x + w - h - w1, y + w - h - w1, w1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        eSquares(g2d, 4, 125, 18, 490);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ESquaresDrawing eSquaresDrawing = new ESquaresDrawing();
            eSquaresDrawing.setVisible(true);
        });
    }
}














 class ParenthesesCombinations {

    // Function to generate all possible parenthesized expressions
    public static List<String> generateParenthesesCombinations(int n) {
        List<String> result = new ArrayList<>();
        if (n > 0) {
            generateParenthesesRecursive(n, "x0", result);
        }
        return result;
    }

    // Recursive function to generate parenthesized expressions
    private static void generateParenthesesRecursive(int remainingFactors, String current, List<String> result) {
        if (remainingFactors == 0) {
            result.add(current);
            return;
        }

        for (int i = 1; i <= remainingFactors; i++) {
            String newExpression = "(" + current + " · x" + i + ")";
            generateParenthesesRecursive(remainingFactors - i, newExpression, result);
        }
    }

    public static void main(String[] args) {
        int n = 7; // Adjust the value of n as needed

        List<String> combinations = generateParenthesesCombinations(n);

        System.out.println("All possible parenthesized expressions for C" + n + ":");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}

