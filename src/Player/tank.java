package Player;

public class tank extends basePlayer{

    public tank() {
        super( 5, 1);
        this.setMoney(2);
    }

    @Override
    public void usePassiveSkill() {
        // Warrior-specific passive skill behavior
        // Tank will increase the HP by 1 every 5 turn
        if (this.getHp() < 5) {
            super.setHp(super.getHp() + 1);
        }

    }
}
