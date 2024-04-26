package Player;

public class warrior extends basePlayer {

    public warrior() {
        super( 4, 2);
        this.setMoney(5);
    }

    @Override
    public void usePassiveSkill() {
        // Warrior-specific passive skill behavior
        // Warrior will increase the damage by 1 every 10 turn
        if (this.getAtk() < 5) {
            super.setAtk(super.getAtk() + 1);
        }

    }
}
