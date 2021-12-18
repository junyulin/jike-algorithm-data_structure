package cn.linjunyu.union;

/**
 * 查询和合并操作的时间复杂度都是 O(h) 的并查集，由子节点指向父节点。h 为树的高度
 *
 * @author LinJn
 * @since 2021/12/18 13:29
 */
public final class QuickUnion implements UnionFindSet {

    private final int[] parents;

    public QuickUnion(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0");
        }
        this.parents = new int[size];
        for (int i = 0; i < this.parents.length; i++) {
            this.parents[i] = i;
        }
    }

    @Override
    public int size() {
        return this.parents.length;
    }

    @Override
    public void union(int p, int q) {
        final int ip = this.find(p);
        final int iq = this.find(q);
        if (ip == iq) {
            // 如果根节点相同，说明是同一个集合，不需要合并
            return;
        }
        parents[iq] = ip;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return false;
    }

    /**
     * 寻找并查集的根节点
     * @param index 元素的索引
     * @return 根节点
     */
    private int find(int index) {
        if (index < 0 || index >= this.parents.length) {
            throw new IllegalArgumentException("index < 0 || index >= this.parents.length");
        }
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

}
