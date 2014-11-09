package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rhea on 02/11/2014.
 */
public class StorePopup extends Dialog {

        Context context;

    public StorePopup(final Context c, final Item currItem) {
        super(c);

        this.context = c;

        //Set custom dialog information
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.store_popup);

        //get current screen's height and width
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;

        //Set grid_layout window
        getWindow().setLayout((int)( w/100)*75, (int)( h/100)*75);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(true);

        //Set image to whatever is clicked on
        ImageView image = (ImageView) findViewById(R.id.largeView);
        image.setBackgroundResource(currItem.getImage());

        //Set title to correct
        TextView title =  (TextView) findViewById(R.id.popupTitle);
        title.setText(currItem.getName());

        //change description to item's special description
        TextView description =  (TextView) findViewById(R.id.descriptionText);
        description.setText(currItem.getText());

        //change description to item's special description
        TextView cost =  (TextView) findViewById(R.id.price);
        cost.setText( "Cost: " + currItem.getValue() );

        Button buyBttn = (Button) findViewById(R.id.buyButton);
        buyBttn.setText("Purchase " + currItem.getName());

        buyBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build a player object
                DatabaseHelper db = DatabaseHelper.getInstance( v.getContext() );
                Player player = new Player( "TEMP" );
                db.buildPlayer( player );

                //Check if the player has enough money
                if ( player.getMoney() >= currItem.getValue() ) {
                    db.saveItemData( currItem );
                    player.removeMoney( currItem.getValue() );
                    db.savePlayerData( player );

                    Toast.makeText( v.getContext(), "Item purchased!", Toast.LENGTH_SHORT ).show();

                    // Close the popup
                    dismiss();
                } else {
                    Toast.makeText( v.getContext(), "Not enough money!", Toast.LENGTH_SHORT ).show();
                }
            }

        });

        //Show dialog
        show();

        }
}
