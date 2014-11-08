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
}
