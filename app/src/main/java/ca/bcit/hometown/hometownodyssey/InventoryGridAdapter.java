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

        private Context context;
        //list of image resource ID's for population of the grid
        private ArrayList<Integer> imageIds = new ArrayList<Integer>();
        int numImages = 0;


        public InventoryGridAdapter(Context c, String section) {
            context = c;
            buildImageList(section);
        }

        @Override
        public int getCount() {
            return imageIds.size();
        }

        @Override
        public Object getItem(int position) {
            return imageIds.get(position);
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

            iview.setImageResource(imageIds.get(position));

            tv.setText("Pumpkin");

            return llview;
        }



        //TODO: BUILD THIS ARRAY LIST DYNAMICALLY FROM DATABASE
        public void buildImageList(String section)
        {
            if (section == "head")
            {
                    imageIds.add(numImages, R.drawable.ho_icon_jackolantern );
                    imageIds.add(numImages, R.drawable.ho_icon_jackolantern );
                    imageIds.add(numImages, R.drawable.ho_icon_jackolantern );
                    imageIds.add(numImages, R.drawable.ho_icon_jackolantern );
                    imageIds.add(numImages, R.drawable.ho_icon_jackolantern );
                    numImages += 5;

            }

        }

        public ArrayList<Integer> getImageList()
        {
            return imageIds;
        }
}
