package ca.bcit.hometown.hometownodyssey;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Chris on 2014-10-04.
 */
public class Treasure {
    private MarkerOptions tPin;
    private Marker treasure;
    private CircleOptions aura;
    private Circle aCircle;
    private int date;
    /** 0 = money, 1 = item, 2 = special item */
    private int type;

    public Treasure(LatLng pos, int t) {
        type = t;
        date = 0;

        // Create the MarkerOptions object
        tPin = new MarkerOptions();
        tPin.position(pos);
        tPin.title("Treasure");
        tPin.anchor(0.5f, 0.5f);
        tPin.icon(BitmapDescriptorFactory.fromResource(R.drawable.chest));

        // Create the aura
        // TODO: Figure out wtf to do with radius
        aura = new CircleOptions().center(pos).radius(15);
    }

    public void setTreasure(Marker t) {
        treasure = t;
    }

    public void setDate ( int d) { date = d; }

    public Marker getTreasure() {
        return treasure;
    }

    public void setCircle(Circle c) {
        aCircle = c;
    }

    public Circle getCircle() {
        return aCircle;
    }

    public MarkerOptions getPin() {
        return tPin;
    }

    public CircleOptions getAura() {
        return aura;
    }

    public int getType() {
        return type;
    }

    public int getDate() { return date; }
}
