package Game.model.Building;

import Game.model.Position;

public class DefenseTower extends Building {
    protected int power;
    public DefenseTower(Position position) {
        super("Defense Tower", 1, position,10);
        this.power = 10;
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Defense Tower upgraded to level " + level);
    }
}
