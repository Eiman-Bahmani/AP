package Game.model.Building.Storages;
import Game.model.Building.Building;
import Game.model.Position;

public class StoneStorage extends Building {
    protected int capacity;
    public StoneStorage(Position position) {
        super("Stone Storage", 1, position,10);
        this.capacity = 100;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Stone Storage upgraded to level " + level);
    }
}
