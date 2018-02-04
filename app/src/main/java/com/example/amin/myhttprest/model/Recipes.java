package com.example.amin.myhttprest.model;


import android.graphics.Bitmap;

public class Recipes {
    private int reciepe_Id;
    private String name;
    private String description;
    private double price;
    private String chef;
    private String thumbnail;
    private String timestamp;
    private Bitmap bitmap;

    public int getReciepe_Id() {
        return reciepe_Id;
    }

    public void setReciepe_Id(int reciepe_Id) {
        this.reciepe_Id = reciepe_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
