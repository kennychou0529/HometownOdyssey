package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Store extends Activity {

    private ArrayList<Item> headItems = new ArrayList<Item>(1);
    private ArrayList<Item> bodyItems = new ArrayList<Item>(1);
    private ArrayList<Item> legItems = new ArrayList<Item>(1);
    private ArrayList<Item> footItems = new ArrayList<Item>(1);

    private int numHeadItems = 0;
    private int numBodyItems = 0;
    private int numLegItems = 0;
    private int numFootItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        setContentView(R.layout.activity_store);

        // Initialize shop items
        initializeShopItems();

        HorizontalListView listViewHead = (HorizontalListView) findViewById(R.id.headListView);
        listViewHead.setAdapter(headAdapter);

        HorizontalListView listViewBody = (HorizontalListView) findViewById(R.id.bodyListView);
        listViewBody.setAdapter(bodyAdapter);

        HorizontalListView listViewLeg = (HorizontalListView) findViewById(R.id.legListView);
        listViewLeg.setAdapter(legAdapter);

        HorizontalListView listViewFoot = (HorizontalListView) findViewById(R.id.footListView);
        listViewFoot.setAdapter(feetAdapter);
    }

    private BaseAdapter headAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return headItems.size();
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
            shopItemName.setText(headItems.get(position).getName());

            ImageView shopItemImage = (ImageView) v.findViewById(R.id.shopItemImage);

            int itemImage = headItems.get(position).getImage();
            shopItemImage.setBackgroundResource(itemImage);

            return v;
        }
    };

    private BaseAdapter bodyAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return bodyItems.size();
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
            shopItemName.setText(bodyItems.get(position).getName());

            ImageView shopItemImage = (ImageView) v.findViewById(R.id.shopItemImage);

            int itemImage = bodyItems.get(position).getImage();
            shopItemImage.setBackgroundResource(itemImage);

            return v;
        }
    };

    private BaseAdapter legAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return legItems.size();
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
            shopItemName.setText(legItems.get(position).getName());

            ImageView shopItemImage = (ImageView) v.findViewById(R.id.shopItemImage);

            int itemImage = legItems.get(position).getImage();
            shopItemImage.setBackgroundResource(itemImage);

            return v;
        }
    };

    private BaseAdapter feetAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return footItems.size();
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
            shopItemName.setText(footItems.get(position).getName());

            ImageView shopItemImage = (ImageView) v.findViewById(R.id.shopItemImage);

            int itemImage = footItems.get(position).getImage();
            shopItemImage.setBackgroundResource(itemImage);

            return v;
        }
    };



    public void initializeShopItems() {
        // Head Items
        headItems.add(numHeadItems++, new Item("Jack-o-lantern", "Spoopy.", 0, 300,
                getApplicationContext().getResources().getIdentifier("ho_icon_jackolantern", "drawable", getPackageName())));
        headItems.add(numHeadItems++, new Item("Jack-o-lantern", "Spoopy.", 0, 300,
                getApplicationContext().getResources().getIdentifier("ho_icon_jackolantern", "drawable", getPackageName())));
        headItems.add(numHeadItems++, new Item("Jack-o-lantern", "Spoopy.", 0, 300,
                getApplicationContext().getResources().getIdentifier("ho_icon_jackolantern", "drawable", getPackageName())));
        headItems.add(numHeadItems++, new Item("Jack-o-lantern", "Spoopy.", 0, 300,
                getApplicationContext().getResources().getIdentifier("ho_icon_jackolantern", "drawable", getPackageName())));

        // Body Items
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 0, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 0, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 0, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 0, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));


        // Leg Items
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 0, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 0, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 0, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 0, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));


        // Foot Items
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 0, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 0, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 0, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 0, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
    }
}
