package Game.model.Building.Mines;
import Game.model.Building.Building;
import Game.model.Position;

public class Woodcutting extends Building {
    private int power;
    public Woodcutting(Position position) {
        super("Lumber Camp", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Wood Camp upgraded to level " + level);
    }
}
