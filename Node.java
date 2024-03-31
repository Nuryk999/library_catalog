public class Node {
    int val;
    Node next;
    Node() {

    }
    Node(int val) {
        this.val = val;
    }
    Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }
    int countNodes(Node head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}

