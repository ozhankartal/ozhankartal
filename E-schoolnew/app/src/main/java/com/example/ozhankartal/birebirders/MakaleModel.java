package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 10.12.2016.
 */

public class MakaleModel {
    private String image;
    private String name;
    private String price;
    private String yazar;
    private int id;

    public MakaleModel(String image, String name,String price,int id,String yazar){
        this.image=image;
        this.name=name;
        this.yazar=yazar;
        this.price=price;
        this.id=id;


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

