package CS61B.Proj2AB.bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 8; i++) {
            heap.add(i, i);
        }
        assertEquals(8, heap.size());
//        PrintHeapDemo.printSimpleHeapDrawing(heap.itemArray);
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 7; i++) {
            heap.add(i, i);
        }
        assertEquals((Integer) 1, heap.getSmallest());
        heap.add(0, 0);
        assertEquals((Integer) 0, heap.getSmallest());
//        PrintHeapDemo.printSimpleHeapDrawing(heap.itemArray);
    }

    @Test
    public void testGetSmallest2() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 1000; i++) {
            heap.add(i, i);
        }
        assertEquals((Integer) 1, heap.getSmallest());
        for (int i = 1; i <= 100; i++) {
            heap.removeSmallest();
        }
        assertEquals(900, heap.size());
        assertEquals((Integer) 101, heap.getSmallest());
//        PrintHeapDemo.printSimpleHeapDrawing(heap.itemArray);
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 100; i++) {
            heap.add(i, i);
        }
        assertTrue(heap.contains(1));
        assertTrue(heap.contains(2));
        assertTrue(heap.contains(3));
        assertTrue(heap.contains(4));
        assertFalse(heap.contains(101));
        assertFalse(heap.contains(102));
        assertFalse(heap.contains(103));
        assertFalse(heap.contains(104));
    }


    @Test
    public void testArrayResizeUp() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 25; i++) {
            heap.add(i, i);
        }
        assertEquals((Integer) 1, heap.getSmallest());
        assertEquals(25, heap.size());
//        PrintHeapDemo.printSimpleHeapDrawing(heap.itemArray);
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 25; i++) {
            heap.add(i, i);
        }
        assertEquals((Integer) 1, heap.getSmallest());
        heap.changePriority(1, 1000);
        assertEquals((Integer) 2, heap.getSmallest());
        for (int i = 26; i <= 1000; i++) {
            heap.add(i, i);
        }
        assertEquals((Integer) 2, heap.getSmallest());
        heap.changePriority(2, 1001);
        assertEquals((Integer) 3, heap.getSmallest());
        heap.removeSmallest();
        assertEquals((Integer) 4, heap.getSmallest());
        heap.changePriority(2, 2);
        assertEquals((Integer) 2, heap.getSmallest());
        heap.changePriority(1, 1);
        assertEquals((Integer) 1, heap.getSmallest());
    }
}
