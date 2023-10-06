public class DequeTester {
    public static void main(String[] args) {
        ArrayDeque deque = new ArrayDeque();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("a");
        deque.addLast("b");

        System.out.println(deque);
    }
}