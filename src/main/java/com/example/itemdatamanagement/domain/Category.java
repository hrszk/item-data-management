package com.example.itemdatamanagement.domain;

public class Category {
    private Integer id;
    private Integer parent;
    private String name;
    private String nameAll;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getNameAll() {
        return nameAll;
    }

    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }

    @Override
    public String toString() {
        return "category [id=" + id + ", name=" + name + ", parentId=" + parent + ", nameAll=" + nameAll + "]";
    }

}
