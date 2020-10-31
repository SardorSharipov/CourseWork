import java.util.Iterator;

public class LinkedList<T> implements ILinkedList<T>, Iterator<T>, Iterable<T> {

    private Node<T> Head;

    private Node<T> Tail;

    private int size;

    public int getSize() {
        return size;
    }

    public LinkedList() {
        Head = null;
        Tail = null;
        currentNode = null;
        size = 0;
    }

    @SafeVarargs
    public LinkedList(T... items) {
        Head = new Node<>(items[0], null, null);
        Tail = Head;
        currentNode = Head;
        ++size;
        for (int i = 1; i < items.length; i++) {
            if (this.Contains(items[i]))
                continue;
            Node<T> newNode = new Node<>(items[i], Tail, null);
            Tail.Next = newNode;
            Tail = newNode;
            ++size;
        }
    }


    /**
     * @param item : item to add to linked list
     */
    public void Add(T item) {
        if (this.Contains(item))
            return;
        ++size;

        if (Head == null) {
            Head = new Node<>(item, null, null);
            Tail = Head;
            return;
        }

        Node<T> newNode = new Node<>(item, Tail, null);
        Tail.Next = newNode;
        Tail = newNode;
    }


    /**
     * @param item to check, whether it contains in linked list
     */
    public boolean Contains(T item) {
        Node<T> currentNode = Head;
        while (currentNode != null) {
            if (currentNode.equals(item)) {
                return true;
            }
            currentNode = currentNode.Next;
        }
        return false;
    }


    /**
     * @param item to get stuck node
     * @return stuck node to given item
     */
    public Node<T> GetNode(T item) {
        Node<T> currentNode = Head;
        while (currentNode != null) {
            if (currentNode.equals(item)) {
                return currentNode;
            }
            currentNode = currentNode.Next;
        }
        return null;
    }

    public void Remove(T item) {
        if (!this.Contains(item))
            return;
        Node<T> nodeToRemove = GetNode(item);

        if (nodeToRemove == Head) {
            Head = Head.Next;
            Head.Previous = null;
        } else if (nodeToRemove == Tail) {
            Tail = Tail.Previous;
            Tail.Next = null;
        } else {
            Node<T> previousNode = nodeToRemove.Previous;
            Node<T> nextNode = nodeToRemove.Next;

            previousNode.Next = nextNode;
            nextNode.Previous = previousNode;
        }
        --size;
    }

    /* iterator methods realization */
    private Node<T> currentNode;

    public Iterator<T> iterator() {
        return this;
    }

    public boolean hasNext() {
        boolean flag = currentNode != null;
        if (!flag) currentNode = Head;
        return flag;
    }

    public T next() {
        T tempData = currentNode.Data;
        currentNode = currentNode.Next;
        return tempData;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        String[] strings = new String[size];
        Node<T> currentNode = Head;
        int i = 0;
        while (currentNode != null) {
            strings[i++] = currentNode.Data.toString();
            currentNode = currentNode.Next;
        }
        return String.join(" ", strings);
    }
}
