package Player;

public class Tank extends BasePlayer {

    public Tank() {
        super( 5, 1);
        this.setMoney(5);
    }

    @Override
    public void usePassiveSkill() {
        //Tank can regen HP every 10 turns
        if (this.getHp() < 5) {
            super.setHp(super.getHp() + 1);
        }
    }
}
