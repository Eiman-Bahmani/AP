package Game.model.Building.Storages;
import Game.model.Building.Building;
import Game.model.Position;

public class GoldStorage extends Building {
    protected int capacity;
    public GoldStorage(Position position) {
        super("Gold Storage", 1, position,10);
        this.capacity = 100;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Gold Storage upgraded to level " + level);
    }
}
