package leetcode;

public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        ListNode meetingPoint = null;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                meetingPoint = slow;
                break;
            }
        }
        if (!hasCycle)
            return null;

        ListNode node = head;
        while (true) {
            if (node == meetingPoint)
                return node;
            else {
                node = node.next;
                meetingPoint = meetingPoint.next;
            }
        }
    }
}
