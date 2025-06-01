package Game.model.Army;
import Game.util.MyLinkedList;
import Game.util.Node;

public class SoldierUnit {
    private String type;
    private MyLinkedList<Soldier> soldiers;

    public SoldierUnit(String type) {
        this.type = type;
        this.soldiers = new MyLinkedList<>();
    }

    public void addSoldier(Soldier s) {
        if (s.getType().equals(this.type)) {
            soldiers.add(s);
        }
    }

    public boolean removeSoldier(Soldier s) {
        return soldiers.remove(s);
    }

    public int getCount() {
        return soldiers.size();
    }

    public int getTotalAttackPower() {
        int sum = 0;
        Node<Soldier> current = soldiers.getHead();
        while (current != null) {
            sum += current.data.getAttackPower();
            current = current.next;
        }
        return sum;
    }

    public String getType() { return type; }

    public MyLinkedList<Soldier> getSoldiers() {
        return soldiers;
    }

    @Override
    public String toString() {
        return "Unit of " + type + " - " + getCount() + " soldiers (Total AP: " + getTotalAttackPower() + ")";
    }
}
