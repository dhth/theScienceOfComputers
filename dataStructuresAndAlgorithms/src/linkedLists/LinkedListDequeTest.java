package linkedLists;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListDequeTest {
    @Test
    public void testInsertAtPositionZero(){
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(0, 0);

        assertEquals(arr.get(0), (Integer)0);
        assertEquals(arr.get(1), (Integer)1);
        assertEquals(arr.get(5), (Integer)8);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertAtLastPosition(){
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(9, 5);

        assertEquals(arr.get(5), (Integer)9);
        assertEquals(arr.get(0), (Integer)1);
        assertEquals(arr.get(4), (Integer)8);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertInMiddle1(){
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(5, 3);

        assertEquals(arr.get(3), (Integer)5);
        assertEquals(arr.get(0), (Integer)1);
        assertEquals(arr.get(2), (Integer)4);
        assertEquals(arr.get(4), (Integer)6);
        assertEquals(arr.size(), 6);
    }

    @Test
    public void testInsertInMiddle2(){
        LinkedListDeque<Integer> arr = new LinkedListDeque();
        arr.addFirst(4);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addLast(6);
        arr.addLast(8);
        arr.insert(3, 1);

        assertEquals(arr.get(1), (Integer)3);
        assertEquals(arr.get(0), (Integer)1);
        assertEquals(arr.get(2), (Integer)2);
        assertEquals(arr.size(), 6);
    }

}
