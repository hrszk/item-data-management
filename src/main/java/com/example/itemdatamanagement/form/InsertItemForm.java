package com.example.itemdatamanagement.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsertItemForm {

    @NotBlank(message = "error.not.empty")
    @Length(max = 100, message = "error.max")
    private String name;

    @NotNull(message = "error.not.empty")
    @Range(min = 0, max = 80000, message = "error.numRange")
    private double price;

    private String parentCategory;
    private String childCategory;
    private String grandChild;
    private String brand;

    @NotNull(message = "error.not.empty")
    private Integer condition;

    @NotBlank(message = "error.not.empty")
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
        return "InsertItemForm [name=" + name + ", price=" + price + ", parentCategory=" + parentCategory
                + ", childCategory=" + childCategory + ", grandChild=" + grandChild + ", brand=" + brand
                + ", condition=" + condition + ", description=" + description + ", image="
                + image + "]";
    }

}
