package Game.model.Building.Storages;
import Game.model.Building.Building;
import Game.model.Position;

public class WoodStorage extends Building {
    protected int capacity;
    public WoodStorage(Position position) {
        super("Lumber Storage", 1, position,10);
        this.capacity = 100;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Wood Storage upgraded to level " + level);
    }
}
