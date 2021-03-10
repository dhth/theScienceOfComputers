package leetcode;

public class RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode last = dummyNode;
        ListNode current = head;
        while (current != null){
            if (current.val == val){
                last.next = current.next;
            }
            else{
                last = current;
            }
            current = current.next;
        }
//        remove dummy node
        return dummyNode.next;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode current = l1;
        int[] arr = {2, 6, 3, 4,5 ,6};
        int val = 6;
//        int[] arr = {1, 2, 6, 3, 4,5 ,6};
//        int[] arr = {7,7,7};
//        int val = 7;
        for (int i: arr){
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode result = removeElements(l1, val);
        ListNode looper = result;
        while (looper != null){
            System.out.println(looper.val);
            looper = looper.next;
        }
    }
}
