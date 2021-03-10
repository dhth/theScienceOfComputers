package leetcode;

public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
//        ListNode last = new ListNode(head.val);
//        for (ListNode node=head.next; node!=null; node=node.next){
//            last = new ListNode(node.val, last);
//        }
        ListNode last = null;
        ListNode node = head;
        ListNode next = head;
        while (next != null) {
            next = node.next;
            node.next = last;
            last = node;
            node = next;
        }
        return last;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode current = l1;
        int[] arr = {2, 3, 4, 5, 6};
        for (int i : arr) {
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode result = reverseList(l1);
        ListNode looper = result;
        while (looper != null) {
            System.out.println(looper.val);
            looper = looper.next;
        }
    }
}
