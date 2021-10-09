package cn.linjunyu.linked.datastructure;

/**
 * 自定义 LinkedList 的测试
 *
 * @author LinJn
 * @since 2021/10/3 1:05
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.println("从 1 存到 10，调用的是 addLast方法");
        for (int i = 1; i <= 10; i++) {
            linkedList.addLast(i);
        }
        System.out.println(linkedList);
        System.out.println("存 11 和 12，调用的是 addFirst方法");
        for (int i = 11; i <= 12; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);
        System.out.println("索引为 3 的位置存 13");
        linkedList.add(2, 13);
        System.out.println(linkedList);
        System.out.println("取索引为 12 的元素");
        final Integer integer = linkedList.get(12);
        System.out.println(integer);
        System.out.println("删除索引为 2 的元素");
        linkedList.remove(2);
        System.out.println(linkedList);
    }

}
