package Player;

public class Thief extends BasePlayer {

    public Thief() {
        super( 3, 1);
        this.setMoney(5);
    }

    @Override
    public void usePassiveSkill() {
        //thief can double their coin every 10 turns
        super.setMoney(this.getMoney()*2);
    }
}
