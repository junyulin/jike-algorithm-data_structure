package cn.linjunyu.union;

/**
 * 基于 QuickUnion2 的优化，从 size 改为 rank
 *
 * @author LinJn
 * @since 2021/12/18 20:50
 */
public final class QuickUnion3 implements UnionFindSet {

    private final int[] parents;

    private final int[] rank;

    public QuickUnion3(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0");
        }
        this.parents = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < this.parents.length; i++) {
            this.parents[i] = i;
            this.rank[i] = 1;
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
            return;
        }
        // 根据 rank 来合并两个集合
        if (this.rank[ip] < this.rank[iq]) {
            parents[ip] = iq;
        } else if (this.rank[ip] > this.rank[iq]) {
            parents[iq] = ip;
        } else {
            // this.rank[ip] = this.rank[iq]
            parents[ip] = iq;
            // 当 rank 相等时，合并后的集合 rank 需要加 1
            this.rank[iq] = this.rank[iq] + 1;
        }
    }

    @Override
    public boolean isConnect(int p, int q) {
        return this.find(p) == this.find(q);
    }

    private int find(int index) {
        if (index < 0 || index >= this.parents.length) {
            throw new IllegalArgumentException("index < 0 || index >= this.arr.length");
        }
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }
}
