package com.example.alishawky.androidtask.Entitties;

public class NavItem {

    itemsIds id;
    String title;
    int imageRes;
    boolean selected = false;

    public NavItem(itemsIds id, String title, int imageRes) {
        this.id = id;
        this.title = title;
        this.imageRes = imageRes;
    }

    public itemsIds getId() {
        return id;
    }

    public void setId(itemsIds id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public enum itemsIds {
        explore, live_chat, gallery, wish_list, e_magazine
    }

}
