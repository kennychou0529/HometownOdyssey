package ca.bcit.hometown.hometownodyssey;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class Map extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks,
                                             GooglePlayServicesClient.OnConnectionFailedListener {

    LocationClient locationClient;
    Location currentLocation;
    GPSTracker gps;
    private Player player;
    private MapSettings mapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Create GPS object
        gps = new GPSTracker(this, Map.this);

        //Check if GPS is up and running
        if(gps.canGetLocation()) {

            //Create the maps object and initialize it
            GoogleMap map = ((MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            map.setMyLocationEnabled(true);

            // Create the Player object
            player = new Player("Chris");
            player.setMap(map);
            player.setPos(new LatLng(gps.getLatitude(), gps.getLongitude()));
            player.updateMapPosition();

            // Create the MapSettings object
            mapSettings = new MapSettings(map, player);

            // Set the player's home
            mapSettings.setHome(player.getPos());

            mapSettings.createTreasure(2000, 0);
            mapSettings.createTreasure(2000, 0);
            mapSettings.createTreasure(2000, 0);

        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    /*
      * Called by Location Services when the request to connect the
      * client finishes successfully. At this point, you can
      * request the current location or start periodic updates
      */
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }
    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();

        locationClient.disconnect();

    }
    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */

        }

    public Player getPlayer() { return player; };
    public MapSettings getMapSettings() { return mapSettings; };
}