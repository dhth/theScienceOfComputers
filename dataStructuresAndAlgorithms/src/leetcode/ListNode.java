package leetcode;

import java.util.ArrayList;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public String toString(){
        ListNode looper = this;
        StringBuilder builder = new StringBuilder();
        while (looper != null) {
            builder.append(looper.val + " > ");
            looper = looper.next;
        }
        return builder.toString();
    }
    public static ListNode createList(int[] vals){
        ListNode list = new ListNode(1);
        ListNode current = list;
        for (int i: vals){
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode second = list.next;
        list.next = null;
        return second;
    }
}

