package com.example.itemdatamanagement.form;

import org.springframework.web.multipart.MultipartFile;

public class UpdateItemForm {
    private Integer id;
    private String name;
    private double price;
    private String parentCategory;
    private String childCategory;
    private String grandChild;
    private String brand;
    private Integer condition;
    private String description;
    private MultipartFile image;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UpdateItemForm [id=" + id + ", name=" + name + ", price=" + price + ", parentCategory=" + parentCategory
                + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", brand=" + brand
                + ", condition=" + condition + ", description=" + description + ", image=" + image + "]";
    }

}
