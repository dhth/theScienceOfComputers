package CS61B.HW1.synthesizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        int a = arb.peek();
        assertEquals(1, a);
        assertEquals(2, arb.fillCount());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(5, arb.fillCount());
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals((Integer) 1, arb.dequeue());
        assertEquals((Integer) 2, arb.dequeue());
        assertEquals(3, arb.fillCount());
        assertEquals((Integer) 3, arb.dequeue());
        assertEquals((Integer) 4, arb.dequeue());
        assertEquals(1, arb.fillCount());
    }

    @Test
    public void testWrapping() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals((Integer) 1, arb.dequeue());
        arb.enqueue(6);
        assertEquals(5, arb.fillCount());
        assertEquals((Integer) 2, arb.dequeue());
        arb.enqueue(7);
        assertEquals(5, arb.fillCount());
        assertEquals((Integer) 3, arb.dequeue());
        assertEquals((Integer) 4, arb.dequeue());
        assertEquals((Integer) 5, arb.dequeue());
        assertEquals((Integer) 6, arb.dequeue());
        assertEquals(1, arb.fillCount());
        assertEquals((Integer) 7, arb.dequeue());
        assertEquals(0, arb.fillCount());
    }

}
