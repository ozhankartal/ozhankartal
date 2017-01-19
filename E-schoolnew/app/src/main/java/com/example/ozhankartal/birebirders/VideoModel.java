package com.example.ozhankartal.birebirders;

public class VideoModel {

    private String name;
    private String price;
    private  String url;
    private String yazar;
    private int id;

    public VideoModel(String name,String url,String price,int id,String yazar){

        this.name=name;
        this.yazar=yazar;
        this.url=url;
        this.price=price;
        this.id=id;


    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
