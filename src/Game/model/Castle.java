package Game.model;

import Game.model.Building.Building;

public class Castle extends Building {
        public Castle(String name, int level, Position position , int strength) {
            super(name, level, position , strength);
        }

        @Override
        public void upgrade() {
            if (level < 5) {
                level++;
                System.out.println(name + " upgraded to level " + level);
            } else {
                System.out.println(name + " is already at max level.");
            }
        }
    }

