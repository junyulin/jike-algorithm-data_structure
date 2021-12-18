package cn.linjunyu.union;

/**
 * 对 QuickUnion 的优化，基于集合 size 的优化。
 * 合并集合的时候，size 小的集合向 size 多的集合合并。
 *
 * @author LinJn
 * @since 2021/12/18 15:17
 */
public final class QuickUnion2 implements UnionFindSet {

    private final int[] parents;

    private final int[] sizes;

    public QuickUnion2(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0");
        }
        this.parents = new int[size];
        this.sizes = new int[size];
        for (int i = 0; i < this.parents.length; i++) {
            this.parents[i] = i;
            this.sizes[i] = 1;
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
        // 优化的地方
        if (this.sizes[ip] < this.sizes[iq]) {
            this.parents[ip] = iq;
            this.sizes[iq] = this.sizes[iq] + this.sizes[ip];
        } else {
            this.parents[iq] = ip;
            this.sizes[ip] = this.sizes[ip] + this.sizes[iq];
        }
    }

    @Override
    public boolean isConnect(int p, int q) {
        return false;
    }

    private int find(int index) {
        if (index < 0 || index >= this.parents.length) {
            throw new IllegalArgumentException("index < 0 || index >= this.parents.length");
        }
        while (index != this.parents[index]) {
            index = this.parents[index];
        }
        return index;
    }
}
