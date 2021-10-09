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

    private final Node dummyNode;

    private int size;

    public DoubleLinkedList() {
        this.dummyNode = new Node();
    }

    private Node getNode(int i) {
        if (i >= this.size) {
            throw new IllegalArgumentException();
        }
        Node node = dummyNode.next;
        while (i > 0) {
            node = node.next;
            i = i - 1;
        }
        return node;
    }

    public T get(int i) {
        return this.getNode(i).val;
    }

    public void add(int i, T t) {
        if (i > this.size) {
            throw new IllegalArgumentException();
        }
        Node node = dummyNode.next;
        while (i > 0) {
            node = node.next;
            i = i - 1;
        }
        final Node newNode = new Node(t);
        if (node == null) {
            newNode.prev = dummyNode;
            newNode.next = null;
            dummyNode.next = newNode;
            return;
        }
        newNode.next = node.next;
        node.next = newNode;

        newNode.prev = node;
        /*node.prev.next = newNode;
        newNode.prev = node.prev;

        newNode.next = node;
        node.next.prev = newNode;*/
        this.size = this.size + 1;
    }

    public void addLast(T t) {
        this.add(this.size, t);
    }

    public void addFirst(T t) {
        this.add(0, t);
    }

    public void remove(int i) {
        final Node node = this.getNode(i);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size = this.size - 1;
    }

    private void update(int i, T t) {
        final Node node = this.getNode(i);
        node.val = t;
    }
 }
