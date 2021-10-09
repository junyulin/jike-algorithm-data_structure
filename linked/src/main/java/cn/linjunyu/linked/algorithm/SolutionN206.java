package cn.linjunyu.linked.algorithm;

/**
 *
 * Leetcode 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @author LinJn
 * @since 2021/10/1 15:13
 */
public class SolutionN206 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 使用遍历的方式
     */
    public ListNode reverseList(ListNode head) {
        ListNode ret = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = ret;
            ret = head;
            head = next;
        }
        return ret;
    }

    /**
     * 递归的方式
     */
    public ListNode reverseList2(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 让 head.next 反转，返回来的 node 是反转后链表
        ListNode node = reverseList2(head.next);
        // 将 head 加入到反转后的链表中去，这里的 head.next 是为了获取 node 的最后一个元素
        head.next.next = head;
        // head.next 置空，避免成环
        head.next = null;
        return node;
    }
}
