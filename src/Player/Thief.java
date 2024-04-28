package Player;

public class Thief extends BasePlayer {

    public Thief() {
        super( 3, 1);
        this.setMoney(500);
    }

    @Override
    public void usePassiveSkill() {
        //theif can double their coin every 10 turns
        super.setMoney(this.getMoney()*2);
    }
}
