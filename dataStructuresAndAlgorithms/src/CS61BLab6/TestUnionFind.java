package CS61BLab6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUnionFind {

    public UnionFind getUnionFindSample() {
        UnionFind uf = new UnionFind(16);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(5, 7);
        uf.union(3, 7);
        uf.union(8, 9);
        uf.union(10, 11);
        uf.union(9, 11);
        uf.union(12, 13);
        uf.union(14, 15);
        uf.union(13, 15);
        uf.union(11, 15);
        return uf;
    }

    @Test
    public void testFindWithoutPathCompression() {
        UnionFind uf = getUnionFindSample();
        assertEquals(7, uf.find(0));
        assertEquals(7, uf.find(1));
        assertEquals(7, uf.find(2));
        assertEquals(7, uf.find(3));
        assertEquals(7, uf.find(4));
        assertEquals(7, uf.find(5));
        assertEquals(7, uf.find(6));
        assertEquals(15, uf.find(8));
        assertEquals(15, uf.find(9));
        assertEquals(15, uf.find(10));
        assertEquals(15, uf.find(11));
        assertEquals(15, uf.find(12));
        assertEquals(15, uf.find(13));
        assertEquals(15, uf.find(14));
    }

    @Test
    public void testPathCompression() {
        UnionFind uf = getUnionFindSample();
        uf.union(8, 0);
        assertEquals(7, uf.parent(0));
        assertEquals(7, uf.parent(1));
        assertEquals(15, uf.parent(8));
        assertEquals(15, uf.parent(9));
        assertEquals(7, uf.parent(15));
        assertEquals(16, uf.sizeOf(7));
        uf.union(2, 12);
        assertEquals(7, uf.parent(2));
        assertEquals(7, uf.parent(12));
        assertEquals(7, uf.parent(13));
    }

    @Test
    public void testPathCompression2() {
        UnionFind uf = getUnionFindSample();
        boolean isConnected = uf.connected(0, 4);
        assertEquals(7, uf.parent(0));
        assertEquals(7, uf.parent(1));
        assertEquals(7, uf.parent(4));
    }

    @Test
    public void testParent1() {
        UnionFind uf = getUnionFindSample();
        assertEquals(1, uf.parent((0)));
        assertEquals(3, uf.parent((1)));
        assertEquals(3, uf.parent((2)));
        assertEquals(7, uf.parent((3)));
        assertEquals(5, uf.parent((4)));
        assertEquals(7, uf.parent((5)));
        assertEquals(7, uf.parent((6)));
        assertEquals(-8, uf.parent((7)));
    }

    @Test
    public void testSizeOf() {
        UnionFind uf = getUnionFindSample();
        assertEquals(8, uf.sizeOf(1));
        assertEquals(8, uf.sizeOf(2));
        assertEquals(8, uf.sizeOf(3));
        assertEquals(8, uf.sizeOf(4));
        assertEquals(8, uf.sizeOf(5));
        assertEquals(8, uf.sizeOf(6));
        assertEquals(8, uf.sizeOf(7));
        assertEquals(8, uf.sizeOf(0));
    }

    @Test
    public void testConnected1() {
        UnionFind uf = getUnionFindSample();
        assertEquals(true, uf.connected(2, 5));
        assertEquals(true, uf.connected(3, 7));
        assertEquals(true, uf.connected(3, 6));
        assertEquals(true, uf.connected(2, 1));
        assertEquals(true, uf.connected(10, 13));
        assertEquals(false, uf.connected(8, 1));
        assertEquals(false, uf.connected(2, 8));
        assertEquals(false, uf.connected(6, 9));
        assertEquals(false, uf.connected(7, 10));
        assertEquals(false, uf.connected(3, 11));
    }

}
