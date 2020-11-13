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
        Assertions.assertEquals(3,list.get(0) );
    }

    @org.junit.jupiter.api.Test
    void put() {
        list.put(6);
        Assertions.assertEquals(6, list.get(0));
        list.put(7);
        Assertions.assertNotEquals(8, list.get(1));
    }

    @org.junit.jupiter.api.Test
    void size() {
        Assertions.assertEquals(0, list.size());
        list.put(6);
        list.put(6);
        list.put(6);
        Assertions.assertEquals(3, list.size());

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
        Assertions.assertEquals(2, list.size());
        list.clear();
        Assertions.assertEquals(0, list.size());
        Assertions.assertFalse(list.contains(6));
        Assertions.assertFalse(list.contains(7));
    }

    @org.junit.jupiter.api.Test
    void capacity() {
        Assertions.assertEquals(10, list.capacity());
        for (int i = 0; i < 15; i++) list.put(10);
        Assertions.assertEquals(20, list.capacity());
    }
}