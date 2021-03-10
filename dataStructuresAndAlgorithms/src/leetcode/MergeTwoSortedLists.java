package leetcode;

// https://leetcode.com/problems/merge-two-sorted-lists
public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if ((l1 == null) && (l2 == null))
            return null;
        ListNode result = new ListNode();
        ListNode looper = result;
        boolean proceed = true;
        ListNode l1Next = l1;
        ListNode l2Next = l2;
        while (proceed) {
            if ((l1Next == null) && (l2Next == null)) {
                proceed = false;
            } else if (l1Next == null) {
                looper.val = l2Next.val;
                l2Next = l2Next.next;
            } else if (l2Next == null) {
                looper.val = l1Next.val;
                l1Next = l1Next.next;
            } else {
                if (l1Next.val < l2Next.val) {
                    looper.val = l1Next.val;
                    l1Next = l1Next.next;
                } else {
                    looper.val = l2Next.val;
                    l2Next = l2Next.next;
                }
            }
            if ((l1Next != null) || (l2Next != null)) {
                looper.next = new ListNode();
                looper = looper.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode result = mergeTwoLists(l1, l2);
        System.out.println(result);
//        int[][] matrix = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        System.out.println(findCircleNum(matrix));
    }
}
