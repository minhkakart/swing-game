import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(8);
        list.add(7);
        list.add(10);
        list.add(9);

        list.sort((o1, o2) -> o2 - o1);

        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

}
