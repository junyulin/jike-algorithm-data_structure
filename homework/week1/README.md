# 极客时间第一周作业

极客时间第一周作业



## leetcode 61.加一

```java
public class Homeword1 {

    /**
     * 第一版本：依次遍历数组，然后加1，需要处理加1之后超过10的情况
     */
    public int[] plusOne(int[] digits) {
        int num = 1;
        for (int i = digits.length - 1; i >= 0; i --) {
            int val = digits[i];
            int j = val + num;
            if (j == 10) {
                digits[i] = 0;
            } else {
                digits[i] = j;
                num = 0;
            }
        }
        // 判断数组的第一位是否超过10，如果是需要处理
        if (num == 1) {
            int[] retArr = new int[digits.length + 1];
            retArr[0] = 1;
            for (int i = 0; i < digits.length; i ++) {
                retArr[i + 1] = digits[i];
            }
            digits = retArr;
        }
        return digits;
    }

    /**
     * 上面版本的优化。当最高位加1后超过10，后面的低位数只有一种情况，那就是全部是 0
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] arry = new int[digits.length + 1];
        arry[0] = 1;
        return arry;
    }

    public static void main(String[] args) {
        final Homeword1 homeword1 = new Homeword1();
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        final int[] ints = homeword1.plusOne(arr);
        System.out.println();
    }

}
```



## leetcode 21.合并两个有序链表

两种做法：递归和 while 循环。while 循环使用的是双指针方法。

```java
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
```

