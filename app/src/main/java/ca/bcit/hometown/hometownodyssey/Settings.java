package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import ca.bcit.hometown.hometownodyssey.AdventureFragment.OnFragmentInteractionListener;

public class Settings extends Activity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void clearData( View view ) {
        DatabaseHelper db = DatabaseHelper.getInstance(this);

        db.deleteAllTreasures();
        db.deleteInventory();

        Player player = new Player("TEMP");

        db.savePlayerData( player );

        FragmentManager fm = getFragmentManager();
        AdventureFragment ad = (AdventureFragment) fm.findFragmentById( R.id.adventure_fragment );
        ad.updateCharacter();

        Toast.makeText( this, "Data cleared!", Toast.LENGTH_SHORT ).show();
    }

    public void onFragmentInteraction(Uri uri) {
        // Does nothing currently
    }
}
