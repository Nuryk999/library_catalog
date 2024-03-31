public class stack {
    public int size;
    int[] stack;
    private int top = -1;
    stack(int n) {
        this.size = n;
        stack = new int[size];
    }
    stack(){}
    void push(int num) {
        if (top == size - 1) {
            System.err.println("ERROR, STACK OVERFLOW");
            return;
        }
        stack[++top] = num;
    }
    boolean is_empty() {
        return top == -1;
    }
    void print_stack() {
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
    int pop() {
        if (is_empty()) {
            System.err.println("ERROR, STACK UNDERFLOW");
            return -1;
        }
        top--;
        return stack[top + 1];
    }
}
