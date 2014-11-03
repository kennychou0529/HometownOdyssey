package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rhea on 02/11/2014.
 */
public class InventoryGridAdapter extends BaseAdapter{
        private DatabaseHelper db;
        private Context context;
        //list of image resource ID's for population of the grid
        private ArrayList<Item> items = new ArrayList<Item>();
        int numItems = 0;


        public InventoryGridAdapter(Context c, String section) {
            db = DatabaseHelper.getInstance(c);
            context = c;

            buildImageList(section);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View llview = view;
            ImageView iview;
            TextView tv;

            if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                llview = inflater.inflate(R.layout.grid_layout, parent, false);

            } else {
                llview = view;
            }

            //Get the image and text view for the square
            iview = (ImageView) llview.findViewById(R.id.gridImage);
            tv = (TextView) llview.findViewById(R.id.gridText);

            //Get Device sizes
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;

            //Apply the images and layout constraints to the imageView
            iview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height/3));
            iview.setScaleType(ImageView.ScaleType.FIT_CENTER);

            iview.setImageResource( items.get(position).getImage() );

            tv.setText( items.get( position ).getName() );

            return llview;
        }



        //TODO: BUILD THIS ARRAY LIST DYNAMICALLY FROM DATABASE
        public void buildImageList(String section)
        {
            if (section.equals( "head" ) ) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 0 );

                for ( Item i : iList ) {
                    items.add( numItems, i );
                    numItems++;
                }
            } else if ( section.equals( "body" ) ) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 1 );

                for ( Item i : iList ) {
                    items.add( numItems, i );
                    numItems++;
                }
            } else if ( section.equals( "leg" ) ) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 2 );

                for ( Item i : iList ) {
                    items.add( numItems, i );
                    numItems++;
                }
            } else if ( section.equals( "foot" ) ) {
                ArrayList<Item> iList = new ArrayList<Item>();
                db.buildInventorySection( iList, 3 );

                for ( Item i : iList ) {
                    items.add( numItems, i );
                    numItems++;
                }
            }

        }

        public ArrayList<Item> getImageList()
        {
            return items;
        }
}
