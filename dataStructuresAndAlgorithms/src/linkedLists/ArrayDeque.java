package linkedLists;

public class ArrayDeque<T> {
    /* Array Deque
    Complexities:
    - addFirst, addLast -> O(1) (except the case where resizing is necessary)
    - removeFirst, removeLast -> O(1) (except the case where resizing is necessary)
    - get -> O(1)
    - size -> O(1)
    Memory usage factor: at least 25% (resizing occurs if less than 25)
    * */
    private T[] items;
    private int size;
    private final int INIT_LENGTH = 8;
    private final int MULTIPLYING_FACTOR = 2;
    private int nextFirst;
    private int nextLast;
    private int currentCapacity;

    /*public getter setters for
     internal properties, just to be able to test*/
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNextFirst() {
        return nextFirst;
    }

    public void setNextFirst(int nextFirst) {
        this.nextFirst = nextFirst;
    }

    public int getNextLast() {
        return nextLast;
    }

    public void setNextLast(int nextLast) {
        this.nextLast = nextLast;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void setItem(int index, T val) {
        this.items[index] = val;
    }

    public T getItemFromInternalArray(int index) {
        return this.items[index];
    }

    public ArrayDeque() {
        this.items = (T[]) new Object[INIT_LENGTH];
        this.nextFirst = INIT_LENGTH - 1;
        this.nextLast = 0;
        this.size = 0;
        this.currentCapacity = INIT_LENGTH;
    }

    public ArrayDeque(ArrayDeque other){
        this.items = (T[]) new Object[INIT_LENGTH];
        this.nextFirst = INIT_LENGTH - 1;
        this.nextLast = 0;
        this.size = 0;
        this.currentCapacity = INIT_LENGTH;

        for (int i = 0; i < other.size(); i++) {
            this.addLast((T)other.get(i));
        }
    }

    public void resizeUp(int capacity) {
        /*Helper function*/
        T[] newList = (T[]) new Object[capacity];

        System.arraycopy(this.items,
                this.nextFirst + 1,
                newList,
                0,
                this.currentCapacity - this.nextFirst - 1);
        System.arraycopy(this.items,
                0,
                newList,
                this.currentCapacity - this.nextFirst - 1,
                this.nextFirst + 1);

        this.items = newList;
        this.currentCapacity = capacity;
        this.nextFirst = capacity - 1;
        this.nextLast = this.size;
    }

    public void resizeDown(int capacity) {
        T[] newList = (T[]) new Object[capacity];
        /*When resizing down, two scenarios can occur
         * either nextFirst is ahead of nextLast,
         * or the opposite.
         * In the former case, items have rolled back to the
         * start of the array, we start at nextFirst and
         * copy all elements till the end of the array,
         * then we start at 0 and copy till endLast
         *
         * In the latter case, items have not rolled.
         * So, we just start at nextFirst, and end at nextLast
         * */
        if (this.nextFirst > this.nextLast) {
            System.arraycopy(this.items,
                    this.nextFirst + 1,
                    newList,
                    0,
                    this.currentCapacity - this.nextFirst - 1);
            System.arraycopy(this.items,
                    0,
                    newList,
                    this.currentCapacity - this.nextFirst - 1,
                    this.nextLast);
        } else {
            System.arraycopy(this.items,
                    this.nextFirst + 1,
                    newList,
                    0,
                    this.nextLast - this.nextFirst - 1);
        }

        this.items = newList;
        this.currentCapacity = capacity;
        this.nextFirst = capacity - 1;
        this.nextLast = this.size;
    }

    public void addFirst(T item) {
        /*
        O(1) except in the case of resizing
        * */
        if (this.size == this.items.length) {
            this.resizeUp(this.items.length * MULTIPLYING_FACTOR);
        }

        this.items[this.nextFirst] = item;

        if (this.nextFirst == 0) {
            this.nextFirst = this.items.length - 1;
        } else {
            this.nextFirst = this.nextFirst - 1;
        }
        this.size = this.size + 1;
    }

    public void addLast(T item) {
        if (this.size == this.items.length) {
            this.resizeUp(this.items.length * MULTIPLYING_FACTOR);
        }
        this.items[this.nextLast] = item;

        if (this.nextLast == this.items.length - 1) {
            this.nextLast = 0;
        } else {
            this.nextLast = this.nextLast + 1;
        }
        this.size = this.size + 1;
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        int indexToRemove;
        if (this.nextFirst == this.currentCapacity - 1) {
            indexToRemove = 0;
        } else {
            indexToRemove = this.nextFirst + 1;
        }
        T removedItem = this.items[indexToRemove];
        this.size = this.size - 1;
        this.nextFirst = indexToRemove;
        this.items[indexToRemove] = null;

        while ((this.usageFactor() < 0.25) && this.currentCapacity >INIT_LENGTH) {
            this.resizeDown(this.items.length / MULTIPLYING_FACTOR);
        }

        return removedItem;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }

        int indexToRemove;
        if (this.nextLast == 0) {
            indexToRemove = this.currentCapacity - 1;
        } else {
            indexToRemove = this.nextLast - 1;
        }
        T removedItem = this.items[indexToRemove];
        this.size = this.size - 1;
        this.nextLast = indexToRemove;
        this.items[indexToRemove] = null;

        while ((this.usageFactor() < 0.25) && this.currentCapacity >INIT_LENGTH) {
            this.resizeDown(this.items.length / MULTIPLYING_FACTOR);
        }
        return removedItem;
    }

    public int size() {
        /*
        * O(1)
        * */
        return this.size;
    }

    public Object[] getArray() {
        /*Just for testing*/
        int counter = 0;
        int ind = this.nextFirst;
        Object[] arr = new Object[this.size];
        while (counter < this.size) {
            if (ind == this.currentCapacity - 1) {
                ind = 0;
            } else {
                ind = ind + 1;
            }
            arr[counter] = this.items[ind];
            counter += 1;
        }
        return arr;
    }

    public double usageFactor() {
        /*Helper function*/
        return (double) this.size / this.currentCapacity;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public T get(int index){
        /*
        * O(1)
        * */
        if ((this.size == 0) || index >= this.size){
            return null;
        }
        int indexToFetch;
        if (this.nextFirst < this.nextLast){
            indexToFetch = this.nextFirst + 1 + index;
        }
        else{
            if (this.nextFirst + 1 + index < this.currentCapacity){
                indexToFetch = this.nextFirst + 1 + index;
            }
            else{
                indexToFetch = index - (this.currentCapacity - 1 - this.nextFirst);
            }
        }
        return this.items[indexToFetch];
    }

    public void printDeque(){
        for (int i = 0; i <this.size ; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.addLast(5);
        arr.addLast(6);
        arr.addLast(7);
        arr.addFirst(4);
        arr.printDeque();

        ArrayDeque<Integer> arr2 = new ArrayDeque(arr);
        arr2.printDeque();
    }
}
