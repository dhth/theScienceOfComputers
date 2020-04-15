package linkedLists;

public class LinkedListDeque<T> {
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

    private LinkedListNode sentinelNode;
    private int size;

    public LinkedListDeque() {
        this.sentinelNode = new LinkedListNode<>(10, null, null);
        this.sentinelNode.setNext(this.sentinelNode);
        this.sentinelNode.setPrev(this.sentinelNode);
    }

    public LinkedListDeque(LinkedListDeque other){
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
        }
        this.size = this.size + 1;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (this.size != 0) {
            LinkedListNode<T> currentNode = this.sentinelNode.getNext();
            while (currentNode != this.sentinelNode) {
                System.out.print(currentNode.getValue() + " ");
                currentNode = currentNode.getNext();
            }
        }
        System.out.println("");
    }

    public T removeFirst(){
        if (this.size > 0){
            this.sentinelNode.setNext(this.sentinelNode.getNext().getNext());
            this.sentinelNode.getNext().setPrev(this.sentinelNode);
            this.size = this.size - 1;
        }
        return null;
    }

    public T removeLast(){
        if (this.size > 0){
            this.sentinelNode.setPrev(this.sentinelNode.getPrev().getPrev());
            this.sentinelNode.getPrev().setNext(this.sentinelNode);
            this.size = this.size - 1;
        }
        return null;
    }

    public T get(int index){
        if (index > this.size - 1){
            return null;
        }
        else{
            int currentInd = 0;
            LinkedListNode<T> currentNode = this.sentinelNode.getNext();
            while (currentInd < index){
                currentNode = currentNode.getNext();
                currentInd = currentInd + 1;
            }
            return currentNode.getValue();
        }
    }


    public String toString() {
        return String.format("LL -> size -> %d", this.size);
    }

    public static void main(String[] args) {

        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        l.addFirst(5);
        l.addFirst(2);
        l.addLast(6);
        l.addFirst(1);
        l.addLast(8);
        l.removeFirst();
        l.removeLast();

    }
}