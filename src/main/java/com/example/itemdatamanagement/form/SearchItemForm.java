package com.example.itemdatamanagement.form;

public class SearchItemForm {
    private String name;
    private String parentCategory;
    private String childCategory;
    private String grandChild;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "SearchItemFoerm [name=" + name + ", parentCategory=" + parentCategory + ", childCategory="
                + childCategory + ", grandChild=" + grandChild + ", brand=" + brand + "]";
    }

}
