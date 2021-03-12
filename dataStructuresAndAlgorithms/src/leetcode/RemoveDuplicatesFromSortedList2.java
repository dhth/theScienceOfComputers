package leetcode;

public class RemoveDuplicatesFromSortedList2 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode sentinel = new ListNode(-1, head);
        ListNode lastDistinct = sentinel;
        ListNode current = head;

        while (true) {
            if (current.next == null) {
                lastDistinct.next = current;
                break;
            }
            if (current.val != current.next.val) {
                lastDistinct.next = current;
                lastDistinct = current;
                current = current.next;
            } else {
                while (current.val == current.next.val) {
                    current = current.next;
                    if (current.next == null)
                        break;
                }
                if (current.next == null) {
                    lastDistinct.next = null;
                    break;
                } else {
                    current = current.next;
                }
            }
        }
        ListNode second = sentinel.next;
        sentinel.next = null;
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 4, 5, 6, 7, 7, 8};
        ListNode l1 = ListNode.createList(arr);
        ListNode result = deleteDuplicates(l1);
        System.out.println(result);
    }
}
