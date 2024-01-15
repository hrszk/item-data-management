package com.example.itemdatamanagement.domain;

public class Image {
    private Integer id;
    private Integer itemId;
    private String imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + ", itemId=" + itemId + ", imagePath=" + imagePath + "]";
    }

}
