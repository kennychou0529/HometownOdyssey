package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
