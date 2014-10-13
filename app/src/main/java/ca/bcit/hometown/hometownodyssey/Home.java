package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ca.bcit.hometown.hometownodyssey.AdventureFragment.OnFragmentInteractionListener;

public class Home extends Activity implements OnFragmentInteractionListener {

    private Player player = null;
    private MapSettings map = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create the player object
        player = new Player("Chris Klassen");
        // Create the map settings object
        map = new MapSettings(player);

        DatabaseHelper db = new DatabaseHelper(this);

        db.savePlayerData(player);
        //db.addValue("treasure2", "meepblah");
        //db.addValue("treasure3", "meepmeep");
        //db.addValue("treasure4", "meepers");


        // Reading all values
        Log.d("Reading: ", "Reading all values..");
        List<String> values = db.getAllValues();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts the Map activity
     *
     * @param view the initiating view
     */
    public void startActivityMap(View view) {
        Intent intent = new Intent(this, Map.class);

        startActivity(intent);

        return;
    }

    /**
     * Starts the Inventory activity
     *
     * @param view the initiating view
     */
    public void startActivityInventory(View view) {
        Intent intent = new Intent(this, Inventory.class);

        startActivity(intent);

        return;
    }

    /**
     * Starts the Store activity
     *
     * @param view the initiating view
     */
    public void startActivityShop(View view) {
        Intent intent = new Intent(this, Store.class);

        startActivity(intent);

        return;
    }

    /**
     * Starts the Map activity
     *
     * @param view the initiating view
     */
    public void startActivitySettings(View view) {
        Intent intent = new Intent(this, Settings.class);

        startActivity(intent);

        return;
    }

    public void onFragmentInteraction(Uri uri) {
        // Does nothing currently
    }
}
