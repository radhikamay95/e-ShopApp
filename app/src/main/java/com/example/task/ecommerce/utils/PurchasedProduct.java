package com.example.task.ecommerce.utils;

public class PurchasedProduct {
    private String purchasedName;
    private String purchasedPrice;
    private String purchasedImage;
    private String purchasedID;
    private String purchasedItemCount;

    public PurchasedProduct(String purchaseID,String purchasedName, String purchasedPrice, String purchasedImage, String purchasedItemCount) {
        this.purchasedName = purchasedName;
        this.purchasedPrice = purchasedPrice;
        this.purchasedImage = purchasedImage;
        this.purchasedID = purchaseID;
        this.purchasedItemCount = purchasedItemCount;
    }

    public String getPurchasedName() {
        return purchasedName;
    }

    public String getPurchasedPrice() {
        return purchasedPrice;
    }

    public String getPurchasedImage() {
        return purchasedImage;
    }

    public String getPurchasedID() {
        return purchasedID;
    }

    public void setPurchasedName(String purchasedName) {
        this.purchasedName = purchasedName;
    }

    public void setPurchasedPrice(String purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public void setPurchasedImage(String purchasedImage) {
        this.purchasedImage = purchasedImage;
    }

    public void setPurchasedID(String  purchasedID) {
        this.purchasedID = purchasedID;
    }

    public String getPurchasedItemCount() {
        return purchasedItemCount;
    }

    public void setPurchasedItemCount(String purchasedItemCount) {
        this.purchasedItemCount = purchasedItemCount;
    }
}
