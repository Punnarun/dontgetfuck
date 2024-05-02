package Player;

import GameInstance.Facing;

public abstract class BasePlayer {

    private int hp;
    private int atk;
    private int diceValue;
    private int moveLeft;
    private int currentX;
    private int currentY;
    private Facing playerFacing;
    private int money;

//    public BasePlayer() {
//        this.diceValue = -1;
//        this.currentX = 0;
//        this.currentY = 0;
//        this.money = 0;
//        this.moveLeft = 0;
//    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public BasePlayer(int hp, int atk) {
        this.hp = hp;
        this.atk = atk;
    }

    public Facing getPlayerFacing() {
        return playerFacing;
    }

    public void setPlayerFacing(Facing playerFacing) {
        this.playerFacing = playerFacing;
    }

    public int getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public abstract void usePassiveSkill();
}
