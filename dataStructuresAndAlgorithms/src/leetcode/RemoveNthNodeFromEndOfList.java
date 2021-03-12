package leetcode;

import org.junit.Assert;

import java.util.List;

public class RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next==null && n==1){
            head = null;
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }
        if (p2==null){
//            first element is to be removed
            ListNode secondNode = head.next;
            head.next = null;
            return secondNode;
        }
        while(p2.next !=null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return head;
    }
//    two passes -> slower
//    public static ListNode removeNthFromEnd(ListNode head, int n) {
//        // where list contains one element
//        if (head.next==null && n==1){
//            head = null;
//            return head;
//        }
//        int lenOfList = 0;
//        for (ListNode node=head; node!=null; node=node.next){
//            lenOfList++;
//        }
//        if (n==lenOfList){
//            ListNode secondNode = head.next;
//            head.next = null;
//            return secondNode;
//        }
//        ListNode last = null;
//        ListNode current = head;
//        for (int counter = 1; counter<=lenOfList-n-1;counter++){
//            current = current.next;
//        }
//        current.next = current.next.next;
//        return head;
//    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode current = l1;
//        int[] arr = {2};
        int[] arr = {2, 3, 4, 5};
        for (int i: arr){
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode result = removeNthFromEnd(l1, 5);
        System.out.println(result);
    }
}
