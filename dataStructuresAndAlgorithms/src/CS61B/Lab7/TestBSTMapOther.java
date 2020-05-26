package CS61B.Lab7;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestBSTMapOther {
    public BSTMap getBSTMap() {
        BSTMap<Integer, Character> bstMap = new BSTMap<>();
        bstMap.put(5, 'H');
        bstMap.put(2, 'A');
        bstMap.put(8, 'M');
        return bstMap;
    }

    @Test
    public void testContainsKey() {
        BSTMap<Integer, Character> bstMap = getBSTMap();
        assertEquals(true, bstMap.containsKey(5));
        assertEquals(true, bstMap.containsKey(2));
        assertEquals(true, bstMap.containsKey(8));
        assertEquals(false, bstMap.containsKey(1));
        assertEquals(false, bstMap.containsKey(20));
        assertEquals(false, bstMap.containsKey(12));
    }

    @Test
    public void testGet() {
        BSTMap<Integer, Character> bstMap = getBSTMap();
        assertEquals('H', bstMap.get(5));
        assertEquals('A', bstMap.get(2));
        assertEquals('M', bstMap.get(8));
        assertNull(bstMap.get(1));
    }

    public BSTMap getBSTMap2() {
        BSTMap<Integer, Character> bstMap = new BSTMap<>();
        bstMap.put(10, 'a');
        bstMap.put(2, 'b');
        bstMap.put(1, 'c');
        bstMap.put(9, 'd');
        bstMap.put(15, 'e');
        bstMap.put(12, 'f');
        bstMap.put(18, 'g');
        bstMap.put(11, 'h');
        return bstMap;
    }

    @Test
    public void testGetParent() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        assertNull(bstMap.getParentsKey(10));
        assertEquals(10, (int) bstMap.getParentsKey(2));
        assertEquals(2, (int) bstMap.getParentsKey(1));
        assertEquals(2, (int) bstMap.getParentsKey(9));
        assertEquals(10, (int) bstMap.getParentsKey(15));
        assertEquals(15, (int) bstMap.getParentsKey(12));
        assertEquals(15, (int) bstMap.getParentsKey(18));
        assertEquals(12, (int) bstMap.getParentsKey(11));
    }

    @Test
    public void testSizeOfNodes() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        assertEquals(8, bstMap.getSize(10));
        assertEquals(3, bstMap.getSize(2));
        assertEquals(1, bstMap.getSize(1));
        assertEquals(1, bstMap.getSize(9));
        assertEquals(4, bstMap.getSize(15));
        assertEquals(2, bstMap.getSize(12));
        assertEquals(1, bstMap.getSize(18));
        assertEquals(1, bstMap.getSize(11));
    }

    @Test
    public void testRemoveLeafNode1() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        bstMap.remove(11);
        assertEquals(7, bstMap.getSize(10));
        assertEquals(3, bstMap.getSize(15));
        assertEquals(1, bstMap.getSize(12));
        assertEquals(7, bstMap.size());
        bstMap.remove(1);
        assertEquals(2, bstMap.getSize(2));
        assertEquals(6, bstMap.getSize(10));
        assertEquals(6, bstMap.size());
        bstMap.remove(12);
        assertEquals(2, bstMap.getSize(15));
        assertEquals(5, bstMap.getSize(10));
        assertEquals(5, bstMap.size());
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        bstMap.remove(12);
        assertEquals(7, bstMap.getSize(10));
        assertEquals(3, bstMap.getSize(15));
        assertEquals(7, bstMap.size());
        bstMap.remove(18);
        assertEquals(2, bstMap.getSize(15));
        assertEquals(6, bstMap.getSize(10));
        assertEquals(6, bstMap.size());
        bstMap.remove(15);
        assertEquals(5, bstMap.getSize(10));
        assertEquals(5, bstMap.size());
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        bstMap.remove(10);
        assertEquals(7, bstMap.getSize(11));
        assertEquals(1, bstMap.getSize(12));
        assertEquals(3, bstMap.getSize(15));
        assertEquals(7, bstMap.size());
    }

    @Test
    public void testRemoveNodeWithTwoChildren2() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
        bstMap.remove(15);
        assertEquals(7, bstMap.getSize(10));
        assertEquals(3, bstMap.getSize(18));
        assertEquals(2, bstMap.getSize(12));
        assertEquals(7, bstMap.size());
    }

    @Test
    public void testRemoveRootNodeWithTwoChildren() {
        BSTMap<Integer, Character> bstMap = new BSTMap<>();
        bstMap.put(3, 'a');
        bstMap.put(1, 'b');
        bstMap.put(5, 'c');
        Character r1 = (Character) bstMap.remove(3);
        assertEquals((Character) 'a', r1);
        assertEquals(2, bstMap.getSize(5));
        assertEquals(1, bstMap.getSize(1));
        assertEquals(2, bstMap.size());
    }

    @Test
    public void testRemoveRootNodeWithOneChild() {
        BSTMap<Integer, Character> bstMap = new BSTMap<>();
        bstMap.put(3, 'a');
        bstMap.put(1, 'b');
        Character r1 = (Character) bstMap.remove(3);
        assertEquals((Character) 'a', r1);
        assertEquals(1, bstMap.getSize(1));
        assertEquals(1, bstMap.size());
    }

    @Test
    public void testPrintInOrder() {
        BSTMap<Integer, Character> bstMap = getBSTMap2();
//        bstMap.printInOrder();
        Set s = bstMap.keySet();
        assertEquals(8, s.size());
    }
}
