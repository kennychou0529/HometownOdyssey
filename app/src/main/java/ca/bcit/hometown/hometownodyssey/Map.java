package ca.bcit.hometown.hometownodyssey;

import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;


public class Map extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks,
                                             GooglePlayServicesClient.OnConnectionFailedListener, AdventureFragment.OnFragmentInteractionListener {

    LocationClient locationClient;
    Location currentLocation;
    GPSTracker gps;
    private Player player = new Player("TEMP");
    private MapSettings mapSettings;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button homeBtn = (Button) findViewById( R.id.homeBtn );
        Typeface titleTypeface = Typeface.createFromAsset( getAssets(), "header.ttf" );
        homeBtn.setTypeface( titleTypeface );

        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DATE);

        //Create GPS object
        gps = new GPSTracker(this, Map.this);

        //Check if GPS is up and running
        if(gps.canGetLocation()) {

            //Create the maps object and initialize it
            GoogleMap map = ((MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            map.setMyLocationEnabled(true);

            // Create a new database helper
            db = DatabaseHelper.getInstance(this);

            // Create the Player object
            db.buildPlayer(player);

            if (player.getMap() == null) {
                player.setMap(map);
            }

            player.setPos(new LatLng(gps.getLatitude(), gps.getLongitude()));
            player.updateMapPosition();

            // Create the MapSettings object
            mapSettings = new MapSettings(player, this);
            if (mapSettings.getMap() == null) {
                mapSettings.setMap(map);
            }

            // Set the first-time home location
            if (player.getHome().latitude == 0) {
                player.setHome( player.getPos() );

                // Set the player's home
                mapSettings.setHome(player.getPos());
            }

            Log.d("Player date: " ,  "" + player.getDate());
            Log.d("date: " ,  "" + date);

             if (player.getDate() != date)
             {
                 db.deleteAllTreasures();
                 player.setDate(date);
                 Log.d("Player date: " ,  "" + player.getDate());
                 db.savePlayerData(player);

                 mapSettings.createTreasure(2000, 0);
                 mapSettings.createTreasure(2000, 0);
                 mapSettings.createTreasure(2000, 0);
                 mapSettings.createTreasure(2000, 0);
                 mapSettings.createTreasure(2000, 0);
             }
            else
             {
                 // Retrieve all existing treasures
                 ArrayList<Treasure> tList = new ArrayList<Treasure>();
                 db.buildTreasureList(tList);


                 if (tList.size() > 0) {
                     // Add the existing treasures to the MapSettings object
                     for (Treasure t: tList) {
                         mapSettings.addTreasure(t);
                     }
                 }
             }


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

    public Player getPlayer() {
        db.buildPlayer( player );
        return player;
    };
    public MapSettings getMapSettings() { return mapSettings; };

    public void saveTreasure(Treasure t) {
        db.saveTreasureData(t);
    }

    public void setHome( View view ) {
        // Set the player's home
        player.setHome( player.getPos() );
        mapSettings.setHome(player.getPos());

        Toast.makeText( this, "Home set!", Toast.LENGTH_SHORT ).show();

    }

    public void onFragmentInteraction(Uri uri) {
        // Does nothing currently
    }
}