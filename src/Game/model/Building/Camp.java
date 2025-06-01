package Game.model.Building;

import Game.model.Position;

public class Camp extends  Building {
    protected int capacity;
    public Camp(Position position) {
        super("Camp", 1, position,10);
        this.capacity = 100;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Camp upgraded to level " + level);
    }
}
