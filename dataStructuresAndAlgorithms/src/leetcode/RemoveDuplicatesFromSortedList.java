package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode result = new ListNode(head.val);
        ListNode looper = result;
        ListNode current = head.next;
        ListNode last = head;
        boolean proceed = true;
        while (proceed){
            if (current == null){
                proceed = false;
            }
            else {
                if (current.val != last.val){
                    looper.next = new ListNode(current.val);
                    looper = looper.next;
                    last = current;
                }
            }
            if (current != null)
                current = current.next;
        }
        return result;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode current = l1;
        int[] arr = {1, 2, 3, 3};
        for (int i: arr){
            current.next = new ListNode(i);
            current = current.next;
        }
        System.out.println("hello");
        ListNode result = deleteDuplicates(l1);
        System.out.println(result);
    }
}
