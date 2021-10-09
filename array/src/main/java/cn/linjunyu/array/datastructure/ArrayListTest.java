package cn.linjunyu.array.datastructure;

/**
 * 测试自己写 ArrayList
 *
 * @author LinJn
 * @since 2021/9/29 13:32
 */
public class ArrayListTest {

    public static void main(String[] args) {
        final ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.println("添加 1 - 10");
        for (int i = 1; i <= 10; i++) {
            arrayList.add(i);
            System.out.println(arrayList);
        }
        System.out.println("遍历 1 - 10");
        for (Integer i : arrayList) {
            System.out.println(i);
        }
        System.out.println("将索引 5 - 9 的元素全部删除，也即是删除 10、9、8、7、6");
        for (int i = 9; i >= 5; i--) {
            System.out.println(arrayList.remove(i));
            System.out.println(arrayList);
        }

        System.out.println("添加元素 22 到索引为 2 的位置");
        arrayList.add(2, 22);
        System.out.println(arrayList);

        System.out.println("向头部添加 77");
        arrayList.addFirst(77);
        System.out.println(arrayList);

        System.out.println("删除掉第一个元素");
        System.out.println(arrayList.removeFirst());
        System.out.println(arrayList);

        System.out.println("删除掉最后元素");
        System.out.println(arrayList.removeLast());
        System.out.println(arrayList);

        System.out.println("获取索引为 2 的元素");
        final Integer integer = arrayList.get(2);
        System.out.println(integer);

        System.out.println("将索引为 3 的元素设置为 33");
        arrayList.set(3, 33);
        System.out.println(arrayList);

        System.out.println(arrayList.get(4));
        // arrayList.remove(5);
        arrayList.set(5, 40);
    }

}
