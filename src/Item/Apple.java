package Item;

public class Apple implements Buyable{

    private final int price = 5;

    @Override
    public int buy(int playerMoney) {
        if (playerMoney >= this.price) {
            System.out.println("BOUGHT!!!");
            return (playerMoney - this.price);
        }
        return playerMoney;
    }

    @Override
    public void addToPlayerSlot() {

    }
}
