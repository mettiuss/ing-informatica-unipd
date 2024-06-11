public class ArrayQueueTester {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("hi1");
        queue.enqueue("hi2");
        queue.enqueue("hi3");
        queue.enqueue("hi4");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("hi5");
        queue.enqueue("hello");
        queue.enqueue("hello2");
        queue.enqueue("hello3");

        System.out.print(queue);
    }
}