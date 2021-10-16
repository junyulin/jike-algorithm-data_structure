package cn.linjunyu.homeword.week1;

/**
 * 21. 合并两个有序链表
 *
 * @author LinJn
 * @since 2021/10/9 17:26
 */
public class Homeword2 {

    static public class ListNode {

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

    /**
     * 非递归解法
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建一个新的链表
        ListNode node = new ListNode();
        // 保存尾结点，方便插入
        ListNode tail = node;
        // 双指针移动
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }
        // 把还没有插入节点插入到新的链表中去
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return node.next;
    }

    /**
     * 递归解法
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            // 调用 mergeTwoLists2 把已经合并好的链表返回，然后接到 l1
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoLists2(l1, l2.next);
        return l2;
    }

}
