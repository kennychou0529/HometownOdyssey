package ca.bcit.hometown.hometownodyssey;

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
}
