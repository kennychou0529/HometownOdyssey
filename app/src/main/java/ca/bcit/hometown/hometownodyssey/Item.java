package ca.bcit.hometown.hometownodyssey;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Chris on 2014-10-14.
 */
public class Item {
    private int id;
    private int type;
    private String name;
    private String text;
    private int imageID;
    private int value;

    public Item() {
        name = "";
        text = "";
        value = 0;
        type = 0;
        imageID = 0;
    }

    public Item(String n, String t, int ty, int v, int img) {
        name = n;
        text = t;
        value = v;
        type = ty;
        imageID = img;
    }

    public void setId(int i) {
        id = i;
    }

    public void setType(int t) {
        type = t;
    }

    public void setName(String s) {
        name = s;
    }

    public void setText(String t) {
       text = t;
    }

    public void setImage(int id) {
        imageID = id;
    }

    public void setValue(int v) {
        value = v;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return imageID;
    }

    public int getValue() {
        return value;
    }

    public static int getHeadImage( Context c ) {
        // Build the player
        DatabaseHelper db = DatabaseHelper.getInstance( c );
        Player p = new Player( "TEMP" );

        if ( !db.playerExists() ) {
            return c.getResources().getIdentifier("ho_f_char_head", "drawable", c.getPackageName());
        } else {
            db.buildPlayer(p);

            if (p.getHeadItem().equalsIgnoreCase("Jack-o-lantern")) {
                return c.getResources().getIdentifier("ho_f_char_jackolantern", "drawable", c.getPackageName());
            } else if ( p.getHeadItem().equalsIgnoreCase("Clown Nose")) {
                return c.getResources().getIdentifier("ho_f_char_clownnose", "drawable", c.getPackageName());
            } else {
                return c.getResources().getIdentifier("ho_f_char_head", "drawable", c.getPackageName());
            }
        }
    }

    public static ArrayList<Integer> getBodyImages( Context c ) {
        // Build the player
        DatabaseHelper db = DatabaseHelper.getInstance( c );
        Player p = new Player( "TEMP" );

        // Create the ArrayList
        ArrayList<Integer> imageList = new ArrayList<Integer>();

        if ( !db.playerExists() ) {
            imageList.add(c.getResources().getIdentifier("ho_f_char_torso", "drawable", c.getPackageName()));
            imageList.add(c.getResources().getIdentifier("ho_f_char_arm", "drawable", c.getPackageName()));
            imageList.add(c.getResources().getIdentifier("ho_f_char_arm2", "drawable", c.getPackageName()));
        } else {
            db.buildPlayer(p);

            if (p.getBodyItem().equalsIgnoreCase("V-neck")) {
                imageList.add(c.getResources().getIdentifier("ho_f_char_vneck_torso", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_vneck_arm", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_vneck_arm2", "drawable", c.getPackageName()));
            } else {
                imageList.add(c.getResources().getIdentifier("ho_f_char_torso", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_arm", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_arm2", "drawable", c.getPackageName()));
            }
        }

        return imageList;
    }

    public static ArrayList<Integer> getLegImages( Context c ) {
        // Build the player
        DatabaseHelper db = DatabaseHelper.getInstance( c );
        Player p = new Player( "TEMP" );

        // Create the ArrayList
        ArrayList<Integer> imageList = new ArrayList<Integer>();

        if ( !db.playerExists() ) {
            imageList.add(c.getResources().getIdentifier("ho_f_char_leg", "drawable", c.getPackageName()));
            imageList.add(c.getResources().getIdentifier("ho_f_char_leg2", "drawable", c.getPackageName()));
        } else {
            db.buildPlayer(p);

            if (p.getLegItem().equalsIgnoreCase("Skeleton Pants")) {
                imageList.add(c.getResources().getIdentifier("ho_f_char_skeletonpants_leg", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_skeletonpants_leg2", "drawable", c.getPackageName()));
            } else {
                imageList.add(c.getResources().getIdentifier("ho_f_char_leg", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_leg2", "drawable", c.getPackageName()));
            }
        }

        return imageList;
    }

    public static ArrayList<Integer> getFootImages( Context c ) {
        // Build the player
        DatabaseHelper db = DatabaseHelper.getInstance( c );
        Player p = new Player( "TEMP" );

        // Create the ArrayList
        ArrayList<Integer> imageList = new ArrayList<Integer>();

        if ( !db.playerExists() ) {
            imageList.add(c.getResources().getIdentifier("ho_f_char_foot", "drawable", c.getPackageName()));
            imageList.add(c.getResources().getIdentifier("ho_f_char_foot2", "drawable", c.getPackageName()));
        } else {
            db.buildPlayer(p);

            if (p.getFootItem().equalsIgnoreCase("Sandals")) {
                imageList.add(c.getResources().getIdentifier("ho_f_char_sandals_foot", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_sandals_foot2", "drawable", c.getPackageName()));
            } else {
                imageList.add(c.getResources().getIdentifier("ho_f_char_foot", "drawable", c.getPackageName()));
                imageList.add(c.getResources().getIdentifier("ho_f_char_foot2", "drawable", c.getPackageName()));
            }
        }

        return imageList;
    }
}
