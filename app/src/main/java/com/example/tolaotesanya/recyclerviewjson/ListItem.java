package com.example.tolaotesanya.recyclerviewjson;

/**
 * Created by tolaotesanya on 27/12/2018.
 */

public class ListItem {

    private String imageURL;
    private String name;
    private String desc;

    public ListItem(String imageURL, String name, String desc) {
        this.imageURL = imageURL;
        this.name = name;
        this.desc = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
