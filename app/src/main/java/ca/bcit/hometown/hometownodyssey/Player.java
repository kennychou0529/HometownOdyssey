package ca.bcit.hometown.hometownodyssey;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Chris on 2014-09-25.
 *
 * The main Player class that holds all player data.
 */
public class Player {
    private GoogleMap map;
    private LatLng pos;
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

    public void updateMapPosition() {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
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
     * Adds to the player's money count
     *
     * @param m the money count
     */
    public void addMoney(int m) {
        money += m;
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

    /**
     * Returns the player's position
     *
     * @return the player's position
     */
    public LatLng getPos() {
        return pos;
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
}
