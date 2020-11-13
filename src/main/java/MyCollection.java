public interface MyCollection<T> {

    /**
     * @param index - index of collection trying to get
     * @return the element of Collection at given index
     */
    T get(int index);

    /**
     * @param element - it is a element trying to put on collection
     */
    void put(T element);

    /**
     * @return size of collection
     */
    int size();

    /**
     * @param element - element to check
     * @return check whether element contains in Collection
     */
    boolean contains(T element);


    /**
     * Clears collection, delete all elements
     */
    void clear();


    /**
     * @return current capacity of collection
     */
    int capacity();
}
