public class Heap<K extends Comparable<K>, V> {
    private ExpandableArray<Entry<K, V>> heap;
    private boolean isMinHeap;

    /**
     * Constructs an empty heap with the default min-heap property.
     */
    public Heap() {
        heap = new ExpandableArray<>();
        isMinHeap = true;
    }

    /**
     * Toggles the heap between min-heap and max-heap and rebuilds the heap.
     */
    public void toggle() {
        isMinHeap = !isMinHeap;
        buildHeap();
    }

    /**
     * Removes and returns the top element of the heap.
     *
     * @return the top entry or null if the heap is empty.
     */
    public Entry<K, V> removeTop() {
        if (heap.isEmpty()) {
            return null;
        }
        Entry<K, V> top = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        downHeap(0);
        return top;
    }

    /**
     * Inserts a new entry with the specified key and value into the heap.
     *
     * @param key the key of the entry.
     * @param value the value of the entry.
     * @return the inserted entry.
     */
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        heap.add(entry);
        upHeap(heap.size() - 1);
        return entry;
    }

    /**
     * Returns the top entry of the heap without removing it.
     *
     * @return the top entry or null if the heap is empty.
     */
    public Entry<K, V> top() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    /**
     * Removes the specified entry from the heap.
     *
     * @param entry the entry to remove.
     */
    public void remove(Entry<K, V> entry) {
        int index = findEntry(entry);
        if (index == -1) {
            return;
        }
        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        downHeap(index);
    }

    /**
     * Replaces the key of the specified entry with a new key.
     *
     * @param entry the entry to update.
     * @param newKey the new key.
     * @return the old key or null if the entry was not found.
     */
    public K replaceKey(Entry<K, V> entry, K newKey) {
        int index = findEntry(entry);
        if (index == -1) {
            return null;
        }
        K oldKey = entry.getKey();
        heap.get(index).setKey(newKey);
        if (compare(newKey, oldKey) > 0) {
            downHeap(index);
        } else {
            upHeap(index);
        }
        return oldKey;
    }

    /**
     * Replaces the value of the specified entry with a new value.
     *
     * @param entry the entry to update.
     * @param newValue the new value.
     * @return the old value or null if the entry was not found.
     */
    public V replaceValue(Entry<K, V> entry, V newValue) {
        int index = findEntry(entry);
        if (index == -1) {
            return null;
        }
        V oldValue = heap.get(index).getValue();
        heap.get(index).setValue(newValue);
        return oldValue;
    }

    /**
     * Returns the current state of the heap (Min or Max).
     *
     * @return "Min" if the heap is a min-heap, "Max" otherwise.
     */
    public String state() {
        return isMinHeap ? "Min" : "Max";
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the size of the heap.
     *
     * @return the number of elements in the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Builds the heap by applying downHeap from the middle to the start of the array.
     */
    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Restores the heap property by moving the element at the specified index up.
     *
     * @param index the index of the element to move up.
     */
    private void upHeap(int index) {
        while (index > 0 && compare(heap.get(index).getKey(), heap.get(parent(index)).getKey()) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * Restores the heap property by moving the element at the specified index down.
     *
     * @param index the index of the element to move down.
     */
    private void downHeap(int index) {
        int smallestOrLargest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && compare(heap.get(left).getKey(), heap.get(smallestOrLargest).getKey()) < 0) {
            smallestOrLargest = left;
        }

        if (right < heap.size() && compare(heap.get(right).getKey(), heap.get(smallestOrLargest).getKey()) < 0) {
            smallestOrLargest = right;
        }

        if (smallestOrLargest != index) {
            swap(index, smallestOrLargest);
            downHeap(smallestOrLargest);
        }
    }

    /**
     * Compares two keys based on the heap type (min-heap or max-heap).
     *
     * @param key1 the first key.
     * @param key2 the second key.
     * @return a negative integer, zero, or a positive integer as the first key is less than, equal to, or greater than the second key.
     */
    private int compare(K key1, K key2) {
        return isMinHeap ? key1.compareTo(key2) : key2.compareTo(key1);
    }

    /**
     * Swaps the elements at the specified indices.
     *
     * @param i the first index.
     * @param j the second index.
     */
    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Returns the index of the parent of the element at the specified index.
     *
     * @param index the index of the element.
     * @return the index of the parent.
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of the element at the specified index.
     *
     * @param index the index of the element.
     * @return the index of the left child.
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of the element at the specified index.
     *
     * @param index the index of the element.
     * @return the index of the right child.
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * Finds the index of the specified entry in the heap.
     *
     * @param entry the entry to find.
     * @return the index of the entry or -1 if not found.
     */
    private int findEntry(Entry<K, V> entry) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a string representation of the heap.
     *
     * @return a string representation of the heap.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            Entry<K, V> entry = heap.get(i);
            sb.append("{key=").append(entry.getKey()).append(", value=").append(entry.getValue()).append("}");
            if (i < heap.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}