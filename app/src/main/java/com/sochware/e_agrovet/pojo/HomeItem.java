package com.sochware.e_agrovet.pojo;

public class HomeItem {
    private String name, icon;
    private int image;

    public HomeItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public HomeItem(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
