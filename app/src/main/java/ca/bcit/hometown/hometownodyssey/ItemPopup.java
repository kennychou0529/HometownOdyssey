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

/**
 * Created by Rhea on 02/11/2014.
 */
public class ItemPopup extends Dialog {

        Context context;
        public ItemPopup(final Context context, Item currItem) {
            super(context);
            this.context = context;

            //Set custom dialog information
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.info_popup);

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
            TextView description=  (TextView) findViewById(R.id.descriptionText);
            description.setText(currItem.getText());


            Button selectableBtn = (Button) findViewById(R.id.selectableButton);
            selectableBtn.setText("EQUIP THIS ITEM!");
            selectableBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Change characters equipped item to this
                }

            });

            //Show dialog
            show();

        }

}