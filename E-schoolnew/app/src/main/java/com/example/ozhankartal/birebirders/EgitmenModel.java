package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 13.12.2016.
 */

public class EgitmenModel {

    private String image;
    private String name;
    private int id;

    public EgitmenModel(String image, String name, int id) {
        this.image = image;
        this.name = name;
        this.id = id;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

