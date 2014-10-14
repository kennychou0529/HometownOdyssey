package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Store extends Activity {

    //TODO: REMOVE AND UNCOMMENT ARRAY OF ITEMS
    private static String[] dataObjects = new String[]{ "Text #1",
            "Text #2",
            "Text #3", "Text #4",   "Text #5",
            "Text #6", "Text #7",   "Text #8",
            "Text #9", "Text #10",   "Text #11",
            "Text #12", "Text #13",   "Text #14",
            "Text #15", "Text #16",   "Text #17",
            "Text #18", "Text #19",   "Text #20",
            "Text #21", "Text #22",   "Text #23",
            "Text #24", "Text #25",   "Text #26",
            "Text #27", "Text #28",   "Text #29",
            "Text #30", "Text #31","Blah #32" };

    /* private static Item[] shopItems;
    each item needs to have an image & name
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        setContentView(R.layout.activity_store);

        HorizontalListView listview = (HorizontalListView) findViewById(R.id.listView);
        listview.setAdapter(mAdapter);

    }

    private BaseAdapter mAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return dataObjects.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            TextView shopItemName = (TextView) v.findViewById(R.id.shopItemName);
            shopItemName.setText(dataObjects[position]);

            ImageView shopItemImage = (ImageView) v.findViewById(R.id.shopItemImage);

            //TODO: Change to ItemArray[position].getImage()
            shopItemImage.setBackgroundResource(R.drawable.testhat);

            return v;
        }

    };
}
