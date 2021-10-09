package cn.linjunyu.linked.datastructure;


/**
 * 实现一个链表。带有虚拟头结点
 *
 * @author LinJn
 * @since 2021/10/3 0:51
 */
public class LinkedList<T> {

    private class Node {

        T val;

        Node next;

        public Node(T t) {
            this.val = t;
        }

        public Node(T t, Node next) {
            this.val = t;
            this.next = next;
        }

    }

    private final Node dummyHead;

    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    public void add(int i, T t) {
        if (i > this.size) {
            throw new IllegalArgumentException();
        }
        Node prev = dummyHead;
        while (i > 0) {
            prev = prev.next;
            i = i - 1;
        }
        prev.next = new Node(t, prev.next);
        this.size = this.size + 1;
    }

    public void addLast(T t) {
        this.add(this.size, t);
    }

    public void addFirst(T t) {
        this.add(0, t);
    }

    public void remove(int i) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        Node prev = dummyHead;
        while (i > 0) {
            prev = prev.next;
            i = i - 1;
        }
        prev.next = prev.next.next;
        this.size = this.size - 1;
    }

    public void update(int i, T t) {
        final Node node = this.getNode(i);
        node.val = t;
    }

    public T get(int i) {
        return this.getNode(i).val;
    }

    private Node getNode(int i) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        Node cur = dummyHead.next;
        while (i > 0) {
            cur = cur.next;
            i = i - 1;
        }
        return cur;
    }

    @Override
    public String toString() {
        Node cur = dummyHead.next;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val);
            sb.append("->");
            cur = cur.next;
        }
        sb.append("null");
        sb.append(" size：").append(this.size);
        return sb.toString();
    }
}
