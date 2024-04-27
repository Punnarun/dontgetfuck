package Player;

public class Warrior extends BasePlayer {

    public Warrior() {
        super( 4, 2);
        this.setMoney(5);
    }

    @Override
    public void usePassiveSkill() {
        //warrior can increase their atk every 10 turns
        if (this.getAtk() < 5) {
            super.setAtk(super.getAtk() + 1);
        }

    }
}
