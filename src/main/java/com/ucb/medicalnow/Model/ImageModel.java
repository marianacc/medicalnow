package com.ucb.medicalnow.Model;

public class ImageModel {

    private String image;

    public ImageModel() {
    }

    public ImageModel(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "image='" + image + '\'' +
                '}';
    }
}
