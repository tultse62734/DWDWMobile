package com.example.dwdwproject.models;

public class ItemDashBoard  {
    private String titlle ;
    private int image;

    public ItemDashBoard(String titlle, int image) {
        this.titlle = titlle;
        this.image = image;
    }

    public String getTitlle() {
        return titlle;
    }

    public void setTitlle(String titlle) {
        this.titlle = titlle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
