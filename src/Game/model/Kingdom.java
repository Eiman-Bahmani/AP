package Game.model;
import Game.Login.GameUser;
import Game.enums.KingdomType;
import Game.model.Building.Building;

import java.util.LinkedList;
import java.util.List;

public class Kingdom {
    private KingdomType type;
    private LinkedList<Building> buildings;
  private  Position kingdomPosition;
  private GameUser owner;
    public Kingdom(KingdomType type) {
        this.type = type;
        this.buildings = new LinkedList<>();

        // ایجاد قلعه اولیه
        Position mainCastlePosition = new Position(0, 0, 5, 5); // در مختصات 0, 0 با ابعاد 5x5
        Castle castle = new Castle("GameBuilder Castle", 1, mainCastlePosition,100);
        buildings.add(castle);
    }

    public boolean addBuilding(Building newBuilding) {
        // بررسی تداخل ساختمان جدید با ساختمان‌های موجود
        for (Building b : buildings) {
            if (b.isOverlapping(newBuilding)) {
                System.out.println("Error: Buildings overlap! Choose different coordinates.");
                return false; // تداخل دارد
            }
        }
        buildings.add(newBuilding);
        return true; // اضافه شد
    }

    public void printMap() {
        System.out.println("=== Kingdom: " + type + " ===");
        for (Building b : buildings) {
            System.out.println(b);
        }
    }
}
