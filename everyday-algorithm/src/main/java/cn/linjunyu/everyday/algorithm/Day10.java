package cn.linjunyu.everyday.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 *
 * 思路：
 * 1、新添加的元素放到前面，如果空间满了，需要删除最后一个元素
 * 2、使用到的元素需要放到前面，也就是需要先删除再在前面添加
 * 3、使用一个 hash 表，使得通过 key 可以马上找到 node
 *
 * @author LinJn
 * @since 2021/10/13 16:16
 */
public class Day10 {

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node() {}
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;

    private Node dummyHeadNode;

    private Node dummyTailNode;

    private int size;

    Map<Integer, Node> nodeMap = new HashMap<>();

    public Day10(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.dummyHeadNode = new Node();
        this.dummyTailNode = new Node();
        this.dummyHeadNode.next = this.dummyTailNode;
        this.dummyTailNode.prev = this.dummyTailNode;
    }

    public int get(int key) {
        // 从 hash 表中获取 node
        final Node node = nodeMap.get(key);
        // 没有，返回 -1
        if (node == null) {
            return -1;
        }
        // 有，需要调整位置，移动到链表的最前面。先删除节点，再在最前面添加
        this.remove(node);
        this.addFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        // 先判断是否存在此节点
        final Node node = nodeMap.get(key);
        if (node == null) {
            // 不存在，创建新节点并放到链表的最前面
            final Node currNode = new Node(key, value);
            addFirst(currNode);
            if (this.size > capacity) {
                // 如果内存满了（容量满了），需要删除最后一个元素
                removeLast();
            }
            // 把新的节点添加到 hash 表中
            nodeMap.put(key, currNode);
        } else {
            // 如果存在，更改 value，并调整位置
            node.val = value;
            remove(node);
            addFirst(node);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size = this.size - 1;
    }

    private void removeLast() {
        final Node removeNode = this.dummyTailNode.prev;
        remove(removeNode);
        nodeMap.remove(removeNode.key);
    }

    private void addFirst(Node node) {
        this.dummyHeadNode.next.prev = node;
        node.next = this.dummyHeadNode.next;
        this.dummyHeadNode.next = node;
        node.prev = this.dummyHeadNode;
        this.size = this.size + 1;
    }

    public static void main(String[] args) {
        // [[2],[1,1],[2,2],[1],[2, 5],[3,3],[2],[4,4],[1],[3],[4]]
        final Day10 day10 = new Day10(2);
        day10.put(1, 1);
        day10.put(2, 2);
        final int i = day10.get(1);
        System.out.println(i);
        day10.put(3, 3);
        final int i1 = day10.get(2);
        System.out.println(i1);
    }
}
