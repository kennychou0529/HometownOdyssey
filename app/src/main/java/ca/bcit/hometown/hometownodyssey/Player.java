package ca.bcit.hometown.hometownodyssey;

/**
 * Created by Chris on 2014-09-25.
 *
 * The main Player class that holds all player data.
 */
public class Player {
    private String name;
    private int money;
    private int level;

    /**
     * Default Player constructor
     *
     * @param n the player's name
     */
    public Player(String n) {
        name = n;
        money = 0;
        level = 1;
    }

    /**
     * Sets the player's name
     *
     * @param n the name to be set
     */
    public void setPlayerName(String n) {
        name = n;
    }

    /**
     * Sets the player's money count
     *
     * @param m the money count
     */
    public void setMoney(int m) {
        money = m;
    }

    /**
     * Sets the player's level
     *
     * @param l the level
     */
    public void setLevel(int l) {
        level = l;
    }

    /**
     * Returns the player's name
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * Returns the player's money count
     *
     * @return money count
     */
    public int getMoney() {
        return money;
    }

    /**
     * Returns the player's level
     *
     * @return the player's level
     */
    public int getLevel() {
        return level;
    }
}
