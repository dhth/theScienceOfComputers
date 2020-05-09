package CS61BProj1a;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedListDequeTest {
    @Test
    public void testInsertAtPositionZero() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(0, 0);

        assertEquals(arr.get(0), (Integer) 0);
        assertEquals(arr.get(1), (Integer) 1);
        assertEquals(arr.get(5), (Integer) 8);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertAtLastPosition() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(9, 5);

        assertEquals(arr.get(5), (Integer) 9);
        assertEquals(arr.get(0), (Integer) 1);
        assertEquals(arr.get(4), (Integer) 8);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertInMiddle1() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(5, 3);

        assertEquals(arr.get(3), (Integer) 5);
        assertEquals(arr.get(0), (Integer) 1);
        assertEquals(arr.get(2), (Integer) 4);
        assertEquals(arr.get(4), (Integer) 6);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertInMiddle2() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(3, 1);

        assertEquals(3, (int) arr.get(1));
        assertEquals(1, (int) arr.get(0));
        assertEquals(2, (int) arr.get(2));
        assertEquals(6, arr.size());
    }

    @Test
    public void testAddLast() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addLast(6);
        arr.addLast(8);
        arr.addLast(9);

        assertEquals(6, (int) arr.get(0));
        assertEquals(8, (int) arr.get(1));
        assertEquals(9, (int) arr.get(2));
        assertEquals(3, arr.size());
    }

    @Test
    public void removeFirst() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);

        assertEquals(arr.removeFirst(), (Integer) 1);
    }

    @Test
    public void removeLast() {
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);

        assertEquals(arr.removeLast(), (Integer) 8);
    }
}
