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
    private GoogleMap map;
    private Random r = new Random();

    public MapSettings(GoogleMap m) {
        map = m;
        treasures = new Treasure[10];
    }

    public void createTreasure(Player p, double maxRad, int type) {
        // Generate a random location around the Player
        // TODO: should generate around Home
        double lat, lon;
        lat = p.getLatitude() + 0.003;
        lon = p.getLongitude() + 0.003;

        LatLng pos = new LatLng(lat, lon);

        // Create the treasure
        treasures[numTreasures] = new Treasure(pos, type);

        // Place the treasure
        map.addMarker(treasures[numTreasures].getPin());

        numTreasures++;
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
}
