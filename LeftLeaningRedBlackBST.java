
import java.util.*;

public class LeftLeaningRedBlackBST {
    private Node root;
    private int rotationCount;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static class Node {
        int key;
        int value;
        Node left, right;
        boolean color;

        Node(int key, int value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
    public void put(int key, int val) {
        root = put(root, key, val);
        root.color = BLACK; // Root is always black
    }

    private Node put(Node node, int key, int val) {
        if (node == null) {
            return new Node(key, val, RED);
        }

        if (key < node.key) {
            node.left = put(node.left, key, val);
        } else if (key > node.key) {
            node.right = put(node.right, key, val);
        } else {
            node.value = val;
        }

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

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node h) {
        rotationCount++;
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        rotationCount++;
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int getRotationCount() {
        return rotationCount;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
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
