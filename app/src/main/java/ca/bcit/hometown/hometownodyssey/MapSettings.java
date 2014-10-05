package ca.bcit.hometown.hometownodyssey;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

import static android.graphics.Color.HSVToColor;

/**
 * Created by Chris on 2014-09-25.
 *
 * This class handles the settings for the map screen.
 */
public class MapSettings {
    private Treasure[] treasures;
    private int numTreasures = 0;
    private LatLng home;
    private Player player;

    private GoogleMap map;
    private Random r = new Random();

    /** The minimum distance in metres to claim a treasure */
    public static final int MIN_TREASURE_DISTANCE = 50;

    public MapSettings(GoogleMap m, Player p) {
        map = m;
        player = p;
        treasures = new Treasure[10];
    }

    /**
     * Checks all treasures to see if they are close enough
     * to the player to be 'opened'.
     */
    public void getTreasure() {
        float[] results = new float[1];
        for (int i = 0; i < numTreasures; i++) {
            Location.distanceBetween(player.getLatitude(),
                    player.getLongitude(),
                    treasures[i].getPin().getPosition().latitude,
                    treasures[i].getPin().getPosition().longitude,
                    results);

            if (results[0] <= MIN_TREASURE_DISTANCE) {
                // Claim the treasure
                switch(treasures[i].getType()) {
                    case 0: // Money
                        player.addMoney(100);
                        break;
                    case 1: // Item
                        break;
                    case 2: // Special item
                        break;
                }

                // Remove the treasure
                removeTreasure(i);
            }
        }
    }

    /**
     * Creates a treasure within a certain radius from the player's home
     * @param metres max radius in metres
     * @param type treasure type
     */
    public void createTreasure(int metres, int type) {
        // Generate a random location around the player's home
        double lat, lon;
        double maxRad = metreToDegree(metres);

        lat = home.latitude + ( -maxRad + (maxRad * 2) * r.nextDouble());
        lon = home.longitude + ( -maxRad + (maxRad * 2) * r.nextDouble());

        LatLng pos = new LatLng(lat, lon);

        // Create the treasure
        treasures[numTreasures] = new Treasure(pos, type);

        // Place the treasure
        placeTreasure(numTreasures);
        numTreasures++;
    }

    /**
     * Creates a treasure within a certain radius of a given coordinate
     * @param p coordinate
     * @param metres max radius in metres
     * @param type treasure type
     */
    public void createTreasure(LatLng p, int metres, int type) {
        // Generate a random location around the coordinate
        double lat, lon;
        double maxRad = metreToDegree(metres);

        lat = p.latitude + ( -maxRad + (maxRad * 2) * r.nextDouble());
        lon = p.longitude + ( -maxRad + (maxRad * 2) * r.nextDouble());

        LatLng pos = new LatLng(lat, lon);

        // Create the treasure
        treasures[numTreasures] = new Treasure(pos, type);

        // Place the treasure
        placeTreasure(numTreasures);
        numTreasures++;
    }

    private double metreToDegree(int m) {
        return (double) m  / 100000;
    }

    public void placeTreasure(int i) {
        treasures[i].setTreasure(map.addMarker(treasures[i].getPin()));

        // Add the treasure's aura
        Circle c = map.addCircle(treasures[i].getAura());
        c.setStrokeColor(Color.YELLOW);
        c.setStrokeWidth(3);

        float hsv[] = { 45, 70, 93 };


        treasures[i].setCircle(c);
    }

    /**
     * Place all treasures in the array onto the map
     * NOTE: this is done when the app is loaded from
     * a saved state.
     */
    public void placeTreasures() {
        for (int i = 0; i < numTreasures; i++) {
            treasures[i].setTreasure(map.addMarker(treasures[i].getPin()));

            // Add the treasure's aura
            Circle c = map.addCircle(treasures[i].getAura());
            c.setStrokeColor(Color.YELLOW);
            c.setStrokeWidth(3);

            float hsv[] = { 45, 70, 93 };
            c.setFillColor(HSVToColor(60, hsv));

            treasures[i].setCircle(c);
        }
    }

    public void removeTreasure(int i) {
        treasures[i].getTreasure().remove();
        treasures[i].getCircle().remove();

        for (int j = i; j < numTreasures; j++) {
            treasures[j] = treasures[j + 1];
        }

        numTreasures--;
    }

    public void setHome(LatLng loc) {
        home = loc;
    }

    public LatLng getHome() {
        return home;
    }
}
