package leetcode;

public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode sentinel1 = new ListNode(-1);
        ListNode sentinel2 = new ListNode(-1);
        ListNode node = head;
        ListNode less = sentinel1;
        ListNode more = sentinel2;
        while (node != null) {
            if (node.val < x) {
                less.next = node;
                less = node;
            } else {
                more.next = node;
                more = node;
            }
            node = node.next;
        }
        more.next = null;
        less.next = sentinel2.next;
        sentinel2.next = null;
        ListNode second = sentinel1.next;
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        ListNode l1 = ListNode.createList(arr);
        ListNode result = partition(l1, 2);
        System.out.println(result);
    }
}
