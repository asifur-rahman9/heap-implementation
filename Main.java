public class Main {
    public static void main(String[] args) {
        Heap<Integer, String> heap = new Heap<>();

        heap.insert(10, "A");
        heap.insert(5, "B");
        heap.insert(20, "C");
        heap.insert(3, "D");
        heap.insert(8, "E");

        System.out.println("Top element: " + heap.top());

        System.out.println("Removed top element: " + heap.removeTop());

        heap.insert(1, "F");
        heap.insert(15, "G");

        System.out.println("Heap before replacing key: " + heap);

        heap.replaceKey(new Entry<>(5, "B"), 2);
        System.out.println("Heap after replacing key of 5 with 2: " + heap);

        heap.replaceValue(new Entry<>(10, "A"), "Z");
        System.out.println("Heap after replacing value of key 10 with 'Z': " + heap);

        heap.remove(new Entry<>(8, "E"));
        System.out.println("Heap after removing entry with key 8: " + heap);

        System.out.println("Heap type: " + heap.state());

        heap.toggle();
        System.out.println("Toggled heap type: " + heap.state());

        for (int i = 5; i < 15; i++) {
            heap.insert(i, String.valueOf(i));
        }

        System.out.println("Heap size after inserting 30 elements: " + heap.size());
        System.out.println("Is heap empty? " + heap.isEmpty());


        heap.insert(2, "X");
        heap.insert(18, "W");

        heap.remove(new Entry<>(15, "G"));
        System.out.println("Heap after removing entry with key 15: " + heap);

        heap.insert(4, "M");
        heap.insert(12, "N");
        System.out.println("Removed top element: " + heap.removeTop());
        heap.insert(7, "O");
        heap.replaceKey(new Entry<>(12, "N"), 1);
        heap.toggle();
        heap.remove(new Entry<>(4, "M"));
        System.out.println("Heap after all operations: " + heap);
    }
}