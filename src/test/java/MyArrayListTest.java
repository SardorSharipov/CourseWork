import org.junit.jupiter.api.Assertions;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @org.junit.jupiter.api.Test
    void get() {
        list.put(3);
        Assertions.assertEquals(list.get(0), 3);
    }

    @org.junit.jupiter.api.Test
    void put() {
        list.put(6);
        Assertions.assertEquals(list.get(0), 6);
        list.put(7);
        Assertions.assertNotEquals(list.get(1), 8);
    }

    @org.junit.jupiter.api.Test
    void size() {
        Assertions.assertEquals(list.size(), 0);
        list.put(6);
        list.put(6);
        list.put(6);
        Assertions.assertEquals(list.size(), 3);

    }

    @org.junit.jupiter.api.Test
    void contains() {
        list.put(6);
        Assertions.assertTrue(list.contains(6));
        Assertions.assertFalse(list.contains(7));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        list.put(6);
        list.put(7);
        Assertions.assertEquals(list.size(), 2);
        list.clear();
        Assertions.assertEquals(list.size(), 0);
        Assertions.assertFalse(list.contains(6));
        Assertions.assertFalse(list.contains(7));
    }

    @org.junit.jupiter.api.Test
    void capacity() {
        Assertions.assertEquals(list.capacity(), 10);
        for (int i = 0; i < 15; i++) list.put(10);
        Assertions.assertEquals(list.capacity(), 20);
    }
}