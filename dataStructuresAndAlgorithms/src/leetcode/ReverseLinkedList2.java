package leetcode;

public class ReverseLinkedList2 {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode sentinel = new ListNode(-1, head);
        ListNode node = sentinel;
        int counter = 1;
        while (true) {
            if (counter == left) {
                node.next = reverseList(node.next, counter, right);
                break;
            }
            node = node.next;
            counter++;
        }
        ListNode second = sentinel.next;
        return second;
    }

    private static ListNode reverseList(ListNode node, int counter, int right) {
        //assumes right is at least distance 1 from node
        ListNode last = new ListNode(-1);
        ListNode furthest = node;
        ListNode nextNode;
        while (true) {
            if (counter == right) {
                nextNode = node.next;
                node.next = last;
                last = node;
                node = nextNode;
                furthest.next = node;
                break;
            } else {
                nextNode = node.next;
                node.next = last;
                last = node;
                node = nextNode;
                counter++;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5};
        ListNode l1 = ListNode.createList(arr);
        ListNode result = reverseBetween(l1, 1, 2);
        System.out.println(result);
    }
}
