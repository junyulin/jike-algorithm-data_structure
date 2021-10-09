package cn.linjunyu.everyday.algorithm;

/**
 * 206. 反转链表
 *
 * @author LinJn
 * @since 2021/10/2 23:22
 */
public class Day2 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        ListNode(int[] arr) {
            ListNode listNode = new ListNode();
            ListNode tail = listNode;
            for (int i : arr) {
                ListNode node = new ListNode(i);
                tail.next = node;
                tail = node;
            }
            this.val = listNode.next.val;
            this.next = listNode.next.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode node = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = node;
            // 注意赋值的顺序
            node = head;
            head = next;
        }
        return node;
    }

    public static void main(String[] args) {
        final int[] arr = {1, 3, 4, 5};
        final ListNode node = new ListNode(arr);
        final Day2 day2 = new Day2();
        final ListNode node1 = day2.reverseList2(node);
        System.out.println();
    }
}
