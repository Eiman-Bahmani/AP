package Game.model.Building;

import Game.model.Position;

public class MainGate extends Building {
    public MainGate(Position position) {
        super("Main Gate", 1, position,10);
    }

    @Override
    public void upgrade() {
        level++;
        strength += 0;
        System.out.println("Main Gate upgraded to level " + level);
    }
}
