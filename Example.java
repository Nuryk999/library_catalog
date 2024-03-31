import java.util.Scanner;
class NODE {
    int key;
    int value;
    NODE left, right;
    boolean color;
    NODE(int key, int value, boolean color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }
}
public class Example {
    NODE root;
    boolean RED = true;
    boolean BLACK = false;
    private int rotation;

    private NODE put(NODE node, int key, int val) {
        if (node == null) {
            return new NODE(key, val, RED);
        }
        if (key < node.key) {
            node.left = put(node.left, key, val);
        } else if (key > node.key) {
            node.right = put(node.right, key, val);
        } else node.value = val;

//        if (isRed(node.right) && !isRed(node.left)) {
//            node = rotateLeft(node);
//        }
//        if (isRed(node.left) && isRed(node.left.left)) {
//            node = rotateRight(node);
//        }
//        if (isRed(node.left) && isRed(node.right)) {
//            flipColors(node);
//        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
             node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }
    private boolean isRed(NODE node) {
        if (node == null) return false;
        return node.color == RED;
    }
    private void flipColors(NODE h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private NODE rotateLeft(NODE h) {
        rotation++;
        NODE x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private NODE rotateRight(NODE h) {
        rotation++;
        NODE x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = RED;
        return x;
    }

    public int getRotationCount() {
        return rotation;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(NODE node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);

        System.out.println("Key: " + node.key + " | Value: " + node.value + " | Color: " + (node.color == RED ? "Red" : "Black"));

        inOrderTraversal(node.right);
    }
    public static void main(String[] args) {
        LeftLeaningRedBlackBST llrbBST = new LeftLeaningRedBlackBST();
        // Read input
        int n; // Number of key-value pairs
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();
            llrbBST.put(key, value);
        }

        llrbBST.inOrderTraversal();

        // Print rotation count
        System.out.println("Rotate count: " + llrbBST.getRotationCount());
    }
}
/*
private NODE rotateLeft(NODE h) {
  rotation++;
  NODE x = NODE.right;
  h = h.right
}
 */

/*
void max_heapify(vector<int>& arr, int i, int n) {
    int largest {i};
    int l {2 * i + 1};
    int r {2 * i + 2};
    if (n > l and arr[largest] < arr[l]) largest = l;
    if (n > r and arr[largest] < arr[r]) largest = r;
    if (largest != i) {
        swap(arr[i], arr[largest]);
        max_heapify(arr, largest, n);
    }
}
void build_max_heap(vector<int>& arr) {
    int n {static_cast<int>(arr.size())};
    for (int i = n / 2; i >= 0; i--) {
        max_heapify(arr, i, n);
    }
}
void heap_sort(vector<int>& arr) {
    build_max_heap(arr);
    int n {static_cast<int>(arr.size())};
    for (int i = n - 1; i > 0; i--) {
        swap(arr[0], arr[i]);
        max_heapify(arr, 0, i);
    }
}
int partition(vector<int>& arr, int start, int end) {
    int pivot = arr[end - 1];
    int i {start - 1};
    for (int j = start; j < end - 1; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[end - 1]);
    cout << i + 1 << endl;
    return i + 1;
}
int hoare_partition(vector<int>& arr, int start, int end) {
    int pivot = arr[end - 1];
    int i {start - 1};
    int j {end};
    while (1) {
        while (arr[--j] <= pivot);
        while (arr[++i] >= pivot);
        if (i < j) swap(arr[i], arr[j]);
        else return j;
    }
}
void quicksort(vector<int>& arr, int start, int end) {
    if (start < end) {
        int pivot = hoare_partition(arr, start, end);
        quicksort(arr, start, pivot - 1);
        quicksort(arr, pivot + 1, end);
    }
}
void sort(vector<int>& arr) {
    quicksort(arr, 0, arr.size());
}
void print(vector<int>& arr) {
    for (int num: arr) cout << num << ' ';
    cout << endl;
}
 */