package cn.linjunyu.union;

/**
 * 并查集。解决连接问题和路径问题
 *
 * @author LinJn
 * @since 2021/12/18 12:42
 */
public sealed interface UnionFindSet permits QuickFind, QuickUnion, QuickUnion2, QuickUnion3,
        UnionFindSet1, UnionFindSet2 {

    /**
     * 查询并查集的大小
     * @return 返回并查集的大小
     */
    int size();

    /**
     * 合并 p 和 q 所属的集合
     * @param p p 所属集合的代表
     * @param q q 所属集合的代表
     */
    void union(int p, int q);

    /**
     * 判断 p 和 q 的两个集合是否是同一个集合（是否连接）
     * @param p p 所属集合的元素
     * @param q q 所属集合的元素
     * @return 返回一个 boolean，true 表示 p 和 q 两个集合是同一个集合（是连接的），false 表示不属于同一个集合
     */
    boolean isConnect(int p, int q);
}
