package Player;

import GameInstance.Facing;

public abstract class BasePlayer {

    private int hp;
    private int atk;
    private int diceValue = -1;
    private int moveLeft = 0;
    private int currentX = 0;
    private int currentY = 0;
    private Facing playerFacing;
    private int money = 0;

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
