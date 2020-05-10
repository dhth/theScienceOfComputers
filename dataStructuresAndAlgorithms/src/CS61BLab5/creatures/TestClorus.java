package CS61BLab5.creatures;

import CS61BLab5.huglife.Creature;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus clorus = new Clorus(2);
        assertEquals(2, clorus.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), clorus.color());
        clorus.move();
        assertEquals(1.97, clorus.energy(), 0.01);
        clorus.move();
        assertEquals(1.94, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(1.93, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(1.92, clorus.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(1.4);
        Creature newClorus = c.replicate();
        assertEquals(0.7, c.energy(), 0.01);
        assertNotEquals(c, newClorus);
    }
}
