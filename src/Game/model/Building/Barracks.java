package Game.model.Building;

import Game.model.Position;

public class Barracks extends Building {
    protected int power;
    public Barracks(Position position) {
        super("Barracks", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Barracks upgraded to level " + level);
    }
}
