public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 4, 3, 3);
        for (Integer a : list) {
            System.out.println(a);
        }
        list.Add(5);
        list.Remove(0);
        list.Remove(4);
        list.Add(9);
        list.Add(10);
        System.out.println("add 5 9 10, remove 0 4");
        for (Integer a : list) {
            System.out.println(a);
        }
        System.out.println("Size: " + list.getSize());
    }
}

