package Game.model.Building.Storages;
import Game.model.Building.Building;
import Game.model.Position;

public class FoodStorage extends Building {
    protected int capacity;
    public FoodStorage(Position position) {
        super("Food Storage", 1, position,10);
        this.capacity = 100;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Food Storage upgraded to level " + level);
    }
}
