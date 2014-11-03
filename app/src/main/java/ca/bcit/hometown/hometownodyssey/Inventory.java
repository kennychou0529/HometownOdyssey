package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;


public class Inventory extends Activity {
    private static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Pull equipped items from person class and change the source
        ImageButton headButton = (ImageButton) findViewById(R.id.headInv);
        ImageButton bodyButton = (ImageButton) findViewById(R.id.bodyInv);
        ImageButton legButton = (ImageButton) findViewById(R.id.legInv);
        ImageButton footButton = (ImageButton) findViewById(R.id.footInv);

        //Change the source
        legButton.setImageResource(R.drawable.ho_icon_skeletonpants);

        // Create a new database helper
        db = DatabaseHelper.getInstance(this);

        //Populate grid with head items by default
        GridView gridview = (GridView) findViewById(R.id.styleGridView);
        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "head");
        gridview.setAdapter(gridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

               // Item currentItem =  db.getItemAt(pos, headSection);
                Item tempItem = new Item("Pumpkinhead", "Fuck you pumpkin head.", 0, 300, R.drawable.ho_icon_jackolantern);


                //Create popup for each item
                ItemPopup popup = new ItemPopup(v.getContext(), tempItem);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inventory, menu);
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

    public void loadHeadItems(View v)
    {
      //Empty linear layout
      //Pull all obtained head items from DB
      //Populate list

    }

    public void loadBodyItems(View v)
    {

    }

    public void loadLegItems(View v)
    {

    }

    public void loadFootItems(View v)
    {

    }

}
