package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Inventory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Pull equipped items from person class and change the source
        ImageButton headButton = (ImageButton) findViewById(R.id.headInv);
        ImageButton bodyButton = (ImageButton) findViewById(R.id.bodyInv);
        ImageButton legButton = (ImageButton) findViewById(R.id.legInv);
        ImageButton footButton = (ImageButton) findViewById(R.id.footInv);

        //Change teh source
        legButton.setImageResource(R.drawable.ho_icon_skeletonpants);

        //Load head items by default into the listview
        //Probably get an array list of drawable id's from database?
        ImageView image = new ImageView(this);
        image.setBackgroundResource(R.drawable.ho_icon_jackolantern);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        ImageView image2 = new ImageView(this);
        image2.setBackgroundResource(R.drawable.ho_icon_sandal);
        image2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        ImageView image3 = new ImageView(this);
        image3.setBackgroundResource(R.drawable.ho_icon_skeletonpants);
        image3.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        ImageView image4 = new ImageView(this);
        image4.setBackgroundResource(R.drawable.ho_icon_jackolantern);
        image4.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        LinearLayout ll = (LinearLayout) findViewById(R.id.scrollingInv);
        ll.addView(image);
        ll.addView(image2);
        ll.addView(image3);
        ll.addView(image4);

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
