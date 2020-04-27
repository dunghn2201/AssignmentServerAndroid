package com.dunghn2792.assignmentserverandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sneaker {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("nameSneaker")
    @Expose
    private String nameSneaker;

    @SerializedName("size")
    @Expose
    private Integer size;

    @SerializedName("material")
    @Expose
    private String material;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("image")
    @Expose
    private String image;

    public Sneaker() {
    }

    public Sneaker(String id, String nameSneaker, Integer size, String price, String color, String image) {
        this.id = id;
        this.nameSneaker = nameSneaker;
        this.size = size;
        this.price = price;
        this.color = color;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSneaker() {
        return nameSneaker;
    }

    public void setNameSneaker(String nameSneaker) {
        this.nameSneaker = nameSneaker;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}