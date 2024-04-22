package Player;

public class warrior extends basePlayer {

    public warrior() {
        super( 4, 2);
    }

    @Override
    public void usePassiveSkill() {
        // Warrior-specific passive skill behavior
        // Warrior will increase the damage by 1 every 5 turn
        super.setAtk(super.getAtk() + 1);
    }
}
