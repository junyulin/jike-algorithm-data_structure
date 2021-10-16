package cn.linjunyu.linked.datastructure;

/**
 * 写 一个双向链表
 *
 * @author LinJn
 * @since 2021/10/3 11:22
 */
public class DoubleLinkedList<T> {

    private class Node {

        T val;

        Node next;

        Node prev;

        Node() {

        }

        Node(T val) {
            this.val = val;
        }

        Node(T val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

    }

    private final Node dummyHeadNode;

    private final Node dummyTailNode;

    private int size;

    public DoubleLinkedList() {
        this.dummyHeadNode = new Node();
        this.dummyTailNode = new Node();
        this.dummyHeadNode.next = this.dummyTailNode;
        this.dummyTailNode.prev = this.dummyHeadNode;
    }

    private Node getNode(int i) {
        Node node = dummyHeadNode;
        while (i >= 0) {
            node = node.next;
            i = i - 1;
        }
        return node;
    }

    public T get(int i) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        return this.getNode(i).val;
    }

    public void add(int i, T t) {
        if (i > this.size) {
            throw new IllegalArgumentException();
        }
        final Node currNode = new Node(t);
        final Node node = this.getNode(i);
        node.prev.next = currNode;
        currNode.prev = node.prev;
        currNode.next = node;
        node.prev = currNode;
        this.size = this.size + 1;
    }

    public void addLast(T t) {
        /*final Node currNode = new Node(t);
        this.dummyTailNode.prev.next = currNode;
        currNode.prev = this.dummyTailNode.prev;
        currNode.next = this.dummyTailNode;
        this.dummyTailNode.prev = currNode;*/
        this.add(this.size, t);
    }

    public void addFirst(T t) {
        this.add(0, t);
    }

    public void remove(int i) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        final Node node = this.getNode(i);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size = this.size - 1;
    }

    public void update(int i, T t) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        final Node node = this.getNode(i);
        node.val = t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = dummyHeadNode;
        sb.append("size：").append(this.size).append("\n");
        while (node != null) {
            sb.append(node.val).append("->");
            node = node.next;
        }
        sb.append("\n");
        Node node2 = dummyTailNode;
        while (node2 != null) {
            sb.append(node2.val).append("->");
            node2 = node2.prev;
        }
        return sb.toString();
    }
}
