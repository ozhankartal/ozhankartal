package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 11.12.2016.
 */

public class EgitimSetModel {

    private String image;
    private String name;
    private String ucret;
    private String yazar;
    private int id;

    public EgitimSetModel(String image, String name, String ucret, int id, String yazar) {
        this.image = image;
        this.name = name;
        this.ucret = ucret;
        this.yazar = yazar;
        this.id = id;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getUcret() {
        return ucret;
    }

    public void setUcret(String ucret) {
        this.ucret = ucret;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
