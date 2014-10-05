package ca.bcit.hometown.hometownodyssey;

import android.graphics.Color;

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

    private GoogleMap map;
    private Random r = new Random();

    public MapSettings(GoogleMap m) {
        map = m;
        treasures = new Treasure[10];
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
        map.addMarker(treasures[i].getPin());

        // Add the treasure's aura
        Circle c = map.addCircle(treasures[i].getAura());
        c.setStrokeColor(Color.YELLOW);
        c.setStrokeWidth(3);

        float hsv[] = { 45, 70, 93 };
        c.setFillColor(HSVToColor(60, hsv));
    }

    /**
     * Place all treasures in the array onto the map
     * NOTE: this is done when the app is loaded from
     * a saved state.
     */
    public void placeTreasures() {
        for (int i = 0; i < numTreasures; i++) {
            map.addMarker(treasures[i].getPin());

            // Add the treasure's aura
            Circle c = map.addCircle(treasures[i].getAura());
            c.setStrokeColor(Color.YELLOW);
            c.setStrokeWidth(3);

            float hsv[] = { 45, 70, 93 };
            c.setFillColor(HSVToColor(60, hsv));
        }
    }

    public void setHome(LatLng loc) {
        home = loc;
    }

    public LatLng getHome() {
        return home;
    }
}
