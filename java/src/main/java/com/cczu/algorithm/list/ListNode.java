package com.cczu.algorithm.list;

/**
 * @author jianzhen.yin
 * @date 2020/9/27
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode build(Integer[] arr) {
        ListNode root = new ListNode(), cur = root;
        for (int i = 0; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return root.next;
    }


    @Override
    public String toString() {
        ListNode tmp = this;
        if (detectCycle(tmp) != null) {
            return "【存在循环链表，当前节点值:" + tmp.val + "】";
        }
        StringBuilder sb = new StringBuilder();
        while (tmp != null) {
            sb.append(",").append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString().length() > 0 ? sb.substring(1) : "";
    }

    private ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next == null) {
                return null;
            } else {
                fast = fast.next.next;
            }
            if (slow == fast) {
                ListNode pre = head;
                while (pre != slow) {
                    pre = pre.next;
                    slow = slow.next;
                }
                return pre;
            }
        }
        return null;

    }
}
