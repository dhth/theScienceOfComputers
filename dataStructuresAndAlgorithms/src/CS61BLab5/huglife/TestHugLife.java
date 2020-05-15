package CS61BLab5.huglife;

import org.junit.Test;

/**
 * Performs basic tests of huglife package.
 *
 * @author Josh Hug
 */

public class TestHugLife {
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestHugLife.class);
    }

    /**
     * Performs basic tests of huglife package.
     */

    @Test
    public void populateAndDraw() {
        Grid g = new Grid(20);
        g.placeOccupant(0, 10, new SampleCreature());
        g.placeOccupant(5, 5, new SampleCreature());
        g.drawWorld();
        StdDraw.show(20);
        g.doMove(0, 10, 0, 11);
        g.drawWorld();
        StdDraw.show(20);

    }
} 