package Player;

public class theif extends basePlayer{

    public theif() {
        super( 3, 1);
        this.setMoney(1000);
    }

    @Override
    public void usePassiveSkill() {
        // Warrior-specific passive skill behavior
        // Warrior will increase the damage by 1 every 10 turn
        super.setMoney(this.getMoney()*2);
    }
}
