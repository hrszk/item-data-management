package com.example.itemdatamanagement.form;

public class InsertItemForm {

    private String name;
    private double price;
    private String parentCategory;
    private String childCategory;
    private String grandChild;
    private String brand;
    private Integer condition;
    private Integer shipping;
    private String description;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InsertItemForm [name=" + name + ", price=" + price + ", parentCategory=" + parentCategory
                + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", brand=" + brand
                + ", condition=" + condition + ", shipping=" + shipping + ", description=" + description + "]";
    }

}
