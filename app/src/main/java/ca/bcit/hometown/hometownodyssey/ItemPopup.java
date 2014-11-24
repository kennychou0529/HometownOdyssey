package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rhea on 02/11/2014.
 */
public class ItemPopup extends Dialog {

        Context context;
        public ItemPopup(final Context context, final Item currItem) {
            super(context);
            this.context = context;

            //Set custom dialog information
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.invo_popup);

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

            Typeface titleTypeface = Typeface.createFromAsset( context.getAssets(), "header.ttf" );
            title.setTypeface( titleTypeface );


            //change description to item's special description
            TextView description=  (TextView) findViewById(R.id.descriptionText);
            description.setText(currItem.getText());


            Button selectableBtn = (Button) findViewById(R.id.selectableButton);
            selectableBtn.setText("EQUIP THIS ITEM!");
            selectableBtn.setTypeface( titleTypeface );


            selectableBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DatabaseHelper db = DatabaseHelper.getInstance( context );
                    Player p = new Player( "TEMP" );
                    db.buildPlayer( p );

                    //Change characters equipped item to this
                    switch( currItem.getType() ) {
                        case 0:
                            ImageButton headButton = (ImageButton) ((Activity) context).findViewById( R.id.headInv );
                            headButton.setImageResource( currItem.getImage() );
                            p.setHeadItem( currItem.getName() );
                            break;
                        case 1:
                            ImageButton bodyButton = (ImageButton) ((Activity) context).findViewById( R.id.bodyInv );
                            bodyButton.setImageResource( currItem.getImage() );
                            p.setBodyItem(currItem.getName());
                            break;
                        case 2:
                            ImageButton legButton = (ImageButton) ((Activity) context).findViewById( R.id.legInv );
                            legButton.setImageResource( currItem.getImage() );
                            p.setLegItem(currItem.getName());
                            break;
                        case 3:
                            ImageButton footButton = (ImageButton) ((Activity) context).findViewById( R.id.footInv );
                            footButton.setImageResource( currItem.getImage() );
                            p.setFootItem(currItem.getName());
                            break;


                    }
                    dismiss();

                    FragmentManager fm = ((Activity) context).getFragmentManager();
                    AdventureFragment ad = (AdventureFragment) fm.findFragmentById( R.id.adventure_fragment );

                    db.savePlayerData( p );
                    ad.updateCharacter();
                }

            });

            //Show dialog
            show();

        }

}
