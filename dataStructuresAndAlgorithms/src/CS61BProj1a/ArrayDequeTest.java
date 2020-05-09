package CS61BProj1a;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

    @Test
    public void testResizeUp1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(0, 1);
        arr.setItem(1, 2);
        arr.setItem(6, 7);
        arr.setItem(7, 8);

        arr.setNextFirst(5);
        arr.setNextLast(2);
        arr.setSize(4);

        arr.resizeUp(16);

        Object[] expected = {7, 8, 1, 2};
        Object[] actual = arr.getArray();

        assertArrayEquals(expected, actual);
        assertEquals(16, arr.getCurrentCapacity());
        assertEquals(15, arr.getNextFirst());
        assertEquals(4, arr.getNextLast());
    }

    @Test
    public void testResizeUp2() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(3, 1);
        arr.setItem(4, 2);
        arr.setItem(5, 7);
        arr.setItem(6, 8);

        arr.setNextFirst(2);
        arr.setNextLast(7);
        arr.setSize(4);

        arr.resizeUp(16);

        Object[] expected = {1, 2, 7, 8};
        Object[] actual = arr.getArray();

        assertArrayEquals(expected, actual);
        assertEquals(16, arr.getCurrentCapacity());
        assertEquals(15, arr.getNextFirst());
        assertEquals(4, arr.getNextLast());
    }

    @Test
    public void testResizeUp3() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 8; i++) {
            arr.setItem(i, i);
        }

        arr.setNextFirst(7);
        arr.setNextLast(0);
        arr.setSize(8);

        arr.resizeUp(16);

        Object[] expected = {0, 1, 2, 3, 4, 5, 6, 7};
        Object[] actual = arr.getArray();

        assertArrayEquals(expected, actual);
        assertEquals(16, arr.getCurrentCapacity());
        assertEquals(15, arr.getNextFirst());
        assertEquals(8, arr.getNextLast());
    }

    @Test
    public void testResizeDown1() {
        /*Case where nextFirst is ahead of nextLast
        ie roll over
        * */
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(0, 0);
        arr.setItem(7, 8);

        arr.setNextFirst(6);
        arr.setNextLast(1);
        arr.setSize(2);

        arr.resizeDown(4);

        Object[] expected = {8, 0};
        Object[] actual = arr.getArray();

        assertArrayEquals(expected, actual);
        assertEquals(4, arr.getCurrentCapacity());
        assertEquals(3, arr.getNextFirst());
        assertEquals(2, arr.getNextLast());
    }

    @Test
    public void testResizeDown2() {
        /*Case where nextFirst behind nextLast
         * */
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(2, 0);
        arr.setItem(3, 8);
        arr.setItem(4, 9);

        arr.setNextFirst(1);
        arr.setNextLast(5);
        arr.setSize(3);

        arr.resizeDown(4);

        Object[] expected = {0, 8, 9};
        Object[] actual = arr.getArray();

        assertArrayEquals(expected, actual);
        assertEquals(4, arr.getCurrentCapacity());
        assertEquals(3, arr.getNextFirst());
        assertEquals(3, arr.getNextLast());
    }

    @Test
    public void testAddFirst1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(2, 0);
        arr.setItem(3, 8);
        arr.setItem(4, 9);

        arr.setNextFirst(1);
        arr.setNextLast(5);
        arr.setSize(3);

        arr.addFirst(5);

        assertEquals(0, arr.getNextFirst());
        assertEquals(5, (int) arr.getItemFromInternalArray(1));
        assertEquals(4, arr.size());

    }

    @Test
    public void testAddFirst2() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(1, 6);
        arr.setItem(2, 7);
        arr.setItem(3, 8);
        arr.setItem(4, 9);

        arr.setNextFirst(0);
        arr.setNextLast(5);
        arr.setSize(4);

        arr.addFirst(5);
        arr.addFirst(4);

        assertEquals(6, arr.getNextFirst());
        assertEquals(5, (int) arr.getItemFromInternalArray(0));
        assertEquals(4, (int) arr.getItemFromInternalArray(7));
        assertEquals(6, arr.size());
    }

    @Test
    public void testAddFirstWithResizing1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 12; i++) {
            arr.addFirst(i);
        }

        assertEquals(11, arr.getNextFirst());
        assertEquals(8, arr.getNextLast());

        assertEquals(7, (int) arr.getItemFromInternalArray(0));
        assertEquals(6, (int) arr.getItemFromInternalArray(1));
        assertEquals(5, (int) arr.getItemFromInternalArray(2));

        assertEquals(8, (int) arr.getItemFromInternalArray(15));
        assertEquals(9, (int) arr.getItemFromInternalArray(14));
        assertEquals(10, (int) arr.getItemFromInternalArray(13));
        assertEquals(11, (int) arr.getItemFromInternalArray(12));
        assertEquals(12, arr.size());
    }

    @Test
    public void testAddLast1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(2, 0);
        arr.setItem(3, 8);
        arr.setItem(4, 9);

        arr.setNextFirst(1);
        arr.setNextLast(5);
        arr.setSize(3);

        arr.addLast(10);

        assertEquals(6, arr.getNextLast());
        assertEquals(10, (int) arr.getItemFromInternalArray(5));
        assertEquals(4, arr.size());
    }

    @Test
    public void testAddLast12() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.setItem(2, 0);
        arr.setItem(3, 8);
        arr.setItem(4, 9);

        arr.setNextFirst(1);
        arr.setNextLast(5);
        arr.setSize(3);

        arr.addLast(10);
        arr.addLast(11);
        arr.addLast(12);
        arr.addLast(13);

        assertEquals(1, arr.getNextLast());
        assertEquals(10, (int) arr.getItemFromInternalArray(5));
        assertEquals(11, (int) arr.getItemFromInternalArray(6));
        assertEquals(12, (int) arr.getItemFromInternalArray(7));
        assertEquals(13, (int) arr.getItemFromInternalArray(0));
        assertEquals(7, arr.size());
    }

    @Test
    public void testAddLastWithResizing1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 12; i++) {
            arr.addLast(i);
        }

        assertEquals(15, arr.getNextFirst());
        assertEquals(12, arr.getNextLast());

        assertEquals(0, (int) arr.getItemFromInternalArray(0));
        assertEquals(1, (int) arr.getItemFromInternalArray(1));
        assertEquals(2, (int) arr.getItemFromInternalArray(2));

        assertEquals(8, (int) arr.getItemFromInternalArray(8));
        assertEquals(9, (int) arr.getItemFromInternalArray(9));
        assertEquals(10, (int) arr.getItemFromInternalArray(10));
        assertEquals(11, (int) arr.getItemFromInternalArray(11));
        assertEquals(12, arr.size());
    }

    @Test
    public void testRemoveFirst1() {
        /*nextFirst at last index*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 8; i++) {
            arr.addFirst(i);
        }
        int f = arr.removeFirst();

        assertEquals(7, f);
        assertEquals(7, arr.size());
        assertEquals(0, arr.getNextFirst());
    }

    @Test
    public void testRemoveFirst2() {
        /*nextFirst not at last index*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 4; i++) {
            arr.addFirst(i);
        }
        int f = arr.removeFirst();

        assertEquals(3, f);
        assertEquals(3, arr.size());
        assertEquals(4, arr.getNextFirst());
    }

    @Test
    public void testRemoveFirst3() {
        /*multiple removes*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 3; i++) {
            arr.addFirst(i);
        }
        for (int i = 3; i < 10; i++) {
            arr.addLast(i);
        }
        int f1 = arr.removeFirst();
        int f2 = arr.removeFirst();
        int f3 = arr.removeFirst();
        int f4 = arr.removeFirst();
        int f5 = arr.removeFirst();
        int f6 = arr.removeFirst();

        assertEquals(2, f1);
        assertEquals(1, f2);
        assertEquals(0, f3);
        assertEquals(3, f4);
        assertEquals(4, f5);
        assertEquals(5, f6);

        arr.addFirst(-1);
        arr.addLast(10);

        int f7 = arr.removeFirst();
        int f8 = arr.removeFirst();

        assertEquals(-1, f7);
        assertEquals(6, f8);

        assertEquals(4, arr.size());
    }

    @Test
    public void testRemoveFirstWithResize1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 128; i++) {
            arr.addFirst(i);
        }
        int r;
        for (int i = 0; i < 127; i++) {
            r = arr.removeFirst();
        }

        assertEquals(1, arr.size());
        assertEquals(8, arr.getCurrentCapacity());
    }

    @Test
    public void testRemoveLast1() {
        /*nextLast at last index*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 8; i++) {
            arr.addLast(i);
        }
        int f = arr.removeLast();

        assertEquals(7, f);
        assertEquals(7, arr.size());
        assertEquals(7, arr.getNextLast());
    }

    @Test
    public void testRemoveLast2() {
        /*nextLast not at last index*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 4; i++) {
            arr.addLast(i);
        }
        int f = arr.removeLast();

        assertEquals(3, f);
        assertEquals(3, arr.size());
        assertEquals(3, arr.getNextLast());
    }

    @Test
    public void testRemoveLast3() {
        /*multiple removes*/
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 7; i++) {
            arr.addFirst(i);
        }
        for (int i = 7; i < 10; i++) {
            arr.addLast(i);
        }
        int f1 = arr.removeLast();
        int f2 = arr.removeLast();
        int f3 = arr.removeLast();
        int f4 = arr.removeLast();
        int f5 = arr.removeLast();
        int f6 = arr.removeLast();

        assertEquals(9, f1);
        assertEquals(8, f2);
        assertEquals(7, f3);
        assertEquals(0, f4);
        assertEquals(1, f5);
        assertEquals(2, f6);

        arr.addFirst(-1);
        arr.addLast(10);

        int f7 = arr.removeLast();
        int f8 = arr.removeLast();

        assertEquals(10, f7);
        assertEquals(3, f8);

        assertEquals(4, arr.size());
    }

    @Test
    public void testRemoveLastWithResize1() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        for (int i = 0; i < 128; i++) {
            arr.addFirst(i);
        }
        int r;
        for (int i = 0; i < 127; i++) {
            r = arr.removeLast();
        }

        assertEquals(1, arr.size());
        assertEquals(8, arr.getCurrentCapacity());
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> arr = new ArrayDeque();
        arr.addLast(5);
        arr.addLast(6);
        arr.addLast(7);
        arr.addFirst(4);
        arr.addFirst(3);
        arr.addFirst(2);

        assertEquals(3, (int) arr.get(1));
        assertEquals(4, (int) arr.get(2));
        assertEquals(6, (int) arr.get(4));
        assertEquals(7, (int) arr.get(5));

        arr.addFirst(1);
        arr.addFirst(0);
        arr.addLast(8);
        arr.addFirst(-1);
        arr.addFirst(-2);
        arr.addLast(9);
        arr.addLast(10);
        arr.addLast(11);

        assertEquals(-2, (int) arr.get(0));
        assertEquals(-1, (int) arr.get(1));
        assertEquals(4, (int) arr.get(6));
        assertEquals(8, (int) arr.get(10));
        assertEquals(9, (int) arr.get(11));
        assertEquals(10, (int) arr.get(12));

        assertEquals(14, arr.size());
    }

}
