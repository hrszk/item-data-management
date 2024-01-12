package com.example.itemdatamanagement.form;

public class UpdateItemForm {
    private String name;
    private double price;
    private String parentCategory;
    private String childCategory;
    private String grandChild;
    private String brandName;
    private Integer itemConditionId;
    private Integer shipping;
    private String itemDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(String childCategory) {
        this.childCategory = childCategory;
    }

    public String getGrandChild() {
        return grandChild;
    }

    public void setGrandChild(String grandChild) {
        this.grandChild = grandChild;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getItemConditionId() {
        return itemConditionId;
    }

    public void setItemConditionId(Integer itemConditionId) {
        this.itemConditionId = itemConditionId;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "UpdateItemForm [name=" + name + ", price=" + price + ", parentCategory=" + parentCategory
                + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", brandName=" + brandName
                + ", itemConditionId=" + itemConditionId + ", shipping=" + shipping + ", itemDescription="
                + itemDescription + "]";
    }

}
