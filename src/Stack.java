class Stack {
    private int size;
    private String[] stackArray;
    private int top;

    Stack() {
        size = 1;
        stackArray = new String[size];
        top = -1;
    }

    void push(String j) {
        checkArraySize();
        stackArray[++top] = j;
    }

    String pop() {
        return stackArray[top--];
    }

    String peek() {
        return stackArray[top];
    }

    boolean isEmpty() {
        return (top == -1);
    }

    private void checkArraySize() {
        if (size - 1 <= top) {
            size *= 2;
            String[] tempArray = stackArray;
            stackArray = new String[size];
            System.arraycopy(tempArray, 0, stackArray, 0, top + 1);
//            for (int i = 0; i <= top; i++) {
//                stackArray[i] = tempArray[i];
//            }
        }
    }

}