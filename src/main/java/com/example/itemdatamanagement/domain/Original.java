package com.example.itemdatamanagement.domain;

public class Original {
    private Integer trainId;
    private String name;
    private Integer itemConditionId;
    private String categoryName;
    private String brandName;
    private double price;
    private Integer shopping;
    private String itemDescription;

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemConditionId() {
        return itemConditionId;
    }

    public void setItemConditionId(Integer itemConditionId) {
        this.itemConditionId = itemConditionId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getShopping() {
        return shopping;
    }

    public void setShopping(Integer shopping) {
        this.shopping = shopping;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Original [trainId=" + trainId + ", name=" + name + ", itemConditionId=" + itemConditionId
                + ", categoryName=" + categoryName + ", brandName=" + brandName + ", price=" + price + ", shopping="
                + shopping + ", itemDescription=" + itemDescription + "]";
    }

}
