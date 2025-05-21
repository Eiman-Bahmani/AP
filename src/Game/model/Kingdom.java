package Game.model;

import Game.enums.KingdomType;
import java.util.LinkedList;
import java.util.List;

public class Kingdom {
    private KingdomType type;
    private List<Building> buildings;

    public Kingdom(KingdomType type) {
        this.type = type;
        this.buildings = new LinkedList<>();

        // ایجاد قلعه اولیه
        Position mainCastlePosition = new Position(0, 0, 5, 5); // در مختصات 0, 0 با ابعاد 5x5
        Castle castle = new Castle("Main Castle", 1, mainCastlePosition);
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
