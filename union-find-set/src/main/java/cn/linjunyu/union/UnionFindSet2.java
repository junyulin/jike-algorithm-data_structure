package cn.linjunyu.union;

/**
 * 并查集最终版本，增加了路径压缩。递归版本。
 *
 * @author LinJn
 * @since 2021/12/18 21:09
 */
public final class UnionFindSet2 implements UnionFindSet {

    private final int[] parents;

    private final int[] rank;

    public UnionFindSet2(int size) {
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
        if (index == parents[index]) {
            return index;
        }
        // 让路径上的节点的父节点全部指向根节点
        int root = this.find(parents[index]);
        parents[index] = root;
        return root;
    }
}
