package leetcode;

import org.junit.Assert;

public class IntersectionOfTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        if (headA == null || headB == null)
            return null;
        boolean proceed = true;
        while (proceed) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            if (nodeA.next == null && nodeB.next == null)
                return null;
            if (nodeA.next == null)
                nodeA = headB;
            else
                nodeA = nodeA.next;
            if (nodeB.next == null)
                nodeB = headA;
            else
                nodeB = nodeB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode a4 = new ListNode(4);
        ListNode a3 = new ListNode(2, a4);
        ListNode a2 = new ListNode(1, a3);
        ListNode a1 = new ListNode(9, a2);
        ListNode a0 = new ListNode(1, a1);
        ListNode b0 = new ListNode(3, a3);
        ListNode result1 = getIntersectionNode(a0, b0);
        Assert.assertEquals(2, result1.val);

        ListNode c0 = new ListNode(3);
        ListNode d0 = new ListNode(2, c0);
        ListNode result2 = getIntersectionNode(c0, d0);
        Assert.assertEquals(3, result2.val);

        ListNode e2 = new ListNode(4);
        ListNode e1 = new ListNode(6, e2);
        ListNode e0 = new ListNode(2, e1);
        ListNode f1 = new ListNode(5);
        ListNode f0 = new ListNode(1, f1);

        ListNode result3 = getIntersectionNode(e0, f0);
        Assert.assertNull(result3);
    }
}
