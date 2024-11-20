public class ExpandableArray<T> {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty expandable array with the default capacity.
     */
    public ExpandableArray() {
        array = createArray(DEFAULT_CAPACITY);
        size = 0;
    }

    /**
     * Constructs an empty expandable array with the specified initial capacity.
     *
     * @param startingSize the initial capacity of the array.
     */
    public ExpandableArray(int startingSize) {
        array = createArray(startingSize);
        size = 0;
    }

    /**
     * Creates an array of the specified size.
     *
     * @param size the size of the new array.
     * @return the created array.
     */
    private T[] createArray(int size) {
        return (T[]) new Object[size];
    }

    /**
     * Returns the number of elements in the array.
     *
     * @return the size of the array.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the array is empty.
     *
     * @return true if the array is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the index of the element.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Sets the element at the specified index to the given value.
     *
     * @param index the index of the element.
     * @param value the value to set.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = value;
    }

    /**
     * Adds a new element to the end of the array.
     *
     * @param value the element to add.
     */
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size++] = value;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    /**
     * Doubles the capacity of the array.
     */
    private void expandArray() {
        T[] newArray = createArray(array.length * 2);
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Returns a string representation of the array.
     *
     * @return a string representation of the array.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}