package com.stu.lwj.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class GoodItem implements Serializable{
    private long id;

    private String title;
    private float oldPrice;
    private float price;
    private String introduction;

    private Bitmap photo;
    private String photoString;
    private int browseNum;

    private long solderId;
    private long buyerId;
    private boolean sold;

    public GoodItem(){

    }

    public GoodItem(String title, float oldPrice, float price, String introduction, String photoString){
        this.setTitle(title);
        this.setOldPrice(oldPrice);
        this.setPrice(price);
        this.setIntroduction(introduction);
        this.setPhotoString(photoString);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public long getSolderId() {
        return solderId;
    }

    public void setSolderId(long solderId) {
        this.solderId = solderId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gooditem {")
                .append("id=").append(id).append(", ")
                .append("title=").append(title).append(", ")
                .append("oldPrice=").append(oldPrice).append(", ")
                .append("price=").append(price).append(", ")
                .append("introduction=").append(introduction).append(", ")
                .append("photoString=").append(photoString).append(", ")
                .append("browseNum=").append(browseNum).append(", ")
                .append("solderId=").append(solderId).append(", ")
                .append("buyerId=").append(buyerId).append(", ")
                .append("sold=").append(sold).append("}");
        return sb.toString();
    }
}
