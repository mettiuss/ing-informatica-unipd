public class ArrayDequeTester {
    public static void main(String[] args) {
        ArrayDeque queue = new ArrayDeque();
        queue.addFirst("heil1");
        queue.addFirst("heil2");
        queue.addFirst("heil3");
        queue.addFirst("heil4");
        queue.removeLast();
        queue.removeLast();
        queue.removeLast();
        queue.addFirst("heil5");
        queue.addFirst("heil6");

        System.out.print(queue);
    }
}