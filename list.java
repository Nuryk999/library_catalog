public class list<T> {
    node list;
    private class node {
        T data;
        node next;
        node(){};
        node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    void insert(T data) {
        node curr = new node(data);
        list.next = curr;

    }
}
