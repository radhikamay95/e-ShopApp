package com.example.task.ecommerce.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("zoom_thumb")
    @Expose
    private String zoomThumb;
    @SerializedName("options")
    @Expose
    private List<Object> options = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("special")
    @Expose
    private String special;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getSku() {
        return sku;
    }

    public String getImage() {
        return image;
    }

    public String getThumb() {
        return thumb;
    }

    public String getZoomThumb() {
        return zoomThumb;
    }

    public List<Object> getOptions() {
        return options;
    }

    public String getDescription() {
        return description;
    }

    public String getHref() {
        return href;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Object> getImages() {
        return images;
    }

    public String getPrice() {
        return price;
    }

    public String getSpecial() {
        return special;
    }
}
