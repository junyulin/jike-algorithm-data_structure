package cn.linjunyu.everyday.algorithm;

/**
 * 25. K 个一组翻转链表
 *
 * @author LinJn
 * @since 2021/10/9 17:02
 */
public class Day6 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        static ListNode build(int[] arr) {
            ListNode retNode = new ListNode();
            ListNode tail = retNode;
            for (int i : arr) {
                final ListNode listNode = new ListNode(i);
                tail.next = listNode;
                tail = listNode;
            }
            return retNode.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode retNode = new ListNode();
        ListNode tail = retNode;
        while (head != null) {
            final ListNode endNode = this.getEndNode(head, k);
            if (endNode == null) {
                tail.next = head;
                break;
            }
            final ListNode next = endNode.next;
            endNode.next = null;
            final ListNode reverse = this.reverse(head);
            tail.next = reverse;
            tail = head;
            head = next;
        }

        return retNode.next;
    }

    private ListNode getEndNode(ListNode node, int k) {
        while (node != null) {
            if (k == 1) {
                return node;
            }
            k = k - 1;
            node = node.next;
        }
        return null;
    }

    private ListNode reverse(ListNode node) {
        ListNode ret = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = ret;
            ret = node;
            node = next;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int k = 2;
        final Day6 day6 = new Day6();
        final ListNode node = ListNode.build(arr);
        final ListNode node1 = day6.reverseKGroup(node, k);
        System.out.println();
    }
}
