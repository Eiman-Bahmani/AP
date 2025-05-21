package Game.model;

public abstract class Building {
    protected String name;
    protected int level;
    protected Position position; // پوزیشن ساختمان

    public Building(String name, int level, Position position) {
        this.name = name;
        this.level = level;
        this.position = position;
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


