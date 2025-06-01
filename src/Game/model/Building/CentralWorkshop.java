package Game.model.Building;

import Game.model.Position;

public class CentralWorkshop extends Building {
    protected int power;
    public CentralWorkshop(Position position) {
        super("Central Workshop", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Central Workshop upgraded to level " + level);
    }
}
