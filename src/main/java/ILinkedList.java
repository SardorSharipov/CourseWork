public interface ILinkedList<T> {
    void Add(T item);
    boolean Contains(T item);
    int getSize();
    void Remove(T item);
}
