package ca.bcit.hometown.hometownodyssey;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    DatabaseHelper db;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        setContentView(R.layout.activity_store);

        // Initialize shop items
        initializeShopItems();

        //Head listview
        HorizontalListView listViewHead = (HorizontalListView) findViewById(R.id.headListView);
        listViewHead.setAdapter(headAdapter);

        listViewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Log.d("Clicked", "Item popup");

                Item tempItem = (Item) headAdapter.getItem(position);

                //Create popup for each item
                StorePopup popup = new StorePopup(v.getContext(), tempItem);
                popup.setOnDismissListener( new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        db.buildPlayer( player );
                        TextView gold = (TextView) findViewById( R.id.store_gold );
                        gold.setText( "Gold: " + player.getMoney() );
                    }
                });
            }
        });

        //Body Listview
        HorizontalListView listViewBody = (HorizontalListView) findViewById(R.id.bodyListView);
        listViewBody.setAdapter(bodyAdapter);

        listViewBody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Item tempItem = (Item) bodyAdapter.getItem(position);

                //Create popup for each item
                StorePopup popup = new StorePopup( v.getContext(), tempItem );
                popup.setOnDismissListener( new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        db.buildPlayer( player );
                        TextView gold = (TextView) findViewById( R.id.store_gold );
                        gold.setText( "Gold: " + player.getMoney() );
                    }
                });
            }
        });


        //Leg listview
        HorizontalListView listViewLeg = (HorizontalListView) findViewById(R.id.legListView);
        listViewLeg.setAdapter(legAdapter);

        listViewLeg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Item tempItem = (Item) legAdapter.getItem(position);

                //Create popup for each item
                StorePopup popup = new StorePopup( v.getContext(), tempItem );
                popup.setOnDismissListener( new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        db.buildPlayer( player );
                        TextView gold = (TextView) findViewById( R.id.store_gold );
                        gold.setText( "Gold: " + player.getMoney() );
                    }
                });
            }
        });


        //Foot listview
        HorizontalListView listViewFoot = (HorizontalListView) findViewById(R.id.footListView);
        listViewFoot.setAdapter(feetAdapter);

        listViewFoot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Item tempItem = (Item) feetAdapter.getItem(position);

                //Create popup for each item
                StorePopup popup = new StorePopup( v.getContext(), tempItem );
                popup.setOnDismissListener( new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        db.buildPlayer( player );
                        TextView gold = (TextView) findViewById( R.id.store_gold );
                        gold.setText( "Gold: " + player.getMoney() );
                    }
                });
            }
        });

        // Get the player's current gold
        db = DatabaseHelper.getInstance(this);
        player = new Player( "TEMP" );
        db.buildPlayer( player );

        TextView gold = (TextView) findViewById( R.id.store_gold );
        gold.setText( "Gold: " + player.getMoney() );
    }

    private BaseAdapter headAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return headItems.size();
        }

        @Override
        public Item  getItem(int position) {
            Item i = headItems.get(position);

            return i;
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
            shopItemImage.setImageResource(itemImage);

            return v;
        }
    };

    private BaseAdapter bodyAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return bodyItems.size();
        }

        @Override
        public Item  getItem(int position) {
            Item i = bodyItems.get(position);

            return i;
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
            shopItemImage.setImageResource(itemImage);

            return v;
        }
    };

    private BaseAdapter legAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return legItems.size();
        }

        @Override
        public Item getItem(int position) {
            Item i = legItems.get(position);

            return i;
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
            shopItemImage.setImageResource(itemImage);

            return v;
        }
    };

    private BaseAdapter feetAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return footItems.size();
        }

        @Override
        public Item  getItem(int position) {
            Item i = footItems.get(position);

            return i;
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
            shopItemImage.setImageResource(itemImage);

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
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 1, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 1, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 1, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));
        bodyItems.add(numBodyItems++, new Item("V-neck", "Everyone loves V-necks!", 1, 150,
                getApplicationContext().getResources().getIdentifier("ho_icon_vneck", "drawable", getPackageName())));


        // Leg Items
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 2, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 2, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 2, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));
        legItems.add(numLegItems++, new Item("Skeleton Pants", "You're looking a little thin.", 2, 200,
                getApplicationContext().getResources().getIdentifier("ho_icon_skeletonpants", "drawable", getPackageName())));


        // Foot Items
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 3, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 3, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 3, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
        footItems.add(numFootItems++, new Item("Sandals", "Actually, they're called thongs.", 3, 100,
                getApplicationContext().getResources().getIdentifier("ho_icon_sandal", "drawable", getPackageName())));
    }
}
