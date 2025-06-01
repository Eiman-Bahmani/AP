package Game.model.Building.Mines;
import Game.model.Building.Building;
import Game.model.Position;

public class StoneMine extends Building {
    private int power;
    public StoneMine(Position position) {
        super("Stone Mine", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Stone Mine upgraded to level " + level);
    }
}
