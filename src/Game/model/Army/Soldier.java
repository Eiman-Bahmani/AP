package Game.model.Army;

public abstract class Soldier {
    protected String type;
    protected int attackPower;
    protected int cost;
    protected int trainingTime;

    public Soldier (String type, int attackPower , int cost , int trainingTime) {
        this.type = type;
        this.attackPower=attackPower;
        this.cost=cost;
        this.trainingTime=trainingTime;
    }
    public String getType() {
        return this.type;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getCost() {
        return this.cost;
    }

    public int getTrainingTime() {
        return this.trainingTime;
    }

    @Override
    public String toString() {
        return this.type+" (AP : "+this.attackPower+", Cost : "+this.cost+", Training Time : "+this.trainingTime+")";
    }
}
