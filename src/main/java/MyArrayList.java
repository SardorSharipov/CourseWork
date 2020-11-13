import java.util.Arrays;

public class MyArrayList<T> implements MyCollection<T> {

    private final static int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private Object[] elements;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        elements = new Object[capacity];
    }

    /**
     * @param index - index of collection trying to get
     * @return the element of Collection at given index
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= currentSize) {
            throw new IndexOutOfBoundsException(
                    "trying to get element at index which is more than size of collection"
            );
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException(
                    "trying to get element at negative index"
            );
        }
        return ((T) elements[index]);
    }

    /**
     * @param element - it is an element trying to put on collection
     */
    public void put(T element) {
        if (currentSize >= elements.length) {
            elements = grow();
        }

        elements[currentSize] = element;
        ++currentSize;
    }

    /**
     * @return size of collection
     */
    public int size() {
        return currentSize;
    }

    /**
     * @param element - element to check
     * @return check whether element contains in Collection
     */
    public boolean contains(final T element) {
        return Arrays.asList(elements).contains(element);
    }

    /**
     * Clears collection, delete all elements
     */
    public void clear() {
        currentSize = 0;
        Arrays.fill(elements, null);
    }

    /**
     * @return current capacity of collection
     */
    public int capacity() {
        return elements.length;
    }

    /**
     * @return extends the capacity of MyArray
     */
    private Object[] grow() {
        return Arrays.copyOf(elements, elements.length * 2);
    }
}
