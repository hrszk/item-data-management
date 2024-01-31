package com.example.itemdatamanagement.form;

public class UpdateCategoryForm {
    private Integer id;
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

    public String getNameAll() {
        return nameAll;
    }

    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }

    @Override
    public String toString() {
        return "UpdateCategoryForm [id=" + id + ", name=" + name + ", nameAll=" + nameAll + "]";
    }

}
