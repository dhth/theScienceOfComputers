package leetcode;

public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode sentinel = new ListNode(-1, head);
        ListNode last = sentinel;
        ListNode current = head;
        ListNode next, nextToNext;
        while (current != null) {
            next = current.next;
            if (next == null) {
                break;
            } else {
                nextToNext = current.next.next;
                last.next = next;
                next.next = current;
                current.next = nextToNext;
                last = current;
                current = nextToNext;
            }
        }
        ListNode second = sentinel.next;
        sentinel.next = null;
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4};
        ListNode l1 = ListNode.createList(arr);
        ListNode result = swapPairs(l1);
        System.out.println(result);
    }
}
