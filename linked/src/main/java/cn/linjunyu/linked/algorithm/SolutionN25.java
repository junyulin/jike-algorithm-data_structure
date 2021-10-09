package cn.linjunyu.linked.algorithm;

/**
 * Leetcode 25. K 个一组翻转链表
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author LinJn
 * @since 2021/10/1 17:43
 */
public class SolutionN25 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        static ListNode create(int[] arr) {
            ListNode node = new ListNode();
            ListNode curNode = node;
            for (int i = 1; i <= arr.length; i++) {
                curNode.next = new ListNode(i);
                curNode = curNode.next;
            }
            return node.next;
        }
    }

    /**
     * 1、先分组
     * 2、把分组内的链表反转
     * 3、维护一个新的链表，用来存放翻转后的链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ret = new ListNode();
        ListNode last = ret;
        while (head != null) {
            // 1、分组操作
            // 1.1 获取组内的最后一个节点，有了 head 和 end，分组完成
            ListNode end = this.getEndNode(head, k);
            if (end == null) {
                // 如果是 null，说明不需要反转了，直接添加到新链表中即可
                last.next = head;
                break;
            }
            // 保存下一组的开头
            ListNode nextGroupNode = end.next;
            // end.next 置为 null，到此分组完毕：head -> ... -> end -> null
            end.next = null;
            // 2、把这一组进行反转
            ListNode reverseNode = reverseList(head);
            // 3、将反转后的链表放到新的链表中去
            last.next = reverseNode;
            // 维护新链表的 last，方便下一个链表添加
            last = head;
            // 设置下一组的头部
            head = nextGroupNode;
        }
        return ret.next;
    }

    private ListNode getEndNode(ListNode head, int k) {
        while (head != null) {
            k = k - 1;
            if (k == 0) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

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

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        final ListNode node = ListNode.create(arr);
        final SolutionN25 solutionN25 = new SolutionN25();
        final ListNode node1 = solutionN25.reverseKGroup(node, 2);
        System.out.println();
    }
}
