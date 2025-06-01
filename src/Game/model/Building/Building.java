package Game.model.Building;
import Game.model.Position;

public abstract class Building {
    protected String name;
    protected int level;
    protected int strength;
    protected Position position; // پوزیشن ساختمان

    public Building(String name, int level, Position position,int strength) {
        this.name = name;
        this.level = level;
        this.position = position;
        this.strength = strength;
    }

    public abstract void upgrade();

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isOverlapping(Building other) {
        return this.position.isOverlapping(other.getPosition());
    }

    @Override
    public String toString() {
        return name + " (Level " + level + ") at " + position;
    }
}


