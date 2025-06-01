package Game.model.Building.Mines;
import Game.model.Building.Building;
import Game.model.Position;

public class GoldMine extends Building {
    private int power;
    public GoldMine(Position position) {
        super("Gold Mine", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Gold Mine upgraded to level " + level);
    }
}
