package Game.model.Building.Mines;
import Game.model.Building.Building;
import Game.model.Position;

public class Farm extends Building {
    private int power;
    public Farm(Position position) {
        super("Farm", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Farm upgraded to level " + level);
    }
}
