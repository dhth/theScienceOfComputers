package CS61BLab5.huglife;

import java.awt.*;

public class Empty extends Occupant {
    public Empty() {
        super("empty");
    }

    /**
     * Returns hardcoded black
     */
    public Color color() {
        return color(255, 255, 255);
    }
}