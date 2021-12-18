package cn.linjunyu.union;

/**
 * 并查集最终版本，增加了路径压缩。非递归版本
 *
 * @author LinJn
 * @since 2021/12/18 21:00
 */
public final class UnionFindSet1 implements UnionFindSet {

    private final int[] parents;

    private final int[] rank;

    public UnionFindSet1(int size) {
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
        final int pRoot = this.find(p);
        final int qRoot = this.find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (this.rank[pRoot] < this.rank[qRoot]) {
            this.parents[pRoot] = qRoot;
        } else if (this.rank[pRoot] > this.rank[qRoot]) {
            this.parents[qRoot] = pRoot;
        } else {
            this.parents[pRoot] = qRoot;
            this.rank[qRoot] = this.rank[qRoot] + 1;
        }
    }

    @Override
    public boolean isConnect(int p, int q) {
        return this.find(p) == this.find(q);
    }

    private int find(int index) {
        if (index < 0 || index >= this.parents.length) {
            throw new IllegalArgumentException("index < 0 || index >= this.parents.length");
        }
        while (index != parents[index]) {
            // 路径压缩，让 index 的父节点等于它的父父节点，也就是爷爷节点
            parents[index] = parents[parents[index]];
            index = parents[index];
        }
        return index;
    }
}
