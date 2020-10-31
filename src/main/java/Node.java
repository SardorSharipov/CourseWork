
public class Node<T> {
    public T Data;
    public Node<T> Next;
    public Node<T> Previous;

    public Node(T data, Node<T> previous,
                Node<T> next) {
        Data = data;
        Next = next;
        Previous = previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        T item = (T) o;
        return Data.equals(item);
    }

    @Override
    public String toString() {
        return Data.toString();
    }

    @Override
    public int hashCode() {
        return Data.hashCode();
    }
}
