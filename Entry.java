public class Entry<K, V> {
    private K key;
    private V value;

    /**
     * Constructs an entry with the specified key and value.
     *
     * @param key the key of the entry.
     * @param value the value of the entry.
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of the entry.
     *
     * @return the key of the entry.
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key of the entry.
     *
     * @param key the new key.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Returns the value of the entry.
     *
     * @return the value of the entry.
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value of the entry.
     *
     * @param value the new value.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the entry.
     *
     * @return a string representation of the entry.
     */
    @Override
    public String toString() {
        return "Entry{" + "key=" + key + ", value=" + value + '}';
    }

    /**
     * Compares this entry to the specified object for equality.
     *
     * @param o the object to compare to.
     * @return true if the specified object is equal to this entry, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry<?, ?> entry = (Entry<?, ?>) o;

        if (!key.equals(entry.key)) return false;
        return value.equals(entry.value);
    }

}