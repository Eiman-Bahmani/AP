package Game.model;

    public class Position {
        private int x, y;         // مختصات گوشه بالا-چپ
        private int width, height; // ابعاد ساختمان

        public Position(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        // Getters and Setters
        public int getX() { return this.x; }
        public void setX(int x) { this.x = x; }

        public int getY() { return this.y; }
        public void setY(int y) { this.y = y; }

        public int getWidth() { return this.width; }
        public void setWidth(int width) { this.width = width; }

        public int getHeight() { return this.height; }
        public void setHeight(int height) { this.height = height; }

        // متد برای بررسی تداخل پوزیشن
        public boolean isOverlapping(Position other) {
            return this.x < other.x + other.width &&
                    this.x + this.width > other.x &&
                    this.y < other.y + other.height &&
                    this.y + this.height > other.y;
        }

        @Override
        public String toString() {
            return "Position{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + '}';
        }
    }

