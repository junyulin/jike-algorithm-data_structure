package cn.linjunyu.array.datastructure;

import java.util.Iterator;

/**
 * 自定义一个动态数组
 *
 * @author LinJn
 * @since 2021/9/29 13:04
 */
public class ArrayList<T> implements Iterable<T> {

    private T[] array;

    private int size;

    public ArrayList() {
        // 默认容量为 10
        this(10);
    }

    public ArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 添加元素
     * @param index 要添加的索引位置
     * @param t 要添加的元素
     */
    public void add(int index, T t) {
        if (index > this.size) {
            throw new IllegalArgumentException("index error。index：" + index + "。size：" + this.size);
        }
        final int capacity = this.capacity();
        if (this.size + 1 == capacity) {
            // 如果容量不够，那么数组容量扩大为原来的两倍
            this.resize(capacity << 1);
        }

        for (int i = this.size - 1; i >= index; i --) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = t;

        // 维护 size
        this.size = this.size + 1;
    }

    /**
     * 添加元素到末尾
     * @param t 要添加的元素
     */
    public void add(T t) {
        this.add(this.size, t);
    }

    /**
     * 添加元素到头部
     * @param t 要添加的元素
     */
    public void addFirst(T t) {
        this.add(0, t);
    }

    public T remove(int index) {
        if (index > this.size) {
            throw new IllegalArgumentException("index error。index：" + index + "。size：" + this.size);
        }

        T t = this.get(index);
        // 通过移位删除元素
        for (int i = index; i < this.size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        // 维护 size
        this.size = this.size - 1;

        // 缩容。
        // 当数组大小只有数组容量 1/4 的时候，容量缩小为原来的一半
        final int capacity = capacity();
        if (this.size <= capacity / 4) {
            resize(capacity >> 1);
        }
        return t;
    }

    /**
     * 删除第一个元素
     */
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     * 删除最后一个元素
     */
    public T removeLast() {
        return this.remove(this.size - 1);
    }

    /**
     * 将索引为 index 的元素设置成指定的元素
     * @param index 要设置的索引
     * @param t 要设置的元素
     */
    public void set(int index, T t) {
        if (index >= this.size) {
            throw new IllegalArgumentException("index error。index：" + index + "。size：" + this.size);
        }
        this.array[index] = t;
    }

    /**
     * 通过索引获取元素
     * @param index 索引
     * @return 返回的元素
     */
    public T get(int index) {
        if (index >= this.size) {
            throw new IllegalArgumentException("index error。index：" + index + "。size：" + this.size);
        }
        return this.array[index];
    }

    /**
     * 重新调整数组的容量
     * @param capacity 新容量
     */
    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.array[i];
        }
        this.array = newArr;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.array.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[i]);
            if (i != this.size - 1) {
                sb.append(" ");
            }
        }
        sb.append("] ");
        sb.append("size：").append(this.size).append(" ");
        sb.append("capacity：").append(this.capacity());
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * 自定义迭代器
     */
    private class MyIterator implements Iterator<T> {

        private int curIndex;

        MyIterator() {
            this.curIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return this.curIndex < size;
        }

        @Override
        public T next() {
            return array[curIndex ++];
        }
    }
}
