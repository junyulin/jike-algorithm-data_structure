package cn.linjunyu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 最小堆
 *
 * 索引 1 作为起点
 *
 * @author LinJn
 * @since 2021/11/3 17:41
 */
public class MinHeap<T> {

    private int size;

    private List<T> data;

    private Comparator<T> comparator;

    public MinHeap(Comparator<T> comparator) {
        this.data = new ArrayList<>();
        // 占用第一个元素
        this.data.add(null);
        this.comparator = comparator;
        this.size = 0;
    }

    public void add(T t) {
        // 添加到最后一个元素
        this.data.add(t);
        // 上浮
        this.siftUp(this.size + 1);
        this.size = this.size + 1;
    }

    public T remove() {
        final T peek = this.peek();
        // 将第一个元素和最后一个元素交换
        this.swap(1, this.size);
        // 删除掉最后一个元素
        this.data.remove(this.size);
        // 下沉
        this.siftDown(1);
        this.size = this.size - 1;
        return peek;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        return this.data.get(1);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private int getParentIndex(int index) {
        return index / 2;
    }

    private void siftUp(int index) {
        while (index > 1) {
            final int pIndex = getParentIndex(index);
            final int compare = comparator.compare(this.data.get(index), this.data.get(pIndex));
            if (compare < 0) {
                this.swap(index, pIndex);
            } else {
                break;
            }
            index = pIndex;
        }
    }

    private void siftDown(int index) {
        while (index < this.size) {
            // 求左子树的索引
            int leftIndex = this.getLeftIndex(index);
            // 求右子树的索引
            int rightIndex = this.getRightIndex(index);
            // 左子树不存在，结束循环
            if (leftIndex >= this.size) {
                break;
            }
            // 右子树存在
            if (rightIndex < this.size) {
                // 找到左右子树中最小的那个
                final int compare = this.comparator.compare(this.data.get(leftIndex), this.data.get(rightIndex));
                if (compare > 0) {
                    leftIndex = rightIndex;
                }
            }
            // 和左右子树最小的值的那个进行比较，如果大于左右子树中的最小值，那么与其交换
            final int compare = this.comparator.compare(this.data.get(index), this.data.get(leftIndex));
            if (compare > 0) {
                this.swap(index, leftIndex);
                index = leftIndex;
            } else {
                break;
            }
        }
    }

    private int getLeftIndex(int index) {
        return 2 * index;
    }

    private int getRightIndex(int index) {
        return 2 * index + 1;
    }

    private void swap(int index, int index2) {
        final T t = this.data.get(index);
        final T t1 = this.data.get(index2);
        this.data.set(index, t1);
        this.data.set(index2, t);
    }

    public static void main(String[] args) {
        final MinHeap<Integer> minHeap = new MinHeap<>((n1, n2) -> n1 - n2);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(5);
        minHeap.add(2);
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
        System.out.println(minHeap.remove());
    }
}
