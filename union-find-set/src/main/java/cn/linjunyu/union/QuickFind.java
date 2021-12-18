package cn.linjunyu.union;

/**
 * 查询速度快，但是合并速度慢的并查集。查询是 O(1)，合并是 O(n)
 *
 * @author LinJn
 * @since 2021/12/18 12:47
 */
public final class QuickFind implements UnionFindSet {

    private final int[] arr;

    public QuickFind(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("the size < 0");
        }
        this.arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = i;
        }
    }

    @Override
    public int size() {
        return this.arr.length;
    }

    @Override
    public void union(int p, int q) {
        final int ip = this.find(p);
        final int iq = this.find(q);
        if (ip == iq) {
            return;
        }
        // 合并两个集合
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == ip) {
                this.arr[i] = iq;
            }
        }
    }

    @Override
    public boolean isConnect(int p, int q) {
        return this.find(p) == this.find(q);
    }

    private int find(int index) {
        if (index < 0 || index >= this.arr.length) {
            throw new IllegalArgumentException("index < 0 || index >= this.arr.length");
        }
        return this.arr[index];
    }
}
