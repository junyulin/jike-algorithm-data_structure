package cn.linjunyu.union;

/**
 * QuickUnion 的测试类
 *
 * @author LinJn
 * @since 2021/12/18 13:37
 */
public class QuickUnionTest {

    public static void main(String[] args) {
        final QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(2, 3);
        quickUnion.union(1, 3);
        quickUnion.union(5, 6);
        quickUnion.union(7, 8);
    }

}
