package Game.model.Army;
import Game.util.MyLinkedList;
import Game.util.Node;

public class ArmyManager {
    private MyLinkedList<Soldier> allSoldiers;
    private MyLinkedList<SoldierUnit> defenders;
    private MyLinkedList<SoldierUnit> attackers;

    public ArmyManager() {
        allSoldiers = new MyLinkedList<>();
        defenders = new MyLinkedList<>();
        attackers = new MyLinkedList<>();
    }

    public void recruit(Soldier s) {
        allSoldiers.add(s);
        SoldierUnit unit = findOrCreateUnit(defenders, s.getType());
        unit.addSoldier(s);
    }

    public boolean sendToAttack(Soldier s) {
        // از لیست مدافع حذف
        SoldierUnit defUnit = findUnit(defenders, s.getType());
        if (defUnit == null || !defUnit.removeSoldier(s))
            return false;

        // به لیست مهاجم اضافه
        SoldierUnit atkUnit = findOrCreateUnit(attackers, s.getType());
        atkUnit.addSoldier(s);
        return true;
    }

    public void returnFromBattle() {
        Node<SoldierUnit> current = attackers.getHead();
        while (current != null) {
            Node<Soldier> sNode = current.data.getSoldiers().getHead();
            while (sNode != null) {
                Soldier s = sNode.data;
                SoldierUnit def = findOrCreateUnit(defenders, s.getType());
                def.addSoldier(s);
                sNode = sNode.next;
            }
            current = current.next;
        }
        attackers.clear();
    }

    public void printDefenders() {
        System.out.println("Defending Units:");
        defenders.printAll();
    }

    public void printAttackers() {
        System.out.println("Attacking Units:");
        attackers.printAll();
    }

    private SoldierUnit findUnit(MyLinkedList<SoldierUnit> list, String type) {
        Node<SoldierUnit> current = list.getHead();
        while (current != null) {
            if (current.data.getType().equals(type))
                return current.data;
            current = current.next;
        }
        return null;
    }

    private SoldierUnit findOrCreateUnit(MyLinkedList<SoldierUnit> list, String type) {
        SoldierUnit unit = findUnit(list, type);
        if (unit == null) {
            unit = new SoldierUnit(type);
            list.add(unit);
        }
        return unit;
    }

}
