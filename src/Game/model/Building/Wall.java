package Game.model.Building;

import Game.model.Position;

public class Wall extends Building {
    public Wall(Position position) {
        super("Wall", 1, position,10);
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Wall upgraded to level " + level);
    }
}
