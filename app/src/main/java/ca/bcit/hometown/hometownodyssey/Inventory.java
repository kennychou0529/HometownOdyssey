package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;


public class Inventory extends Activity implements AdventureFragment.OnFragmentInteractionListener {
    private static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Create a new database helper
        db = DatabaseHelper.getInstance(this);

        //Pull equipped items from person class and change the source
        ImageButton headButton = (ImageButton) findViewById(R.id.headInv);
        ImageButton bodyButton = (ImageButton) findViewById(R.id.bodyInv);
        ImageButton legButton = (ImageButton) findViewById(R.id.legInv);
        ImageButton footButton = (ImageButton) findViewById(R.id.footInv);

        // Retrieve the player object from the database
        Player player = new Player( "TEMP" );
        db.buildPlayer( player );

        // Construct the current equipped items
        Item headItem = new Item();
        Item bodyItem = new Item();
        Item legItem = new Item();
        Item footItem = new Item();

        if ( player.getHeadItem() == "" ) {
            headButton.setImageResource( R.drawable.temphead );
        } else {
            db.buildItem( headItem, player.getHeadItem() );
            headButton.setImageResource(headItem.getImage());
        }

        if ( player.getBodyItem() == "" ) {
            bodyButton.setImageResource( R.drawable.temptop );
        } else {
            db.buildItem( bodyItem, player.getBodyItem() );
            bodyButton.setImageResource( bodyItem.getImage() );
        }

        if ( player.getLegItem() == "" ) {
            legButton.setImageResource( R.drawable.temppants );
        } else {
            db.buildItem( legItem, player.getLegItem() );
            legButton.setImageResource( legItem.getImage() );
        }

        if ( player.getFootItem() == "" ) {
            footButton.setImageResource( R.drawable.tempshoes );
        } else {
            db.buildItem( footItem, player.getFootItem() );
            footButton.setImageResource( footItem.getImage() );
        }

        //Populate grid with head items by default
        GridView gridview = (GridView) findViewById(R.id.styleGridView);
        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "head");
        gridview.setAdapter(gridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 0 );

                Item tempItem = iList.get( position );

                //Create popup for each item
                ItemPopup popup = new ItemPopup( v.getContext(), tempItem );
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
        return super.onOptionsItemSelected(item);
    }

    public void loadHeadItems(View v)
    {
        GridView grid = (GridView) findViewById( R.id.styleGridView );

        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "head");
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 0 );

                Item tempItem = iList.get( position );

                //Create popup for each item
                ItemPopup popup = new ItemPopup( v.getContext(), tempItem );
            }
        });
    }

    public void loadBodyItems(View v)
    {
        GridView grid = (GridView) findViewById( R.id.styleGridView );

        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "body");
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 1 );

                Item tempItem = iList.get( position );

                //Create popup for each item
                ItemPopup popup = new ItemPopup( v.getContext(), tempItem );
            }
        });
    }

    public void loadLegItems(View v)
    {
        GridView grid = (GridView) findViewById( R.id.styleGridView );

        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "leg");
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 2 );

                Item tempItem = iList.get( position );

                //Create popup for each item
                ItemPopup popup = new ItemPopup( v.getContext(), tempItem );
            }
        });
    }

    public void loadFootItems(View v)
    {
        GridView grid = (GridView) findViewById( R.id.styleGridView );

        final InventoryGridAdapter gridAdapter = new InventoryGridAdapter(this, "foot");
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 3 );

                Item tempItem = iList.get( position );

                //Create popup for each item
                ItemPopup popup = new ItemPopup( v.getContext(), tempItem );
            }
        });
    }


    public void onFragmentInteraction(Uri uri) {
        // Does nothing currently
    }

}
