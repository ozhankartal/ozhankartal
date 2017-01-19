package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 13.12.2016.
 */

public class DuyuruModel {

    private String name;
    private String price;
    private String clock;
    private int id;

    public DuyuruModel(String name, String clock, String price, int id) {

        this.name = name;
        this.clock=clock;
        this.price = price;
        this.id = id;


    }

    public int getId() {
        return id;
    }



    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public void setId(int id) {
        this.id = id;
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
