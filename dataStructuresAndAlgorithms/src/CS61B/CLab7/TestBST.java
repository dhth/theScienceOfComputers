package CS61B.CLab7;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBST {
    @Test
    public void testGetAverageDepth1() {
        BST<Integer> bst = new BST();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        bst.add(6);
        bst.add(7);
        double avgDepth = bst.getAverageDepth();
        assertEquals(3.0, avgDepth, 0.1);
    }

    @Test
    public void testGetAverageDepth2() {
        BST<Integer> bst = new BST();
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        double avgDepth = bst.getAverageDepth();
        assertEquals(1.428, avgDepth, 0.1);
    }

    @Test
    public void testGetAverageDepth3() {
        BST<Integer> bst = new BST();
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(8);
        double avgDepth = bst.getAverageDepth();
        assertEquals(1.625, avgDepth, 0.1);
    }

    @Test
    public void testOptimalIP() {
        assertEquals(0, ExperimentHelper.optimalIPL(1));
        assertEquals(6, ExperimentHelper.optimalIPL(5));
        assertEquals(13, ExperimentHelper.optimalIPL(8));
        assertEquals(10, ExperimentHelper.optimalIPL(7));
        assertEquals(4, ExperimentHelper.optimalIPL(4));
    }

    @Test
    public void testAverage() {
        assertEquals(0, ExperimentHelper.optimalAverageDepth(1), 0.001);
        assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5), 0.001);
        assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8), 0.001);
    }
}
