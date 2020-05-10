package CS61BProj1a;

import CS61BProj1b.Deque;

public class LinkedListDeque<T> implements Deque<T> {
    private final LinkedListNode sentinelNode;
    private int size;

    public LinkedListDeque() {
        this.sentinelNode = new LinkedListNode<>(10, null, null);
        this.sentinelNode.setNext(this.sentinelNode);
        this.sentinelNode.setPrev(this.sentinelNode);
    }

    public LinkedListDeque(LinkedListDeque other) {
        this.sentinelNode = new LinkedListNode(10, null, null);
        LinkedListNode<T> currentNode = this.sentinelNode;

        for (int i = 0; i < other.size(); i++) {
            LinkedListNode<T> newNode = new LinkedListNode(other.get(i),
                    null, currentNode);
            currentNode.setNext(newNode);
            currentNode = newNode;
            this.size = this.size + 1;
        }
        this.sentinelNode.setPrev(currentNode);
        currentNode.setNext(this.sentinelNode);
    }

    public static void main(String[] args) {

        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        l.addFirst(5);
        l.addFirst(2);
        l.addLast(6);
        l.addFirst(1);
        l.addLast(8);
        l.insert(3, 3);
        l.removeFirst();
        l.removeLast();
        System.out.println(l);
    }

    @Override
    public void addFirst(T val) {
        if (this.size == 0) {
//            List is empty. Add first element
            this.sentinelNode.setNext(new LinkedListNode(val,
                    this.sentinelNode, this.sentinelNode));
            this.sentinelNode.setPrev(this.sentinelNode.next);
        } else {
//            List already contains elements. Change references.
            LinkedListNode newNode = new LinkedListNode(val,
                    this.sentinelNode.next, this.sentinelNode);
            this.sentinelNode.getNext().setPrev(newNode);
            this.sentinelNode.setNext(newNode);
        }
        this.size = this.size + 1;
    }

    @Override
    public void addLast(T val) {
        if (this.size == 0) {
//            if list is empty, just call addFirst
            this.addFirst(val);
        } else {
//            list contains elements, change references
            LinkedListNode<T> newNode = new LinkedListNode<>(val,
                    this.sentinelNode, this.sentinelNode.prev);
            this.sentinelNode.getPrev().setNext(newNode);
            this.sentinelNode.setPrev(newNode);
            this.size = this.size + 1;
        }
    }

    public void insert(T val, int position) {
        /* From Discussion 3:
        https://sp19.datastructur.es/materials/discussion/disc03.pdf
        Copied for DLL.
        "Implement SLList.insert which takes in an integer x
        and an integer position. It inserts x at the given position.
        If position is after the end of the list, insert the new node
        at the end. For example, if the SLList is 5 → 6 → 2,
        insert(10, 1) results in 5 → 10 → 6 → 2 and if the
        SLList is 5 → 6 → 2, insert(10, 7) results in 5 → 6 → 2 → 10.
        Additionally, for this problem assume that position is a
        non-negative integer."
        * */
        if (position >= this.size) {
            // just add to the end
            this.addLast(val);
        } else if (position == 0) {
            // just add to the start
            this.addFirst(val);
        } else {
            // add somewhere in the middle of the list
            int currentInd = 0;
            LinkedListNode<T> currentNode = this.sentinelNode.getNext();
            while (currentInd < position - 1) {
                currentNode = currentNode.getNext();
                currentInd = currentInd + 1;
            }
            LinkedListNode<T> newNode = new LinkedListNode<>(val,
                    currentNode.next, currentNode);
            currentNode.getNext().setPrev(newNode);
            currentNode.setNext(newNode);
            this.size = this.size + 1;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        if (this.size != 0) {
            LinkedListNode<T> currentNode = this.sentinelNode.getNext();
            while (currentNode != this.sentinelNode) {
                System.out.print(currentNode.getValue() + " ");
                currentNode = currentNode.getNext();
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        T removedItem = null;
        if (this.size > 0) {
            removedItem = (T) this.sentinelNode.getNext().getValue();
            this.sentinelNode.setNext(this.sentinelNode.getNext().getNext());
            this.sentinelNode.getNext().setPrev(this.sentinelNode);
            this.size = this.size - 1;
        }
        return removedItem;
    }

    @Override
    public T removeLast() {
        T removedItem = null;
        if (this.size > 0) {
            removedItem = (T) this.sentinelNode.getPrev().getValue();
            this.sentinelNode.setPrev(this.sentinelNode.getPrev().getPrev());
            this.sentinelNode.getPrev().setNext(this.sentinelNode);
            this.size = this.size - 1;
        }
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index > this.size - 1) {
            return null;
        } else {
            int currentInd = 0;
            LinkedListNode<T> currentNode = this.sentinelNode.getNext();
            while (currentInd < index) {
                currentNode = currentNode.getNext();
                currentInd = currentInd + 1;
            }
            return currentNode.getValue();
        }
    }

    @Override
    public String toString() {
        StringBuilder returnStringSB = new StringBuilder("{ ");
        for (int i = 0; i < this.size - 1; i++) {
            returnStringSB.append(this.get(i));
            returnStringSB.append(", ");
        }
        returnStringSB.append(this.get(this.size - 1));
        returnStringSB.append(" }");
        return returnStringSB.toString();
    }

    /* Linked List Deque
    Complexities:
    - addFirst, addLast -> O(1)
    - removeFirst, removeLast -> O(1) (except the case where resizing is necessary)
    - get -> O(n)
    - size -> O(1)
    * */
    private class LinkedListNode<T> {
        private T value;
        private LinkedListNode<T> next;
        private LinkedListNode<T> prev;

        private LinkedListNode(T val, LinkedListNode nxt, LinkedListNode prev) {
            this.value = val;
            this.next = nxt;
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public LinkedListNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedListNode<T> next) {
            this.next = next;
        }

        public LinkedListNode<T> getPrev() {
            return prev;
        }

        public void setPrev(LinkedListNode<T> prev) {
            this.prev = prev;
        }
    }
}
