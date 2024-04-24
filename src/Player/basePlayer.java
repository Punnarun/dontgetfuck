package Player;

import Controller.facing;

public abstract class basePlayer {

    private String name;
    private int hp;
    private int atk;
    private int diceValue = -1;
    private int move = 0;
    private int currentX = 0;
    private int currentY = 0;
    private facing playerFacing;
    private int money = 0;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public basePlayer(int hp, int atk) {
        this.hp = hp;
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public facing getPlayerFacing() {
        return playerFacing;
    }

    public void setPlayerFacing(facing playerFacing) {
        this.playerFacing = playerFacing;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setName(String name) {
        this.name = name;
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
