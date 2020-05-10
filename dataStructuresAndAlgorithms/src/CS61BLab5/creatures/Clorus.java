package CS61BLab5.creatures;

import CS61BLab5.huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private final int r;
    private final int g;
    private final int b;
    private final double MIN_ENERGY = 0.0;
    private final double MAX_ENERGY = 2.0;
    private final double MOVE_PROBABILITY = 0.5;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        double newEnergy = this.energy / 2.0;
        this.energy = newEnergy;
        return new Clorus(newEnergy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> nearbyPlips = new ArrayDeque<>();
        boolean anyPlip = false;
        boolean allOccupied = true;

        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                allOccupied = false;
                emptyNeighbors.add(dir);
            }
            if (neighbors.get(dir).name().equals("plip")) {
                anyPlip = true;
                nearbyPlips.add(dir);
            }
        }

        //stay
        if (allOccupied) {
            return new Action(Action.ActionType.STAY);
        }

        //attack
        if (anyPlip) {
            return new Action(Action.ActionType.ATTACK,
                    HugLifeUtils.randomEntry(nearbyPlips));
        }

        // replicate
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE,
                    HugLifeUtils.randomEntry(emptyNeighbors));
        }

        //move
        return new Action(Action.ActionType.MOVE,
                HugLifeUtils.randomEntry(emptyNeighbors));
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }
}
