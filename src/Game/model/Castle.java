package Game.model;

    public class Castle extends Building {
        public Castle(String name, int level, Position position) {
            super(name, level, position);
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

