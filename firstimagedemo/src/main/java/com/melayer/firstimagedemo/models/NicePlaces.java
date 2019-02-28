package com.melayer.firstimagedemo.models;

public class NicePlaces {

    private String title;
    private String imageUrl;

    @Override
    public String toString() {
        return "NicePlaces{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public NicePlaces(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}