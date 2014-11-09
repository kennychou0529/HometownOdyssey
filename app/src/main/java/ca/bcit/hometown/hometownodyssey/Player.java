package ca.bcit.hometown.hometownodyssey;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Chris on 2014-09-25.
 *
 * The main Player class that holds all player data.
 */
public class Player implements Serializable {
    private GoogleMap map;
    private LatLng pos;
    private String name;
    private int money;
    private int level;
    private int date;
    private String headItem;
    private String bodyItem;
    private String legItem;
    private String footItem;

    /**
     * Default Player constructor
     *
     * @param n the player's name
     */
    public Player(String n) {
        name = n;
        money = 0;
        level = 1;
        headItem = "";
        bodyItem = "";
        legItem = "";
        footItem = "";
    }

    public void updateMapPosition() {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 13));
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
     * Sets the player's head item
     *
     * @param i the item to be set
     */
    public void setHeadItem(String i) {
        headItem = i;
    }

    /**
     * Sets the player's body item
     *
     * @param i the item to be set
     */
    public void setBodyItem(String i) {
        bodyItem = i;
    }

    /**
     * Sets the player's leg item
     *
     * @param i the item to be set
     */
    public void setLegItem(String i) {
        legItem = i;
    }

    /**
     * Sets the player's foot item
     *
     * @param i the item to be set
     */
    public void setFootItem(String i) {
        footItem = i;
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
     * Adds to the player's money count
     *
     * @param m the money count
     */
    public void addMoney(int m) {
        money += m;
    }

    /**
     * Subtracts from the player's money count
     *
     * @param m the money count
     */
    public void removeMoney(int m) {
        money -= m;
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
     * Sets the player's position
     *
     * @param p the position
     */
    public void setPos(LatLng p) {
        pos = p;
    }

    public void setMap(GoogleMap m) {
        map = m;
    }


    public void setDate(int d) { date = d; }

    /**
     * Returns the player's name
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * Returns the player's head item
     */
    public String getHeadItem() {
        return headItem;
    }

    /**
     * Returns the player's body item
     */
    public String getBodyItem() {
        return bodyItem;
    }

    /**
     * Returns the player's leg item
     */
    public String getLegItem() {
        return legItem;
    }

    /**
     * Returns the player's foot item
     */
    public String getFootItem() {
        return footItem;
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

    /**
     * Returns the player's position
     *
     * @return the player's position
     */
    public LatLng getPos() {
        return pos;
    }

    public GoogleMap getMap() {
        return map;
    }

    /**
     * Returns the player's latitude
     *
     * @return the player's latitude
     */
    public double getLatitude() {
        return pos.latitude;
    }

    /**
     * Returns the player's longtitude
     *
     * @return the player's longtitude
     */
    public double getLongitude() {
        return pos.longitude;
    }

    public int getDate() { return date; }
}
